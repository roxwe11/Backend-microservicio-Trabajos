package com.home.demo.entity;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TrabajoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTrabajo;
    private String detalle;
    private Blob imagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false) // idUsuario es la clave foránea
    private UsuarioEntity usuarioEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria",nullable = false)
    private CategoriaEntity categoriaEntity;

	public Integer getIdTrabajo() {
		return idTrabajo;
	}

	public void setIdTrabajo(Integer idTrabajo) {
		this.idTrabajo = idTrabajo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Blob getImagen() {
		return imagen;
	}

	public void setImagen(Blob bs) {
		this.imagen = bs;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	public CategoriaEntity getCategoriaEntity() {
		return categoriaEntity;
	}

	public void setCategoriaEntity(CategoriaEntity categoriaEntity) {
		this.categoriaEntity = categoriaEntity;
	}

	public TrabajoEntity(Integer idTrabajo, String detalle, Blob imagen, UsuarioEntity usuarioEntity,
			CategoriaEntity categoriaEntity) {
		this.idTrabajo = idTrabajo;
		this.detalle = detalle;
		this.imagen = imagen;
		this.usuarioEntity = usuarioEntity;
		this.categoriaEntity = categoriaEntity;
	}

	public TrabajoEntity() {
	}

    
    
    
    
    
}

