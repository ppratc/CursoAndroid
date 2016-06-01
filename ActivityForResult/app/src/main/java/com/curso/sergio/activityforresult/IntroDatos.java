package com.curso.sergio.activityforresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IntroDatos extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Creo el View con el layout activity_intro_datos
        setContentView(R.layout.activity_intro_datos);

        // Accedo al botón creado para pasar de nuevo a la Activity inicial
        Button buttonFinalizar = (Button) findViewById(R.id.button_finalizar);

        // Implemento el método que define los que hacemos al presionar el botón
        // El Listener espera eventos del Botón
        buttonFinalizar.setOnClickListener(new View.OnClickListener() {

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


                // Creao un nuevo Intent que almacena y pasa mis datos a la Activity que espera resultado
                Intent sugerencias = new Intent();

                // Almaceno los datos en el Intent con el método putExtra
                sugerencias.putExtra("nombre",texto);
                sugerencias.putExtra("genero",genero);

                // Paso el resultado a la Activity que me ha llamado
                // El código RESULT_OK es un atributo de la función Activity y sugerencias es el Intent
                // que paso con los datos almacenados
                setResult(Activity.RESULT_OK, sugerencias);
                finish();

            }
        });

    }

}
