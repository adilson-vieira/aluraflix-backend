package com.aluraflix.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class VideoForm {

	@NotBlank(message = "o título do vídeo deve ser informado")
	@Size(max = 255)
	private String titulo;
	
	@NotBlank(message = "a descrição do vídeo deve ser informada")
	@Size(max = 255)
	private String descricao;
	
	@NotBlank(message = "a url do video deve ser informada")
	//@Pattern(regexp = "")
	@Size(max = 300)
	private String url;
	
	public VideoForm(String titulo, String descricao, String url) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
