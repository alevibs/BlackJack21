package org.example.blackjack21.model;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {

    private ArrayList <Carta> mazo= new ArrayList<>();
    String[] palo= {"P","C","D","T"};
    String[] rango= {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

    public void crearMazo(){
        for (String p: palo)
        {
            for (String r:rango){
                mazo.add(new Carta(p,r));}
        }
    }

    public void barajar() {
        Collections.shuffle(mazo);
    }

    public int cantidadCartas(){
        return mazo.size();
    }

    public Carta sacarCarta()
    {
        if (mazo.isEmpty()){
                return null;
            }
        return mazo.remove(0);

    }

}
