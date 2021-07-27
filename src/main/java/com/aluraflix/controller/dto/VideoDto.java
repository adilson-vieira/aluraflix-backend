package com.aluraflix.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.aluraflix.model.Video;

public class VideoDto {
	
	private Long id;
	
	private Long categoriaId;
	
	private String titulo;
	
	private String descricao;
	
	private String url;
	
	public VideoDto() {
	
	}
	
	public VideoDto(Video video) {
		this.id = video.getId();
		this.categoriaId = video.getCategoria().getId();
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
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
	
	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public List<VideoDto> converteListaParaVideoDTO(List<Video> lista) {
		return lista.stream().map(VideoDto::new).collect(Collectors.toList());
	}

	public Page<VideoDto> converteParaPaginaVideoDTO(Page<Video> lista) {
		return lista.map(VideoDto::new);
	}
	
}
