package com.curso.sergio.detectorluz;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetectorLuz extends AppCompatActivity implements
        SensorEventListener {

    private static final int UPDATE_THRESHOLD = 500;
    private SensorManager mSensorManager;
    private Sensor mLuz;


    private TextView mLuzView;
    private long mLastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        mLuzView = (TextView) findViewById(R.id.valor_real_luz);

        // Get reference to SensorManager
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

       mLuz = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

       if (null == mLuz)
            finish();
    }



    // Register listener
    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mLuz,
                SensorManager.SENSOR_DELAY_UI);

        mLastUpdate = System.currentTimeMillis();

    }

    // Unregister listener
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    // Process new reading
    @Override
    public void onSensorChanged(SensorEvent event) {


        if(event.sensor.getType() == Sensor.TYPE_LIGHT){

            long actualTime = System.currentTimeMillis();

            if (actualTime - mLastUpdate > UPDATE_THRESHOLD) {

                mLastUpdate = actualTime;

                float luz = event.values[0];

                mLuzView.setText(String.valueOf(luz));

            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Implementación obligatoria cuando defino un SensorEventListener
    }
}



