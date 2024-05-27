package com.example.atividade5.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade5.R;
import com.example.atividade5.adapters.AlunoAdapter;
import com.example.atividade5.api.mockapi.AlunoService;
import com.example.atividade5.api.mockapi.Mockapi;
import com.example.atividade5.models.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerAluno;
    AlunoService alunoService;
    AlunoAdapter alunoAdapter;
    List<Aluno> listaAlunos;

    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recyclerAluno = (RecyclerView) findViewById(R.id.recyclerAluno);
        this.btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        this.alunoService = Mockapi.getUsuarioService();

        this.btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AlunoActivity.class);
            startActivity(intent);
        });
//        btnAdd.setOnClickListener(v -> {
//            Intent i = new Intent(MainActivity.this, AlunoActivity.class);
//            startActivity(i);
//        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        obterAlunos();
    }

    private void configurarRecycler(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerAluno.setLayoutManager(layoutManager);
        alunoAdapter = new AlunoAdapter(listaAlunos, this);
        recyclerAluno.setAdapter(alunoAdapter);
        recyclerAluno.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void obterAlunos(){
        Call<List<Aluno>> call = alunoService.getAlunos();

        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                listaAlunos = response.body();
                configurarRecycler();
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {
                Log.e("TESTE", "Erro ao obter os contatos: " + t.getMessage());
            }
        });
    }
}