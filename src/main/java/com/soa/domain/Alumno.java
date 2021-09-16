package com.soa.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "alumno")
public class Alumno extends Persona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
