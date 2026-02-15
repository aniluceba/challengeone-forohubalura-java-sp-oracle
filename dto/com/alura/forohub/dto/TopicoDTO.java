package com.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicoDTO(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotBlank String curso
) {}
