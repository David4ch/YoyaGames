package com.example.yoyagames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VistaLogin extends ComponentActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText email;
    EditText password;
    Button login;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vistalogin);

        email = findViewById(R.id.entryCorreo1);
        password = findViewById(R.id.entryPassword1);
        login = findViewById(R.id.loginbutton);
    }
    public void logear(View v){
        String mail=String.valueOf(email.getText());
        String contrasena=String.valueOf(password.getText());
        mAuth.signInWithEmailAndPassword(mail, contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Inicio de sesión exitoso
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(mail.equals("admin@gmail.com")){
                                cambiarAdmin();
                            }else{
                                cambiar();
                            }
                            // Redirige a la actividad principal u otra actividad
                        } else {
                            // Error en el inicio de sesión
                            Toast.makeText(VistaLogin.this, "Error de inicio de sesión", Toast.LENGTH_SHORT).show();
                            Toast.makeText(VistaLogin.this, mail, Toast.LENGTH_SHORT).show();
                            Toast.makeText(VistaLogin.this, contrasena, Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void cambiarAdmin() {
        Intent intent = new Intent(VistaLogin.this, VistaAdmin.class);
        startActivity(intent);
        finish();
    }

    public void cambiar() {
        Intent intent = new Intent(VistaLogin.this, VistaPrincipal.class);
        startActivity(intent);
        finish();
    }



}
