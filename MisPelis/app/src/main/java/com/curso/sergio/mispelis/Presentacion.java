package com.curso.sergio.mispelis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Presentacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Creo el View con el layout activity_presentacion
        setContentView(R.layout.activity_presentacion);

        // Accedo al botón creado para cargar la Activity IntroDatos que permite entrar datos por teclado
        Button button = (Button) findViewById(R.id.button);

        // Implemento el método que define los que hacemos al presionar el botón
        // El Listener espera eventos del Botón
        button.setOnClickListener(new View.OnClickListener() {

            // el método onClick nos dice la acción a realizar cuando hay un clic
            @Override
            public void onClick(View v) {


                // Para ello debo crear un Intent
                Intent llamadaIntroDatos = new Intent(getApplicationContext(),IntroDatos.class);
                // y arranco el Intent
                startActivity(llamadaIntroDatos);


            }
        });


        
    }
}
