package com.curso.sergio.listviewsaction;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompatBase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class ListViewAction extends AppCompatActivity{

    static final int CAMBIOS_LISTA = 1;

    static final String ELEMENTO_FINAL = "Final";
    static final String ELEMENTO_POS = "Posicion";
    static final String ELIMINA_POS = "Elimina en pos";
    static final String ELIMINA_TODO = "Elimina todo";

    static final String URL = "http://developer.android.com";


    //Creo atributos para apuntar a la lista y al Adapter
    ListView miLista;
    ArrayAdapter miAdapter;
    Toolbar myToolbar;

    CosasAprendidas cosaActual;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        //Accedemos al objeto toolbar
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


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


        //Asignando el menú contextual a un view determinado
        registerForContextMenu(miLista);

        //Filtrar resultados con tecleo
        //miLista.setTextFilterEnabled(true);


        //Implemento acción a hacer al seleccionar elemento
        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cosaActual = (CosasAprendidas) miAdapter.getItem(position);

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
                String elemento = data.getStringExtra("elemento");
                int pos = data.getIntExtra("posicion",0);

                switch(operacion){
                    case ELEMENTO_FINAL:
                        miAdapter.add(new CosasAprendidas(elemento, "¿Aprendido?", R.drawable.android_1));
                        break;
                    case ELEMENTO_POS:
                        miAdapter.insert(new CosasAprendidas(elemento, "¿Aprendido?", R.drawable.android_1),pos-1);
                        break;
                    case ELIMINA_POS:
                        miAdapter.remove(miAdapter.getItem(pos-1));
                        break;
                    case ELIMINA_TODO:
                        miAdapter.clear();
                        break;


                }
               /* if(operacion.equals(ELEMENTO_FINAL)) {
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
                }*/
            }

        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.action_bar_menu, menu);
       // getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(menu, v, contextMenuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // Escojo la opción settings
                //Si hay elementos elimino la lista entera
                if(miAdapter.getCount()!=0) {

                    miAdapter.clear();
                    Toast.makeText(getApplicationContext(),
                            R.string.action_settings, Toast.LENGTH_SHORT).show();
                    return true;
                }else{
                    Toast.makeText(getApplicationContext(),
                            "No quedan elementos", Toast.LENGTH_SHORT).show();
                    return true;
                }

            case R.id.action_favorite:
                // Escojo la opción del icono creado
                //Si hay elementos elimino el primer elemento
                if(miAdapter.getCount()!=0) {

                    miAdapter.remove(miAdapter.getItem(0));
                    Toast.makeText(getApplicationContext(),
                            R.string.action_favorite, Toast.LENGTH_SHORT).show();
                    return true;
                }else{
                    Toast.makeText(getApplicationContext(),
                            "No quedan elementos", Toast.LENGTH_SHORT).show();
                    return true;
                }

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //Creando un AdapterView del menú contextual asociado al elemento contextual
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.context_option:
                // El usuario ha escogido la primera opción del menú contextual
                    miAdapter.remove(miAdapter.getItem(info.position));
                    Toast.makeText(getApplicationContext(),
                            "Objeto eliminado", Toast.LENGTH_SHORT).show();
                    return true;


            case R.id.search:
                // El usuario ha escogido la segunda opción del menú contextual
                Uri webBusqueda = Uri.parse(URL);

                //Creo un Intent implícito que realice una acción de tipo ACTION_VIEW visitando
                //la web especificada por la URL guardada en webFilmAffinity
                Intent busca = new Intent(Intent.ACTION_VIEW, webBusqueda);
                startActivity(busca);

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onContextItemSelected(item);

        }
    }

}
