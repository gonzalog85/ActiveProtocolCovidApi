package com.soa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "protocolo")
@JsonIgnoreProperties({"establecimiento","personas"})
public class Protocolo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_protocolo")
	private Long idProtocolo;
	
	@NotEmpty
	private String nombre;
	
	
	@Column(name = "fecha_inicio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaInicio;
	
	
	@Column(name = "fecha_fin")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date fechaFin;
	
	@ManyToOne()
	@JoinColumn(name = "id_establecimiento", referencedColumnName = "id_establecimiento")
	private Establecimiento establecimiento;
	
	@OneToMany()
	@JoinColumn(name="id_protocolo")
	private List<Persona> personas;
	
}
