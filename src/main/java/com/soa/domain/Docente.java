package com.soa.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "docente")
public class Docente extends Persona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Docente() {
		super();
		// TODO Auto-generated constructor stub
	}

}
