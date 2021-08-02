package com.aluraflix.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VideoForm {

	@NotNull @Size(max = 255)
	@NotBlank(message = "o título do vídeo deve ser informado")
	private String titulo;
	
	@NotNull @Size(max = 255)
	@NotBlank(message = "a descrição do vídeo deve ser informada")
	private String descricao;
    
	@NotNull @Size(max = 300)
	@NotBlank(message = "a url do video deve ser informada")
	//@Pattern(regexp = "")
	private String url;
	
	private Long categoriaId;
	
	public VideoForm(String titulo, String descricao, String url, Long categoriaId) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
		if(categoriaId == null)
			this.categoriaId = 1L;
		else this.categoriaId = categoriaId;
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

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	
	public Optional<Long> getCategoriaId() {
	    return Optional.ofNullable(categoriaId);	    	   
	}
	
}
