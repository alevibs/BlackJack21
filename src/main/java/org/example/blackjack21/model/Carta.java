package org.example.blackjack21.model;

public class Carta {

    private String palo;
    private String rango;

    public Carta(String palo, String rango)
    {
        this.palo=palo;
        this.rango=rango;
    }

    public String getPalo()
    {
        return palo;
    }

    public String getRango()
    {
        return rango;
    }

    @Override
    public String toString(){
        return rango + "de" + palo;}
}
