package com.soa.service;

import java.util.Date;
import java.util.List;

import com.soa.domain.Asignacion;


public interface AsignacionService {

	public List<Asignacion> listarAsignaciones();
	
	public void guardar(Asignacion asignacion);
	
	public void eliminar(Asignacion asignacion);
	
	public Asignacion encontrarAsignacion(Asignacion asignacion);
	
	public List<Asignacion> BuscarAsignacionesEntreFechas(Date des, Date has);
	
}
