package com.balaguera.balaguerapreviop2web.model;

public class Reserva {
    private int id;
    private int mesaId;
    private String clienteNombre;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private int numPersonas;

    public Reserva() {}

    public Reserva(int id, int mesaId, String clienteNombre, String fecha,
                   String horaInicio, String horaFin, int numPersonas) {
        this.id = id;
        this.mesaId = mesaId;
        this.clienteNombre = clienteNombre;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.numPersonas = numPersonas;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMesaId() { return mesaId; }
    public void setMesaId(int mesaId) { this.mesaId = mesaId; }

    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

    public String getHoraFin() { return horaFin; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }

    public int getNumPersonas() { return numPersonas; }
    public void setNumPersonas(int numPersonas) { this.numPersonas = numPersonas; }
}
