package com.curso.sergio.concurrenciaasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ConcurrenciaAsyncTaskActivity extends AppCompatActivity {

    private ImageView miView;
    private int retardo = 5000;
    private ProgressBar miProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concurrencia_async_task);


        miView = (ImageView) findViewById(R.id.imageView);
        miProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        final Button loadButton = (Button) findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CargaImagen().execute(R.drawable.vall_madriu);
            }
        });

        final Button otherButton = (Button) findViewById(R.id.otherButton);
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConcurrenciaAsyncTaskActivity.this, "Estoy cargando la imagen",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CargaImagen extends AsyncTask<Integer, Integer, Bitmap> {

        @Override
        protected void onPreExecute() {
            miProgressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Integer... resId) {
            Bitmap tmp = BitmapFactory.decodeResource(getResources(), resId[0]);
            // Simulando operación costosa en tiempo
            for (int i = 1; i < 11; i++) {
                sleep();
                publishProgress(i * 10);
            }
            return tmp;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            miProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            miProgressBar.setVisibility(ProgressBar.INVISIBLE);
            miView.setImageBitmap(result);
        }

        private void sleep() {
            try {
                Thread.sleep(retardo);
            } catch (InterruptedException e) {
                Log.e("AsyncTask", e.toString());
            }
        }
    }
}


