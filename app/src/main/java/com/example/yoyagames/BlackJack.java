package com.example.yoyagames;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;


public class BlackJack extends ComponentActivity {
    ImageView plantarse1;
    ImageView plantarse2;
    ImageView fondoOscuro;
    ImageView cogerCarta;
    TextView valorTotal1;
    TextView valorTotal2;
    TextView valorTotalCrupier;
    TextView cuentaAtras;
    TextView texto1;
    static boolean turnoJ1 = true;
    static int suma1, suma2, suma3;
    static ArrayList<String> manoJ1 = new ArrayList<>();
    static ArrayList<String> manoJ2 = new ArrayList<>();
    static ArrayList<String> manoCrupier = new ArrayList<>();
    static int posicionManoJ1 = 2;
    static int posicionManoJ2 = 2;
    static int posicionManoCrupier = 1;
    static ArrayList<ImageView> listaImagesJ1 = new ArrayList<>();
    static ArrayList<ImageView> listaImagesJ2 = new ArrayList<>();
    static ArrayList<ImageView> listaImagesCrupier = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blackjack);

        cuentaAtras = findViewById(R.id.cuentaAtras);
        texto1 = findViewById(R.id.tww);

        valorTotal1 = findViewById(R.id.valorTotal1);
        valorTotal2 = findViewById(R.id.valorTotal2);
        valorTotalCrupier = findViewById(R.id.valorTotalCrupier);

        int[] j1Cartas = {R.id.card1j1, R.id.card2j1, R.id.card3j1, R.id.card4j1, R.id.card5j1};
        for (int id : j1Cartas) {
            ImageView carta = findViewById(id);
            listaImagesJ1.add(carta);
        }
        int[] j2Cartas = {R.id.card1j2, R.id.card2j2, R.id.card3j2, R.id.card4j2, R.id.card5j2};
        for (int id : j2Cartas) {
            ImageView carta = findViewById(id);
            listaImagesJ2.add(carta);
        }

        int[] crupierCartas = {R.id.cardCrupier1, R.id.cardCrupier2, R.id.cardCrupier3, R.id.cardCrupier4, R.id.cardCrupier5, R.id.cardCrupier6, R.id.cardCrupier7};
        for (int i = 0; i < crupierCartas.length; i++) {
            ImageView img = findViewById(crupierCartas[i]);
            listaImagesCrupier.add(img);
        }

        cogerCarta = findViewById(R.id.cogerCarta);

        plantarse1 = findViewById(R.id.plantarseJ1);
        plantarse2 = findViewById(R.id.plantarseJ2);

        fondoOscuro = findViewById(R.id.fondooscuro);



        plantarse1.setClickable(false);
        plantarse2.setClickable(false);
        cogerCarta.setClickable(false);

        new CountDownTimer(6000, 1000) {
            public void onTick(long millisUntilFinished) {
                cuentaAtras.setText(Integer.toString( Integer.parseInt(cuentaAtras.getText().toString()) -1 ));
            }
            public void onFinish() {
                empezarPartida();
            }
        }.start();
    }

    private void empezarPartida() {

        texto1.setVisibility(View.INVISIBLE);
        cuentaAtras.setVisibility(View.INVISIBLE);
        fondoOscuro.setVisibility(View.INVISIBLE);
        ArrayList<String> baraja = new ArrayList<>();


        String[] palos = {"D", "H", "C", "S"};
        String[] numeros = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String palo : palos) {
            for (String numero : numeros) {
                baraja.add(numero + palo);
            }
        }

        Collections.shuffle(baraja);
        for (int i = 0; i < 5; i++) {
            manoJ1.add(baraja.get(i));
        }
        for (int i = 5; i < 10; i++) {
            manoJ2.add(baraja.get(i));
        }
        for (int i = 10; i < 17; i++) {
            manoCrupier.add(baraja.get(i));
        }

        Toast.makeText(getApplicationContext(), "Reparto de cartas", Toast.LENGTH_SHORT).show();

        //ESTABLECER BARAJA Y PUNTUACION INICIAL JUGADOR 1
        String imageUrl11 = "https://deckofcardsapi.com/static/img/" + manoJ1.get(0) + ".png";
        Picasso.get().load(imageUrl11).into(listaImagesJ1.get(0));

        String imageUrl21 = "https://deckofcardsapi.com/static/img/" + manoJ1.get(1) + ".png";
        Picasso.get().load(imageUrl21).into(listaImagesJ1.get(1));
        int num1 = devolverValorCarta(manoJ1.get(0));
        int num2 = devolverValorCarta(manoJ1.get(1));
        suma1 = num1 + num2;
        valorTotal1.setText(Integer.toString(suma1));


        //ESTABLECER BARAJA Y PUNTUACION INICIAL JUGADOR 2
        String imageUrl12 = "https://deckofcardsapi.com/static/img/" + manoJ2.get(0) + ".png";
        Picasso.get().load(imageUrl12).into(listaImagesJ2.get(0));

        String imageUrl22 = "https://deckofcardsapi.com/static/img/" + manoJ2.get(1) + ".png";
        Picasso.get().load(imageUrl22).into(listaImagesJ2.get(1));
        int num3 = devolverValorCarta(manoJ2.get(0));
        int num4 = devolverValorCarta(manoJ2.get(1));
        suma2 = num3 + num4;
        valorTotal2.setText(Integer.toString(suma2));


        //ESTABLECER BARAJA Y PUNTUACION INICIAL CRUPIER
        String imageCrupier1 = "https://deckofcardsapi.com/static/img/" + manoCrupier.get(0) + ".png";
        Picasso.get().load(imageCrupier1).into(listaImagesCrupier.get(0));

        int num5 = devolverValorCarta(manoCrupier.get(0));
        suma3 = num5;
        valorTotalCrupier.setText(Integer.toString(suma3));


        new Handler().postDelayed(() -> {
            validarGanador2();
            Toast.makeText(getApplicationContext(), "Turno Jugador 1", Toast.LENGTH_LONG).show();
            cogerCarta.setVisibility(View.VISIBLE);
            cogerCarta.setClickable(true);
            plantarse1.setClickable(true);
        }, 4000);
    }

    public void turno(View view) {
        if (turnoJ1) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                dialogo.setTitle("¿Quieres coger otra carta?");
                dialogo.setMessage("Juégatela venga");
                dialogo.setCancelable(false);
                dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int id) {
                        String imageNueva = "https://deckofcardsapi.com/static/img/" + manoJ1.get(posicionManoJ1) + ".png";
                        Picasso.get().load(imageNueva).into(listaImagesJ1.get(posicionManoJ1));

                        listaImagesJ1.get(posicionManoJ1).setVisibility(View.VISIBLE);
                        suma1 = suma1 + devolverValorCarta(manoJ1.get(posicionManoJ1));
                        valorTotal1.setText(Integer.toString(suma1));

                        for (int i = 0; i < manoJ1.size(); i++) {
                            if (manoJ1.get(i).charAt(0) == 'A') {
                                suma1 = suma1 - 10;
                            }
                        }

                        if (suma1 > 21) {

                            Toast.makeText(getApplicationContext(), "Perdiste! Turno Jugador 2", Toast.LENGTH_LONG).show();
                            plantarse1.setClickable(false);
                            valorTotal1.setTextColor(Color.RED);
                            turnoJ1 = false;
                            plantarse2.setClickable(true);
                        }
                        if (suma1 == 21) {
                            validarGanador();
                        }
                        posicionManoJ1++;
                    }

                });
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int id) {

                    }
                });

                dialogo.show();
        } else {

                AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                dialogo.setTitle("¿Quieres coger otra carta?");
                dialogo.setMessage("Juégatela venga");
                dialogo.setCancelable(false);
                dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        String imageNueva = "https://deckofcardsapi.com/static/img/" + manoJ2.get(posicionManoJ2) + ".png";
                        Picasso.get().load(imageNueva).into(listaImagesJ2.get(posicionManoJ2));
                        listaImagesJ2.get(posicionManoJ2).setVisibility(View.VISIBLE);
                        suma2 = suma2 + devolverValorCarta(manoJ2.get(posicionManoJ2));
                        valorTotal2.setText(Integer.toString(suma2));

                        for (int i = 0; i < manoJ2.size(); i++) {
                            if (suma2 > 21 && manoJ2.get(i).charAt(0) == 'A') {
                                suma2 = suma2 - 10;
                            }
                        }
                        if (suma2 > 21) {
                           turnoCrupier();
                        }
                        if (suma2 == 21) {
                            validarGanador();
                        }
                        posicionManoJ2++;
                    }
                });
                dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogo.show();

        }
    }

    private int devolverValorCarta(String carta) {
        int num;
        switch (carta.charAt(0)) {
            case 'J':
            case 'Q':
            case 'K':
                num = 10;
                break;
            case 'A':
                num = 11;
                break;
            default:
                num = Character.getNumericValue(carta.charAt(0));
        }
        return num;
    }
    private void validarGanador2() {
        if (suma1 == 21){
            jugardeNuevo("GANADOR J1");
        }else if(suma2 ==21){
            jugardeNuevo("GANADOR J2");
        }
    }
    private void validarGanador() {

        String texto = "";
        if ((suma1 > suma2 && suma1 > suma3) || suma1 <= 21) {
            texto = "GANADOR J1";
            jugardeNuevo(texto);
        } else if ((suma2 > suma1 && suma1 > suma3) || suma2 <= 21) {
            texto = "GANADOR J2";
            jugardeNuevo(texto);
        } else if ((suma3 > suma1 && suma3 > suma2) || suma3 <= 21) {
            texto = "GANA EL CRUPIER";
            jugardeNuevo(texto);
        } else {
            texto = "EMPATE !";
            jugardeNuevo(texto);
        }

    }
    public void jugardeNuevo(String texto){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle(texto);
        dialogo.setMessage("¿Quereis jugar de nuevo?");
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo.show();
    }
    public void plantarse1(View view){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("¿Estás seguro que quieres plantarte?");
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Toast.makeText(getApplicationContext(), "Turno Jugador 2", Toast.LENGTH_LONG).show();
                plantarse2.setClickable(true);
                turnoJ1 = false;
            }

        });
        dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo.show();
    }
    public void plantarse2(View view){
      turnoCrupier();
    }

    private void turnoCrupier(){
        cogerCarta.setClickable(false);
        plantarse2.setClickable(false);
        Toast.makeText(getApplicationContext(), "TURNO CRUPIER", Toast.LENGTH_LONG).show();
        while (suma3 <= 16){
            String imageCrupier1 = "https://deckofcardsapi.com/static/img/" + manoCrupier.get(posicionManoCrupier) + ".png";
            Picasso.get().load(imageCrupier1).into(listaImagesCrupier.get(posicionManoCrupier));
            listaImagesCrupier.get(posicionManoCrupier).setVisibility(View.VISIBLE);
            suma3 += devolverValorCarta(manoCrupier.get(posicionManoCrupier));
            valorTotalCrupier.setText(String.valueOf(suma3));
            posicionManoCrupier++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (suma3 > 21) {
            valorTotalCrupier.setTextColor(Color.RED);
        }

        new Handler().postDelayed(() -> validarGanador(), 2000);
    }
}
