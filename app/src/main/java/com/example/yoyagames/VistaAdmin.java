package com.example.yoyagames;

import android.os.Bundle;

import androidx.activity.ComponentActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class VistaAdmin extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vistaadmin);

        ConstraintLayout constraintLayout = findViewById(R.id.clayout);

    }
}
