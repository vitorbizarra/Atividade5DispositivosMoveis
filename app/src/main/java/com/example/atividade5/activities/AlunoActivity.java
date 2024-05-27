package com.example.atividade5.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.atividade5.R;
import com.example.atividade5.api.mockapi.AlunoService;
import com.example.atividade5.api.mockapi.Mockapi;
import com.example.atividade5.api.viacep.ViaCep;
import com.example.atividade5.api.viacep.ViaCepService;
import com.example.atividade5.models.Aluno;
import com.example.atividade5.models.Endereco;

import java.io.IOException;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlunoActivity extends AppCompatActivity {
    EditText txtRa, txtNome, txtCep, txtLogradouro, txtComplemento, txtBairro, txtCidade, txtUf;
    Button btnCep, btnSalvar;
    AlunoService alunoService;
    ViaCepService viaCepService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        this.alunoService = Mockapi.getUsuarioService();
        this.viaCepService = ViaCep.getViaCepService();

        this.txtRa = (EditText) findViewById(R.id.txtRa);
        this.txtNome = (EditText) findViewById(R.id.txtNome);
        this.txtCep = (EditText) findViewById(R.id.txtCep);
        this.txtLogradouro = (EditText) findViewById(R.id.txtLogradouro);
        this.txtComplemento = (EditText) findViewById(R.id.txtComplemento);
        this.txtBairro = (EditText) findViewById(R.id.txtBairro);
        this.txtCidade = (EditText) findViewById(R.id.txtCidade);
        this.txtUf = (EditText) findViewById(R.id.txtUf);

        this.btnCep = (Button) findViewById(R.id.btnCep);
        this.btnSalvar = (Button) findViewById(R.id.btnSalvar);

        this.btnCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cep = txtCep.getText().toString();

                if (cep.isEmpty()){
                    return;
                }

                viaCepService.getEnderecoPorCep(cep).enqueue(new Callback<Endereco>() {
                    @Override
                    public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                        if (response.isSuccessful()){
                            Endereco endereco = response.body();
                            txtLogradouro.setText(endereco.getLogradouro());
                            txtComplemento.setText(endereco.getComplemento());
                            txtBairro.setText(endereco.getBairro());
                            txtCidade.setText(endereco.getLocalidade());
                            txtUf.setText(endereco.getUf());
                        } else {
                            Toast.makeText(AlunoActivity.this, "CEP não encontrado", Toast.LENGTH_LONG).show();
                            Log.e("Endereço não encontrado", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Endereco> call, Throwable t) {
                        Toast.makeText(AlunoActivity.this, "Não foi possível encontrar o endereço", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        this.btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = new Aluno();
                aluno.setRa(Integer.valueOf(txtRa.getText().toString()));
                aluno.setNome(txtNome.getText().toString());
                aluno.setCep(txtCep.getText().toString());
                aluno.setLogradouro(txtLogradouro.getText().toString());
                aluno.setComplemento(txtComplemento.getText().toString());
                aluno.setBairro(txtBairro.getText().toString());
                aluno.setCidade(txtCidade.getText().toString());
                aluno.setUf(txtUf.getText().toString());

                inserirAluno(aluno);
            }
        });
    }

    private void inserirAluno(Aluno aluno){
        Call<Aluno> call = alunoService.postAluno(aluno);
        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                if (response.isSuccessful()){
                    Aluno createdAluno = response.body();
                    Toast.makeText(AlunoActivity.this, "Inserido com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AlunoActivity.this, "Erro ao criar: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                Toast.makeText(AlunoActivity.this, "Erro ao criar: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}