package com.example.applabarra;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PagPrincipal extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Se establece el layout para la actividad principal
        setContentView(R.layout.activity_pag_principal);

        // Inicio del botón de bienvenida y asignación del listener para manejar clics
        Button bienvenido = findViewById(R.id.btn_bienvenido);
        bienvenido.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Al hacer clic, se crea un Intent para navegar a la actividad de Login
        Intent i = new Intent(PagPrincipal.this, LoginActivity.class);
        startActivity(i);
    }
}