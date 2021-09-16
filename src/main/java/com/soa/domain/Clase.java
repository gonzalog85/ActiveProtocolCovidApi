package com.soa.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "clase")
@JsonIgnoreProperties({"curso","asignaciones","estado"})
public class Clase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id_clase")
	private Long idClase;
	
	@NotEmpty
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
	private Curso curso;
	
	@ManyToOne
	@JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
	private Estado estado;
	
	@OneToMany(mappedBy = "clase")
	private List<Asignacion> asignaciones;
	
}
