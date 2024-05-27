package com.example.atividade5.holders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade5.R;

public class AlunoHolder extends RecyclerView.ViewHolder {
    public TextView aluno, endereco;
    public AlunoHolder(View itemView) {
        super(itemView);

        this.aluno = (TextView) itemView.findViewById(R.id.txtAluno);
        this.endereco = (TextView) itemView.findViewById(R.id.txtEndereco);
    }
}
