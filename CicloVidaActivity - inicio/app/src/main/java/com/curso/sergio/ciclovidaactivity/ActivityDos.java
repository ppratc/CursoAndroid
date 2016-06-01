package com.curso.sergio.ciclovidaactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


// ActivityDos es la Activity cargada desde el botón de ActivityUno
public class ActivityDos extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Siempre tengo que hacer esta llamada en el onCreate()
        //Crea Activity y recupera estados anteriores
        super.onCreate(savedInstanceState);
        //Defino el layout activity_uno.xml como el layout de esta activity
        setContentView(R.layout.activity_dos);



        // Accedo al botón creado para cargar la ActivityDos
        Button botonCierreActivityDos = (Button) findViewById(R.id.bCerrarActivityDos);

        // Implemento el método que define los que hacemos al presionar el botón
        // El Listener espera eventos del Botón
        botonCierreActivityDos.setOnClickListener(new View.OnClickListener() {

            // el método onClick nos dice la acción a realizar cuando hay un clic
            @Override
            public void onClick(View v) {

                // finalizo la activity
                finish();


            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();



    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onRestart() {
        super.onRestart();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }



}
