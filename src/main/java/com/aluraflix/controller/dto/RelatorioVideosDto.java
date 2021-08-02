package com.aluraflix.controller.dto;

public class RelatorioVideosDto {
	
	private String categoria;
	
	private Long quantidade;
	
		
	public RelatorioVideosDto() {
		
	}

	public RelatorioVideosDto(String categoria, Long quantidade) {
		this.categoria = categoria;
		this.quantidade = quantidade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
}
