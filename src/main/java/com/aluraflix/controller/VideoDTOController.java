package com.aluraflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluraflix.dto.VideoDTO;
import com.aluraflix.service.VideoDTOService;

@RestController
@RequestMapping("videos")
public class VideoDTOController {
	
	@Autowired
	VideoDTOService videoService;
	
	@GetMapping
	public ResponseEntity<List<VideoDTO>> buscarTodosOsVideos() {
		return videoService.buscarTodosOsVideos();		
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<VideoDTO> buscarPorID(@PathVariable("id") Long id) {
		return videoService.buscaVideoPorId(id);		
	}
	
	@PostMapping
	public ResponseEntity<VideoDTO> cadastrarVideo(@RequestBody @Validated VideoDTO video) {
		return videoService.cadastrarVideo(video);				
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VideoDTO> atualizarVideo(@PathVariable(required=true) long id, 
												   @RequestBody @Validated VideoDTO video) {
		 video.setId(id);	
		 return videoService.atualizarVideo(video);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarVideo(@PathVariable("id") Long id) {
		return videoService.deletarVideo(id);		
	}

}
