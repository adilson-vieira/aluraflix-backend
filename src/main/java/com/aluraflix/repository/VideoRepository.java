package com.aluraflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aluraflix.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{
	
	List<Video> findByTituloLike(String chave);

}
