package com.example.applabarra.menu.tienda;

import java.util.ArrayList;
import java.util.List;

public class CarritoCompras {
    private static CarritoCompras instance;
    private List<CartItem> items;

    private CarritoCompras() {
        items = new ArrayList<>();
    }

    public static synchronized CarritoCompras getInstance() {
        if (instance == null) {
            instance = new CarritoCompras();
        }
        return instance;
    }

    // Añade un producto: si ya existe, incrementa la cantidad
    public void addItem(TiendaItem producto) {
        for (CartItem item : items) {
            if (item.getProducto().getNombre().equals(producto.getNombre())) { // Puedes comparar por id si tienes ese campo
                item.incrementarCantidad();
                return;
            }
        }
        items.add(new CartItem(producto));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void removeItem(TiendaItem producto) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProducto().getNombre().equals(producto.getNombre())) {
                items.remove(i);
                break;
            }
        }
    }

    public void clear() {
        items.clear();
    }
}

