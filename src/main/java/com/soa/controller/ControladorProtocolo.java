package com.soa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soa.domain.Asignacion;
import com.soa.domain.Persona;
import com.soa.domain.Protocolo;
import com.soa.service.AsignacionService;
import com.soa.service.EstadoService;
import com.soa.service.MailService;
import com.soa.service.PersonaService;
import com.soa.service.ProtocoloService;

@RestController
@RequestMapping("/protocolo")
public class ControladorProtocolo {

	@Autowired
	private ProtocoloService protocoloService;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private EstadoService estadoService;
	@Autowired
	private AsignacionService asignacionService;
	@Autowired
	private MailService mailService;

	@PostMapping(value = "/iniciar/{dni}/{dias}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> iniciarProtocolo(@PathVariable Long dni, @PathVariable Integer dias)
			throws ParseException {
		Protocolo protocolo = new Protocolo();
		var persona = personaService.buscarPorDni(dni);
		if (persona == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("La persona indicada no se encuentra en la base de datos o pertenece a un protocolo activo.");
		}
		if (persona.getProtocolo() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya se encuentra con protocolo activo");
		}
		if (dias <= 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Los dias previos a considerar, debe ser mayor que 1");
		}

		protocolo.setNombre(persona.getDni().toString());

		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String hoy = df.format(d);
		String fin = df.format((d.getTime() + 15 * 24 * 60 * 60 * 1000));
		String desde = df.format((d.getTime() - dias * 24 * 60 * 60 * 1000));

		protocolo.setFechaInicio(df.parse(hoy));
		protocolo.setFechaFin(df.parse(fin));

		List<Persona> personas = new ArrayList<>();
		var estadoPositivo = estadoService.buscarPorNombre("positivo");
		var estadoAislado = estadoService.buscarPorNombre("aislado");
		var estadoSuspendido = estadoService.buscarPorNombre("suspendida");

		var asignaciones = asignacionService.BuscarAsignacionesEntreFechas(df.parse(desde), df.parse(hoy));
		var ap = persona.getAsignaciones();// asignaciones de la persona

		personas.add(persona);
		for (Asignacion a : asignaciones) {
			for (Asignacion b : ap) {
				if (a.getClase().equals(b.getClase()) && !a.getPersona().equals(persona)) {
					 if (a.getPersona().getEstado() == null) {
					personas.add(a.getPersona());
					 }
				}
			}
		}

		protocolo.setPersonas(personas);

		// Enviar mail automatico a los contactos estrechos
		// set estado aislado
		for (Persona p : personas) {
			if (persona.equals(p)) {
				p.setEstado(estadoPositivo);
			} else {
				p.setEstado(estadoAislado);
				this.mailService.sendEmail(p.getEmail(), "Contacto estrecho",
						"Se notifica que ha sido aislado por 15 dias por contacto estrecho con caso positivo por Covid");
			}
		}
		// set estado clase suspendida
		for (Asignacion a : ap) {
			a.getClase().setEstado(estadoSuspendido);
		}

		protocoloService.guardar(protocolo);

		return ResponseEntity.ok(Boolean.TRUE);
	}

	@GetMapping(value = "/listadoProtocolos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listadoProtocolos() {
		return ResponseEntity.ok(this.protocoloService.listarProtocolos());
	}

	@GetMapping(value = "/casoPositivo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> casoPositivo(@PathVariable Long id) {
		var protocolo = protocoloService.encontrarProtocolo(id);

		if (protocolo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El protocolo indicado no existe");
		}

		Persona persona = new Persona();
		var personas = protocolo.getPersonas();
		for (Persona p : personas) {
			if (p.getEstado().getNombre().equals("positivo"))
				persona = p;
		}
		return ResponseEntity.ok(persona);
	}

	@GetMapping(value = "/contactoEstrecho/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> contactoEstrechos(@PathVariable Long id) {
		var protocolo = protocoloService.encontrarProtocolo(id);

		if (protocolo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El protocolo indicado no existe");
		}

		List<Persona> contactosEstrechos = new ArrayList<>();
		var personas = protocolo.getPersonas();
		for (Persona p : personas) {
			if (p.getEstado().getNombre().equals("aislado"))
				contactosEstrechos.add(p);
		}
		return ResponseEntity.ok(contactosEstrechos);
	}

	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable Long id) {
		var protocolo = protocoloService.encontrarProtocolo(id);

		if (protocolo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El protocolo indicado no existe");
		}

		var personas = protocolo.getPersonas();
		List<Asignacion> asignaciones = new ArrayList<>();
		for (Persona p : personas) {
			p.setEstado(null);
			asignaciones = p.getAsignaciones();
		}
		for (Asignacion a : asignaciones) {
			var clase = a.getClase();
			clase.setEstado(null);
		}
		protocoloService.eliminar(protocolo);

		return ResponseEntity.ok(Boolean.TRUE);
	}

	@PutMapping(value = "/enviarMail/{dni}")
	public ResponseEntity<Object> enviarMail(@PathVariable Long dni) {
		var persona = this.personaService.buscarPorDni(dni);
		if (persona == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La persona indicada no existe");
		}
		if (persona.getEmail() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("La persona indicada no dispone de una cuenta de mail");
		}
		this.mailService.sendEmail(persona.getEmail(), "Contacto estrecho",
				"Se notifica que ha sido aislado por 15 dias por contacto estrecho con caso positivo por Covid");
		return ResponseEntity.ok("mail enviado con exito");
	}

}
