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
import org.springframework.web.util.UriComponentsBuilder;

import com.aluraflix.controller.dto.CategoriaDto;
import com.aluraflix.controller.dto.VideoDto;
import com.aluraflix.controller.form.CategoriaForm;
import com.aluraflix.service.CategoriaService;

@RestController
@RequestMapping("categorias")

public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping 
	public ResponseEntity<List<CategoriaDto>> buscarTodasAsCategorias() {
		return categoriaService.buscarTodasAsCategorias();		
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<CategoriaDto> buscarPorID(@PathVariable("id") Long id) {
		return categoriaService.buscarCategoriaPorId(id);		
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDto> cadastrarCategoria(@RequestBody @Validated CategoriaForm Categoria,
													UriComponentsBuilder uriBuilder) {
		return categoriaService.cadastrarCategoria(Categoria, uriBuilder);				
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDto> atualizarCategoria(@PathVariable(required=true) Long id, 
												   @RequestBody @Validated CategoriaForm CategoriaForm,
												   UriComponentsBuilder uriBuilder) {
		 return categoriaService.atualizarCategoria(id, CategoriaForm, uriBuilder);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarCategoria(@PathVariable("id") Long id) {
		return categoriaService.deletarCategoria(id);		
	}
	
	@GetMapping("{id}/videos")
	public ResponseEntity buscarVideosPorCategoria(@PathVariable(required = true) Long id){
		 return categoriaService.buscarVideosPorCategoria(id);		
	}
	
	
}