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

    // Componentes de la interfaz: campos de texto y botón de registro
    EditText etNombreyape, etEmail, etClave;
    Button btnRegistrar;

    // URL del servidor (emulador Android)
    private static final String BASE_URL = "http://10.0.2.2/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Se establece el layout de la actividad
        setContentView(R.layout.activity_registro);

        // Inicialización de los elementos de la interfaz mediante sus IDs
        etNombreyape = findViewById(R.id.etNombreyape);
        etEmail = findViewById(R.id.etEmail);
        etClave = findViewById(R.id.etClave);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        // Configuración del listener para el botón de registro
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtención y limpieza del texto ingresado en los campos
                String nombreyape = etNombreyape.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String clave = etClave.getText().toString().trim();
                // Validación: se comprueba que ninguno de los campos esté vacío
                if (nombreyape.isEmpty() || email.isEmpty() || clave.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Configuración de los Retrofit para consumir el API
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)  // Establece la URL base
                        .addConverterFactory(GsonConverterFactory.create()) //Define el convertidor JSON
                        .build();
                // Creación de la instancia del servicio API a partir de la interfaz ApiService
                ApiService apiService = retrofit.create(ApiService.class);

                //Realización de la llamada al API para registrar al usuario
                Call<RegistroRespuesta> call = apiService.registro(nombreyape, email, clave);
                call.enqueue(new Callback<RegistroRespuesta>() {
                    @Override
                    public void onResponse(Call<RegistroRespuesta> call, Response<RegistroRespuesta> response) {
                        //Verifica que la respuesta es exitosa y contiene un cuerpo
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().isSuccess()) {
                                Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                finish(); // Finaliza la actividad
                            } else {
                                // muestra el mensaje de error devuelto por el servidor
                                Toast.makeText(RegistroActivity.this, "Error: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Notifica que la respuesta del servidor no fue exitosa
                            Toast.makeText(RegistroActivity.this, "Respuesta no exitosa del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegistroRespuesta> call, Throwable t) {
                        //Manejo de errores de conexión o de red
                        Toast.makeText(RegistroActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}