package com.example.applabarra.menu.tienda;

public class TiendaItem {
    private String nombre;
    private double precio;
    private int imagenResId;
    private String descripcion;

    public TiendaItem(String nombre, double precio, int imagenResId, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagenResId = imagenResId;
        this.descripcion = descripcion;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getImagenResId() { return imagenResId; }
    public String getDescripcion() { return descripcion; }
}
