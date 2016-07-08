package com.curso.sergio.acelerometro;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Acelerometro extends AppCompatActivity implements
        SensorEventListener{

    private static final int UPDATE_THRESHOLD = 500;
    private SensorManager mSensorManager;
    private Sensor mAcelerometro;

    private TextView mXValueView, mYValueView, mZValueView;
    private long mLastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelometro);

        mXValueView = (TextView) findViewById(R.id.valor_real_x);
        mYValueView = (TextView) findViewById(R.id.valor_real_y);
        mZValueView = (TextView) findViewById(R.id.valor_real_z);

        // Get reference to SensorManager
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get reference to Accelerometer
        mAcelerometro = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

       // || null == mTemperatura || null == mHumedad
        if (null == mAcelerometro) {
            Log.i("Faltan sensores", "Falta acelerómetero");
            Toast.makeText(getApplicationContext(), "Falta acelerómetero",
                    Toast.LENGTH_LONG).show();
        }

    }



        // Register listener
        @Override
        protected void onResume() {
            super.onResume();

            mSensorManager.registerListener(this, mAcelerometro,
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

            /*if(event.sensor.getType() == Sensor.TYPE_LIGHT){

                long actualTime = System.currentTimeMillis();

                if (actualTime - mLastUpdate > UPDATE_THRESHOLD) {

                    mLastUpdate = actualTime;

                    float presion = event.values[0];

                    mPresionView.setText(String.valueOf(presion));
                }*/

            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

                long actualTime = System.currentTimeMillis();

                if (actualTime - mLastUpdate > UPDATE_THRESHOLD) {

                    mLastUpdate = actualTime;

                    float x = event.values[0], y = event.values[1], z = event.values[2];

                    mXValueView.setText(String.valueOf(x));
                    mYValueView.setText(String.valueOf(y));
                    mZValueView.setText(String.valueOf(z));

                }

            }



        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // N/A
        }
}
