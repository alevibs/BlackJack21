package org.example.blackjack21.model;

public class BlackJack {

    public enum Estado {
        INICIO_RONDA,
        TURNO_JUGADOR,
        TURNO_COMPUTADOR,
        TERMINO_RONDA
    }

    public enum Resultado {
        GANO_RONDA,
        PERDIO_RONDA,
        EMPATE,
        BLACK_JACK
    }

    private int saldo = 1000;

    private Mazo mazo;
    private Mano manoJugador;
    private Mano manoComputador;
    private Apuesta apuestaActual;

    private Estado estado;
    private Resultado resultado;

    public BlackJack() {

        mazo = new Mazo();
        manoJugador = new Mano();
        manoComputador = new Mano();

        estado = Estado.INICIO_RONDA;
        resultado = null;
    }

    public String iniciarRonda(String textoApuesta) {


        if (estado == Estado.TURNO_JUGADOR ||
                estado == Estado.TURNO_COMPUTADOR) {

            return "No puedes iniciar otra ronda mientras la actual esté activa.";
        }

        String error = Apuesta.validarEntrada(textoApuesta, saldo);

        if (error != null) {
            return error;
        }

        int cantidad = Integer.parseInt(textoApuesta);
        apuestaActual = new Apuesta(cantidad);
        saldo -= cantidad;

        mazo.reiniciar();
        manoJugador.limpiar();
        manoComputador.limpiar();

        resultado = null;

        manoJugador.agregarCarta(mazo.sacarCarta());
        manoJugador.agregarCarta(mazo.sacarCarta());

        if (manoJugador.calcularPuntaje() == 21 &&
                manoJugador.cantidadCartas() == 2) {

            estado = Estado.TURNO_COMPUTADOR;

        } else {

            estado = Estado.TURNO_JUGADOR;
        }

        return null;
    }

}
