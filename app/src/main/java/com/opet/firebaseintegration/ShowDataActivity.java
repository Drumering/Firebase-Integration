package com.opet.firebaseintegration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.opet.firebaseintegration.fragments.ShowDataFragment;

import java.util.ArrayList;
import java.util.List;

public class ShowDataActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore db;
    public static String resultado;
    private ShowDataFragment showDataFragment;

    private Button getDataByName;
    private EditText editGetByName;

    private Button getDataByFaixaSalarial;
    private EditText editGetByFaixaSalarial;

    private Button getDataByPetName;
    private EditText editGetDataByPetName;

    private EditText editGetByFaixaSalarialBiggerThan;
    private EditText editGetByNumberTwins;
    private Button getDataBySalarioEFilhos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        editGetByName = findViewById(R.id.editGetByName);
        getDataByName = findViewById(R.id.getDataByName);
        getDataByName.setOnClickListener(this);

        getDataByFaixaSalarial = findViewById(R.id.getDataByFaixaSalarial);
        editGetByFaixaSalarial = findViewById(R.id.editGetByFaixaSalarial);
        getDataByFaixaSalarial.setOnClickListener(this);

        editGetDataByPetName = findViewById(R.id.editGetDataByPetName);
        getDataByPetName = findViewById(R.id.getDataByPetName);
        getDataByPetName.setOnClickListener(this);

        editGetByFaixaSalarialBiggerThan = findViewById(R.id.editGetByFaixaSalarialBiggerThan);
        editGetByNumberTwins = findViewById(R.id.editGetByNumberTwins);
        getDataBySalarioEFilhos = findViewById(R.id.getDataBySalarioEFilhos);
        getDataBySalarioEFilhos.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.getDataByName:{
                if (!editGetByName.getText().toString().isEmpty()){
                    CollectionReference refPessoa = db.collection("exemplo");
                    refPessoa.whereEqualTo("nome", editGetByName.getText().toString())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful()){
                                        List<Pessoa> pessoaList = new ArrayList<>();
                                        for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                            pessoaList.add(documentSnapshot.toObject(Pessoa.class));
                                        }
                                        ShowDataActivity.resultado = "";
                                        for(Pessoa p : pessoaList){
                                            ShowDataActivity.resultado += p.toString() + "\n";
                                        }
                                        initFragmentShowData();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(ShowDataActivity.this, "Insira um nome", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.getDataByFaixaSalarial:{
                if(!editGetByFaixaSalarial.getText().toString().isEmpty()){
                    CollectionReference refPessoa = db.collection("exemplo");
                    refPessoa.whereLessThanOrEqualTo("salario", Double.parseDouble(editGetByFaixaSalarial.getText().toString()))
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful()){
                                        List<Pessoa> pessoaList = new ArrayList<>();
                                        for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                            pessoaList.add(documentSnapshot.toObject(Pessoa.class));
                                        }
                                        ShowDataActivity.resultado = "";
                                        for(Pessoa p : pessoaList){
                                            ShowDataActivity.resultado += p.toString() + "\n";
                                        }
                                        initFragmentShowData();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(ShowDataActivity.this, "Insira uma faixa salarial", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.getDataByPetName:{
                if(!editGetDataByPetName.getText().toString().isEmpty()){
                    CollectionReference refPet = db.collection("exemplo");
                    refPet.whereArrayContains("pets", editGetDataByPetName.getText().toString())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()){
                                        List<Pessoa> pessoaList = new ArrayList<>();
                                        for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                            pessoaList.add(documentSnapshot.toObject(Pessoa.class));
                                        }
                                        ShowDataActivity.resultado = "";
                                        for(Pessoa p : pessoaList){
                                            ShowDataActivity.resultado += p.toString() + "\n";
                                        }
                                        initFragmentShowData();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(ShowDataActivity.this, "Insira um nome de pet", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.getDataBySalarioEFilhos:{
                int qtde_filhos;
                if(!editGetByFaixaSalarialBiggerThan.getText().toString().isEmpty()){
                    if(editGetByNumberTwins.getText().toString().isEmpty()){
                        qtde_filhos = 0;
                    } else {
                        qtde_filhos = Integer.parseInt(editGetByNumberTwins.getText().toString());
                    }
                    CollectionReference refPessoa = db.collection("exemplo");
                    refPessoa.whereGreaterThan("salario", Double.parseDouble(editGetByFaixaSalarialBiggerThan.getText().toString()));
                    Query query = refPessoa.whereGreaterThanOrEqualTo("qtde_filhos", qtde_filhos);
                    query.get()
                            .addOnCompleteListener(task -> {
                                if(task.isSuccessful()){
                                    List<Pessoa> pessoaList = new ArrayList<>();
                                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                        pessoaList.add(documentSnapshot.toObject(Pessoa.class));
                                    }
                                    ShowDataActivity.resultado = "";
                                    for(Pessoa p : pessoaList){
                                        ShowDataActivity.resultado += p.toString() + "\n";
                                    }
                                    initFragmentShowData();
                                }
                            });
                } else {
                    Toast.makeText(ShowDataActivity.this, "Insira uma faixa salarial e uma quantidade de filhos", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    private void initFragmentShowData() {
        showDataFragment = new ShowDataFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.showDataContainer, showDataFragment);
        transaction.commit();
    }
}
