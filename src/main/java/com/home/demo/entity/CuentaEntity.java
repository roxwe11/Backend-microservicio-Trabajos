package com.home.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class CuentaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCuenta;
	private String correo;
	private String contrasena;
	

    @OneToOne(mappedBy = "cuentaEntity") // Aquí hacemos referencia al atributo en UsuarioEntity
    private UsuarioEntity usuarioEntity;


    
    //Getters and Setters 
	public Integer getIdCuenta() {
		return idCuenta;
	}


	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}


	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	
	//Constructors 

	public CuentaEntity(Integer idCuenta, String correo, String contrasena, UsuarioEntity usuarioEntity) {
		this.idCuenta = idCuenta;
		this.correo = correo;
		this.contrasena = contrasena;
		this.usuarioEntity = usuarioEntity;
	}


	public CuentaEntity() {
	}

	
	
	
	
}
