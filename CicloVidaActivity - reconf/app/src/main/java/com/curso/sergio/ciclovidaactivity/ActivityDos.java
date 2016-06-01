package com.curso.sergio.ciclovidaactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


// ActivityDos es la Activity cargada desde el botón de ActivityUno
public class ActivityDos extends AppCompatActivity {

    // Usamos claves para almacenar los contadores de llamadas a cada método
    private static final String RESTART_KEY = "restart";
    private static final String RESUME_KEY = "resume";
    private static final String START_KEY = "start";
    private static final String CREATE_KEY = "create";

    // Defino TAG (Etiqueta) para el Logcat de esta Activity
    private final static String TAG = "Lab-ActivityDos";

    // Creo los contadores de ejecución de cada método, llamados
    // mCreate, mRestart, mStart and mResume
    // que contarán llamadas a onCreate(), onRestart(), onStart() y
    // onResume(). No deben ser definidas como static porque deberán modificarse
    int mCreate = 0;
    int mRestart = 0;
    int mStart = 0;
    int mResume = 0;

    // Deberemos incrementar el contador cada vez que el método
    // correspondiente sea llamado

    // Creamos variables para cada TextView
    // los llamamos mTvCreate, mTvRestart, mTvStart, mTvResume.
    // para mostrar el número actual de llamadas hechas
    TextView mTvCreate;
    TextView mTvRestart;
    TextView mTvStart;
    TextView mTvResume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Siempre tengo que hacer esta llamada en el onCreate()
        //Crea Activity y recupera estados anteriores
        super.onCreate(savedInstanceState);
        //Defino el layout activity_uno.xml como el layout de esta activity
        setContentView(R.layout.activity_dos);


        // Accedo a los TextView creados para mostrar los resultados
        // llamando al método findViewById() de la clase Activity
        mTvCreate = (TextView) findViewById(R.id.create);
        mTvRestart = (TextView) findViewById(R.id.restart);
        mTvStart = (TextView) findViewById(R.id.start);
        mTvResume = (TextView) findViewById(R.id.resume);

        // Accedo al botón creado para cargar la ActivityDos
        Button botonCierreActivityDos = (Button) findViewById(R.id.bCerrarActivityDos);

        // Implemento el método que define los que hacemos al presionar el botón
        // El Listener espera eventos del Botón
        botonCierreActivityDos.setOnClickListener(new View.OnClickListener() {

            // el método onClick nos dice la acción a realizar cuando hay un clic
            @Override
            public void onClick(View v) {

                // finalizo la activity
                finish();


            }
        });

        // Recupero valores guardados si los había
        if (savedInstanceState != null) {

            // Uso método getInt() de la clase savedInstanceState
            mCreate = savedInstanceState.getInt(CREATE_KEY);
            mRestart = savedInstanceState.getInt(RESTART_KEY);
            mResume = savedInstanceState.getInt(RESUME_KEY);
            mStart = savedInstanceState.getInt(START_KEY);

        }

        // Mensaje LogCat de método onCreate()
        Log.i(TAG, "Estamos en el método onCreate()");

        // Incrementa el contador de llamadas a onCreate()
        // Cambia la pantalla mostrando los nuevos valores
        mCreate++;
        muestraContadores();

    }


    @Override
    public void onStart() {
        super.onStart();

        // Mensaje LogCat de método onStart()
        Log.i(TAG, "Estamos en el método onStart()");

        // Incrementa el contador de llamadas a onStart()
        // Cambia la pantalla mostrando los nuevos valores
        mStart++;
        muestraContadores();


    }

    @Override
    public void onResume() {
        super.onResume();

        // Mensaje LogCat de método onResume()
        Log.i(TAG, "Estamos en el método onResume()");

        // Incrementa el contador de llamadas a onResume()
        // Cambia la pantalla mostrando los nuevos valores
        mResume++;
        muestraContadores();

    }

    @Override
    public void onPause() {
        super.onPause();

        // Mensaje LogCat de método onPause()
        Log.i(TAG, "Estamos en el método onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();

        // Mensaje LogCat de método onStop()
        Log.i(TAG, "Estamos en el método onStop()");
    }

    @Override
    public void onRestart() {
        super.onRestart();

        // Mensaje LogCat de método onRestart()
        Log.i(TAG, "Estamos en el método onRestart()");

        // Incrementa el contador de llamadas a onRestart()
        // Cambia la pantalla mostrando los nuevos valores
        mRestart++;
        muestraContadores();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Mensaje LogCat de método onDestroy()
        Log.i(TAG, "Estamos en el método onDestroy()");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Guarda los contadores de veces que hemos entrado a los estados
        // Uso el método putInt y la clave creada al inicio
        savedInstanceState.putInt(CREATE_KEY, mCreate);
        savedInstanceState.putInt(RESTART_KEY, mRestart);
        savedInstanceState.putInt(RESUME_KEY, mResume);
        savedInstanceState.putInt(START_KEY, mStart);

    }

    // Actualizo los contadores en pantalla
    public void muestraContadores() {

        mTvCreate.setText("Llamadas a onCreate(): " + mCreate);
        mTvStart.setText("Llamadas a onStart(): " + mStart);
        mTvResume.setText("Llamadas a onResume(): " + mResume);
        mTvRestart.setText("Llamadas a onRestart(): " + mRestart);
    }
}
