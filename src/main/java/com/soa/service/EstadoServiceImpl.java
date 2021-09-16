package com.soa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soa.dao.EstadoDao;
import com.soa.domain.Estado;


@Service
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	EstadoDao estadoDao;
	
	@Override
	public List<Estado> listarEstados() {
		return estadoDao.findAll();
	}

	@Override
	public void guardar(Estado estado) {
		estadoDao.save(estado);
	}

	@Override
	public void eliminar(Estado estado) {
		estadoDao.delete(estado);
	}

	@Override
	public Estado encotrarEstado(Estado estado) {
		return estadoDao.getById(estado.getIdEstado());
	}

	@Override
	public Estado buscarPorNombre(String nombre) {
		return estadoDao.findOneByNombre(nombre);
	}

}
