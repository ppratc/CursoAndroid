package com.curso.sergio.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;


public class SpinnerActivity extends AppCompatActivity {

    Spinner miSpinner;
    ArrayAdapter<CharSequence> miAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        //Guardo la referencia al elemento de la lista en mi atributo miSpinner
        miSpinner = (Spinner)findViewById(R.id.spinner);

        //Creo el Adpater
        //Primer parámetro: Contexto
        //Segundo parámetro: array o agrupación de elementos que mostrará la lista
        //Tercer parámetro: layout que especifica cómo se verá el spinner
        miAdapter = ArrayAdapter.createFromResource(this, R.array.cosas_aprendidas, android.R.layout.simple_spinner_item);


        // Layout que especifica cómo se verá cada elemento
        miAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //paso referencia a mi Spinner al atributo miSpinner
        miSpinner.setAdapter(miAdapter);



        //Implemento acción a hacer al seleccionar elemento
        miSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                // muestra elemento seleccionado con un Toast
                Toast.makeText(getApplicationContext(),
                        ((TextView) view).getText(), Toast.LENGTH_LONG).show();

                if(pos==0){
                    Toast.makeText(getApplicationContext(),
                            "Primer elemento de la lista", Toast.LENGTH_SHORT).show();
                }

                if(pos==miSpinner.getCount()-1){
                    Toast.makeText(getApplicationContext(),
                            "Último elemento de la lista",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


}
