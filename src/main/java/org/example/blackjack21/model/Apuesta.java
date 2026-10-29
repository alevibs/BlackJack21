package org.example.blackjack21.model;

public class Apuesta {

    private int cantidadApuesta;

    public Apuesta (int cantidadApuesta)
    {
        this.cantidadApuesta=cantidadApuesta;
    }

    int getCantidad()
    {
        return cantidadApuesta;
    }

    public static String validarEntrada(String texto, int saldo)
    {

        if (texto==null || texto.isBlank())
        {
            return "Ingrese una cantidad para apostar.";
        }
        int cantidadApuesta;

        try {
            cantidadApuesta = Integer.parseInt(texto);
        } catch (NumberFormatException errorTipoDato) {
            return "Ingresa un número entero válido.";
        }

        if (cantidadApuesta < 10)
        {
            return "El valor mínimo para apostar es 10.";
        }

        if (cantidadApuesta % 10 != 0)
        {
            return "Ingrese un valor multiplo de 10 para apostar.";
        }

        if (cantidadApuesta > saldo)
        {
            return "Para apostar ingrese un valor menor o igual al saldo disponible.";
        }

        if (saldo<10)
        {
            return "No tienes saldo suficiente para otra partida.";
        }

        return null;
    }


}