package org.example.blackjack21.model;

public class Carta {

    private String palo;
    private String rango;

    public Carta(String palo, String rango) {
        this.palo = palo;
        this.rango = rango;
    }

    public String getPalo() {
        return palo;
    }

    public String getRango() {
        return rango;
    }

    @Override
    public String toString() {
        return rango + " de " + palo;
    }

    public int getValor() {
        if (rango.equals("Q") || rango.equals("K") || rango.equals("J")) {
            return 10;
        } else if (rango.equals("As")) {
            return 1;
        } else {
            return Integer.parseInt(rango);
        }

    }
}
