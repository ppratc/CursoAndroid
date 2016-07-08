package com.curso.sergio.cargaimagennotification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationSubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sub);

        TextView texto = (TextView) findViewById(R.id.textView1);

       //Recibo el boolean de la otra clase que me indica si el archivo está cargado o no
       Intent recibo = getIntent();

        boolean cargado=false;
        if(recibo !=null) {
            cargado = recibo.getBooleanExtra("CARGADO",false);
        }

        //Si el archivo se ha cargado, cambio el texto que muestra el estado de carga en la Activity
        if(cargado){texto.setText("Cargado");}
    }
}
