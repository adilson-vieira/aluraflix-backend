package com.aluraflix.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoriaForm {

	@NotBlank(message = "o título da categoria deve ser informado")
	@Size(max = 30)
	private String titulo;
	
	@NotBlank(message = "a cor da categoria deve ser informada")
	@Size(max = 7)
	private String cor;
		
	public CategoriaForm(String titulo, String cor) {
		this.titulo = titulo;
		this.cor = cor;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCor() {
		return cor;
	}	
}
