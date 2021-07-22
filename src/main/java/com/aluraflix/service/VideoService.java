package com.aluraflix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import com.aluraflix.dto.VideoDTO;
import com.aluraflix.models.Video;
import com.aluraflix.repository.VideoRepository;

@Service
public class VideoService {
	
	@Autowired
	private VideoRepository videoRepository;
	
	public ResponseEntity<List<Video>> buscarTodosOsVideos(){
		return new ResponseEntity<List<Video>>(videoRepository.findAll(), HttpStatus.OK);				
	}
	
	public ResponseEntity<VideoDTO> buscaVideoPorId(Long id) {
		try {
			  Video video = videoRepository.findById(id).get();
			  VideoDTO videoBuscado = new VideoDTO(video.getTitulo(), video.getTitulo(), video.getUrl());
			  return new ResponseEntity<VideoDTO>(videoBuscado, HttpStatus.OK); 
		}catch(Exception e) {
			  return new ResponseEntity<VideoDTO>(new VideoDTO(), HttpStatus.NOT_FOUND);
		}		
	}

	public ResponseEntity<VideoDTO> cadastrarVideo(VideoDTO videoDto) {
		Video video = new Video(videoDto.getTitulo(), videoDto.getDescricao(), videoDto.getUrl());
		try {
				videoRepository.save(video);
				videoDto.setId(video.getId());
				return new ResponseEntity<VideoDTO>(videoDto, HttpStatus.OK);			
		}catch (Exception e) {
			return new ResponseEntity<VideoDTO>(videoDto, HttpStatus.BAD_REQUEST);		
		}			
	}

	public ResponseEntity<VideoDTO> atualizarVideo(VideoDTO videoDto) {
		try {
				Video video = videoRepository.findById(videoDto.getId()).get();
				video.setId(videoDto.getId());
				video.setDescricao(videoDto.getDescricao());
				video.setTitulo(videoDto.getTitulo());
				video.setUrl(videoDto.getUrl());
				videoRepository.save(video);
				return new ResponseEntity<VideoDTO>(videoDto, HttpStatus.OK);
		}
		catch(Exception e) {
				return new ResponseEntity<VideoDTO>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> deletarVideo(Long id) {
		try {
				videoRepository.deleteById(id);
				return new ResponseEntity<String>("Vídeo excluído com sucesso!", HttpStatus.OK);
		    }	
		catch(Exception e) {
				return new ResponseEntity<String>("Não concluído: " + e.getMessage(), HttpStatus.NOT_FOUND);
			 }	
	}
}
