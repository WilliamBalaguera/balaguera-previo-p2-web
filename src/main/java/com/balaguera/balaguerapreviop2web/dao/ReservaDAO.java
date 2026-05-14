package com.balaguera.balaguerapreviop2web.dao;

import com.balaguera.balaguerapreviop2web.model.Reserva;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReservaDAO {

    private List<Reserva> lista = new ArrayList<>();
    private static int contador = 1;

    public List<Reserva> findAll() {
        return lista;
    }

    public Reserva findById(int id) {
        for (Reserva r : lista) {
            if (r.getId() == id) return r;
        }
        return null;
    }

    public Reserva save(Reserva r) {
        r.setId(contador++);
        lista.add(r);
        return r;
    }

    public void delete(int id) {
        lista.removeIf(r -> r.getId() == id);
    }

    public List<Reserva> findByMesa(int mesaId) {
        return lista.stream()
                .filter(r -> r.getMesaId() == mesaId)
                .collect(Collectors.toList());
    }
}