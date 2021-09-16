package com.soa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soa.dao.PersonaDao;
import com.soa.domain.Alumno;
import com.soa.domain.Docente;
import com.soa.domain.Persona;


@Service
public class PersonaServiceImpl implements PersonaService{
	
	@Autowired
	private PersonaDao<Persona> personaDao;
	
	@Autowired
	private PersonaDao<Alumno> alumnoRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Persona> listarPersonas() {
		return personaDao.findAll();
	}

	@Override
	public void guardar(Persona persona) {
		personaDao.save(persona);
		
	}

	@Override
	public void eliminar(Persona persona) {
		personaDao.delete(persona);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Persona encontrarPersona(Persona persona) {
		
		return personaDao.getById(persona.getIdPersona());
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Alumno> listarAlumnos() {
		
		return alumnoRepo.findAllAlumnos();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Docente> listarDocentes() {
		
		return personaDao.findAllDocentes();
	}

	@Override
	@Transactional(readOnly = true)
	public Persona buscarPorDni(Long dni) {
		
		return personaDao.findByDni(dni);
	}

	

//	@Override
//	public List<Persona> BuscarPersonasEntreFechas(Date des, Date has, Long id) {
//		return personaDao.BuscarPersonasEntreFechas(des, has, id);
//	}



	

	

	

}
