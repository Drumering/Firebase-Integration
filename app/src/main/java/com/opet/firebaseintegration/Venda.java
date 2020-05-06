package com.opet.firebaseintegration;

public class Venda {

    private String titulo;
    private String descricao;
    private double valor;

    public Venda(String titulo, String descricao, double valor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Venda() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
