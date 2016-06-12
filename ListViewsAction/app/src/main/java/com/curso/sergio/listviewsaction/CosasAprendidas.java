package com.curso.sergio.listviewsaction;


/**
 * Created by usuario on 06/06/2016.
 */
public class CosasAprendidas {

    //Atributos de cada uno de los elementos de la lista
    private String cosaAprendida;
    private String aprendido;
    private int imagenCosaAprendida;

    //Constructor que deja los valores que definimos en los atributos de la clase CosasAprendidas
    public CosasAprendidas(String cosaAprendida, String aprendido, int imagenCosaAprendida){

        //Los atributos con this son los de la clase
        //los atributos sin this son los parámetros del constructor
        this.cosaAprendida = cosaAprendida;
        this.aprendido = aprendido;
        this.imagenCosaAprendida = imagenCosaAprendida;

    }

    //métodos set para cambiar los valores de los atributos de la clase
    public void setCosaAprendida(String cosaAprendida){
        this.cosaAprendida = cosaAprendida;
    }


    public void setAprendido(String aprendido){
        this.aprendido = aprendido;
    }

    public void setImagenCosaAprendida(int imagenCosaAprendida){
        this.imagenCosaAprendida = imagenCosaAprendida;
    }

    //métodos get para obtener los valores de los atributos de la clase
    public String getCosaAprendida(){        return cosaAprendida;   }

    public String getAprendido(){return aprendido;}
    public int getImagenCosaAprendida(){return imagenCosaAprendida;}

}
