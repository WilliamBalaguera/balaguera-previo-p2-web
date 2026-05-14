package com.balaguera.balaguerapreviop2web.service;

import com.balaguera.balaguerapreviop2web.dao.MesaDAO;
import com.balaguera.balaguerapreviop2web.dao.ReservaDAO;
import com.balaguera.balaguerapreviop2web.model.Mesa;
import com.balaguera.balaguerapreviop2web.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservaService {

    @Autowired
    private ReservaDAO reservaDAO;

    @Autowired
    private MesaDAO mesaDAO;

    public Map<String, String> validar(Reserva r) {
        Map<String, String> errores = new HashMap<>();

        // Validar mesaId
        Mesa mesa = mesaDAO.findById(r.getMesaId());
        if (mesa == null || !mesa.isActiva()) {
            errores.put("mesaId", "La mesa no existe o no está activa");
        }

        // Validar clienteNombre
        if (r.getClienteNombre() == null || r.getClienteNombre().trim().length() < 3) {
            errores.put("clienteNombre", "El nombre debe tener al menos 3 caracteres");
        }

        // Validar horario
        if (r.getHoraFin() == null || r.getHoraInicio() == null ||
                r.getHoraFin().compareTo(r.getHoraInicio()) <= 0) {
            errores.put("horario", "La hora de fin debe ser posterior a la hora de inicio");
        }

        // Validar numPersonas (solo si la mesa existe y está activa)
        if (mesa != null && mesa.isActiva()) {
            if (r.getNumPersonas() <= 0 || r.getNumPersonas() > mesa.getCapacidad()) {
                errores.put("numPersonas", "El número de personas debe ser mayor a 0 y no superar la capacidad de la mesa");
            }
        }

        return errores;
    }

    public Map<String, String> guardar(Reserva r) {
        Map<String, String> errores = validar(r);
        if (errores.isEmpty()) {
            reservaDAO.save(r);
        }
        return errores;
    }

    public List<Reserva> findAll() {
        return reservaDAO.findAll();
    }

    public Reserva findById(int id) {
        return reservaDAO.findById(id);
    }

    public void delete(int id) {
        reservaDAO.delete(id);
    }
}