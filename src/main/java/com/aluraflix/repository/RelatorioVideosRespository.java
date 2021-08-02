package com.aluraflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aluraflix.controller.dto.RelatorioVideosDto;
import com.aluraflix.model.Video;

@Repository
public interface RelatorioVideosRespository extends JpaRepository<Video, Long> {
	
	@Query("select new com.aluraflix.controller.dto.RelatorioVideosDto(c.titulo, count(v.categoria.titulo))"
			+ "from Video v Join v.categoria c group by c.titulo")
	List<RelatorioVideosDto> buscarQuantidadeDeVideosPorCategoria();

}
