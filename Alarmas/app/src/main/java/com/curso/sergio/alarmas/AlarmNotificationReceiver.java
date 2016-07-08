package com.curso.sergio.alarmas;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class AlarmNotificationReceiver extends BroadcastReceiver {
    public AlarmNotificationReceiver() {
    }

    // Notification ID
    private static final int MY_NOTIFICATION_ID = 1;
    private static final String TAG = "AlarmNotifReceiver";

    // Texto de la notificación
    private final CharSequence tickerText = "Alarma llamada";
    private final CharSequence contentTitle = "Recordatorio";
    private final CharSequence contentText = "Alarma activada!";

    // Intents necesarios para la notificación
    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;

    // Vibración en la recepción de la notificación
    private final long[] mVibratePattern = { 0, 200, 200, 300 };

    @Override
    public void onReceive(Context context, Intent intent) {

        // Intent a llamar cuando clicamos la notificación
        mNotificationIntent = new Intent(context, AlarmasActivity.class);

        // PendingIntent que se encargará de ejecutar el Intent
        mContentIntent = PendingIntent.getActivity(context, 0,
                mNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Creo la Notification
        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setTicker(tickerText)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setAutoCancel(true).setContentTitle(contentTitle)
                .setContentText(contentText).setContentIntent(mContentIntent);

        // Obtengo referencia al NotificationManager
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        // Paso la Notification al NotificationManager:
        mNotificationManager.notify(MY_NOTIFICATION_ID,
                notificationBuilder.build());

        // Log mostrando el envío de la notificación
        Toast.makeText(context,"Alarma registrada en NotifRec en:" + DateFormat.getDateTimeInstance().format(new Date()), Toast.LENGTH_LONG).show();
        Log.i(TAG, "Enviada notificación en:"
                + DateFormat.getDateTimeInstance().format(new Date()));

    }
}
