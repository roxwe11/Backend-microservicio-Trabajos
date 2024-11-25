package com.home.demo.controller;

import com.home.demo.dto.CategoriaDto;
import com.home.demo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Crear una nueva categoría
    @PostMapping("/create")
    public ResponseEntity<CategoriaDto> createCategoria(@RequestBody CategoriaDto categoriaDto) {
        CategoriaDto nuevaCategoria = categoriaService.save(categoriaDto);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    // Obtener todas las categorías
    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaDto>> getAllCategorias() {
        List<CategoriaDto> categorias = categoriaService.findAll();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    // Buscar categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable int id) {
        Optional<CategoriaDto> categoria = categoriaService.findById(id);
        return categoria.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar una categoría
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> updateCategoria(@PathVariable int id, @RequestBody CategoriaDto categoriaDto) {
        Optional<CategoriaDto> categoriaExistente = categoriaService.findById(id);

        if (categoriaExistente.isPresent()) {
            categoriaDto.setIdCategoria(id); // Asegurarse de mantener el mismo ID
            CategoriaDto categoriaActualizada = categoriaService.save(categoriaDto);
            return new ResponseEntity<>(categoriaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una categoría por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable int id) {
        Optional<CategoriaDto> categoriaExistente = categoriaService.findById(id);

        if (categoriaExistente.isPresent()) {
            categoriaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
