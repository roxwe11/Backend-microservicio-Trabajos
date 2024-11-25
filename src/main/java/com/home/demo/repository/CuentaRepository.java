package com.home.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.demo.entity.CuentaEntity;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Integer>{

}
