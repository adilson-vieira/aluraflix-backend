package com.aluraflix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import com.aluraflix.models.Video;
import com.aluraflix.repository.VideoRepository;

@Service
public class VideoService {
	
	@Autowired
	private VideoRepository videoRepository;
	
	public ResponseEntity<List<Video>> buscarTodosOsVideos(){
		return new ResponseEntity<List<Video>>(videoRepository.findAll(), HttpStatus.OK);		
	}
	
	public ResponseEntity<Video> buscaVideoPorId(Long id) {
		try {
			  return new ResponseEntity<Video>(videoRepository.findById(id).get(), HttpStatus.OK); 
		}catch(Exception e) {
			  return new ResponseEntity<Video>(new Video(), HttpStatus.BAD_REQUEST);
		}
		
	}

	public ResponseEntity<Video> cadastrarVideo(Video video) {
		try {
				Long id = videoRepository.save(video).getId();
				return new ResponseEntity<Video>(videoRepository.findById(id).get(), HttpStatus.OK);			
		}catch (Exception e) {
			return new ResponseEntity<Video>(video, HttpStatus.BAD_REQUEST);		
		}			
	}

	public ResponseEntity<Video> atualizarVideo(Video video) {
		try {
				Video videoParaUpdate = videoRepository.findById(video.getId()).get();
				videoParaUpdate = video;
				return new ResponseEntity<Video>(videoParaUpdate, HttpStatus.OK);
		}		
		catch(Exception e) {
				return new ResponseEntity<Video>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> deletarVideo(Long id) {
		try {
				videoRepository.deleteById(id);
				return new ResponseEntity<String>("video excluído com sucesso!", HttpStatus.OK);
		    }	
		catch(Exception e) {
				return new ResponseEntity<String>("Não concluído: " + e.getMessage(), HttpStatus.BAD_REQUEST);
			 }	
		}
	}


