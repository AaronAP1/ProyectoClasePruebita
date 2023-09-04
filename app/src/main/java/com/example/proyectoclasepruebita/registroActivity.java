package com.example.proyectoclasepruebita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class registroActivity extends AppCompatActivity {

    Button volver;
    Button insertar;

    EditText user, contra;

    FirebaseAuth mAuth;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        user = findViewById(R.id.txtregistrouser);
        contra = findViewById(R.id.txtasd);
        insertar = findViewById(R.id.btnregistro);
        mAuth = FirebaseAuth.getInstance();


        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Useremail = user.getText().toString().trim();
                String Usercontra = contra.getText().toString().trim();

                if (Useremail.isEmpty() && Usercontra.isEmpty()){
                    Toast.makeText(registroActivity.this, "Ingrese Datos a Registrar", Toast.LENGTH_SHORT).show();
                } else {
                    registroUser(Useremail, Usercontra);
                }



            }
        });



        volver = findViewById(R.id.btnvolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent(registroActivity.this, loginActivity.class);
                startActivities(new Intent[]{is});

            }
        });


    }


    private void registroUser(String Useremail, String Usercontra){
        mAuth.createUserWithEmailAndPassword(Useremail, Usercontra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent is = new Intent(registroActivity.this, loginActivity.class);
                    startActivities(new Intent[]{is});
                    finish();

                }else{

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(registroActivity.this, "Error Conexion Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }
}