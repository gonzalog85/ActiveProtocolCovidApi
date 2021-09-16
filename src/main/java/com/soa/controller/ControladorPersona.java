package com.soa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soa.domain.Alumno;
import com.soa.domain.Docente;
import com.soa.domain.Persona;
import com.soa.service.PersonaService;

@RestController
@RequestMapping("/personas")
public class ControladorPersona {
	
	@Autowired
	private PersonaService personaService;

	@GetMapping(value = "/listadoPersonas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listadoPersonas() {
		return ResponseEntity.ok(this.personaService.listarPersonas());
	}
	
	@GetMapping(value = "/listadoAlumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listadoAlumnos() {
		return ResponseEntity.ok(personaService.listarAlumnos());
	}
	
	@GetMapping(value = "/listadoDocentes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listadoDocentes() {
		return ResponseEntity.ok(personaService.listarDocentes());
	}
	
	//ALUMNOS
	@PostMapping(value = "/guardarAlumno", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardarAlumno(@RequestBody Alumno alumno) {
		personaService.guardar(alumno);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@PutMapping(value = "/modificarAlumno/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> modificarAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
		Alumno p = alumno;
		p.setIdPersona(id);
		this.personaService.guardar(p);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@DeleteMapping(value = "/eliminarAlumno/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> eliminarAlumno(@PathVariable Long id){
		var alumno = new Persona();
		alumno.setIdPersona(id);
		alumno = this.personaService.encontrarPersona(alumno);
		this.personaService.eliminar(alumno);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	//DOCENTES
	@PostMapping(value = "/guardarDocente", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardarDocente(@RequestBody Docente docente) {
		personaService.guardar(docente);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@PutMapping(value = "/modificarDocente/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> modificarDocente(@RequestBody Docente docente, @PathVariable Long id) {
		Docente p = docente;
		p.setIdPersona(id);
		this.personaService.guardar(p);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@DeleteMapping(value = "/eliminarDocente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> eliminarDocente(@PathVariable Long id){
		var docente = new Persona();
		docente.setIdPersona(id);
		docente = this.personaService.encontrarPersona(docente);
		this.personaService.eliminar(docente);
		return ResponseEntity.ok(Boolean.TRUE);
	}

}
