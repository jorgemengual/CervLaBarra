package com.example.applabarra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applabarra.menu.MenuActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    // Declaración de las variables para los elementos de la interfaz
    EditText usuario, clave;
    TextView lblregistrar;
    Button btnregistrar;

    // URL del servidor
    private static final String BASE_URL = "http://10.0.2.2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Se establece el layout correspondiente a esta actividad
        setContentView(R.layout.login);

        // Inicio de los componentes de la interfaz
        usuario = findViewById(R.id.txtUsuario);
        clave = findViewById(R.id.txtContraseña);
        lblregistrar = findViewById(R.id.txtRegistrar);
        btnregistrar = findViewById(R.id.btningresar);

        // COnfiguración del listener para el botón de ingresar
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Obtención y limpieza de los datos ingresados por el usuario
                String usuarioStr = usuario.getText().toString().trim();
                String claveStr = clave.getText().toString().trim();

                // Validación: Se verifica que ambos campos no estén vacios
                if(usuarioStr.isEmpty() || claveStr.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Configuración de Retrofit para realizar la llamada al API
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL) // Establece la URL base
                        .addConverterFactory(GsonConverterFactory.create()) // Confugura el convertidor JSON
                        .build();
                // Creación de la instancia del servicio API usando la interfaz ApiService
                ApiService apiService = retrofit.create(ApiService.class);

                // Llamada al metodo de login del API, escribiendo usuario y clave
                Call<LoginResponse> call = apiService.login(usuarioStr, claveStr);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        //Verifica que la respuesta sea exitosa y con tenga datos
                        if(response.isSuccessful() && response.body() != null){
                            // Si el login es exitoso según el servidor
                            if(response.body().isSuccess()){
                                Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                                //Si el login es exitoso procederá a abrir la siguiente pantalla
                                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in, R.anim.fade_out);
                                finish(); //Esto cierra la LoginActivity para que el usuario no pueda volver atrás
                            } else {
                                // Muestra el mensaje de error enviado por el servidor
                                Toast.makeText(LoginActivity.this, "Error: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Notifica que la respuesta del servidor no fue exitosa
                            Toast.makeText(LoginActivity.this, "Respuesta no exitosa del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        // Manejo de errores en la conexión o durante la llamada
                        Toast.makeText(LoginActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Configuración del listener para el TextView que redirige a la actividad de registro
        lblregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicia la act de registro
               startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
            }
        });
    }
}

