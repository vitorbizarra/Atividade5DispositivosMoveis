package com.example.atividade5.api.viacep;

import com.example.atividade5.models.Endereco;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ViaCepService {
    @GET("{cep}/json")
    Call<Endereco> getEnderecoPorCep(@Path("cep") String cep);
}
