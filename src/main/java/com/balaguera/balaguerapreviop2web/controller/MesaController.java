package com.balaguera.balaguerapreviop2web.controller;

import com.balaguera.balaguerapreviop2web.dao.MesaDAO;
import com.balaguera.balaguerapreviop2web.model.Mesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaDAO mesaDAO;

    @GetMapping
    public ResponseEntity<List<Mesa>> getAll() {
        return ResponseEntity.ok(mesaDAO.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Mesa m = mesaDAO.findById(id);
        if (m == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mesa no encontrada");
        }
        return ResponseEntity.ok(m);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Mesa m) {
        if (m.getNumero() <= 0 || m.getCapacidad() <= 0 ||
                m.getZona() == null || m.getZona().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Error: numero y capacidad deben ser mayores a 0, y zona no puede estar vacía");
        }
        mesaDAO.save(m);
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Mesa m) {
        Mesa existente = mesaDAO.findById(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mesa no encontrada");
        }
        m.setId(id);
        mesaDAO.update(m);
        return ResponseEntity.ok(m);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Mesa existente = mesaDAO.findById(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mesa no encontrada");
        }
        mesaDAO.delete(id);
        return ResponseEntity.noContent().build();
    }
}