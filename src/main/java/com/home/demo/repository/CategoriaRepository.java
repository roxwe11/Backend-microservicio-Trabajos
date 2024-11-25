package com.home.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.demo.entity.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {

}
