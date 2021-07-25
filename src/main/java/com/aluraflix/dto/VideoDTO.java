package com.aluraflix.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;

import com.aluraflix.models.Video;

public class VideoDTO {
	
	private Long Id;
	
	@NotBlank(message = "o título do vídeo deve ser informado")
	@Size(max = 255)
	private String titulo;
	
	@NotBlank(message = "a descrição do vídeo deve ser informada" )
	@Size(max = 255)
	private String descricao;
	
	@NotBlank(message = "a url do video deve ser informada")
	//@Pattern(regexp = "")
	@Size(max = 300)
	private String url;
	
	public VideoDTO() {
	
	}
	
	public VideoDTO(Video video) {
		this.Id = video.getId();
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
	}
	
	public VideoDTO(@NotBlank @Size(max = 255) String titulo, @NotBlank @Size(max = 255) String descricao,
			@NotBlank @Size(max = 300) String url) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
	}
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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
	
	public List<VideoDTO> converteListaParaVideoDTO(List<Video> lista) {
		return lista.stream().map(VideoDTO::new).collect(Collectors.toList());
	}

	public Page<VideoDTO> converteParaPaginaVideoDTO(Page<Video> lista) {
		return lista.map(VideoDTO::new);
	}
	
}
