package com.aluraflix.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.aluraflix.controller.dto.CategoriaDto;
import com.aluraflix.controller.dto.VideoDto;
import com.aluraflix.controller.form.CategoriaForm;
import com.aluraflix.model.Categoria;
import com.aluraflix.model.Video;
import com.aluraflix.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	private CategoriaRepository categoriaRepository;
	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}	
						
	public ResponseEntity<List<CategoriaDto>> buscarTodasAsCategorias(){	
		List<Categoria> lista = categoriaRepository.findAll();
		if(!lista.isEmpty()) 
			return ResponseEntity.ok(new CategoriaDto().converterListaParaCategoriaDTO(lista));
		return ResponseEntity.noContent().build();				
	}
	
	public ResponseEntity buscarCategoriaPorId(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(categoria.isEmpty())
			return new ResponseEntity<String>("categoria não existente no BD!", HttpStatus.NOT_FOUND);
		CategoriaDto categoriaBuscada = new CategoriaDto(categoria.get());
		return ResponseEntity.ok().body(categoriaBuscada);
	}

	public ResponseEntity<CategoriaDto> cadastrarCategoria(CategoriaForm categoriaForm, UriComponentsBuilder uriBuilder) {
		Categoria categoria = new Categoria(categoriaForm.getTitulo(), categoriaForm.getCor());
		categoriaRepository.save(categoria);
		CategoriaDto categoriaDto = new CategoriaDto(categoria);
				
		URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(categoriaDto);	
	}

	public ResponseEntity<CategoriaDto> atualizarCategoria(Long id, CategoriaForm categoriaForm, UriComponentsBuilder uriBuilder) {
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if(optional.isEmpty()) 
			return ResponseEntity.notFound().build();
		Categoria categoria = (Categoria)optional.get(); 
		categoria.setTitulo(categoriaForm.getTitulo());
		categoria.setCor(categoriaForm.getCor());
		categoriaRepository.save(categoria);
		CategoriaDto categoriaDto = new CategoriaDto(categoria);
		return ResponseEntity.ok(categoriaDto);
	}

	public ResponseEntity<String> deletarCategoria(Long id) {
		return categoriaRepository.findById(id)
        .map(categoria -> {
				            categoriaRepository.deleteById(id);
				            return ResponseEntity.ok().body("categoria excluída com sucesso do BD!");
        }).orElse(new ResponseEntity<String>("categoria não existe no BD!", HttpStatus.NOT_FOUND));
	}

	public ResponseEntity<List<VideoDto>> buscarVideosPorCategoria(Long id) {
		List<Video> lista = categoriaRepository.buscarVideosPorCategoria(id);
		if(lista.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok( new VideoDto().converteListaParaVideoDTO(lista));
	}
}
