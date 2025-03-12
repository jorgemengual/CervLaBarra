package com.example.applabarra;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    //Método para realizar la petición de login al servidor
    @FormUrlEncoded // Los datos se envian en formato de formulario
    @POST("login.php") // Especifica que se trata de una solicitud POST a login.php
    Call<LoginResponse> login(
            // Los parametros se envían en formato @Field, que asocia cada valor a su correspondiente clave en el formulario
            @Field("usuario") String usuario,
            @Field("clave") String clave
    );

    // Método para realizar la petición de registro al servidor
    @FormUrlEncoded
    @POST("registro.php")
    Call<RegistroRespuesta> registro(
            @Field("nombreyape") String nombreyape,
            @Field("email") String email,
            @Field("clave") String clave
    );
}

