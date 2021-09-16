package com.soa.service;

import java.util.List;

import com.soa.domain.Protocolo;



public interface ProtocoloService {

	public List<Protocolo> listarProtocolos();
	
	public void guardar(Protocolo protocolo);
	
	public void eliminar(Protocolo protocolo);
	
	public Protocolo encontrarProtocolo(Long id);
	
	
}
