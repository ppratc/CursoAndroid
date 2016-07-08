package com.curso.sergio.broadcastdinamico;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class MiReceiver extends BroadcastReceiver {

    private final String TAG = "MiReceiver";

    public MiReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i(TAG, "INTENT RECIBIDO");

        //Cuando recibo el Intent hago vibrar el dispositivo
        Vibrator v = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);

        //Y muestro un Toast que identifica que se ha recibido el Intent
        Toast.makeText(context, "INTENT RECIBIDO por MiReceiver dinámico", Toast.LENGTH_LONG).show();

        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
