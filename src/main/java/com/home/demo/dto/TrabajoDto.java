package com.home.demo.dto;


import org.springframework.web.multipart.MultipartFile;

import com.home.demo.entity.CategoriaEntity;
import com.home.demo.entity.UsuarioEntity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class TrabajoDto {
	private Integer idTrabajo;
	private String detalle;
	private MultipartFile imagen;
	private String usuarioNombre; // Este campo mapea el nombre del usuario
    private String categoriaNombre; // Este campo mapea el nombre de la categoría

	
	 @ManyToOne
	    @JoinColumn(name = "idUsuario", nullable = false) // idUsuario es la clave foránea
	    private UsuarioEntity usuarioEntity;

	    @ManyToOne
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

		public MultipartFile getImagen() {
			return imagen;
		}

		public void setImagen(MultipartFile imagen) {
			this.imagen = imagen;
		}

		public String getUsuarioNombre() {
			return usuarioNombre;
		}

		public void setUsuarioNombre(String usuarioNombre) {
			this.usuarioNombre = usuarioNombre;
		}

		public String getCategoriaNombre() {
			return categoriaNombre;
		}

		public void setCategoriaNombre(String categoriaNombre) {
			this.categoriaNombre = categoriaNombre;
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

		public TrabajoDto(Integer idTrabajo, String detalle, MultipartFile imagen, String usuarioNombre,
				String categoriaNombre, UsuarioEntity usuarioEntity, CategoriaEntity categoriaEntity) {
			this.idTrabajo = idTrabajo;
			this.detalle = detalle;
			this.imagen = imagen;
			this.usuarioNombre = usuarioNombre;
			this.categoriaNombre = categoriaNombre;
			this.usuarioEntity = usuarioEntity;
			this.categoriaEntity = categoriaEntity;
		}

		public TrabajoDto() {
		}

	    
	    
	}
	
	

	
	
	
	

