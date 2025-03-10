package com.example.applabarra.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applabarra.R;

public class MenuActivity extends AppCompatActivity {

    Button btnSobreNosotros, btnMenuBebidas, btnTienda, btnContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnSobreNosotros = findViewById(R.id.btnSobreNosotros);
        btnMenuBebidas = findViewById(R.id.btnMenuBebidas);
        btnTienda = findViewById(R.id.btnTienda);
        btnContacto = findViewById(R.id.btnContacto);

        // Carga la animación base
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);


        fadeIn.setStartOffset(0); // primer botón sin delay
        btnSobreNosotros.startAnimation(fadeIn);

        Animation fadeIn2 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeIn2.setStartOffset(300); // 300 ms de retraso para el segundo botón
        btnMenuBebidas.startAnimation(fadeIn2);

        Animation fadeIn3 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeIn3.setStartOffset(600); // 600 ms para el tercero
        btnTienda.startAnimation(fadeIn3);

        Animation fadeIn4 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeIn4.setStartOffset(900); // 900 ms para el cuarto
        btnContacto.startAnimation(fadeIn4);


        // Configurar listeners para que al pulsar cada botón se abra la actividad correspondiente.
        btnSobreNosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lanza NosotrosActivity
                startActivity(new Intent(MenuActivity.this, NosotrosActivity.class));
            }
        });

        btnMenuBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lanza MenuBebidasActivy
                startActivity(new Intent(MenuActivity.this, MenuBebidasActivity.class));
            }
        });

        btnTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lanza TiendaACtivity
                startActivity(new Intent(MenuActivity.this, TiendaActivity.class));
            }
        });

        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lanza ContactoActivity
                startActivity(new Intent(MenuActivity.this, ContactoActivity.class));
            }
        });
    }
}
