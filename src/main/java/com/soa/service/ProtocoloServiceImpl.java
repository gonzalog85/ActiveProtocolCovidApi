package com.soa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soa.dao.ProtocoloDao;
import com.soa.domain.Protocolo;


@Service
public class ProtocoloServiceImpl implements ProtocoloService{

	@Autowired
	ProtocoloDao protocoloDao;

	@Override
	public List<Protocolo> listarProtocolos() {
		return protocoloDao.findAll();
	}

	@Override
	public void guardar(Protocolo protocolo) {
		protocoloDao.save(protocolo);
	}

	@Override
	public void eliminar(Protocolo protocolo) {
		protocoloDao.delete(protocolo);
	}

	@Override
	public Protocolo encontrarProtocolo(Long id) {
		return protocoloDao.getById(id);
	}

}
