package org.example.blackjack21.model;

import java.util.ArrayList;

public class Mazo {

    private ArrayList <Carta> mazo= new ArrayList<>();
    String[] palo= {"P","C","D","T"};
    String[] rango= {"2","3","4","5","6","7","8","9","J","Q","K","As"};

    public void crear_Mazo(){
        for (String palo: palo)
        {
            palo.add();
            for (String rango:rango)
            {
                rango.add();

            }
        }
    }



}
