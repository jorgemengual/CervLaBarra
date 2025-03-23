package com.example.applabarra.menu;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.applabarra.R;

public class SobreNosotrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobrenosotros);

        // Referencias a los ImageViews
        ImageView ivLogo = findViewById(R.id.ivLogo);
        ImageView ivFotocopas = findViewById(R.id.ivFotocopas);
        ImageView ivEquipo = findViewById(R.id.ivEquipo);
        ImageView ivPoster1 = findViewById(R.id.ivPoster1);
        ImageView ivPoster2 = findViewById(R.id.ivPoster2);

        // Cargar imágenes optimizadas con Glide
        Glide.with(this)
                .load(R.drawable.logoprincipal_)  // Recurso para el logo
                .override(400, 400)
                .into(ivLogo);

        Glide.with(this)
                .load(R.drawable.fotocopas)        // Nueva imagen "fotocopas.webp"
                .override(300, 200)
                .into(ivFotocopas);

        Glide.with(this)
                .load(R.drawable.panobar)          // Imagen representativa
                .override(300, 200)
                .into(ivEquipo);

        Glide.with(this)
                .load(R.drawable.claracartel)      // Poster 1
                .override(0, 200)
                .into(ivPoster1);

        Glide.with(this)
                .load(R.drawable.davidcartel)      // Poster 2
                .override(0, 200)
                .into(ivPoster2);

        // Animar la aparición secuencial de cada bloque de contenido con retardo incremental
        final ViewGroup llContent = findViewById(R.id.llContent);
        llContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int count = llContent.getChildCount();
                for (int i = 0; i < count; i++) {
                    View child = llContent.getChildAt(i);
                    child.animate().alpha(1f).setDuration(800).setStartDelay(i * 400).start();
                }
                llContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
}


