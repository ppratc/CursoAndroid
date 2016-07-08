package com.curso.sergio.concurrencia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ConcurrenciaActivity extends AppCompatActivity {

    private Bitmap miImagen;
    private ImageView miView;
    private int retardo = 5000;

    private static final String CUSTOM_INTENT = "com.curso.sergio.BroadcastEstatico.show_toast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concurrencia);


        miView = (ImageView) findViewById(R.id.imageView);

            final Button loadButton = (Button) findViewById(R.id.loadButton);
            loadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cargaImagen();
                    if (null != miImagen) {
                        miView.setImageBitmap(miImagen);
                    }
                }
            });

            final Button otherButton = (Button) findViewById(R.id.otherButton);
            otherButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendBroadcast(new Intent(CUSTOM_INTENT),
                            android.Manifest.permission.VIBRATE);
                    Toast.makeText(ConcurrenciaActivity.this, "Estoy cargando la imagen",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void cargaImagen() {
            try {
                Thread.sleep(retardo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            miImagen = BitmapFactory.decodeResource(getResources(), R.drawable.vall_madriu);
        }
}

