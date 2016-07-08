package com.curso.sergio.listviews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class ListViewActivity extends AppCompatActivity{

    static final int CAMBIOS_LISTA = 1;

    static final String ELEMENTO_FINAL = "Final";
    static final String ELEMENTO_POS = "Posicion";
    static final String ELIMINA_POS = "Elimina en pos";
    static final String ELIMINA_TODO = "Elimina todo";


    //Creo atributos para apuntar a la lista y al Adapter
    ListView miLista;
    ArrayAdapter miAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //Guardo la referencia al elemento de la lista en mi atributo miLista
        miLista = (ListView) findViewById(R.id.miLista);


        //Creo el Adpater
        //Primer parámetro: Contexto
        //Segundo parámetro: layout que especifica cómo se verá cada elemento de la lista
        //Tercer parámetro: array o agrupación de elementos que mostrará la lista
        // miAdapter = new ArrayAdapter<String>(getApplicationContext(),
        //         android.R.layout.simple_list_item_activated_1,
        //         getResources().getStringArray(R.array.cosas_aprendidas));


        //Inicializar el adaptador con la fuente de datos
        miAdapter = new MiArrayAdapter(this, DatosLista.COSAS);

        //paso referencia a mi listview al atributo miLista
        miLista.setAdapter(miAdapter);

        //Filtrar resultados con tecleo
        //miLista.setTextFilterEnabled(true);


        //Implemento acción a hacer al seleccionar elemento
        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CosasAprendidas cosaActual = (CosasAprendidas) miAdapter.getItem(position);

                // muestra elemento seleccionado con un Toast
                Toast.makeText(getApplicationContext(),
                        cosaActual.getCosaAprendida().toString(), Toast.LENGTH_LONG).show();

                if (position == 0) {
                    Toast.makeText(getApplicationContext(),
                            "Primer elemento de la lista", Toast.LENGTH_SHORT).show();
                }

                if (position == miLista.getCount() - 1) {
                    Toast.makeText(getApplicationContext(),
                            "Último elemento de la lista", Toast.LENGTH_SHORT).show();
                }


            }

        });


    }


    public void cambios_lista(View view){

        Intent cambios = new Intent(this,CambiosActivity.class);
        cambios.putExtra("elementos",miAdapter.getCount());
        startActivityForResult(cambios,CAMBIOS_LISTA);
    }


    //Método que se ejecuta cuando recibimos el resultado del Intent creado para esperar resultado
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Comprobar que estamos recibiendo el RequestCode esperado
        if (requestCode == CAMBIOS_LISTA) {
            // Comprobar que hay un resultado correcto
            if (resultCode == RESULT_OK) {

                String operacion = data.getStringExtra("operacion");


                if(operacion.equals(ELEMENTO_FINAL)) {
                    String elemento = data.getStringExtra("elemento");
                    miAdapter.add(new CosasAprendidas(elemento, "¿Aprendido?", R.drawable.android_1));
                }

                if (operacion.equals(ELEMENTO_POS)) {
                    String elemento = data.getStringExtra("elemento");
                    int pos = data.getIntExtra("posicion",0);
                    miAdapter.insert(new CosasAprendidas(elemento, "¿Aprendido?", R.drawable.android_1),pos-1);
                }

                if (operacion.equals(ELIMINA_POS)) {
                    int pos = data.getIntExtra("posicion",0);
                    miAdapter.remove(miAdapter.getItem(pos-1));
                }

                if (operacion.equals(ELIMINA_TODO)) {
                    miAdapter.clear();
                }
            }

        }



    }




}
