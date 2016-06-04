package com.curso.sergio.viewgroups;

import android.app.ListActivity;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class grupos extends AppCompatActivity{

    //Atributos para RadioGroup
    RadioButton radioBayern;
    RadioButton radioBarca;
    RadioButton radioOtro;
    TextView ganadorChampions;
    OnClickListener radioListener;

    //Atributos para WebView
    WebView varWebView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);

        radioBayern = (RadioButton)findViewById(R.id.radioButton);
        radioBarca = (RadioButton)findViewById(R.id.radioButton2);
        radioOtro = (RadioButton)findViewById(R.id.radioButton3);

        ganadorChampions = (TextView)findViewById(R.id.textViewGanador);

        //Crear Objecto que hace referencia a nuestro webView
        varWebView = (WebView) findViewById(R.id.webView);

        //Creo radioListener para referirme al radiobutton escogido
        radioListener = new OnClickListener(){
            @Override
            public void onClick(View v) {
                RadioButton rbEscogido = (RadioButton) v;
                ganadorChampions.setText("El ganador será " + rbEscogido.getText());

                //Toast.makeText(getApplicationContext(),"El ganador será " + rbEscogido.getText(),2000).show();
            }
        };

        radioBayern.setOnClickListener(radioListener);
        radioBarca.setOnClickListener(radioListener);
        radioOtro.setOnClickListener(radioListener);




        // Uso la clase WebViewClient para ver páginas web
        // cargo una URL específica en el webView
        varWebView.setWebViewClient(new WebViewClient());

      //  varWebView.getSettings().setJavaScriptEnabled(true);
        varWebView.loadUrl("http://www.google.com");
        


    }



    //Método que nos permite la navegación atrás dentro del WebView
  //  @Override
  //  public boolean onKeyDown(int keyCode, KeyEvent event) {
  //      if ((keyCode == KeyEvent.KEYCODE_BACK) && varWebView.canGoBack()) {
  //          varWebView.goBack();
  //          return true;
  //      }
  //      return super.onKeyDown(keyCode, event);
  //  }
}
