package com.curso.sergio.listviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class ListViewActivity extends AppCompatActivity {

    //Creo atributos para apuntar a la lista y al Adapter
    ListView miLista;
    ArrayAdapter<String> miAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //Guardo la referencia al elemento de la lista en mi atributo miLista
        miLista = (ListView)findViewById(R.id.miLista);

        //Creo el Adpater
        //Primer parámetro: Contexto
        //Segundo parámetro: layout que especifica cómo se verá cada elemento de la lista
        //Tercer parámetro: array o agrupación de elementos que mostrará la lista
        miAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_activated_1,
                getResources().getStringArray(R.array.cosas_aprendidas));


        //paso referencia a mi listview al atributo miLista
        miLista.setAdapter(miAdapter);

        //Filtrar resultados con tecleo
        miLista.setTextFilterEnabled(true);


        //Implemento acción a hacer al seleccionar elemento
        miLista.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // muestra elemento seleccionado con un Toast
                Toast.makeText(getApplicationContext(),
                        ((TextView) view).getText(), Toast.LENGTH_LONG).show();

                if(position==0){
                    Toast.makeText(getApplicationContext(),
                        "Primer elemento de la lista", Toast.LENGTH_SHORT).show();
                }

                if(position==miLista.getCount()-1){
                    Toast.makeText(getApplicationContext(),
                            "Último elemento de la lista",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

}
