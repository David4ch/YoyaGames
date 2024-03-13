package com.example.yoyagames;

import androidx.activity.ComponentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class VistaPrincipal extends ComponentActivity {
    ViewFlipper flipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vistaprincipal);

        int[] imagenes={R.drawable.carruselblackjack, R.drawable.carruselcaballos, R.drawable.carruselmates, R.drawable.carruselbotones};
        flipper=findViewById(R.id.viewFlipper);

        for(int imagen: imagenes){
            flipperImages(imagen);
        }
        flipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentImagePosition = flipper.getDisplayedChild();

                switch (currentImagePosition) {
                    case 0:
                        // Código para la primera imagen
                        break;
                    case 1:
                        // Código para la segunda imagen
                        break;
                    case 2:
                        cambiar2(v);
                        break;
                    case 3:
                        cambiar3(v);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void flipperImages(int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
    public void cambiar2(View view) {
        Intent intent = new Intent(this, JuegoMates.class);
        startActivity(intent);
    }

    public void cambiar3(View view) {
        Intent intent = new Intent(this, JuegoTiempo.class);
        startActivity(intent);
    }
}