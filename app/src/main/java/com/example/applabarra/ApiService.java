package com.example.applabarra;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("usuario") String usuario,
            @Field("clave") String clave
    );

    @FormUrlEncoded
    @POST("registro.php")
    Call<RegistroRespuesta> registro(
            @Field("nombreyape") String nombreyape,
            @Field("email") String email,
            @Field("clave") String clave
    );
}

