package org.example.blackjack21.model;

public class BlackJack {
    public enum Estado{
        INICIO_RONDA, TURNO_JUGADOR, TURNO_COMPUTADOR, TERMINO_RONDA
    }
    public enum Resultado{
        GANO_RONDA, PERDIO_RONDA, EMPATE, BLACK_JACK
    }
    private Mazo mazo;
    private Mano manoJugador;
    private Mano manoComputador;
    private Apuesta apuesta;
    private Estado estado;
    private Resultado resultado;
    public BlackJack(){
        mazo=new Mazo();
        manoJugador=new Mano();
        manoComputador=new Mano();
        apuesta=new Apuesta();
        estado=Estado.INICIO_RONDA;
        resultado=null;
    }
    public String iniciarRonda (String textoApuesta){
        if (estado==Estado.TURNO_JUGADOR||estado==Estado.TURNO_COMPUTADOR){
            return "No puede iniciar una ronda mientras esta en una";
        }
        String error = Apuesta.validarEntrada(textoApuesta, apuesta.getSaldo());
        if (error!=null){
            return error;
        }



}
