package com.curso.sergio.cargaimagennotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;


public class CargaImagenNotificationActivity extends AppCompatActivity {

    private ImageView miView;
    private int retardo = 5000;
    private ProgressBar miProgressBar;

    // Notification ID para identificar la notificación
    private static final int MY_NOTIFICATION_ID = 1;

    // Contador de notificaciones
    private int mNotificationCount;


    private final CharSequence tickerText = "Cargando imagen";
    private final CharSequence contentText = "Cargando";


    private NotificationCompat.Builder notificationBuilder;

    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;
    private NotificationManager mNotificationManager;

    private long[] mVibratePattern = { 0, 200, 200, 300 };

    //Este RemoteViews me permitirá crear el layout de la notificación
    RemoteViews mContentView = new RemoteViews(
            "com.curso.sergio.cargaimagennotification",
            R.layout.notificacion);

    private boolean cargado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_imagen_notification);

        miView = (ImageView) findViewById(R.id.imageView);
        miProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Intent que me permite pasar a la NotificationSubActivity
        mNotificationIntent = new Intent(CargaImagenNotificationActivity.this,
                NotificationSubActivity.class);
        //Paso el Intent a un Pending Intent para que pueda ser ejecutado desde fuera de la Activity
        mContentIntent = PendingIntent.getActivity(CargaImagenNotificationActivity.this, 0,
                mNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Obtengo el NotificationManager del sistema de notificación
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

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
                Toast.makeText(CargaImagenNotificationActivity.this, "Estoy cargando la imagen",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CargaImagen extends AsyncTask<Integer, Integer, Bitmap> {

        @Override
        protected void onPreExecute() {
            miProgressBar.setVisibility(ProgressBar.VISIBLE);

            //Configuro el texto a mostrar en la notificación
            mContentView.setTextViewText(R.id.text, contentText + " ("
                    + ++mNotificationCount + ")");

            //Ajusto los parámetros de mi notificación
            notificationBuilder = new NotificationCompat.Builder(CargaImagenNotificationActivity.this)
                    .setTicker(tickerText)
                    .setSmallIcon(android.R.drawable.stat_sys_warning)
                    .setAutoCancel(true)
                    .setContentIntent(mContentIntent)
                    .setVibrate(mVibratePattern)
                    .setContent(mContentView);

            //Le paso a la otra activity el valor cargado=false
            mNotificationIntent.putExtra("CARGADO",cargado);

            //Paso la Notification al NotificationManager:
            mNotificationManager.notify(MY_NOTIFICATION_ID,
                    notificationBuilder.build());
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
            cargado=true;
            miProgressBar.setVisibility(ProgressBar.INVISIBLE);
            miView.setImageBitmap(result);

            //La paso a la NotificationSubActivity el cargado=true para que cambie el textView a Cargado
            mNotificationIntent.putExtra("CARGADO",cargado);

            //Asocio el ContentIntent con mNotificationIntent para poder pasar el Intent
            mContentIntent = PendingIntent.getActivity(CargaImagenNotificationActivity.this, 0,
                    mNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            //Cambio el texto de la notificación
            mContentView.setTextViewText(R.id.text,"¡Cargado!");

            //Envío la nueva notificación
            mNotificationManager.notify(MY_NOTIFICATION_ID,
                    notificationBuilder.setTicker("¡Cargado!").build());
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
