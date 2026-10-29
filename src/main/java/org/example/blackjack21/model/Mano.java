package org.example.blackjack21.model;
import java.util.LinkedList;

public class Mano {

    private LinkedList <Carta> cartas= new LinkedList<> ();

    public void agregarCarta(Carta carta)
    {
        cartas.add(carta);
    }

    public int cantidadCartas()
    {
        return cartas.size();
    }

    public LinkedList<Carta> getCartas() {
        return cartas;
    }

    public int calcularPuntaje() {
        int puntaje = 0;
        int cantidadAs = 0;

        for (Carta carta : cartas) {
            puntaje += carta.getValor();

            if (carta.getRango().equals("As")) {
                cantidadAs++;
            }

        }

        while (cantidadAs > 0 && puntaje + 10 <= 21) {
            puntaje += 10;
            cantidadAs--;
        }

        return puntaje;
    }

    public void limpiar() {
        cartas.clear();
    }
}
