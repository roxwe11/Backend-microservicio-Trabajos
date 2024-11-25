package com.home.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.home.demo.entity.TrabajoEntity;

public interface TrabajoRepository extends JpaRepository<TrabajoEntity, Integer>{

	@Query("SELECT t FROM TrabajoEntity t JOIN FETCH t.usuarioEntity JOIN FETCH t.categoriaEntity")
	List<TrabajoEntity> findAllWithDetails();
}
