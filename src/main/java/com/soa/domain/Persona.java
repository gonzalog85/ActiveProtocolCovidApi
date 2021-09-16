package com.soa.domain;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name="persona")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@JsonIgnoreProperties({"protocolo","asignaciones","estado"})	
public class Persona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_persona")
	private Long idPersona;

	@NotNull
	private Long dni;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String apellido;

	@NotEmpty
	private String telefono;

	@Email
	@NotEmpty
	private String email;
	
	@ManyToOne()
	@JoinColumn(name = "id_protocolo", referencedColumnName = "id_protocolo")
	
	private Protocolo protocolo;
	
	@ManyToOne()
	@JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
	private Estado estado;
	
	@OneToMany()
	@JoinColumn(name="id_Persona")
	private List<Asignacion> asignaciones;
}
