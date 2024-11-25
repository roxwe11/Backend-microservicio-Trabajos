package com.home.demo.service;

import com.home.demo.dto.CuentaDto;
import com.home.demo.dto.UsuarioDto;
import com.home.demo.entity.UsuarioEntity;
import com.home.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDto save(UsuarioDto usuarioDto) {
        UsuarioEntity usuarioEntity = convertToEntity(usuarioDto);
        UsuarioEntity savedUsuario = usuarioRepository.save(usuarioEntity);
        return convertToDto(savedUsuario);
    }
    
    public UsuarioEntity save(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity); // Guarda la entidad en la base de datos
    }

    public Optional<UsuarioEntity> findById(Integer usuarioId) {
        return usuarioRepository.findById(usuarioId); // Devolver una Optional<UsuarioEntity>
    }
    
    public Optional<UsuarioEntity> findUsuarioByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public boolean validarCredenciales(String correo, String contrasena) {
        Optional<UsuarioEntity> usuarioOpt = findUsuarioByCorreo(correo);

        if (usuarioOpt.isPresent()) {
            UsuarioEntity usuario = usuarioOpt.get();
            return usuario.getCuentaEntity().getContrasena().equals(contrasena);
        }

        return false;
    }
    
    
    


    

    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDto> findById(int id) {
        return usuarioRepository.findById(id).map(this::convertToDto);
    }

    public void deleteById(int id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDto convertToDto(UsuarioEntity usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setDni(usuario.getDni());
       // dto.setCV(usuario.getCV());
        dto.setDireccion(usuario.getDireccion());
        dto.setTelefono(usuario.getTelefono());
        return dto;
    }

    private UsuarioEntity convertToEntity(UsuarioDto dto) {
        UsuarioEntity usuario = new UsuarioEntity();
        try {
            // 1. Convierte el archivo CV a bytes
            byte[] binario = dto.getCV().getBytes(); // Convierte el archivo CV a bytes

            // 2. Asigna los valores al objeto UsuarioEntity
            usuario.setIdUsuario(dto.getIdUsuario());
            usuario.setNombre(dto.getNombre());
            usuario.setApellido(dto.getApellido());
            usuario.setDni(dto.getDni());
            usuario.setCv(binario); // Asigna los bytes directamente
            usuario.setDireccion(dto.getDireccion());
            usuario.setTelefono(dto.getTelefono());
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
        return usuario;
    }
}
