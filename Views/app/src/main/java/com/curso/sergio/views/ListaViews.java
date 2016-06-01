package com.curso.sergio.views;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ListaViews extends AppCompatActivity {

    TextView TvBoton;
    ToggleButton toggleButton;
    CheckBox checkbox;
    RelativeLayout rel_lay;
    Switch switch1;
    RatingBar puntuacion;
    TextView puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_views);

        rel_lay = (RelativeLayout) findViewById(R.id.rel_Layout);
        TvBoton = (TextView)findViewById(R.id.textView_boton);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        checkbox = (CheckBox) findViewById(R.id.checkBox);
        switch1 = (Switch)findViewById(R.id.switch1);
        puntuacion = (RatingBar) findViewById(R.id.ratingBar);
        puntos = (TextView)findViewById(R.id.textView_puntos);

        toggleButton.setOnClickListener(new View.OnClickListener() {
        // el método onClick nos dice la acción a realizar cuando hay un clic

            @Override
            public void onClick(View v) {

                // Acción realizada cuando se genera el evento de pulsar el botón
                if(toggleButton.isChecked()){

                    //Acción a realizar cuando el estado es verificado
                    rel_lay.setBackgroundColor(Color.YELLOW);

                }else{

                    //Acción a realizar cuando el estado es NO verificado
                    rel_lay.setBackgroundColor(Color.BLACK);

                }

            }
        });

        checkbox.setOnClickListener(new View.OnClickListener() {
            // el método onClick nos dice la acción a realizar cuando hay un clic

            @Override
            public void onClick(View v) {

                // Acción realizada cuando se genera el evento de pulsar el botón
                if(checkbox.isChecked()){

                    //Acción a realizar cuando el estado es verificado
                    checkbox.setText("Estoy verificado");

                }else{

                    //Acción a realizar cuando el estado es NO verificado
                    checkbox.setText("No estoy verificado");

                }

            }
        });

        switch1.setOnClickListener(new View.OnClickListener() {
            // el método onClick nos dice la acción a realizar cuando hay un clic

            @Override
            public void onClick(View v) {

                // Acción realizada cuando se genera el evento de pulsar el botón
                if(switch1.isChecked()){

                    //Acción a realizar cuando el estado es verificado
                    rel_lay.setBackgroundColor(Color.RED);

                }else{

                    //Acción a realizar cuando el estado es NO verificado
                    rel_lay.setBackgroundColor(Color.GREEN);

                }

            }
        });

        puntuacion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            // Llamado cuando el usuario modifica la puntuación
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                puntos.setText("La puntuación otorgada es:" + rating);
                if(rating == puntuacion.getNumStars()){
                    puntuacion.setVisibility(View.INVISIBLE);
                }
            }
        });

    }


    public void prueba(View v){

        TvBoton.setText("Probado");
    }
}
