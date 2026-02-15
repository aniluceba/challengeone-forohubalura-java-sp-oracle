package com.alura.forohub.controller;

import com.alura.forohub.dto.TopicoDTO;
import com.alura.forohub.model.Topico;
import com.alura.forohub.dto.TopicoResponseDTO;
import com.alura.forohub.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@Tag(name = "Tópicos", description = "Operaciones relacionadas con los tópicos del foro")
public class TopicoController {

    @Autowired
    private TopicoService service;


    @Operation(summary = "Listar todos los tópicos", description = "Devuelve una lista completa de tópicos")
    @GetMapping
    public List<TopicoResponseDTO> listar() {
        return service.listar();
    }


    @Operation(summary = "Obtener un tópico por ID")
    @GetMapping("/{id}")
    public TopicoResponseDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }


    @Operation(summary = "Crear un nuevo tópico")
    @PostMapping
    public TopicoResponseDTO crear(@RequestBody @Valid TopicoDTO dto) {
        return service.crear(dto);
    }

    @Operation(summary = "Actualizar un tópico existente")
    @PutMapping("/{id}")
    public TopicoResponseDTO actualizar(@PathVariable Long id, @RequestBody @Valid TopicoDTO dto) {
        return service.actualizar(id, dto);
    }



    @Operation(summary = "Eliminar un tópico")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id, HttpServletRequest request) {
        String rol = (String) request.getAttribute("rol");
        if (!"ADMIN".equals(rol)) {
            throw new RuntimeException("No tienes permisos para eliminar tópicos");
        }
        service.eliminar(id);
    }

    //@PostMapping
    //public TopicoResponseDTO crear(@RequestBody Topico dto) {
    //    TopicoResponseDTO topico = service.crear(dto);
    //    return new TopicoResponseDTO(
    //            topico.getId(),
    //            topico.getTitulo(),
    //            topico.getMensaje(),
    //            topico.getFechaCreacion(),
    //            topico.getCurso()
    //    );
        
    //}


}
