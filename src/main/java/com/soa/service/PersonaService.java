package com.soa.service;

import java.util.List;

import com.soa.domain.Alumno;
import com.soa.domain.Docente;
import com.soa.domain.Persona;


public interface PersonaService {

	public List<Persona> listarPersonas();
	
	public List<Alumno> listarAlumnos();
	
	public List<Docente> listarDocentes();
	
	public void guardar(Persona persona);
	
	public void eliminar(Persona persona);
	
	public Persona encontrarPersona(Persona persona);
	
	public Persona buscarPorDni(Long dni);
	
	
	
//	List<Persona> BuscarPersonasEntreFechas(Date des, Date has, Long id);
	
	
	
	
}
