package com.home.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.home.demo.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{


    @Query("SELECT u FROM UsuarioEntity u WHERE u.cuentaEntity.correo = :correo")
    Optional<UsuarioEntity> findByCorreo(@Param("correo") String correo);

	UsuarioEntity findByCuentaEntityCorreoAndCuentaEntityContrasena(String correo, String contrasena);
}
