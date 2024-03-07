package com.example.yoyagames;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

public class CarreraCaballos extends ComponentActivity {

    Dialog dialog1;
    Button si1;
    Button no1;
    Button botonj1;
    Button botonj2;
    TextView txj1;
    TextView txj2;
    ImageView imgcaballo1;
    ImageView imgcaballo2;
    static int contadorCaballo1 = 0;
    static int contadorCaballo2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.carrerascaballos);
        imgcaballo1 = findViewById(R.id.imgcaballo1);
        imgcaballo2 = findViewById(R.id.imgcaballo2);
        botonj1 = findViewById(R.id.j1);
        botonj2 = findViewById(R.id.j2);
        txj1 = findViewById(R.id.textj1);
        txj2 = findViewById(R.id.textj2);
    }

    public void moverj1(View view) {
        if(contadorCaballo1 <= 15){
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) imgcaballo1.getLayoutParams();
            params.setMargins(params.leftMargin + 30, params.topMargin + 2, params.rightMargin, params.bottomMargin);
            imgcaballo1.setLayoutParams(params);
            contadorCaballo1++;
        }
        if(contadorCaballo1>15 && contadorCaballo1<=30){
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) imgcaballo1.getLayoutParams();
            params.setMargins(params.leftMargin + 30, params.topMargin + 1, params.rightMargin, params.bottomMargin);
            imgcaballo1.setLayoutParams(params);
            contadorCaballo1++;
        }
        if(contadorCaballo1>30 && contadorCaballo1<=55){

        }

        verificarganador();
    }

    public void moverj2(View view) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) imgcaballo2.getLayoutParams();
        params.setMargins(params.leftMargin + 30, params.topMargin, params.rightMargin, params.bottomMargin);
        imgcaballo2.setLayoutParams(params);
        contadorCaballo2++;
        verificarganador();
    }

    private void verificarganador() {
        if (contadorCaballo1 == 55) {

            botonj1.setClickable(false);
            botonj2.setClickable(false);
            dialog1 = new Dialog(CarreraCaballos.this);
            dialog1.setContentView(R.layout.alertdialogcaballo1);
            dialog1.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog1.setCancelable(false);
            si1 = dialog1.findViewById(R.id.btnConfirm);
            no1 = dialog1.findViewById(R.id.btnCancel);

            si1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txj1.setText(String.valueOf(1 + Integer.parseInt(txj1.getText().toString())));

                    botonj1.setClickable(true);
                    botonj2.setClickable(true);

                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) imgcaballo1.getLayoutParams();
                    params.setMargins(params.leftMargin - 1650, params.topMargin, params.rightMargin, params.bottomMargin);
                    imgcaballo1.setLayoutParams(params);

                    ViewGroup.MarginLayoutParams params2 = (ViewGroup.MarginLayoutParams) imgcaballo2.getLayoutParams();
                    params2.setMargins(params2.leftMargin - (30 * contadorCaballo2), params2.topMargin, params2.rightMargin, params2.bottomMargin);
                    imgcaballo2.setLayoutParams(params2);

                    contadorCaballo1 = 0;
                    contadorCaballo2 = 0;

                    dialog1.cancel();
                }
            });

            no1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            dialog1.show();

        } else if (contadorCaballo2 == 55) {
            botonj1.setClickable(false);
            botonj2.setClickable(false);
            AlertDialog.Builder dialogo2 = new AlertDialog.Builder(this, R.style.fondoalertcaballo2);
            dialogo2.setTitle("HA GANADO EL CABALLO NARANJA");
            dialogo2.setMessage("¿Queréis jugar de nuevo?");
            dialogo2.setCancelable(false);
            dialogo2.setPositiveButton("Si !", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogo1, int id) {
                    txj2.setText(String.valueOf(1 + Integer.parseInt(txj2.getText().toString())));


                    botonj1.setClickable(true);
                    botonj2.setClickable(true);

                    ViewGroup.MarginLayoutParams params3 = (ViewGroup.MarginLayoutParams) imgcaballo2.getLayoutParams();
                    params3.setMargins(params3.leftMargin - 1650, params3.topMargin, params3.rightMargin, params3.bottomMargin);
                    imgcaballo2.setLayoutParams(params3);

                    ViewGroup.MarginLayoutParams params4 = (ViewGroup.MarginLayoutParams) imgcaballo1.getLayoutParams();
                    params4.setMargins(params4.leftMargin - (30 * contadorCaballo1), params4.topMargin, params4.rightMargin, params4.bottomMargin);
                    imgcaballo1.setLayoutParams(params4);

                    contadorCaballo1 = 0;
                    contadorCaballo2 = 0;
                }
            });
            dialogo2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogo1, int id) {

                    //Ir al main
                }
            });

            dialogo2.show();
        }
    }


}
