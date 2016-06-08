package com.curso.sergio.listviews;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class CambiosActivity extends AppCompatActivity {

    EditText nombre;
    NumberPicker posicion;
    TextView mTVnumberView;
    int elementos;
    int pos=1;
    boolean anade= true;

    Intent result;

    static final String ELEMENTO_FINAL = "Final";
    static final String ELEMENTO_POS = "Posicion";
    static final String ELIMINA_POS = "Elimina en pos";
    static final String ELIMINA_TODO = "Elimina todo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

        nombre = (EditText)findViewById(R.id.editTextCosas);
        posicion = (NumberPicker) findViewById(R.id.numberPicker);

        mTVnumberView = (TextView)findViewById(R.id.textView2);

        Intent elementIntent = getIntent();
        elementos = elementIntent.getIntExtra("elementos",0);
        result = new Intent();


        posicion.setMinValue(1);
        posicion.setMaxValue(elementos);
        posicion.setWrapSelectorWheel(true);
        posicion.setOnValueChangedListener( new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
                pos = newVal;
            }
        });

    }

    public void anade_final(View view){

        anade=true;
       nombre.setVisibility(View.VISIBLE);

       result.putExtra("operacion",ELEMENTO_FINAL);

    }

    public void anade_pos(View view){

        anade=true;
        nombre.setVisibility(View.VISIBLE);
        posicion.setVisibility(View.VISIBLE);
        posicion.setMaxValue(elementos+1);
        mTVnumberView.setVisibility(View.VISIBLE);

        result.putExtra("operacion",ELEMENTO_POS);


    }

    public void elimina_elemento(View view){

        anade=false;
        posicion.setVisibility(View.VISIBLE);
        mTVnumberView.setVisibility(View.VISIBLE);

        result.putExtra("operacion",ELIMINA_POS);

    }

    public void elimina_todo(View view){

        anade=false;
        result.putExtra("operacion",ELIMINA_TODO);

    }


    public void vuelve(View view){

        String elemento = nombre.getText().toString();

        if(elemento.equals("") && anade){
            Toast.makeText(getApplicationContext(),
                    "Introducido elemento sin nombre", Toast.LENGTH_SHORT).show();
        }

        result.putExtra("elemento",elemento);
        result.putExtra("posicion",pos);

        setResult(Activity.RESULT_OK,result);
        finish();

    }
}
