package com.home.demo.entity;

import java.time.LocalDate;
import java.util.List; // Cambio importante
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;     // Nuevo atributo de fecha
    
    @Lob
    private byte[] cv;    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Cuenta_id", referencedColumnName = "idCuenta")
    private CuentaEntity cuentaEntity;

    @OneToMany(mappedBy = "usuarioEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TrabajoEntity> trabajoEntities;

    
    //Getters and Setters 
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public byte[] getCv() {
		return cv;
	}

	public void setCv(byte[] cv) {
		this.cv = cv;
	}

	public CuentaEntity getCuentaEntity() {
		return cuentaEntity;
	}

	public void setCuentaEntity(CuentaEntity cuentaEntity) {
		this.cuentaEntity = cuentaEntity;
	}

	public List<TrabajoEntity> getTrabajoEntities() {
		return trabajoEntities;
	}

	public void setTrabajoEntities(List<TrabajoEntity> trabajoEntities) {
		this.trabajoEntities = trabajoEntities;
	}

	
	//Constructors 
	public UsuarioEntity(Integer idUsuario, String nombre, String apellido, String dni, String direccion,
			String telefono, LocalDate fechaNacimiento, byte[] cv, CuentaEntity cuentaEntity,
			List<TrabajoEntity> trabajoEntities) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.cv = cv;
		this.cuentaEntity = cuentaEntity;
		this.trabajoEntities = trabajoEntities;
	}

	public UsuarioEntity() {
	}

    
    
    
	
	
	

    
    

}
