package com.aluraflix.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aluraflix.controller.dto.RelatorioVideosDto;
import com.aluraflix.repository.RelatorioVideosRespository;

@Service
public class RelatorioService {
	
	private RelatorioVideosRespository relatorioVideosRepository;
	
	public RelatorioService(RelatorioVideosRespository relatorioVideosRespository) {
		this.relatorioVideosRepository = relatorioVideosRespository;
	}
	
	public ResponseEntity<List<RelatorioVideosDto>> buscarQuantidadeDeVideosPorCategoria(){
		List<RelatorioVideosDto> listaVideos = relatorioVideosRepository.buscarQuantidadeDeVideosPorCategoria();
		if(listaVideos.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok().body(listaVideos);
	}

}
