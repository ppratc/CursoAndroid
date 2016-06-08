package com.curso.sergio.listviews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 06/06/2016.
 */
public class DatosLista {

    static List<CosasAprendidas> COSAS = new ArrayList<CosasAprendidas>();
    static{
        COSAS.add(new CosasAprendidas("Plataforma Android","¿Aprendido?",
                R.drawable.android_1));
        COSAS.add(new CosasAprendidas("Uso del emulador","¿Aprendido?",
                R.drawable.android_2));
        COSAS.add(new CosasAprendidas("Layouts básicos","¿Aprendido?",
                R.drawable.phone));
        COSAS.add(new CosasAprendidas("Uso de botones","¿Aprendido?",
                R.drawable.android_1));
        COSAS.add(new CosasAprendidas("Intents implícitos","¿Aprendido?",
                R.drawable.android_2));

    }
}
