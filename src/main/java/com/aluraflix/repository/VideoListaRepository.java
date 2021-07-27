package com.aluraflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aluraflix.model.VideoLista;

public interface VideoListaRepository extends JpaRepository<VideoLista, Long> {

}
