package com.soa.dao;


import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.soa.domain.Asignacion;


public interface AsignacionDao extends JpaRepository<Asignacion, Long> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM Asignacion a INNER JOIN Persona p ON a.id_persona = p.id_persona WHERE a.fecha BETWEEN ?1 AND ?2", nativeQuery = true)
	List<Asignacion> BuscarAsignacionesEntreFechas(Date des, Date has);
	
}
