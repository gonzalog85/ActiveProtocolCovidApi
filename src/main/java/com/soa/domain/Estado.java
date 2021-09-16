package com.soa.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "estado")
@JsonIgnoreProperties({"personas","clases"})
public class Estado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estado")
	private Long idEstado;
	
	@NotEmpty
	private String nombre;
	
	@OneToMany(mappedBy = "estado")
	private List<Persona> personas;
	
	@OneToMany(mappedBy = "estado")
	private List<Clase> clases;
	
}
