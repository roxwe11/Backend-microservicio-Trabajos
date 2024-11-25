package com.home.demo.service;

import com.home.demo.dto.CuentaDto;
import com.home.demo.entity.CuentaEntity;
import com.home.demo.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public CuentaDto save(CuentaDto cuentaDto) {
        CuentaEntity cuentaEntity = convertToEntity(cuentaDto);
        CuentaEntity savedCuenta = cuentaRepository.save(cuentaEntity);
        return convertToDto(savedCuenta);
    }

    public List<CuentaDto> findAll() {
        return cuentaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<CuentaDto> findById(int id) {
        return cuentaRepository.findById(id).map(this::convertToDto);
    }

    public void deleteById(int id) {
        cuentaRepository.deleteById(id);
    }

    private CuentaDto convertToDto(CuentaEntity cuenta) {
        CuentaDto dto = new CuentaDto();
        dto.setIdCuenta(cuenta.getIdCuenta());
        dto.setCorreo(cuenta.getCorreo());
        dto.setContrasena(cuenta.getContrasena());
        return dto;
    }

    private CuentaEntity convertToEntity(CuentaDto dto) {
        CuentaEntity cuenta = new CuentaEntity();
        cuenta.setIdCuenta(dto.getIdCuenta());
        cuenta.setCorreo(dto.getCorreo());
        cuenta.setContrasena(dto.getContrasena());
        return cuenta;
    }
}
