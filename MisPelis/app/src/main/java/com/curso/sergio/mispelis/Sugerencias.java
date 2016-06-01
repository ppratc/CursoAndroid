package com.curso.sergio.mispelis;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class  Sugerencias extends AppCompatActivity {

    //Declaro variables que guardarán el TextView a mostrar por pantalla y la URL que queremos abrir
    TextView mTvTextoAMostrar;
    static private final String URL = "http://www.filmaffinity.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Creo el View con el layout activity_sugerencias
        setContentView(R.layout.activity_sugerencias);

        //Creo un objeto de tipo TextView asociado al TextView sugerencias del layout activity_sugerencias
        mTvTextoAMostrar = (TextView) findViewById(R.id.sugerencias);

        // Accedo al botón creado para cargar la página web de Film Affinity
        Button buttonBrowser = (Button) findViewById(R.id.buttonBrowser);

        //Creo un Intent para rescatar los String pasados de la anterior activity
        Intent i = getIntent();

        //Accedo a los datos pasados por la Activity IntroDatos
        String nombre = i.getStringExtra("nombre");
        String genero = i.getStringExtra("genero");

        //Defino el texto que mostraré en pantalla usando los datos pasados por la Activity IntroDatos
        mTvTextoAMostrar.setText("Hola " + nombre + ", tu género preferido es " + genero +
                ", por lo que te recomendamos la siguiente web: www.filmaffinity.com");


        // Implemento el método que define los que hacemos al presionar el botón
        // El Listener espera eventos del Botón
        buttonBrowser.setOnClickListener(new View.OnClickListener() {

            // el método onClick nos dice la acción a realizar cuando hay un clic
            @Override
            public void onClick(View v) {

                //Creo un objeto de tipo URI con la URL de la página a visitar
                Uri webFilmAffinity = Uri.parse(URL);

                //Creo un Intent implícito que realice una acción de tipo ACTION_VIEW visitando
                //la web especificada por la URL guardada en webFilmAffinity
                Intent intentVerPagina = new Intent(Intent.ACTION_VIEW, webFilmAffinity);
                startActivity(intentVerPagina);



            }
        });

    }
}
