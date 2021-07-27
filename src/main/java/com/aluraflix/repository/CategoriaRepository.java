package com.aluraflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aluraflix.model.Categoria;
import com.aluraflix.model.Video;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	@Query("select v from Video v where v.categoria.id = :id")
	List<Video> buscarVideosPorCategoria(@Param("id") Long id);

}
