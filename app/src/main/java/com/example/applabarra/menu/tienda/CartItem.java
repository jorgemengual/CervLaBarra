package com.example.applabarra.menu.tienda;

public class CartItem {
    private TiendaItem producto;
    private int cantidad;

    public CartItem(TiendaItem producto) {
        this.producto = producto;
        this.cantidad = 1;
    }

    public TiendaItem getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void incrementarCantidad() {
        cantidad++;
    }

    public void decrementarCantidad() {
        if(cantidad > 1) {
            cantidad--;
        }
    }
}

