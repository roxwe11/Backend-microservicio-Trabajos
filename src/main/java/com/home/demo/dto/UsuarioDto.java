package com.home.demo.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.home.demo.entity.CuentaEntity;
import com.home.demo.entity.TrabajoEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

	public class UsuarioDto {
		private Integer idUsuario;
		private String nombre;
		private String apellido;
		private String dni;
		private MultipartFile CV;
		private String direccion;
		private String telefono;
	    private LocalDate fechaNacimiento;     // Nuevo atributo de fecha

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "Cuenta_id", referencedColumnName = "idCuenta")
	    private CuentaEntity cuentaEntity;

	    @OneToMany(mappedBy = "usuarioEntity", cascade = CascadeType.ALL)
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

		public MultipartFile getCV() {
			return CV;
		}

		public void setCV(MultipartFile cV) {
			CV = cV;
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

		public UsuarioDto(Integer idUsuario, String nombre, String apellido, String dni, MultipartFile cV,
				String direccion, String telefono, LocalDate fechaNacimiento, CuentaEntity cuentaEntity,
				List<TrabajoEntity> trabajoEntities) {
			super();
			this.idUsuario = idUsuario;
			this.nombre = nombre;
			this.apellido = apellido;
			this.dni = dni;
			CV = cV;
			this.direccion = direccion;
			this.telefono = telefono;
			this.fechaNacimiento = fechaNacimiento;
			this.cuentaEntity = cuentaEntity;
			this.trabajoEntities = trabajoEntities;
		}

		public UsuarioDto() {
		}

		
	    
	
	
	
	

}
