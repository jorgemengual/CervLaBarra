package com.example.applabarra.menu.tienda;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.applabarra.R;
import java.util.ArrayList;
import java.util.List;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class TiendaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TiendaAdapter tiendaAdapter;
    private List<TiendaItem> tiendaItems;
    private FloatingActionButton fabCarrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        recyclerView = findViewById(R.id.recyclerViewTienda);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation);
        recyclerView.setLayoutAnimation(controller);

        // Inicializar los productos
        tiendaItems = new ArrayList<>();
        tiendaItems.add(new TiendaItem("Big mountain", 9.0, R.drawable.big_mountain,
                "Mountain IPA de 6,2%. Cuerpo, sensación en boca y turbidez de NEIPA."));
        tiendaItems.add(new TiendaItem("Nobody fucks with the Jesus", 9.0, R.drawable.nobodyjesus,
                "West Coast IPA de 6,9%. Combo herbal y frutas con hueso con un punch de amargor marca de la casa."));
        tiendaItems.add(new TiendaItem("La Zumo", 9.0, R.drawable.lazumo,
                "Hazy IPA de 6%. Intensos aromas tropicales y cítricos que invitan a disfrutarla desde el primer sorbo."));
        tiendaItems.add(new TiendaItem("Pink Flamingo", 9.0, R.drawable.flamingo,
                "Fruited Sour de 5,2%. Queremos hacerte brillar como un flamenco, por eso te traemos una cerveza refrescante con frambuesa, hibisco y maracuyá."));
        tiendaItems.add(new TiendaItem("Santa Clara", 8.50, R.drawable.santaclara,
                "Helles Lager de 4,8%. Su base de maltas clásicas aporta un delicado dulzor que evoca las pipas de girasol, equilibrado con lúpulos nobles Tettnanger que añaden notas florales y un suave amargor a hierba fresca."));
        tiendaItems.add(new TiendaItem("Imparable", 8.0, R.drawable.imparable,
                "West Coast IPA de 6,8%. Su equilibrio perfecto entre amargor y aroma, con notas cítricas, tropicales y resinosas, la convierten en una cerveza única e inolvidable."));
        tiendaItems.add(new TiendaItem("Fudgesicle", 9.50, R.drawable.fudgesicle,
                "Imperial Pastry Stout de 11,6%. Helado crocante hecho cerveza, con avellanas, chocolate y caramelo. Muerde este postre fresco, dulce, ácido y lujurioso, elaborado con una base de 9 maltas para una complejidad extra."));
        tiendaItems.add(new TiendaItem("Chimay", 6.50, R.drawable.chimay,
                "Belgian Strong Dark Ale de 9%. Aromas muy afrutados, toques de chocolate y especias y un sabor que mejora con el paso del tiempo."));
        tiendaItems.add(new TiendaItem("Gulden Draak", 7.0, R.drawable.gulden,
                "Belgian Strong Ale de 10,5%. Una hermosa cerveza de color marrón rojizo intenso, con un carácter maltoso-dulce y expresivas notas de caramelo, chocolate con leche y un toque de plátano."));
        tiendaItems.add(new TiendaItem("Westmalle Tripel", 5.75, R.drawable.westmalletriple,
                "Trappist de 9,5%. De intensos aromas frutales, con toques de miel y florales. Gusto inicial afrutado y maltoso, concluye con un final seco y algo amargo."));
        tiendaItems.add(new TiendaItem("Westmalle Dubbel", 5.75, R.drawable.wetmalledubble,
                "Trappist de 7%. De aromas tostados a malta y ligeramente afrutados. De gusto muy agradable, a maltas, chocolate y miel."));

        tiendaAdapter = new TiendaAdapter(tiendaItems, item -> {
                Toast.makeText(this, "Has seleccionado: " + item.getNombre(), Toast.LENGTH_SHORT).show();
                // Añadir el item al carrito de compras
            CarritoCompras.getInstance().addItem(item);
        });

        recyclerView.setAdapter(tiendaAdapter);

        //Configurar el boton para abrir el carrito
        fabCarrito = findViewById(R.id.fabCarrito);
        fabCarrito.setOnClickListener(v -> {
            //Abre la CarritoActivity
            startActivity(new Intent(TiendaActivity.this, CarritoActivity.class));
        });
    }
}
