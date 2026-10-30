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

        int cantidad = Integer.parseInt(textoApuesta.trim());
        apuestaActual = new Apuesta(cantidad);
        saldo -= cantidad;

        mazo.reiniciar();
        manoJugador.limpiar();
        manoComputador.limpiar();

        resultado = null;

        manoJugador.agregarCarta(mazo.sacarCarta());
        manoJugador.agregarCarta(mazo.sacarCarta());


        estado = Estado.TURNO_JUGADOR;
        if (manoJugador.tieneBlackJack()) {
            jugarTurnoComputador();
        }
        return null;
    }

    public String pedirCarta() {

        if (estado != Estado.TURNO_JUGADOR) {
            return "No puedes pedir cartas en este momento.";
        }

        manoJugador.agregarCarta(mazo.sacarCarta());

        int puntaje = manoJugador.calcularPuntaje();

        if (puntaje > 21) {
            finalizarRonda(Resultado.PERDIO_RONDA);
        } else if (puntaje == 21) {
            jugarTurnoComputador();
        }

        return null;
    }

    public String plantarse() {

        if (estado != Estado.TURNO_JUGADOR) {
            return "No puedes plantarte en este momento.";
        }

        jugarTurnoComputador();

        return null;
    }

    private void jugarTurnoComputador() {

        estado = Estado.TURNO_COMPUTADOR;

        manoComputador.agregarCarta(mazo.sacarCarta());
        manoComputador.agregarCarta(mazo.sacarCarta());

        if (manoJugador.tieneBlackJack()) {

            if (manoComputador.tieneBlackJack()) {
                finalizarRonda(Resultado.EMPATE);
            } else {
                finalizarRonda(Resultado.BLACK_JACK);
            }

            return;
        }

        if (manoComputador.tieneBlackJack()) {
            finalizarRonda(Resultado.PERDIO_RONDA);
            return;
        }

        while (manoComputador.calcularPuntaje() < 17) {
            manoComputador.agregarCarta(mazo.sacarCarta());
        }
        determinarResultado();
    }

    private void determinarResultado() {

        int puntajeJugador = manoJugador.calcularPuntaje();
        int puntajeComputador = manoComputador.calcularPuntaje();

        if (puntajeComputador > 21) {
            finalizarRonda(Resultado.GANO_RONDA);
            return;
        }

        if (puntajeJugador > 21) {
            finalizarRonda(Resultado.PERDIO_RONDA);
            return;
        }

        boolean jugadorEspecial = manoJugador.esVeinteYMedio();
        boolean computadorEspecial = manoComputador.esVeinteYMedio();

        if (jugadorEspecial && computadorEspecial) {
            finalizarRonda(Resultado.EMPATE);
        } else if (jugadorEspecial && puntajeComputador < 21) {
            finalizarRonda(Resultado.GANO_RONDA);
        } else if (computadorEspecial) {
            finalizarRonda(Resultado.PERDIO_RONDA);
        } else if (puntajeJugador > puntajeComputador) {
            finalizarRonda(Resultado.GANO_RONDA);
        } else if (puntajeComputador > puntajeJugador) {
            finalizarRonda(Resultado.PERDIO_RONDA);
        } else {
            finalizarRonda(Resultado.EMPATE);
        }
    }

    private void finalizarRonda(Resultado resultadoFinal) {

        resultado = resultadoFinal;
        estado = Estado.TERMINO_RONDA;

        int apuesta = apuestaActual.getCantidad();

        switch (resultadoFinal) {

            case GANO_RONDA:
                saldo += apuesta * 2;
                break;

            case BLACK_JACK:
                saldo += (int) (apuesta * 2.5);
                break;

            case EMPATE:
                saldo += apuesta;
                break;

            case PERDIO_RONDA:
                // No se devuelve la apuesta.
                break;
        }
    }

    public int getSaldo() {
        return saldo;
    }

    public Mano getManoJugador() {
        return manoJugador;
    }

    public Mano getManoComputador() {
        return manoComputador;
    }

    public Estado getEstado() {
        return estado;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public Apuesta getApuestaActual() {
        return apuestaActual;
    }
}
