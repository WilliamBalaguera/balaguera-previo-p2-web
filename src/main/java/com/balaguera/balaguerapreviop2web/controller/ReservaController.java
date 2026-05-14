package com.balaguera.balaguerapreviop2web.controller;

import com.balaguera.balaguerapreviop2web.model.Reserva;
import com.balaguera.balaguerapreviop2web.service.ReservaService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    private boolean verificarSesion(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("usuario") != null;
    }

    @GetMapping
    public ResponseEntity<?> getAll(HttpServletRequest req) {
        if (!verificarSesion(req)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Acceso denegado: inicie sesión");
        }

        List<Reserva> lista = reservaService.findAll();

        // Leer cookie ultimaMesa
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("ultimaMesa")) {
                    return ResponseEntity.ok()
                            .header("X-Ultima-Mesa", c.getValue())
                            .body(lista);
                }
            }
        }

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Reserva r,
                                   HttpServletRequest req,
                                   HttpServletResponse res) {
        if (!verificarSesion(req)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Acceso denegado: inicie sesión");
        }

        Map<String, String> errores = reservaService.guardar(r);

        if (!errores.isEmpty()) {
            return ResponseEntity.badRequest().body(errores);
        }

        // Crear cookie ultimaMesa
        Cookie cookie = new Cookie("ultimaMesa", String.valueOf(r.getMesaId()));
        cookie.setMaxAge(7 * 24 * 3600);
        cookie.setHttpOnly(true);
        res.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id, HttpServletRequest req) {
        if (!verificarSesion(req)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Acceso denegado: inicie sesión");
        }

        Reserva r = reservaService.findById(id);
        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada");
        }
        return ResponseEntity.ok(r);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id, HttpServletRequest req) {
        if (!verificarSesion(req)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Acceso denegado: inicie sesión");
        }

        Reserva r = reservaService.findById(id);
        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada");
        }

        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}