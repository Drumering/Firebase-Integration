package com.opet.firebaseintegration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView textWelcome;
    private FirebaseFirestore db;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        firebaseAuth = FirebaseAuth.getInstance();
        textWelcome = findViewById(R.id.textWelcome);
        textResultado = findViewById(R.id.textResultado);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            textWelcome.setText("Bem-vindo, " + user.getEmail());
        }
        db = FirebaseFirestore.getInstance();
    }

    public void sair(View view){
        firebaseAuth.signOut();
        Intent inicio = new Intent(DashActivity.this, MainActivity.class);
        startActivity(inicio);
        finish();
    }

    public void registrarDadosUsuario(View view) {
        Intent intent = new Intent(DashActivity.this, CadOneActivity.class);
        startActivity(intent);
    }

    public void registrarDadosVenda(View view) {
        Intent intent = new Intent(DashActivity.this, CadVendasActivity.class);
        startActivity(intent);
    }

    public void registrarDadosTarefa(View view) {
        Intent intent = new Intent(DashActivity.this, CadTaskActivity.class);
        startActivity(intent);
    }

    public void gerarDadosNoFirebase(View view) {

        List<Pessoa> pessoas = PopulateUtil.loadPessoas();

        for (Pessoa p : pessoas){
            db.collection("exemplo").add(p);
        }
    }

    public void carregarDados(View view) {

        CollectionReference refPessoa = db.collection("exemplo");
//        refPessoa.whereEqualTo("ativo", true)
        refPessoa.whereGreaterThan("qtde_filhos", 0)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            String resultado = "";
                            List<Pessoa> pessoaList = new ArrayList<>();

                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                resultado += "ID: " + documentSnapshot.getId() + "\n" +
                                        documentSnapshot.getData().toString() + "\n";

                                pessoaList.add(documentSnapshot.toObject(Pessoa.class));
                            }
                            resultado = "";
                            for(Pessoa p : pessoaList){
                                resultado += p.toString() + "\n";

                            }
                            textResultado.setText(resultado);
                        }
                    }
                });
    }
}