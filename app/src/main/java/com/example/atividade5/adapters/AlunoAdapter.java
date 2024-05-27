package com.example.atividade5.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade5.R;
import com.example.atividade5.holders.AlunoHolder;
import com.example.atividade5.models.Aluno;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlunoAdapter extends RecyclerView.Adapter<AlunoHolder> {
    private final List<Aluno> alunos;
    Context context;

    @NonNull
    @Override
    public AlunoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlunoHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card_item_aluno, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoHolder holder, int position) {
        Aluno aluno = alunos.get(position);

        holder.aluno.setText(aluno.getRa() + " - " + aluno.getNome());
        holder.endereco.setText(this.getEnderecoAluno(aluno));
    }

    private String getEnderecoAluno(Aluno aluno){
        String endereco =  aluno.getCep() + " - " + aluno.getLogradouro();

        if (!aluno.getComplemento().isEmpty()){
            endereco += ", " + aluno.getComplemento();
        };

        endereco += ", " + aluno.getBairro() + ", " + aluno.getCidade() + " - " + aluno.getUf();

        return endereco;
    }

    @Override
    public int getItemCount() {
        return alunos != null ? alunos.size() : 0;
    }
}
