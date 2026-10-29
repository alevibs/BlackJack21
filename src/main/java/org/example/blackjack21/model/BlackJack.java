package org.example.blackjack21.model;

public class BlackJack {

    private Mazo mazo;
    private Mano manoJugador;
    private Mano manoComputador;
    private Apuesta apuestaActual;

    private int saldo=1000;

    public BlackJack() {
        mazo = new Mazo();
        manoJugador = new Mano();
        manoComputador = new Mano();
    }

    public String iniciarPartida(String textoApuesta)
    {
        String error= Apuesta.validarEntrada(textoApuesta,saldo);
        if(error != null)
        {
            return error;
        }
        int cantidadApuesta = Integer.parseInt(textoApuesta);
        apuestaActual = new Apuesta(cantidadApuesta);
        saldo -= cantidadApuesta;

        mazo.reiniciar();
        manoJugador.limpiar();
        manoComputador.limpiar();

        manoJugador.agregarCarta(mazo.sacarCarta());
        manoJugador.agregarCarta(mazo.sacarCarta());

        return null;
    }





}
