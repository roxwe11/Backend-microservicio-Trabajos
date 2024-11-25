package com.home.demo.service;

import com.home.demo.dto.TrabajoDto;
import com.home.demo.entity.TrabajoEntity;
import com.home.demo.repository.TrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;

@Service
public class TrabajoService {

    @Autowired
    private TrabajoRepository trabajoRepository;

    public TrabajoDto save(TrabajoDto trabajoDto) {
        TrabajoEntity trabajoEntity = convertToEntity(trabajoDto);
        TrabajoEntity savedTrabajo = trabajoRepository.save(trabajoEntity);
        return convertToDto(savedTrabajo);
    }

   
    public TrabajoEntity save(TrabajoEntity trabajoEntity) {
        return trabajoRepository.save(trabajoEntity);
    }

  
    public void deleteById(int id) {
        trabajoRepository.deleteById(id);
    }

    private TrabajoDto convertToDto(TrabajoEntity trabajo) {
        TrabajoDto dto = new TrabajoDto();
        try {
        	dto.setIdTrabajo(trabajo.getIdTrabajo());
            dto.setDetalle(trabajo.getDetalle());
         //   dto.setImagen(trabajo.getImagen().getBytes(1,(int)trabajo.getImagen().length())); // Incluye la imagen en Base64
            
		} catch (Exception e) {

		}
  
        return dto;
    }

    private TrabajoEntity convertToEntity(TrabajoDto dto) {
        TrabajoEntity trabajo = new TrabajoEntity();
        try {
        	byte[]binario = dto.getImagen().getBytes();
        	Blob imageBlob = new SerialBlob(binario);
        	trabajo.setIdTrabajo(dto.getIdTrabajo());
            trabajo.setDetalle(dto.getDetalle());
            trabajo.setImagen(imageBlob); // Incluye la imagen en Base64
        } catch (Exception e) {
			e.printStackTrace();
		}
        return trabajo;

        
    }
 // Obtener todos los trabajos como TrabajoEntity
    public List<TrabajoEntity> findAll() {
        return trabajoRepository.findAll();  // Esto devuelve una lista de TrabajoEntity
    }
    
    
    
    // Obtener un trabajo por ID
    public Optional<TrabajoEntity> findById(int id) {
        return trabajoRepository.findById(id);
    }
    
    
    
    public List<TrabajoDto> listarTodos() {
        List<TrabajoEntity> trabajos = trabajoRepository.findAllWithDetails();

        return trabajos.stream().map(trabajo -> {
            TrabajoDto dto = new TrabajoDto();
            dto.setIdTrabajo(trabajo.getIdTrabajo());
            dto.setDetalle(trabajo.getDetalle());
            dto.setUsuarioNombre(trabajo.getUsuarioEntity().getNombre());
            dto.setCategoriaNombre(trabajo.getCategoriaEntity().getNombreCategoria());
            return dto;
        }).collect(Collectors.toList());
    }


    

   
    
}
