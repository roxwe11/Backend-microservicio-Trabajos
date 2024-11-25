package com.home.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CategoriaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoria;
	private String nombreCategoria;
	
	
	@OneToMany(mappedBy = "categoriaEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
	@JsonIgnore
	private List<TrabajoEntity> trabajoEntities;

	//Getters and Setters

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


	//Constructors 
	public CategoriaEntity(Integer idCategoria, String nombreCategoria, List<TrabajoEntity> trabajoEntities) {
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.trabajoEntities = trabajoEntities;
	}


	public CategoriaEntity() {
	}
	
	
	
	
}
