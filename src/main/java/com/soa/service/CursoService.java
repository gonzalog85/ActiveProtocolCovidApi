package com.soa.service;

import java.util.List;

import com.soa.domain.Curso;


public interface CursoService {

	public List<Curso> listarCursos();
	
	public void guardar(Curso curso);
	
	public void eliminar(Curso curso);
	
	public Curso encontrarCurso(Curso curso);
}
