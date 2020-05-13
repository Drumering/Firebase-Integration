package com.opet.firebaseintegration;

import java.util.Date;
import java.util.List;

public class Pessoa {
    public String nome;
    public int qtde_filhos;
    public double salario;
    public boolean ativo;
    public List<String> pets;
    public Date dt_aniversario;

    @Override
    public String toString() {
        return "nome: " + nome + '\n' +
                "qtde_filhos: " + qtde_filhos + '\n' +
                "salario: " + salario + '\n' +
                "ativo: " + ativo + '\n' +
                "pets: " + pets + '\n' +
                "dt_aniversario: " + dt_aniversario + '\n' + '\n';
    }
}
