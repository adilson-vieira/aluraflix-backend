package com.aluraflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aluraflix.controller.dto.VideoDto;
import com.aluraflix.controller.form.VideoForm;
import com.aluraflix.service.VideoService;

@RestController
@RequestMapping("videos")
public class VideoController {
	
	@Autowired
	VideoService videoService;
	
	@GetMapping("pagina") /* busca por paginacao */
	public ResponseEntity<Page> buscarTodosOsVideos(@RequestParam(required = true) Integer pagina,
													@RequestParam(required = true) Integer qtd) {
		return videoService.buscarTodosOsVideosPorPaginacao(pagina, qtd);			
	}
	
	@GetMapping /* busca sem paginacao */
	public ResponseEntity<List<VideoDto>> buscarTodosOsVideos() {
		return videoService.buscarTodosOsVideos();		
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<VideoDto> buscarPorID(@PathVariable("id") Long id) {
		return videoService.buscaVideoPorId(id);		
	}
	
	@PostMapping
	public ResponseEntity<VideoDto> cadastrarVideo(@RequestBody @Validated VideoForm video,
													UriComponentsBuilder uriBuilder) {
		return videoService.cadastrarVideo(video, uriBuilder);				
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VideoDto> atualizarVideo(@PathVariable(required=true) Long id, 
												   @RequestBody @Validated VideoForm videoForm,
												   UriComponentsBuilder uriBuilder) {
		 return videoService.atualizarVideo(id, videoForm, uriBuilder);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarVideo(@PathVariable("id") Long id) {
		return videoService.deletarVideo(id);		
	}
}
