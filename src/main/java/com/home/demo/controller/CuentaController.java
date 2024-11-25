package com.home.demo.controller;

import com.home.demo.dto.CuentaDto;
import com.home.demo.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    // Crear una nueva cuenta
    @PostMapping("/create")
    public ResponseEntity<CuentaDto> createCuenta(@RequestBody CuentaDto cuentaDto) {
        CuentaDto nuevaCuenta = cuentaService.save(cuentaDto);
        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
    }

    // Obtener todas las cuentas
    @GetMapping("/listar")
    public ResponseEntity<List<CuentaDto>> getAllCuentas() {
        try {
            System.out.println("Intentando listar todas las cuentas");
            List<CuentaDto> cuentas = cuentaService.findAll();
            return new ResponseEntity<>(cuentas, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error al listar cuentas: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar cuenta por ID
    @GetMapping("/{id}")
    public ResponseEntity<CuentaDto> getCuentaById(@PathVariable int id) {
        Optional<CuentaDto> cuenta = cuentaService.findById(id);
        return cuenta.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar cuenta
    @PutMapping("/{id}")
    public ResponseEntity<CuentaDto> updateCuenta(@PathVariable int id, @RequestBody CuentaDto cuentaDto) {
        Optional<CuentaDto> cuentaExistente = cuentaService.findById(id);

        if (cuentaExistente.isPresent()) {
            cuentaDto.setIdCuenta(id); // Asegurarse de que el ID no cambie
            CuentaDto cuentaActualizada = cuentaService.save(cuentaDto);
            return new ResponseEntity<>(cuentaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar cuenta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable int id) {
        Optional<CuentaDto> cuentaExistente = cuentaService.findById(id);

        if (cuentaExistente.isPresent()) {
            cuentaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
