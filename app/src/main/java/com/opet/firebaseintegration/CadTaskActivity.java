package com.opet.firebaseintegration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CadTaskActivity extends AppCompatActivity {

    private EditText editTitle;
    private FirebaseFirestore db;
    private RadioGroup radioGroup;
    private String selectedPriority;
    private String creationDate;
    private Spinner categorySpinner;
    private String taskCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_task);

        editTitle = findViewById(R.id.editTitle);

        categorySpinner = findViewById(R.id.categorySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(CadTaskActivity.this, R.array.categoryArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        db = FirebaseFirestore.getInstance();
    }

    public void saveTask(View view) {
        getCreationDate();
        getSelectedPriority();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String taskTitle = editTitle.getText().toString();
        taskCategory = categorySpinner.getSelectedItem().toString();
        CadTask cadTask = new CadTask(creationDate, taskTitle, selectedPriority, taskCategory, user.getUid());

        DocumentReference newTask = db.collection("tasks").document();

        newTask.set(cadTask).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                String message = "Task cadastrada com sucesso";
                Toast.makeText(CadTaskActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = "Erro ao cadastrar Task";
                Toast.makeText(CadTaskActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        finish();
    }

    private void getCreationDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        creationDate = dateFormat.format(date);
    }

    private void getSelectedPriority() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupPriority);
        selectedPriority = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
    }
}
