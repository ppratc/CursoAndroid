package com.curso.sergio.broadcastdinamico;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BroadcastDinamicoActivity extends AppCompatActivity {

    //Definiendo mi Intent personalizado
    private static final String CUSTOM_INTENT = "course.examples.BroadcastReceiver.show_toast";

    //Creando el Intent Filter con la action correspondiente
    private final IntentFilter mi_intentFilter = new IntentFilter(CUSTOM_INTENT);

    //Creando un objeto de tipo MiReceiver()
    private final MiReceiver mi_receiver = new MiReceiver();

    //Variable con un LocalBroadcastManager
    private LocalBroadcastManager mBroadcastMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Obtenemos un objeto de tipo BroadcastManager con el contexto de mi aplicación
        mBroadcastMgr = LocalBroadcastManager
                .getInstance(getApplicationContext());
        //Registro el Receiver dinámicamente con el Receiver e Intent Filter adecuados
        mBroadcastMgr.registerReceiver(mi_receiver, mi_intentFilter);

        setContentView(R.layout.activity_broadcast_dinamico);

        Button button = (Button) findViewById(R.id.boton_receiver);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Envío el Intent del receiver registrado
                mBroadcastMgr.sendBroadcast(new Intent(CUSTOM_INTENT));
            }
        });
    }

    @Override
    protected void onPause() {
        //Borro el registro del Receiver
        mBroadcastMgr.unregisterReceiver(mi_receiver);
        super.onPause();
    }
}
