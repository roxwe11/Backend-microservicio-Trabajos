package com.home.demo.dto;

public class LoginRequestDto {
	
	private String correo;
	private String contrasena;
	
	
	//Getters and Setters 
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
	
	
	//Constructors 
	
	public LoginRequestDto(String correo, String contrasena) {
		this.correo = correo;
		this.contrasena = contrasena;
	}
	public LoginRequestDto() {
	}
	
	

}
