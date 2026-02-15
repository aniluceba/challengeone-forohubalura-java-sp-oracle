package com.alura.forohub.controller;

import com.alura.forohub.model.Usuario;
import com.alura.forohub.repository.UsuarioRepository;
import com.alura.forohub.service.TopicoService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoService service;

    private final String SECRET_KEY = "miClaveSecreta123";

    // Endpoint de login: genera un JWT
    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        Usuario user = usuarioRepository.findByEmail(usuario.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getPassword().equals(usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("rol", user.getRol().name()) // añadimos el rol al token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
    }

    // Endpoint para eliminar tópicos (solo ADMIN)
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token no proporcionado");
        }
    }
}

