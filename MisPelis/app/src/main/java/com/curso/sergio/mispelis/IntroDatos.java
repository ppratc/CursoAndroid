package com.curso.sergio.mispelis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IntroDatos extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Creo el View con el layout activity_intro_datos
        setContentView(R.layout.activity_intro_datos);

        // Accedo al botón creado para pasar a la Activity Sugerencias
        Button buttonSiguiente = (Button) findViewById(R.id.button_siguiente);

        // Implemento el método que define los que hacemos al presionar el botón
        // El Listener espera eventos del Botón
        buttonSiguiente.setOnClickListener(new View.OnClickListener() {

            //Declaro las variables que se refieren a los EditText que almacenan mis datos
            EditText mTextNombre = (EditText)findViewById(R.id.editTextNombre);
            EditText mTextGenero = (EditText)findViewById(R.id.editTextGenero);


            // el método onClick nos dice la acción a realizar cuando hay un clic
            @Override
            public void onClick(View v) {

                //Guardo en los EditText creados los datos introducidos por teclado
                //Los paso a formato String con el método toString()
                String texto= mTextNombre.getText().toString();
                String genero = mTextGenero.getText().toString();


                // Creo un Intent para pasar a la Activity Sugerencias
                Intent sugerencias = new Intent(getApplicationContext(),Sugerencias.class);

                //Le paso a la nueva Activity los datos guardados con el método putExtra
                sugerencias.putExtra("nombre",texto);
                sugerencias.putExtra("genero",genero);

                // y arrancar el Intent
                startActivity(sugerencias);

            }
        });

    }

}
