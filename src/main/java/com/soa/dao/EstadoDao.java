package com.soa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soa.domain.Estado;


public interface EstadoDao extends JpaRepository<Estado, Long> {
	public Estado findOneByNombre(String nombre);
}
