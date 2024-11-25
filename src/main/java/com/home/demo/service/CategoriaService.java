package com.home.demo.service;

import com.home.demo.dto.CategoriaDto;
import com.home.demo.entity.CategoriaEntity;
import com.home.demo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaDto save(CategoriaDto categoriaDto) {
        CategoriaEntity categoriaEntity = convertToEntity(categoriaDto);
        CategoriaEntity savedCategoria = categoriaRepository.save(categoriaEntity);
        return convertToDto(savedCategoria);
    }

    public List<CategoriaDto> findAll() {
        return categoriaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public Optional<CategoriaEntity> findById(Integer categoriaId) {
        return categoriaRepository.findById(categoriaId); // Devolver una Optional<UsuarioEntity>
    }


    public Optional<CategoriaDto> findById(int id) {
        return categoriaRepository.findById(id).map(this::convertToDto);
    }

    public void deleteById(int id) {
        categoriaRepository.deleteById(id);
    }

    private CategoriaDto convertToDto(CategoriaEntity categoria) {
        CategoriaDto dto = new CategoriaDto();
        dto.setIdCategoria(categoria.getIdCategoria());
        dto.setNombreCategoria(categoria.getNombreCategoria());
        return dto;
    }

    private CategoriaEntity convertToEntity(CategoriaDto dto) {
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setIdCategoria(dto.getIdCategoria());
        categoria.setNombreCategoria(dto.getNombreCategoria());
        return categoria;
    }
}
