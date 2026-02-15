package com.alura.forohub.service;

import com.alura.forohub.dto.TopicoDTO;
import com.alura.forohub.dto.TopicoResponseDTO;
import com.alura.forohub.exception.TopicoNotFoundException;
import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    public List<TopicoResponseDTO> listar() {
        return repository.findAll().stream()
                .map(topico -> new TopicoResponseDTO(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getCurso()
                ))
                .toList();
    }

    public TopicoResponseDTO obtener(Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new TopicoNotFoundException(id));
        return new TopicoResponseDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getCurso()
        );
    }

    public TopicoResponseDTO crear(TopicoDTO dto) {
        Topico topico = new Topico();
        topico.setTitulo(dto.titulo());
        topico.setMensaje(dto.mensaje());
        topico.setCurso(dto.curso());
        repository.save(topico);

        return new TopicoResponseDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getCurso()
        );
    }

    public TopicoResponseDTO actualizar(Long id, TopicoDTO dto) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new TopicoNotFoundException(id));
        topico.setTitulo(dto.titulo());
        topico.setMensaje(dto.mensaje());
        topico.setCurso(dto.curso());
        repository.save(topico);

        return new TopicoResponseDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getCurso()
        );
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new TopicoNotFoundException(id);

        }

    }

}