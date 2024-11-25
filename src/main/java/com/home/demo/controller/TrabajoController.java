package com.home.demo.controller;

import com.home.demo.dto.TrabajoDto;
import com.home.demo.entity.CategoriaEntity;
import com.home.demo.entity.TrabajoEntity;
import com.home.demo.entity.UsuarioEntity;
import com.home.demo.service.CategoriaService;
import com.home.demo.service.TrabajoService;
import com.home.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.sql.rowset.serial.SerialBlob;

@RestController
@RequestMapping("/api/trabajos")
public class TrabajoController {

    @Autowired
    private TrabajoService trabajoService;

    @Autowired
    private UsuarioService usuarioService; // Servicio para obtener datos de usuario

    @Autowired
    private CategoriaService categoriaService; // Servicio para obtener datos de categoría

    // Crear un nuevo trabajo con imagen
    @PostMapping("/create")
    public ResponseEntity<?> createTrabajo(
            @RequestParam("detalle") String detalle,
            @RequestParam("usuarioId") Integer usuarioId,
            @RequestParam("categoriaId") Integer categoriaId,
            @RequestParam("imagen") MultipartFile imagen) {
        try {
            // Buscar los objetos UsuarioEntity y CategoriaEntity por su ID
            Optional<UsuarioEntity> usuario = usuarioService.findById(usuarioId);
            Optional<CategoriaEntity> categoria = categoriaService.findById(categoriaId);

            if (!usuario.isPresent() || !categoria.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Si no se encuentra usuario o categoría
            }


            // Crear la entidad TrabajoEntity
            TrabajoEntity trabajoEntity = new TrabajoEntity();
            byte[]binario = imagen.getBytes();
        	Blob imageBlob = new SerialBlob(binario);
            trabajoEntity.setDetalle(detalle); // Setear el detalle del problema
            trabajoEntity.setUsuarioEntity(usuario.get()); // Asociar al usuario
            trabajoEntity.setCategoriaEntity(categoria.get()); // Asociar a la categoría
            trabajoEntity.setImagen(imageBlob);
      
            // Guardar el trabajo en la base de datos
            trabajoService.save(trabajoEntity);

            TrabajoDto trabajoDto = new TrabajoDto();
            trabajoDto.setIdTrabajo(trabajoEntity.getIdTrabajo());
            trabajoDto.setDetalle(trabajoEntity.getDetalle());
            trabajoDto.setImagen(imagen);

            return new ResponseEntity<>(trabajoDto, HttpStatus.CREATED);
        } catch (IOException | SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/listar")
    public ResponseEntity<?> listarTodosLosTrabajos() {
        try {
            // Obtener todos los trabajos
            List<TrabajoDto> trabajos = trabajoService.listarTodos();

            return new ResponseEntity<>(trabajos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

    // Método para guardar la imagen en un directorio específico para el usuario
    private String saveImage(String usuarioId, MultipartFile imagen) throws IOException {
        // Define la ruta donde se guardarán las imágenes para cada usuario
        String userDirectory = "/path/to/save/images/user_" + usuarioId + "/"; // Usar el ID del usuario
        File userFolder = new File(userDirectory);

        // Si la carpeta del usuario no existe, la creamos
        if (!userFolder.exists()) {
            userFolder.mkdirs(); // Crea el directorio si no existe
        }

        // Genera un nombre único para evitar colisiones
        String uniqueFileName = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
        File dest = new File(userDirectory + uniqueFileName);

        // Guardar la imagen en el servidor
        imagen.transferTo(dest);

        // Devuelve la ruta completa de la imagen guardada
        return dest.getAbsolutePath();
    }

    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarTrabajo(
            @PathVariable("id") Integer id,
            @RequestParam("detalle") String detalle,
            @RequestParam("categoriaId") Integer categoriaId,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen) {
        try {
            Optional<TrabajoEntity> trabajoOpt = trabajoService.findById(id);

            if (!trabajoOpt.isPresent()) {
                return new ResponseEntity<>("Trabajo no encontrado.", HttpStatus.NOT_FOUND);
            }

            Optional<CategoriaEntity> categoriaOpt = categoriaService.findById(categoriaId);

            if (!categoriaOpt.isPresent()) {
                return new ResponseEntity<>("Categoría no encontrada.", HttpStatus.BAD_REQUEST);
            }

            TrabajoEntity trabajo = trabajoOpt.get();
            trabajo.setDetalle(detalle);
            trabajo.setCategoriaEntity(categoriaOpt.get());

            if (imagen != null && !imagen.isEmpty()) {
                byte[] binario = imagen.getBytes();
                Blob imageBlob = new SerialBlob(binario);
                trabajo.setImagen(imageBlob);
            }

            trabajoService.save(trabajo);

            return new ResponseEntity<>("Trabajo actualizado exitosamente.", HttpStatus.OK);
        } catch (IOException | SQLException e) {
            return new ResponseEntity<>("Error al editar el trabajo.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTrabajo(@PathVariable("id") Integer id) {
        try {
            Optional<TrabajoEntity> trabajo = trabajoService.findById(id);

            if (!trabajo.isPresent()) {
                return new ResponseEntity<>("Trabajo no encontrado.", HttpStatus.NOT_FOUND);
            }

            trabajoService.deleteById(id); // Elimina el trabajo
            return new ResponseEntity<>("Trabajo eliminado exitosamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el trabajo.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
    
    
}
