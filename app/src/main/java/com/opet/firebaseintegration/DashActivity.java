package com.opet.firebaseintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView textWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        firebaseAuth = FirebaseAuth.getInstance();
        textWelcome = findViewById(R.id.textWelcome);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        textWelcome.setText("Bem-vindo, " + user.getEmail());
    }

    public void sair(View view){
        firebaseAuth.signOut();
        Intent inicio = new Intent(DashActivity.this, MainActivity.class);
        startActivity(inicio);
        finish();
    }
}
