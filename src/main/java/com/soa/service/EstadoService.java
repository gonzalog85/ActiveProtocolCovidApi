package com.soa.service;

import java.util.List;

import com.soa.domain.Estado;


public interface EstadoService {

	public List<Estado> listarEstados();
	
	public void guardar(Estado estado);
	
	public void eliminar(Estado estado);
	
	public Estado encotrarEstado(Estado estado);
	
	public Estado buscarPorNombre(String nombre);
}
