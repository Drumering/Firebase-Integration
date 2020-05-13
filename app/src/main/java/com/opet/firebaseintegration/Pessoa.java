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
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", qtde_filhos=" + qtde_filhos +
                ", salario=" + salario +
                ", ativo=" + ativo +
                ", pets=" + pets +
                ", dt_aniversario=" + dt_aniversario +
                '}';
    }
}
