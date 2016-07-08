package com.curso.sergio.alarmas;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmasActivity extends AppCompatActivity {

    private AlarmManager mAlarmManager;
    private Intent mNotificationReceiverIntent, mLoggerReceiverIntent;
    private PendingIntent mNotificationReceiverPendingIntent,
            mLoggerReceiverPendingIntent;
    private static final long INITIAL_ALARM_DELAY = 10 * 1000L;
    protected static final long JITTER = 2000L;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alarmas);

        // Obtener el servicio AlarmManager
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Creando el Intent para mandar al AlarmNotificationReceiver
        mNotificationReceiverIntent = new Intent(AlarmasActivity.this,
                AlarmNotificationReceiver.class);

        // Creando un PendingIntent que usa el NotificationReceiverIntent
        mNotificationReceiverPendingIntent = PendingIntent.getBroadcast(
                AlarmasActivity.this, 0, mNotificationReceiverIntent, 0);

        // Creando el Intent para mandar al AlarmLoggingReceiver
        mLoggerReceiverIntent = new Intent(AlarmasActivity.this,
                AlarmLoggingReceiver.class);

        // Creando un PendingIntent que usa el mLoggerReceiverPendingIntent
        mLoggerReceiverPendingIntent = PendingIntent.getBroadcast(
                AlarmasActivity.this, 0, mLoggerReceiverIntent, 0);

        // Configura botón de alarma simple
        final Button singleAlarmButton = (Button) findViewById(R.id.single_alarm_button);
        if (singleAlarmButton != null) {
            singleAlarmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    // Configura llamada a NotificationReceiver con alarma simple
                    // La alarma se ejecuta siempre, aunque el dispositivo esté durmiendo
                    /*mAlarmManager.set(AlarmManager.RTC_WAKEUP,
                            System.currentTimeMillis() + INITIAL_ALARM_DELAY,
                            mNotificationReceiverPendingIntent);*/

                    mAlarmManager.set(AlarmManager.ELAPSED_REALTIME,
                            SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY,
                            mNotificationReceiverPendingIntent);


                    // Configura llamada a LoggerReceiver poco después de la alarma anterior
                    // La alarma se ejecuta siempre, aunque el dispositivo esté durmiendo
                    /*mAlarmManager.set(AlarmManager.RTC_WAKEUP,
                            System.currentTimeMillis() + INITIAL_ALARM_DELAY
                                    + JITTER, mLoggerReceiverPendingIntent);*/

                    mAlarmManager.set(AlarmManager.ELAPSED_REALTIME,
                            SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY + JITTER,
                            mLoggerReceiverPendingIntent);

                    // Mostrar Toast personalizado
                    Toast mitoast = new Toast(getApplicationContext());
                    mitoast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    mitoast.setDuration(Toast.LENGTH_LONG);
                    mitoast.setView(getLayoutInflater().inflate(R.layout.mi_toast,null));
                    mitoast.show();

                }
            });
        }

        // Configura botón de alarma con repetición
        final Button repeatingAlarmButton = (Button) findViewById(R.id.repeating_alarm_button);
        repeatingAlarmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Configura llamada a NotificationReceiver con alarma con repetición
                // La alarma se ejecuta sólo con el dispositivo activo
                mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                        SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY,
                        INITIAL_ALARM_DELAY*3,
                        mNotificationReceiverPendingIntent);

                // Configura LoggerReceiver poco después de la alarma anterior
                // La alarma se ejecuta sólo con el dispositivo activo
                mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                        SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY
                                + JITTER,
                        INITIAL_ALARM_DELAY*3,
                        mLoggerReceiverPendingIntent);

                // Muestra Toast
                Toast.makeText(getApplicationContext(), "Configuro alarmas con repetición",
                        Toast.LENGTH_LONG).show();
            }
        });

        // Configura botón de alarma con repetición inexacta
        // La repetición inexacta no se ejecuta automáticamente en el momento que le toca, es más flexible
        // y se puede ejecutar después
        final Button inexactRepeatingAlarmButton = (Button) findViewById(R.id.inexact_repeating_alarm_button);
        inexactRepeatingAlarmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Configura llamada a NotificationReceiver con alarma con repetición inexacta
                // La alarma se ejecuta sólo con el dispositivo activo
                mAlarmManager.setInexactRepeating(
                        AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + INITIAL_ALARM_DELAY,
                        INITIAL_ALARM_DELAY*3,
                        mNotificationReceiverPendingIntent);

                // Configura LoggerReceiver poco después de la alarma anterior
                // La alarma se ejecuta sólo con el dispositivo activo
                mAlarmManager.setInexactRepeating(
                        AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + INITIAL_ALARM_DELAY
                                + JITTER,
                        INITIAL_ALARM_DELAY*3,
                        mLoggerReceiverPendingIntent);

                Toast.makeText(getApplicationContext(),
                        "Configuro alarmas con repetición inexactas", Toast.LENGTH_LONG)
                        .show();
            }
        });

        // Cancelar alarmas repetidas
        final Button cancelRepeatingAlarmButton = (Button) findViewById(R.id.cancel_repeating_alarm_button);
        cancelRepeatingAlarmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Cancelar alarmas repetidas con mNotificationReceiverPendingIntent
                mAlarmManager.cancel(mNotificationReceiverPendingIntent);

                // Cancelar alarmas repetidas conmLoggerReceiverPendingIntent
                mAlarmManager.cancel(mLoggerReceiverPendingIntent);

                // Muestra Toast
                Toast.makeText(getApplicationContext(),
                        "Cancelo alarmas repetidas", Toast.LENGTH_LONG).show();
            }
        });
    }
}
