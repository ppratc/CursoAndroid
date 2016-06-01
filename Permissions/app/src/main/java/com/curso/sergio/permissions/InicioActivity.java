package com.curso.sergio.permissions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends Activity {

    // String usado para llamar a la acción peligrosa
    // Debe coincidir con el del Intent Filter de la acción peligrosa
    private static final String ACCION_PELIGROSA = "com.curso.sergio.exploto.ACCION_PELIGROSA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Creo el View con el layout activity_inicio
        setContentView(R.layout.activity_inicio);

        // Accedo al botón creado para inciar la actividad peligrosa
        Button buttonIniciaActPeligrosa= (Button) findViewById(R.id.inicia_act_peligrosa);

        // Implemento el método que define los que hacemos al presionar el botón
        // El Listener espera eventos del Botón
        buttonIniciaActPeligrosa.setOnClickListener(new View.OnClickListener() {
            // el método onClick nos dice la acción a realizar cuando hay un clic
            @Override
            public void onClick(View v) {

                // Creao un nuevo Intent que inicia la App Peligrosa
                Intent intentAccionPeligrosa = new Intent(ACCION_PELIGROSA);
                startActivity(intentAccionPeligrosa);

            }
        });


    }
}
