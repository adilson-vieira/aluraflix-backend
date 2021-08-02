package com.aluraflix.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluraflix.controller.dto.RelatorioVideosDto;
import com.aluraflix.service.RelatorioService;

@RestController
@RequestMapping("relatorios")
public class RelatoriosController {
	
	private RelatorioService relatorioService;
	
	public RelatoriosController(RelatorioService relatorioService) {
		this.relatorioService = relatorioService;	
	}	
	
	@GetMapping("categorias")
	public ResponseEntity<List<RelatorioVideosDto>> relatorioDeVideos(){
		return relatorioService.buscarQuantidadeDeVideosPorCategoria();
	}

}
