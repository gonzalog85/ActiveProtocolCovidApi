package com.soa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soa.domain.Curso;


public interface CursoDao extends JpaRepository<Curso, Long> {

}
