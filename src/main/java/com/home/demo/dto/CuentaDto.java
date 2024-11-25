package com.home.demo.dto;

public class CuentaDto {
    private Integer idCuenta;
    private String correo;
    private String contrasena;

    // Getters y Setters
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

    
    
    
	public CuentaDto(Integer idCuenta, String correo, String contrasena) {
		this.idCuenta = idCuenta;
		this.correo = correo;
		this.contrasena = contrasena;
	}
	

	public CuentaDto() {
	}
    
    
}

