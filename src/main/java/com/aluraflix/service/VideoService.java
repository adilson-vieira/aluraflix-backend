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
import com.aluraflix.model.Categoria;
import com.aluraflix.model.Video;
import com.aluraflix.repository.CategoriaRepository;
import com.aluraflix.repository.VideoRepository;

@Service
public class VideoService {

	private VideoRepository videoRepository;
	private CategoriaRepository categoriaRepository;

	public VideoService(VideoRepository videoRepository, CategoriaRepository categoriaRepository) {
		this.videoRepository = videoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	public ResponseEntity<Page> buscarTodosOsVideosPorPaginacao(Integer pagina, Integer qtd) {
		PageRequest paginacao = PageRequest.of(pagina, qtd);
		Page<Video> lista = videoRepository.findAll(paginacao);
		if (!lista.isEmpty())
			return ResponseEntity.ok().body(new VideoDto().converteParaPaginaVideoDTO(lista));
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<VideoDto> buscaVideoPorId(Long id) {
		Optional<Video> video = videoRepository.findById(id);
		if (video.isEmpty())
			return ResponseEntity.notFound().build();
		VideoDto videoBuscado = new VideoDto(video.get());
		return ResponseEntity.ok().body(videoBuscado);
	}

	public ResponseEntity<?> cadastrarVideo(VideoForm videoForm, UriComponentsBuilder uriBuilder) {
		Long id = (Long) videoForm.getCategoriaId().get();
		Optional optional = categoriaRepository.findById(id);
		if (optional.isPresent()) {
			Categoria categoria = (Categoria) optional.get();
			Video video = new Video(videoForm.getTitulo(), videoForm.getDescricao(), videoForm.getUrl(), categoria);
			videoRepository.save(video);
			VideoDto videoDto = new VideoDto(video);
			URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
			return ResponseEntity.created(uri).body(videoDto);
		}
		return new ResponseEntity<String>("categoria informada n??o existe no BD!", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<?> atualizarVideo(Long id, VideoForm videoForm) {
		Optional<Video> optVideo = videoRepository.findById(id);
		if (optVideo.isEmpty())
			return ResponseEntity.notFound().build();
		Video video = (Video) optVideo.get();
		Optional<Categoria> optCategoria = categoriaRepository.findById(videoForm.getCategoriaId().get());
		if (optCategoria.isEmpty())
			return ResponseEntity.badRequest().body(videoForm);
		Categoria categoria = (Categoria) optCategoria.get();
		video.setDescricao(videoForm.getDescricao());
		video.setTitulo(videoForm.getTitulo());
		video.setUrl(videoForm.getUrl());
		video.setCategoria(categoria);
		videoRepository.save(video);
		VideoDto videoDto = new VideoDto(video);
		return ResponseEntity.ok(videoDto);
	}

	public ResponseEntity<String> deletarVideo(Long id) {
		return videoRepository.findById(id).map(video -> {
			videoRepository.deleteById(id);
			return ResponseEntity.ok().body("v??deo exclu??do com sucesso do BD!");
		}).orElse(new ResponseEntity<String>("v??deo n??o existe no BD!", HttpStatus.NOT_FOUND));
	}

	public ResponseEntity<List<VideoDto>> buscarVideoPorTitulo(String chave) {
		if (!chave.isEmpty()) {
			chave = '%' + chave + '%';
			System.out.println(chave);
			List<Video> lista = videoRepository.findByTituloLike(chave);
			if (lista.isEmpty())
				return ResponseEntity.notFound().build();
			return ResponseEntity.ok(new VideoDto().converteListaParaVideoDTO(lista));
		}
		return ResponseEntity.badRequest().build();
	}
	
	public ResponseEntity<List<VideoDto>> listarVideosFree() {
		final Byte quantidade = 5;
		List<Video> lista = videoRepository.listarVideosFree(quantidade);
		if(lista.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok().body(new VideoDto().converteListaParaVideoDTO(lista));
	}
}
