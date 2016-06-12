package com.curso.sergio.listviewsaction;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by usuario on 06/06/2016.
 */

public class MiArrayAdapter extends ArrayAdapter<CosasAprendidas> {

        public MiArrayAdapter(Context context, List<CosasAprendidas> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){

            //Obteniendo una instancia del inflater
            LayoutInflater inflater = (LayoutInflater)getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //Salvando la referencia del View de la fila
            View listItemView = convertView;

            //Comprobando si el View no existe
            if (null == convertView) {
                //Si no existe, entonces inflarlo con custom_item_list.xml
                listItemView = inflater.inflate(
                        R.layout.custom_item_list,
                        parent,
                        false);
            }

            //Obteniendo instancias de los elementos
            TextView cosa_aprendida = (TextView)listItemView.findViewById(R.id.cosa_aprendida);
            ImageView imagen_cosa_aprendida = (ImageView)listItemView.findViewById(R.id.imagen_cosa_aprendida);
            CheckBox aprendido = (CheckBox) listItemView.findViewById(R.id.checkbox);


            //Obteniendo instancia del elemento CosasAprendidas en la posición actual
            CosasAprendidas item = (CosasAprendidas) getItem(position);

            //Definiendo los valores del elemento a guardar
            cosa_aprendida.setText(item.getCosaAprendida());
            aprendido.setText(item.getAprendido());
            imagen_cosa_aprendida.setImageResource(item.getImagenCosaAprendida());

            //Devolver al ListView la fila creada
            return listItemView;

        }

}
