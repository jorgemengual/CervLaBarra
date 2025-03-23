package com.example.applabarra;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class PagPrincipal extends AppCompatActivity {

    private static final int SPLASH_DURATION = 6000; // 6 segundos de duración

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_principal);

        // Oculta la barra de acción para que sea pantalla completa
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageView imageLogo = findViewById(R.id.imageLogo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_logo);
        imageLogo.startAnimation(animation);

        // Después del SPLASH_DURATION, lanza la siguiente Activity (LoginActivity)
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(PagPrincipal.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // animación de transición
            finish();
        }, SPLASH_DURATION);
    }
}
