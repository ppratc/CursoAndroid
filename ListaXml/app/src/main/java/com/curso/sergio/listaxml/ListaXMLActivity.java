package com.curso.sergio.listaxml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListaXMLActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_xml);

        //creo botón de carga en implemento dentro el acceso a la Activity ListaXMLActivity

        final Button boton_carga = (Button) findViewById(R.id.boton_carga);
        boton_carga.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaXMLActivity.this,
                        CargaXMLActivity.class));
            }
        });


    }
}
