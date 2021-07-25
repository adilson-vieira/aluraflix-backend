package com.aluraflix.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.aluraflix.controller.dto.VideoDto;
import com.aluraflix.controller.form.VideoForm;
import com.aluraflix.model.Video;
import com.aluraflix.repository.VideoRepository;

@Service
public class VideoService {
	
	private VideoRepository videoRepository;
	
	public VideoService(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}
	
	public ResponseEntity<Page> buscarTodosOsVideosPorPaginacao(Integer pagina, Integer qtd){
		PageRequest paginacao = PageRequest.of(pagina, qtd);	
		Page<Video> lista = videoRepository.findAll(paginacao);
		if(!lista.isEmpty())
			return ResponseEntity.ok().body(new VideoDto().converteParaPaginaVideoDTO(lista));
		return ResponseEntity.notFound().build();
	}
						
	public ResponseEntity<List<VideoDto>> buscarTodosOsVideos(){	
		List<Video> lista = videoRepository.findAll();
		if(!lista.isEmpty()) 
			return ResponseEntity.ok(new VideoDto().converteListaParaVideoDTO(lista));
		return ResponseEntity.noContent().build();				
	}
	
	public ResponseEntity<VideoDto> buscaVideoPorId(Long id) {
		Optional<Video> video = videoRepository.findById(id);
		if(video.isEmpty())
			return ResponseEntity.notFound().build();
		VideoDto videoBuscado = new VideoDto(video.get());
		videoBuscado.setId(id);
		return ResponseEntity.ok().body(videoBuscado);
	}

	public ResponseEntity<VideoDto> cadastrarVideo(VideoForm videoForm, UriComponentsBuilder uriBuilder) {
		Video video = new Video(videoForm.getTitulo(), videoForm.getDescricao(), videoForm.getUrl());
		videoRepository.save(video);
		VideoDto videoDto = new VideoDto(video);
		videoDto.setId(video.getId());
		
		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
		return ResponseEntity.created(uri).body(videoDto);	
	}

	public ResponseEntity<VideoDto> atualizarVideo(Long id, VideoForm videoForm, UriComponentsBuilder uriBuilder) {
		Optional<Video> video = videoRepository.findById(id);
		if(video.isEmpty()) 
			return ResponseEntity.notFound().build();		
		video.get().setDescricao(videoForm.getDescricao());
		video.get().setTitulo(videoForm.getTitulo());
		video.get().setUrl(videoForm.getUrl());
		videoRepository.save(video.get());
		VideoDto videoDto = new VideoDto(video.get());
		return ResponseEntity.ok(videoDto);
	}

	public ResponseEntity<String> deletarVideo(Long id) {
		return videoRepository.findById(id)
        .map(video -> {
				            videoRepository.deleteById(id);
				            return ResponseEntity.ok().body("vídeo excluído com sucesso do BD!");
        }).orElse(new ResponseEntity<String>("vídeo não existe no BD!", HttpStatus.NOT_FOUND));
	}
}
