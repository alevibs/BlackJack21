package org.example.blackjack21.model;

import java.util.Random;

public class Mazo {

    private Carta[] cartas= new Carta[52];
    private String[] palo= {"P","C","D","T"};
    private String[] rango= {"2","3","4","5","6","7","8","9","10","J","Q","K","As"};

    private int siguiente=0;

    public Mazo() {
        int contador = 0;
        for (String p : palo) {
            for (String r : rango) {
                cartas[contador] = new Carta(p ,r);
                contador=contador+1;
            }
        }
    }
    public void barajar() {
        Random azar=new Random();
        for (int i=51; i>0;i--){
            int j = azar.nextInt(1+i);
            Carta temporal=cartas[i];
            cartas[i]=cartas[j];
            cartas[j]=temporal;
        }
    }

    public int cartasRestantes(){
        return 52-siguiente;
    }

    public Carta sacarCarta()
    {
        if (siguiente==52){
            return null;
        }
        else {
            Carta carta=cartas[siguiente];
            siguiente++;
            return carta;
        }
    }
    public void reiniciar(){
        barajar();
        siguiente=0;
    }
}
