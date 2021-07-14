package com.tutorial.crud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class configuracion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String serviceName;
	private String enpointAlpha;
	
	public configuracion() {
		super();
	}

	public configuracion(int id, String serviceName, String enpointAlpha) {
		super();
		this.id = id;
		this.serviceName = serviceName;
		this.enpointAlpha = enpointAlpha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServicenNme() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getEnpointAlpha() {
		return enpointAlpha;
	}

	public void setEnpointAlpha(String enpointAlpha) {
		this.enpointAlpha = enpointAlpha;
	}
}
