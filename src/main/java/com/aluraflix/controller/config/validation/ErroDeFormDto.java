package com.aluraflix.controller.config.validation;

public class ErroDeFormDto {
	
	private String atributo;
	
	private String erro;
	
	public ErroDeFormDto(String atributo, String erro) {
		this.atributo = atributo;
		this.erro = erro;
	}

	public String getAtributo() {
		return atributo;
	}

	public String getErro() {
		return erro;
	}

}
