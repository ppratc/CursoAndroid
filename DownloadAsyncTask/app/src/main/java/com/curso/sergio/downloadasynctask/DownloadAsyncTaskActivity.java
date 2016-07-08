package com.curso.sergio.downloadasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadAsyncTaskActivity extends AppCompatActivity {

    private ImageView miView;
    private ProgressBar miProgressBar;
    private String MI_URL = "http://www.xiruca.com/sites/xiruca.com/files/galeria/Vall_Madriu_32.JPG";
    private String TAG = "DownloadAsyncTaskActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_async_task);


        miView = (ImageView) findViewById(R.id.imageView);
        miProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        final Button loadButton = (Button) findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CargaImagen().execute(MI_URL);
            }
        });

        final Button otherButton = (Button) findViewById(R.id.otherButton);
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DownloadAsyncTaskActivity.this, "Estoy cargando la imagen",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



    class CargaImagen extends AsyncTask<String, Integer, File> {


        @Override
        protected void onPreExecute() {
            miProgressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected File doInBackground(String... carga_url) {

            //Creo las variables locales
            HttpURLConnection httpUrlConnection = null;
            int cargados;
            File file=null;

            try {

                //Creo una URL con la dirección escogida para abrir el archivo
                URL url = new URL(carga_url[0]);
                //Creo una conexión HttpURLConnection y me conecto
                HttpURLConnection conection = (HttpURLConnection) url.openConnection();
                conection.connect();
                // Obtengo la longitud del archivo a descargar
                int longitud = conection.getContentLength();;

                // input stream para leer el archivo de tamaño 8k
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                //Obtengo la ruta de los archivos en memoria interna
                String direct =  getApplicationContext().getFilesDir().getPath();

                //creo imagen en la carpeta
                file = new File(direct, "vall_madriu.jpg");



                // Output stream para guardar el fichero
                OutputStream output = new FileOutputStream(file);

                byte data[] = new byte[200024];

                long total = 0;

                while ((cargados = input.read(data)) != -1) {
                    total += cargados;
                    // procesando el fichero
                    // mostrando los progresos
                    publishProgress((int)((total*100)/longitud));

                    // guardando los datos en el stream que nos guarda en el fichero
                    output.write(data, 0, cargados);
                }

                // liberando stream de salida
                output.flush();

                // cerrando ambos streams
                output.close();
                input.close();

            } catch (MalformedURLException exception) {
                Log.e(TAG, "MalformedURLException");
            } catch (IOException exception) {
                Log.e(TAG, "IOException");
            } finally {
                if (null != httpUrlConnection)
                    httpUrlConnection.disconnect();
            }
            return (file);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            miProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(File result) {
            miProgressBar.setVisibility(ProgressBar.INVISIBLE);

            // Poniendo imagen guarda en el UI
            Bitmap bitmap = BitmapFactory.decodeFile(result.getAbsolutePath());
            miView.setImageBitmap(bitmap);
        }

    }
}





