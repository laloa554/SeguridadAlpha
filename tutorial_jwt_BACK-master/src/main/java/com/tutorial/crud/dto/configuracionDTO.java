package com.tutorial.crud.dto;

import javax.validation.constraints.NotBlank;

public class configuracionDTO {
	@NotBlank
	private String serviceName;
	@NotBlank
	private String enpointAlpha;
	
	public configuracionDTO() {
		super();
	}

	public configuracionDTO(@NotBlank String serviceName, @NotBlank String enpointAlpha) {
		super();
		this.serviceName = serviceName;
		this.enpointAlpha = enpointAlpha;
	}
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getEnpoint_alpha() {
		return enpointAlpha;
	}

	public void setEnpointAlpha(String enpointAlpha) {
		this.enpointAlpha = enpointAlpha;
	}
}
