package com.curso.sergio.broadcastestatico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BroadcastEstaticoActivity extends AppCompatActivity {

    //Defino el nombre de la action de mi Intent personalizado
    private static final String CUSTOM_INTENT = "com.curso.sergio.BroadcastEstatico.show_toast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_estatico);

        Button button = (Button) findViewById(R.id.boton_receiver);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hago un Broadcast del Intent
                sendBroadcast(new Intent(CUSTOM_INTENT),
                        android.Manifest.permission.VIBRATE);
            }
        });

    }
}
