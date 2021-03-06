package com.curso.sergio.concurrenciaviewpost;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ConcurrenciaViewPostActivity extends AppCompatActivity {

    private Bitmap miImagen;
    private ImageView miView;
    private int retardo = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concurrencia_view_post);


        miView = (ImageView) findViewById(R.id.imageView);

        final Button loadButton = (Button) findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargaImagen();
            }
        });

        final Button otherButton = (Button) findViewById(R.id.otherButton);
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConcurrenciaViewPostActivity.this, "Estoy cargando la imagen",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargaImagen() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(retardo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                miImagen = BitmapFactory.decodeResource(getResources(), R.drawable.vall_madriu);

                miView.post(new Runnable() {
                    @Override
                    public void run() {

                        miView.setImageBitmap(miImagen);
                    }
                });

            }
        }).start();
    }

}
