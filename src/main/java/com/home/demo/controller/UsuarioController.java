package com.home.demo.controller;

import com.home.demo.dto.LoginRequestDto;
import com.home.demo.dto.UsuarioDto;
import com.home.demo.entity.CuentaEntity;
import com.home.demo.entity.UsuarioEntity;
import com.home.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService; // Servicio para manejar la lógica de usuario

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        String correo = loginRequest.getCorreo();
        String contrasena = loginRequest.getContrasena();

        Optional<UsuarioEntity> usuarioOpt = usuarioService.findUsuarioByCorreo(correo);

        if (usuarioOpt.isPresent()) {
            UsuarioEntity usuario = usuarioOpt.get();

            // Validar la contraseña
            if (usuario.getCuentaEntity().getContrasena().equals(contrasena)) {
                return ResponseEntity.ok(Map.of(
                    "usuarioId", usuario.getIdUsuario(),
                    "nombre", usuario.getNombre()
                ));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }



    
    
    
    // Crear un nuevo usuario
    @PostMapping("/create")
    public ResponseEntity<?> createUsuario(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("dni") String dni,
            @RequestParam("CV") MultipartFile cv,
            @RequestParam("direccion") String direccion,
            @RequestParam("telefono") String telefono,
            @RequestParam("correo") String correo,
            @RequestParam("contrasena") String contrasena,
            @RequestParam("fechaNacimiento") String fechaNacimiento // Nuevo parámetro
    ) {
        try {
            // 1. Crear una nueva cuenta
            CuentaEntity cuenta = new CuentaEntity();
            cuenta.setCorreo(correo);
            cuenta.setContrasena(contrasena);

            // 2. Convertir el archivo CV a bytes
            byte[] cvBytes = cv.getBytes();

            // 3. Crear un nuevo usuario y asociarlo a la cuenta
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setNombre(nombre);
            usuario.setApellido(apellidos);
            usuario.setDni(dni);
            usuario.setCv(cvBytes);
            usuario.setDireccion(direccion);
            usuario.setTelefono(telefono);
            usuario.setCuentaEntity(cuenta);

            // Convertir fechaNacimiento a LocalDate y asignarlo
            usuario.setFechaNacimiento(LocalDate.parse(fechaNacimiento));

            // 4. Guardar el usuario y la cuenta
            usuarioService.save(usuario);

            return new ResponseEntity<>("Usuario creado con éxito", HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Error al procesar el archivo CV", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // Manejo de excepción para parámetros faltantes
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName(); // Obtiene el nombre del parámetro faltante
        return ResponseEntity.badRequest().body("El parámetro '" + name + "' es obligatorio.");
    }

    // Obtener todos los usuarios
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDto>> getAllUsuarios() {
        List<UsuarioDto> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Buscar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(@PathVariable int id) {
        Optional<UsuarioDto> usuario = usuarioService.findById(id);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario(@PathVariable int id, @RequestBody UsuarioDto usuarioDto) {
        Optional<UsuarioDto> usuarioExistente = usuarioService.findById(id);

        if (usuarioExistente.isPresent()) {
            usuarioDto.setIdUsuario(id); // Asegurarse de que el ID no cambie
            UsuarioDto usuarioActualizado = usuarioService.save(usuarioDto);
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int id) {
        Optional<UsuarioDto> usuarioExistente = usuarioService.findById(id);

        if (usuarioExistente.isPresent()) {
            usuarioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

