package com.alura.forohub.exception;

public class TopicoNotFoundException extends RuntimeException {
    public TopicoNotFoundException(Long id) {
        super("Tópico con id " + id + " no encontrado");
    }
}
