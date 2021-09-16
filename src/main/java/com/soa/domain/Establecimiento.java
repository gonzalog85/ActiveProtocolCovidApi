package com.soa.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "establecimiento")
@JsonIgnoreProperties({"cursos","protocolos"})
public class Establecimiento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_establecimiento")
	private Long idEstablecimiento;
	
	@NotEmpty
	private String nombre;
	
	@OneToMany()
	@JoinColumn(name="id_establecimiento")
	private List<Curso> cursos;
	
	@OneToMany(mappedBy = "establecimiento")
	private List<Protocolo> protocolos;

}
