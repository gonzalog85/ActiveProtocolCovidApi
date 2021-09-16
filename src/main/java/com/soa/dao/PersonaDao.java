package com.soa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soa.domain.Alumno;
import com.soa.domain.Docente;
import com.soa.domain.Persona;


public interface PersonaDao<T extends Persona> extends JpaRepository<T, Long> {

//	List<Persona> findByDni(int dni);
	Persona findByDni(Long dni);
	

//	@Transactional(readOnly = true)
//	@Query(value = "SELECT * FROM Persona p INNER JOIN Asignacion a WHERE a.fecha BETWEEN ?1 AND ?2 AND a.id_persona = ?3",nativeQuery = true)
//	List<Persona> BuscarPersonasEntreFechas(Date des, Date has, Long id);
	
	@Query("from Alumno")
	List<Alumno> findAllAlumnos();
	
	@Query("from Alumno")
	List<Alumno> findByDniAlumno(Integer dni);
	
	@Query("from Docente")
	List<Docente> findAllDocentes();
	
	@Query("from Docente")
	List<Docente> findByDniDocente();
	
}


