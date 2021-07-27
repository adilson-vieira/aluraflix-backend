package com.aluraflix.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.aluraflix.model.Categoria;

public class CategoriaDto {
	
	private Long id;
	
	private String titulo;
	
	private String cor;
	
	
	public CategoriaDto() {
	
	}
	
	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.titulo = categoria.getTitulo();
		this.cor = categoria.getCor();		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public List<CategoriaDto> converterListaParaCategoriaDTO(List<Categoria> lista) {
		return lista.stream().map(CategoriaDto::new).collect(Collectors.toList());		
	}
}