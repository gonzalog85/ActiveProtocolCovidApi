package com.soa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soa.dao.EstablecimientoDao;
import com.soa.domain.Establecimiento;


@Service
public class EstablecimientoServiceImpl implements EstablecimientoService {

	@Autowired
	EstablecimientoDao establecimientoDao;

	@Override
	public List<Establecimiento> listarEstablecimientos() {
		return establecimientoDao.findAll();
	}

	@Override
	public void guardar(Establecimiento establecimiento) {
		establecimientoDao.save(establecimiento);
	}

	@Override
	public void eliminar(Establecimiento establecimiento) {
		establecimientoDao.delete(establecimiento);
	}

	@Override
	public Establecimiento encontrarEstablecimiento(Establecimiento establecimiento) {
		return establecimientoDao.getById(establecimiento.getIdEstablecimiento());
	}
}
