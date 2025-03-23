package com.example.applabarra.menu.tienda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.applabarra.R;
import java.util.List;

public class TiendaAdapter extends RecyclerView.Adapter<TiendaAdapter.TiendaViewHolder> {

    private List<TiendaItem> tiendaItems;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(TiendaItem item);

    }

    public TiendaAdapter(List<TiendaItem> tiendaItems, OnItemClickListener listener) {
        this.tiendaItems = tiendaItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TiendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tienda, parent, false);
        return new TiendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TiendaViewHolder holder, int position) {
        holder.bind(tiendaItems.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return tiendaItems.size();
    }

    public static class TiendaViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewNombre, textViewPrecio, textViewDescripcion;

        public TiendaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewProducto);
            textViewNombre = itemView.findViewById(R.id.textViewNombreProducto);
            textViewPrecio = itemView.findViewById(R.id.textViewPrecioProducto);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcionProducto);
        }


        public void bind(final TiendaItem item, final OnItemClickListener listener) {
            Glide.with(itemView.getContext())
                    .load(item.getImagenResId())
                    .into(imageView);

            textViewNombre.setText(item.getNombre());
            textViewPrecio.setText(item.getPrecio() + "€");
            textViewDescripcion.setText(item.getDescripcion());
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }


    }
}
