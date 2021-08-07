package com.aluraflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.aluraflix.controller.dto.CategoriaDto;
import com.aluraflix.controller.dto.VideoDto;
import com.aluraflix.controller.form.CategoriaForm;
import com.aluraflix.service.CategoriaService;

@RestController
@RequestMapping("categorias")
@CrossOrigin
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<CategoriaDto>> buscarTodasAsCategorias(
			@RequestParam(required = true, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "5") Integer qtd) {
		return categoriaService.buscarTodasAsCategorias(page, qtd);
	}

	@RequestMapping("/{id}")
	public ResponseEntity<?> buscarPorID(@PathVariable("id") Long id) {
		return categoriaService.buscarCategoriaPorId(id);
	}

	@PostMapping
	public ResponseEntity<CategoriaDto> cadastrarCategoria(@RequestBody @Validated CategoriaForm categoria,
			UriComponentsBuilder uriBuilder) {
		return categoriaService.cadastrarCategoria(categoria, uriBuilder);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDto> atualizarCategoria(@PathVariable(required = true) Long id,
			@RequestBody @Validated CategoriaForm CategoriaForm) {
		return categoriaService.atualizarCategoria(id, CategoriaForm);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarCategoria(@PathVariable("id") Long id) {
		return categoriaService.deletarCategoria(id);
	}

	@GetMapping("{id}/videos")
	public ResponseEntity<List<VideoDto>> buscarVideosPorCategoria(@PathVariable(required = true) Long id) {
		return categoriaService.buscarVideosPorCategoria(id);
	}
}