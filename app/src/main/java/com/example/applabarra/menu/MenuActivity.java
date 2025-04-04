package com.example.applabarra.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import android.view.LayoutInflater;
import android.widget.ImageView;
import com.example.applabarra.R;
import com.example.applabarra.menu.tienda.TiendaActivity;

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

        // Configurar listeners para cada botón
        btnSobreNosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, SobreNosotrosActivity.class));
            }
        });

        btnMenuBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // se muestra el QR en un BottomSheet
                mostrarQRBottomSheet();
            }
        });

        btnTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, TiendaActivity.class));
            }
        });

        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, ContactoActivity.class));
            }
        });
    }

    private void mostrarQRBottomSheet() {
        // Crear el BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        // Inflar el layout del QR
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_qr, null);
        bottomSheetDialog.setContentView(view);
        // Configurar para que se cierre al tocar fuera
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);

        // Ajustar la altura del BottomSheet a la mitad de la pantalla
        View parent = (View) view.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        int halfScreenHeight = getResources().getDisplayMetrics().heightPixels / 2;
        behavior.setPeekHeight(halfScreenHeight);

        bottomSheetDialog.show();
    }
}

