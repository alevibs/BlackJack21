package org.example.blackjack21.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.example.blackjack21.model.Carta;
import org.example.blackjack21.model.Mazo;

import java.util.ArrayList;

public class BlackJackController {

    @FXML
    private HBox contenedorCartas;

    @FXML
    private Button btnPedirCarta;

    private Mazo mazo;
    private ArrayList<Carta> manoJugador;

    @FXML
    private void onPedirCarta(){
        if (mazo.estaVacio()) {
            btnPedirCarta.setDisable(true);
            return;
        }
        repartirCarta();
    }

    private void repartirCarta() {

        if (mazo.estaVacio()) {
            btnPedirCarta.setDisable(true);
            return;
        }

        Carta nuevaCarta = mazo.sacarCarta();

        manoJugador.add(nuevaCarta);

    }

    public void initialize() {

        mazo = new Mazo();
        mazo.crearMazo();
        mazo.barajar();
        manoJugador = new ArrayList<>();

        repartirCarta();
        repartirCarta();
    }




}