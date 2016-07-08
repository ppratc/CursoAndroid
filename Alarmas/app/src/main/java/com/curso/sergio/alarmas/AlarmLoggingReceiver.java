package com.curso.sergio.alarmas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class AlarmLoggingReceiver extends BroadcastReceiver {

    private static final String TAG = "AlarmLoggingReceiver";

    public AlarmLoggingReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // Pongo en el Log la hora a la que se registra la alarma
        Toast.makeText(context,"Alarma registrada en LoggingRec:" + DateFormat.getDateTimeInstance().format(new Date()), Toast.LENGTH_LONG).show();
        Log.i(TAG,"Alarma registrada en:" + DateFormat.getDateTimeInstance().format(new Date()));
        //throw new UnsupportedOperationException("Not yet implemented");
    }

}
