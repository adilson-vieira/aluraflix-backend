package com.aluraflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aluraflix.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String >{

	Usuario findByNome(String nome);
	
}