package com.aluraflix.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.aluraflix.dto.VideoDTO;
import com.aluraflix.models.Video;
import com.aluraflix.repository.VideoRepository;

@Service
public class VideoDTOService {
	
	private VideoRepository videoRepository;
	
	public VideoDTOService(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}
	
	public ResponseEntity<Page> buscarTodosOsVideosPorPaginacao(Integer pagina, Integer qtd){
		PageRequest paginacao = PageRequest.of(pagina, qtd);	
		Page<Video> lista = videoRepository.findAll(paginacao);
		if(!lista.isEmpty())
			return ResponseEntity.ok().body(new VideoDTO().converteParaPaginaVideoDTO(lista));
		return ResponseEntity.notFound().build();
	}
						
	public ResponseEntity<List<VideoDTO>> buscarTodosOsVideos(){	
		List<Video> lista = videoRepository.findAll();
		if(!lista.isEmpty()) 
			return ResponseEntity.ok(new VideoDTO().converteListaParaVideoDTO(lista));
		return ResponseEntity.noContent().build();				
	}
	
	public ResponseEntity buscaVideoPorId(Long id) {
		Optional<Video> video = videoRepository.findById(id);
		if(video.isEmpty())
			return ResponseEntity.notFound().build();
		VideoDTO videoBuscado = new VideoDTO(video.get().getTitulo(), video.get().getTitulo(), video.get().getUrl());
		videoBuscado.setId(id);
		return ResponseEntity.ok().body(videoBuscado);
	}

	public ResponseEntity<VideoDTO> cadastrarVideo(VideoDTO videoDto, UriComponentsBuilder uriBuilder) {
		Video video = new Video(videoDto.getTitulo(), videoDto.getDescricao(), videoDto.getUrl());
		videoRepository.save(video);
		videoDto.setId(video.getId());
		
		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
		return ResponseEntity.created(uri).body(videoDto);	
	}

	public ResponseEntity<VideoDTO> atualizarVideo(VideoDTO videoDto, UriComponentsBuilder uriBuilder) {
		Optional<Video> video = videoRepository.findById(videoDto.getId());
		if(video.isEmpty()) 
			return ResponseEntity.notFound().build();		
		video.get().setDescricao(videoDto.getDescricao());
		video.get().setTitulo(videoDto.getTitulo());
		video.get().setUrl(videoDto.getUrl());
		videoRepository.save(video.get());		
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
