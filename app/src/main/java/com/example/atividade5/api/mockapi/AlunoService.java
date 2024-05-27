package com.example.atividade5.api.mockapi;

import com.example.atividade5.models.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AlunoService {
    @GET("alunos")
    Call<List<Aluno>> getAlunos();

    @POST("alunos")
    Call<Aluno> postAluno(@Body Aluno aluno);

    @DELETE("alunos/{id}")
    Call<Void> deleteAluno(@Path("id") int id);

    @GET("alunos/{id}")
    Call<Aluno> getAlunoPorId(@Path("id") int id);

    @PUT("alunos/{id}")
    Call<Aluno> putAluno(@Path("id") int id, @Body Aluno aluno);
}
