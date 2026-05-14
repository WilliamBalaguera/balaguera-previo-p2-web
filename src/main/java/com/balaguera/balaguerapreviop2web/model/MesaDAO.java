package com.balaguera.balaguerapreviop2web.dao;

import com.balaguera.balaguerapreviop2web.model.Mesa;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MesaDAO {

    private List<Mesa> lista = new ArrayList<>();
    private static int contador = 1;

    public List<Mesa> findAll() {
        return lista;
    }

    public Mesa findById(int id) {
        for (Mesa m : lista) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    public Mesa save(Mesa m) {
        m.setId(contador++);
        lista.add(m);
        return m;
    }

    public Mesa update(Mesa m) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == m.getId()) {
                lista.set(i, m);
                return m;
            }
        }
        return null;
    }

    public void delete(int id) {
        lista.removeIf(m -> m.getId() == id);
    }
}