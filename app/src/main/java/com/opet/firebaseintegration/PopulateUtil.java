package com.opet.firebaseintegration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

class PopulateUtil {

    public static List<Pessoa> loadPessoas() {

        List<Pessoa> pessoas = new ArrayList<>();
        Pessoa pessoa = new Pessoa();

        pessoa.nome = "Joao";
        pessoa.qtde_filhos = 1;
        pessoa.salario = 1000.12;
        pessoa.ativo = false;
        pessoa.pets = Arrays.asList("Pingo", "Odin");
        pessoa.dt_aniversario = new GregorianCalendar(1991, Calendar.AUGUST, 17).getTime();

        pessoas.add(pessoa);

        pessoa = new Pessoa();
        pessoa.nome = "Ana Maria";
        pessoa.qtde_filhos = 2;
        pessoa.salario = 1050.12;
        pessoa.ativo = true;
        pessoa.pets = Arrays.asList("Mingau");
        pessoa.dt_aniversario = new GregorianCalendar(1988, Calendar.JANUARY, 21).getTime();

        pessoas.add(pessoa);

        pessoa = new Pessoa();
        pessoa.nome = "Pedro Moreira";
        pessoa.qtde_filhos = 0;
        pessoa.salario = 1900.14;
        pessoa.ativo = true;
        pessoa.pets = null;
        pessoa.dt_aniversario = new GregorianCalendar(1995, Calendar.DECEMBER, 30).getTime();

        pessoas.add(pessoa);

        return pessoas;
    }
}
