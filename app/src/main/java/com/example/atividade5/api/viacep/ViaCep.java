package com.example.atividade5.api.viacep;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViaCep {
    private static String baseUrl = "https://viacep.com.br/ws/";

    public static ViaCepService getViaCepService() {
        return getClient(baseUrl).create(ViaCepService.class);
    }

    private static Retrofit retrofit = null;

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
