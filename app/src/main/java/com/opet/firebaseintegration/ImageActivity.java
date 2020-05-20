package com.opet.firebaseintegration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.sql.Timestamp;

public class ImageActivity extends AppCompatActivity {

    private static final String TAG = ImageActivity.class.getSimpleName();
    private ImageView imagePicked;
    private File imageFile;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imagePicked = findViewById(R.id.imagePicked);
    }

    @Override
    protected void onStart() {
        super.onStart();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void selecionarImagem(View view) {
        ImagePicker.Companion.with(this)
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
//            Log.wtf(TAG, data.get);
            imagePicked.setImageURI(uri);
            imageFile = ImagePicker.Companion.getFile(data);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Tarefa cancelada", Toast.LENGTH_LONG).show();
        }
    }

    public void uploadFile(View view) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userPath = "images/" + firebaseUser.getUid() + "/";
        StorageReference imageRef = storageReference.child(userPath + "image_" + new Timestamp(System.currentTimeMillis()).getTime() + ".png");
        UploadTask uploadTask = imageRef.putFile(Uri.fromFile(imageFile));

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ImageActivity.this, "Falha no upload da imagem", Toast.LENGTH_LONG);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(ImageActivity.this, "Upload concluido com sucesso", Toast.LENGTH_LONG).show();
            }
        });
    }
}
