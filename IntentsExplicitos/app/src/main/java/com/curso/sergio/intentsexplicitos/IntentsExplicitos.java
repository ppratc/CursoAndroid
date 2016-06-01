package com.curso.sergio.intentsexplicitos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentsExplicitos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents_explicitos);


        // Accedo al botón creado para cargar la ActivityDos
        Button botonCargaElSilencio = (Button) findViewById(R.id.button_silencio);

        // Implemento el método que define los que hacemos al presionar el botón
        // El Listener espera eventos del Botón
        botonCargaElSilencio.setOnClickListener(new View.OnClickListener() {

            // el método onClick nos dice la acción a realizar cuando hay un clic
            @Override
            public void onClick(View v) {


                // Para ello debo crear un Intent
                Intent llamadaSilencio = new Intent(getApplicationContext(),ElSilencio.class);
                // y arrancar el Intent
                startActivity(llamadaSilencio);


            }
        });

        Button botonCargaMatrix = (Button) findViewById(R.id.button_matrix);

        // Implemento el método que define los que hacemos al presionar el botón
        // El Listener espera eventos del Botón
        botonCargaMatrix.setOnClickListener(new View.OnClickListener() {

            // el método onClick nos dice la acción a realizar cuando hay un clic
            @Override
            public void onClick(View v) {


                // Para ello debo crear un Intent
                Intent llamadaMatrix = new Intent(getApplicationContext(),Matrix.class);
                // y arrancar el Intent
                startActivity(llamadaMatrix);


            }
        });

        Button botonCargaElRenacido = (Button) findViewById(R.id.button_renacido);

        // Implemento el método que define los que hacemos al presionar el botón
        // El Listener espera eventos del Botón
        botonCargaElRenacido.setOnClickListener(new View.OnClickListener() {

            // el método onClick nos dice la acción a realizar cuando hay un clic
            @Override
            public void onClick(View v) {

                // Llamo a la Activity Dos

                // Para ello debo crear un Intent
                Intent llamadaRenacido = new Intent(getApplicationContext(),ElRenacido.class);
                // y arrancar el Intent
                startActivity(llamadaRenacido);


            }
        });

    }
}
