package com.example.applabarra;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;





public class RegistroActivity extends AppCompatActivity {


    EditText etNombreyape, etEmail, etClave;
    Button btnRegistrar;


    private static final String BASE_URL = "http://10.0.2.2/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombreyape = findViewById(R.id.etNombreyape);
        etEmail = findViewById(R.id.etEmail);
        etClave = findViewById(R.id.etClave);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreyape = etNombreyape.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String clave = etClave.getText().toString().trim();

                if (nombreyape.isEmpty() || email.isEmpty() || clave.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);

                Call<RegistroRespuesta> call = apiService.registro(nombreyape, email, clave);
                call.enqueue(new Callback<RegistroRespuesta>() {
                    @Override
                    public void onResponse(Call<RegistroRespuesta> call, Response<RegistroRespuesta> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().isSuccess()) {
                                Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(RegistroActivity.this, "Error: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegistroActivity.this, "Respuesta no exitosa del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegistroRespuesta> call, Throwable t) {
                        Toast.makeText(RegistroActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}