package com.balaguera.balaguerapreviop2web.model;

public class Mesa {
    private int id;
    private int numero;
    private int capacidad;
    private String zona;
    private boolean activa;

    public Mesa() {}

    public Mesa(int id, int numero, int capacidad, String zona, boolean activa) {
        this.id = id;
        this.numero = numero;
        this.capacidad = capacidad;
        this.zona = zona;
        this.activa = activa;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}
