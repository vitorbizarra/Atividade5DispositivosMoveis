package com.example.atividade5.api.mockapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mockapi {
    private static String baseUrl = "https://66520c3920f4f4c4427996ca.mockapi.io/";
    private static Retrofit retrofit = null;

    public static AlunoService getUsuarioService() {
        return getClient(baseUrl).create(AlunoService.class);
    }

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
