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
        setContentView(R.layout.activity_pag_principal);

        Button bienvenido = findViewById(R.id.btn_bienvenido);
        bienvenido.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(PagPrincipal.this, LoginActivity.class);
        startActivity(i);
    }
}