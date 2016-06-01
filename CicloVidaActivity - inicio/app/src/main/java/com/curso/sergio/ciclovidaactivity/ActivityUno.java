package com.curso.sergio.ciclovidaactivity;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.TextView;


// ActivityUno es la Activity cargada por defecto
public class ActivityUno extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Siempre tengo que hacer esta llamada en el onCreate()
        //Crea Activity y recupera estados anteriores
        super.onCreate(savedInstanceState);
        //Defino el layout activity_uno.xml como el layout de esta activity
        setContentView(R.layout.activity_uno);


        // Accedo al botón creado para cargar la ActivityDos
        Button botonCargaActivityDos = (Button) findViewById(R.id.bCargaActivityDos);

        // Implemento el método que define los que hacemos al presionar el botón
        // El Listener espera eventos del Botón
        botonCargaActivityDos.setOnClickListener(new OnClickListener() {

            // el método onClick nos dice la acción a realizar cuando hay un clic
            @Override
            public void onClick(View v) {

                // Llamo a la Activity Dos

                // Para ello debo crear un Intent
                Intent llamadaActivityDos = new Intent(getApplicationContext(),ActivityDos.class);
                // y arrancar el Intent
                startActivity(llamadaActivityDos);


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
