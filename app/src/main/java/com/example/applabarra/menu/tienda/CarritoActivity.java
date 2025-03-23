package com.example.applabarra.menu.tienda;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.applabarra.R;
import java.util.List;
import android.widget.TextView;

public class CarritoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private MaterialButton btnVaciarCarrito, btnComprar;
    private TextView tvTotal; // Asegúrate de importar TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tvTotal = findViewById(R.id.tvTotal);
        btnVaciarCarrito = findViewById(R.id.btnVaciarCarrito);
        btnComprar = findViewById(R.id.btnComprar);

        List<CartItem> cartItems = CarritoCompras.getInstance().getItems();

        cartAdapter = new CartAdapter(cartItems, cartItem -> {
            CarritoCompras.getInstance().getItems().remove(cartItem);
            cartAdapter.notifyDataSetChanged();
            actualizarTotal();
        });
        recyclerView.setAdapter(cartAdapter);

        btnVaciarCarrito.setOnClickListener(v -> {
            CarritoCompras.getInstance().clear();
            cartAdapter.notifyDataSetChanged();
            actualizarTotal();
        });

        btnComprar.setOnClickListener(v -> {
            startActivity(new Intent(CarritoActivity.this, PaymentActivity.class));
        });

        actualizarTotal();
    }

    private void actualizarTotal() {
        double total = 0;
        for (CartItem item : CarritoCompras.getInstance().getItems()) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        tvTotal.setText(String.format("Total: %.2f€", total));
    }
}
