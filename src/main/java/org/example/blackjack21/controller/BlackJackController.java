package org.example.blackjack21.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

    public class BlackJackController {

        @FXML
        private Label lblPuntajeComputador;

        @FXML
        private HBox hboxCartasComputador;

        @FXML
        private Label lblMensaje;

        @FXML
        private Label lblPuntajeJugador;

        @FXML
        private HBox hboxCartasJugador;

        @FXML
        private Label lblSaldo;

        @FXML
        private Label lblApuesta;

        @FXML
        private TextField txtApuesta;

        @FXML
        private Button btnIniciar;

        @FXML
        private Button btnPedir;

        @FXML
        private Button btnPlantarse;

        @FXML
        private Button btnReglas;

        @FXML
        private Button btnCerrar;


}