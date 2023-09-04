package com.example.proyectoclasepruebita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class CargaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent is = new Intent(CargaActivity.this, loginActivity.class);
                startActivities(new Intent[]{is});
                finish();
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 5000);
    }
}