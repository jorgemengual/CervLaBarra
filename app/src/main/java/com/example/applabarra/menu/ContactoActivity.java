package com.example.applabarra.menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.example.applabarra.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContactoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextInputEditText etNombre, etEmail, etMensaje;
    private MaterialButton btnEnviar, btnInstagram;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etMensaje = findViewById(R.id.etMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnInstagram = findViewById(R.id.btnInstagram);

        btnEnviar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String mensaje = etMensaje.getText().toString().trim();

            if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(email) || TextUtils.isEmpty(mensaje)) {
                Toast.makeText(ContactoActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            // Aquí se podría implementar el envío real del mensaje
            Toast.makeText(ContactoActivity.this, "Mensaje enviado. Te contactaremos pronto.", Toast.LENGTH_LONG).show();
            etNombre.setText("");
            etEmail.setText("");
            etMensaje.setText("");
        });

        btnInstagram.setOnClickListener(v -> {
            String url = "http://instagram.com/cerveceria_la_barra?igsh=MTFqaTg5aTR3Y3Y0eQ==";
            Intent instagramIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            instagramIntent.setPackage("com.instagram.android");
            if (instagramIntent.resolveActivity(getPackageManager()) == null) {
                instagramIntent.setPackage(null);
            }
            startActivity(instagramIntent);
        });

        // Inicializar Google Maps en el contenedor
        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.mapContainer);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fragmentManager.beginTransaction().replace(R.id.mapContainer, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        // Establece la ubicación del bar (ajusta con las coordenadas reales)
        LatLng barLocation = new LatLng(36.5299879, -6.2921928);
        googleMap.addMarker(new MarkerOptions().position(barLocation).title("Cervecería La Barra"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(barLocation, 15));
    }
}

