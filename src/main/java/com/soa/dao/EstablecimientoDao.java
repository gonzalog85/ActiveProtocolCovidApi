package com.soa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soa.domain.Establecimiento;


public interface EstablecimientoDao extends JpaRepository<Establecimiento, Long> {

}
