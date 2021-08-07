package com.aluraflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aluraflix.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{
	
	List<Video> findByTituloLike(String chave);
	
	@Query(nativeQuery = true, value = "select * from videos v order by v.id desc limit :qtd")
	List<Video> listarVideosFree(@Param("qtd") Byte quantidade);
	
	@Query(nativeQuery = true, value = "select * from videos v order by rand() desc limit :qtd")
	List<Video> listarVideosAleatorios(@Param("qtd") Byte quantidade);	
	
}
