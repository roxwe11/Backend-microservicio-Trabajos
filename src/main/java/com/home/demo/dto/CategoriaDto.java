package com.home.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.home.demo.entity.TrabajoEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

public class CategoriaDto {
	private Integer idCategoria;
	private String nombreCategoria;
	
	@OneToMany(mappedBy = "categoriaEntity", cascade = CascadeType.ALL) 
	@JsonIgnore
	private List<TrabajoEntity> trabajoEntities;

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public List<TrabajoEntity> getTrabajoEntities() {
		return trabajoEntities;
	}

	public void setTrabajoEntities(List<TrabajoEntity> trabajoEntities) {
		this.trabajoEntities = trabajoEntities;
	}

	public CategoriaDto(Integer idCategoria, String nombreCategoria, List<TrabajoEntity> trabajoEntities) {
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.trabajoEntities = trabajoEntities;
	}

	public CategoriaDto() {
	}

	
	
	
	
	
}
