package com.curso.sergio.activityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class Inicio extends AppCompatActivity {

    //Declaro variables que guardarán el TextView a mostrar por pantalla y el REQUEST_CODE del intent
    TextView mTvTextoAMostrar;
    static final int PIDE_DATOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Creo el View con el layout activity_inicio
        setContentView(R.layout.activity_inicio);

        //Creo el View al TextView del texto a mostrar en pantalla
        //Inicialmente lo he definido como un texto en blanco, para no ver nada hasta tener resultado
        mTvTextoAMostrar = (TextView) findViewById(R.id.sugerencias);

        // Accedo al botón creado para cargar la ActivityDos
        Button button = (Button) findViewById(R.id.button);

        // Implemento el método que define los que hacemos al presionar el botón
        // El Listener espera eventos del Botón
        button.setOnClickListener(new View.OnClickListener() {

            // el método onClick nos dice la acción a realizar cuando hay un clic
            @Override
            public void onClick(View v) {


                // Para ello debo crear un Intent que me pase a la Activity IntroDatos de manera explícita
                Intent llamadaIntroDatos = new Intent(getApplicationContext(),IntroDatos.class);
                // y arrancar el Intent esperando resultados
                // Mi requestCode es PIDE_DATOS, definido al inicio de esta Activity
                startActivityForResult(llamadaIntroDatos,PIDE_DATOS);


            }
        });



    }

    //Método que se ejecuta cuando recibimos el resultado del Intent creado para esperar resultado
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Comprobar que estamos recibiendo el RequestCode esperado
        if (requestCode == PIDE_DATOS) {
            // Comprobar que hay un resultado correcto
            if (resultCode == RESULT_OK) {
                String nombre = data.getStringExtra("nombre");
                String genero = data.getStringExtra("genero");

                // Configurar el texto a mostrar en pantalla
                mTvTextoAMostrar.setText("Hola " + nombre + ", tu género preferido es " + genero +
                        ", por lo que te recomendamos la siguiente web: www.filmaffinity.com");
            }
        }

    }
}
