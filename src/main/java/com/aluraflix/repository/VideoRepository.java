package com.aluraflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aluraflix.models.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{
	

}
