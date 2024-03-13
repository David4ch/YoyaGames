package com.example.yoyagames;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.ComponentActivity;

public class VistaBienvenida extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vistabienvenida);

    }
    public void irAInicio(View view){
        Intent intent = new Intent(VistaBienvenida.this,VistaPrincipal.class);
        startActivity(intent);
        finish();
    }
    public void irARegistro(View view){
        Intent intent = new Intent(VistaBienvenida.this, VistaRegistro.class);
        startActivity(intent);
        finish();
    }
}
