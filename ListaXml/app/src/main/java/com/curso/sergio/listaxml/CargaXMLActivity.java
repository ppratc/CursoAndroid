package com.curso.sergio.listaxml;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.List;

import android.net.http.AndroidHttpClient;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

public class CargaXMLActivity extends ListActivity {

        private static final String URL =
                "http://www.xiruca.com/sites/xiruca.com/files/track/Vall_Madriu.gpx";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            new HttpGetTask().execute(URL);
        }


    //La clase HttpGetTask usa el String URL para cargar los datos y los guarda en una lista de Strings
    private class HttpGetTask extends AsyncTask<String, Void, List<String>> {

            //Creo el cliente de tipo AndroidHttpClient con newInstance
            AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

            //En Background creo la consulta a mi URL y gestiono los datos recibidos de la misma con
            //mi XMLResponseHandler
            @Override
            protected List<String> doInBackground(String... params) {
                HttpGet request = new HttpGet(params[0]);
                XMLResponseHandler responseHandler = new XMLResponseHandler();
                try {
                    //Ejecuto una request, manejada por mi responsehandler, que retorna una lista de Strings
                    return mClient.execute(request, responseHandler);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            //Ejecutado el proceso en BackGround y devuelta la lista de Strings, cierro la conexió
            //Después creo el Adapter de mi ListView
            @Override
            protected void onPostExecute(List<String> result) {
                if (null != mClient)
                    mClient.close();
                setListAdapter(new ArrayAdapter<String>(
                        CargaXMLActivity.this,
                        R.layout.activity_lista_xml, result));
            }
    }
}
