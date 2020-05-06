package com.opet.firebaseintegration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CadTwoActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText editTitulo, editDescricao, editValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_two);

        editTitulo = findViewById(R.id.editTitulo);
        editDescricao = findViewById(R.id.editDescricao);
        editValor = findViewById(R.id.editValor);
    }

    @Override
    protected void onStart() {
        super.onStart();
        db = FirebaseFirestore.getInstance();
    }

    public void registrarDadosVenda(View view) {
        String titulo = editTitulo.getText().toString();
        String descricao = editDescricao.getText().toString();
        double valor = Double.parseDouble(editValor.getText().toString());

        Venda venda = new Venda(titulo, descricao,valor);

        db.collection("vendas").add(venda).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                String message = "Venda cadastrada com sucesso";
                Toast.makeText(CadTwoActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = "Erro ao cadastrar venda";
                Toast.makeText(CadTwoActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
