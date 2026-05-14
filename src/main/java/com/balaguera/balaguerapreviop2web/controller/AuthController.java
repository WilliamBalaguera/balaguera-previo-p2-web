package com.balaguera.balaguerapreviop2web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Map<String, String> usuarios = new HashMap<>() {{
        put("mesero1", "mes123");
        put("mesero2", "mes456");
        put("supervisor", "sup789");
    }};

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String usuario,
                                        @RequestParam String clave,
                                        HttpServletRequest request) {
        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(clave)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuario);
            session.setMaxInactiveInterval(1800);
            return ResponseEntity.ok("Sesión iniciada como: " + usuario);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            return ResponseEntity.ok("Sesión cerrada");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No hay sesión activa");
    }
}