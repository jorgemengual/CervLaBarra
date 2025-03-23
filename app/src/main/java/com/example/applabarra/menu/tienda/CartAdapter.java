package com.example.applabarra.menu.tienda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.applabarra.R;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private final OnItemActionListener listener;

    public interface OnItemActionListener {
        void onRemoveItem(CartItem item);
        // Puedes añadir otros métodos para modificar cantidad si lo necesitas
    }

    public CartAdapter(List<CartItem> cartItems, OnItemActionListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(cartItems.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewNombre, textViewPrecio, textViewCantidad;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewCartProducto);
            textViewNombre = itemView.findViewById(R.id.textViewCartNombre);
            textViewPrecio = itemView.findViewById(R.id.textViewCartPrecio);
            textViewCantidad = itemView.findViewById(R.id.textViewCartCantidad);
        }

        public void bind(final CartItem cartItem, final OnItemActionListener listener) {
            TiendaItem producto = cartItem.getProducto();
            Glide.with(itemView.getContext())
                    .load(producto.getImagenResId())
                    .into(imageView);
            textViewNombre.setText(producto.getNombre());
            textViewPrecio.setText(String.format("%.2f€", producto.getPrecio()));
            textViewCantidad.setText("Cantidad: " + cartItem.getCantidad());

            // Acción de eliminación al hacer clic prolongado en el ítem
            itemView.setOnLongClickListener(v -> {
                if (listener != null) {
                    listener.onRemoveItem(cartItem);
                }
                return true;
            });
        }
    }
}

