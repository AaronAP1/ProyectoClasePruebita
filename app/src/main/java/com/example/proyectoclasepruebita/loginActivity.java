package com.example.proyectoclasepruebita;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;



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


public class loginActivity extends AppCompatActivity {


    Button iniciar;
    Button registro;
    EditText email, password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.txtuser);
        password = findViewById(R.id.txtcontra);
        iniciar = findViewById(R.id.btnlogin);
        registro = findViewById(R.id.btnregistrarse);
        mAuth = FirebaseAuth.getInstance();


        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EmailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if (EmailUser.isEmpty() && passUser.isEmpty()) {
                    Toast.makeText(loginActivity.this, "Ingrese los Datos", Toast.LENGTH_SHORT).show();

                } else {
                    loginUser(EmailUser, passUser);
                }
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent(loginActivity.this, registroActivity.class);
                startActivities(new Intent[]{is});
                finish();
            }
        });




    }

    private void loginUser(String EmailUser, String passUser) {
        mAuth.signInWithEmailAndPassword(EmailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Intent intent = new Intent(loginActivity.this, MainActivity.class);

                   intent.putExtra("dator", EmailUser);

                    startActivities(new Intent[]{intent});
                    finish();

                }else{

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(loginActivity.this, "Usuario no Encontrado", Toast.LENGTH_SHORT).show();
            }
        });
    }






}
