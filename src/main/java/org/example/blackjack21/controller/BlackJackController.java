package org.example.blackjack21.controller;

import org.example.blackjack21.model.BlackJack;
import org.example.blackjack21.model.Carta;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

        private BlackJack juego;

        @FXML
        public void initialize() {

            juego = new BlackJack();

            actualizarVista();

            lblMensaje.setText("Ingresa tu apuesta para comenzar.");
        }

        @FXML
        private void onIniciarRonda() {

            String error = juego.iniciarRonda(txtApuesta.getText());

            if (error != null) {
                lblMensaje.setText(error);
                return;
            }

            lblMensaje.setText("Comienza la partida.");
            txtApuesta.clear();
            actualizarVista();
        }

        @FXML
        private void onPedirCarta() {

            String error = juego.pedirCarta();

            if (error != null) {
                lblMensaje.setText(error);
                return;
            }
            actualizarVista();
        }

        @FXML
        private void onPlantarse() {

            String error = juego.plantarse();

            if (error != null) {
                lblMensaje.setText(error);
                return;
            }
            actualizarVista();
        }

        private void actualizarVista() {

            lblSaldo.setText("SALDO: " + juego.getSaldo());

            if (juego.getApuestaActual() != null) {

                lblApuesta.setText(
                        "APUESTA: " + juego.getApuestaActual().getCantidad()
                );

            } else {

                lblApuesta.setText("APUESTA: 0");
            }

            hboxCartasJugador.getChildren().clear();

            for (Carta carta : juego.getManoJugador().getCartas()) {

                Label lblCarta = new Label(carta.toString());

                hboxCartasJugador.getChildren().add(lblCarta);
            }

            hboxCartasComputador.getChildren().clear();

            for (Carta carta : juego.getManoComputador().getCartas()) {

                Label lblCarta = new Label(carta.toString());

                hboxCartasComputador.getChildren().add(lblCarta);
            }

            lblPuntajeJugador.setText(
                    "PUNTAJE: " + juego.getManoJugador().calcularPuntaje()
            );

            lblPuntajeComputador.setText(
                    "PUNTAJE: " + juego.getManoComputador().calcularPuntaje()
            );

            boolean turnoJugador =
                    juego.getEstado() == BlackJack.Estado.TURNO_JUGADOR;

            boolean rondaActiva =
                    juego.getEstado() == BlackJack.Estado.TURNO_JUGADOR ||
                            juego.getEstado() == BlackJack.Estado.TURNO_COMPUTADOR;

            btnPedir.setDisable(!turnoJugador);

            btnPlantarse.setDisable(!turnoJugador);

            btnIniciar.setDisable(
                    rondaActiva || juego.getSaldo() < 10
            );

            txtApuesta.setDisable(rondaActiva);

            if (juego.getEstado() == BlackJack.Estado.TERMINO_RONDA) {

                switch (juego.getResultado()) {

                    case GANO_RONDA:
                        lblMensaje.setText("¡Ganaste la ronda!");
                        break;

                    case PERDIO_RONDA:
                        lblMensaje.setText("Perdiste la ronda.");
                        break;

                    case EMPATE:
                        lblMensaje.setText("La ronda terminó en empate.");
                        break;

                    case BLACK_JACK:
                        lblMensaje.setText("¡Blackjack! Ganaste la ronda.");
                        break;
                }
            }
        }

        @FXML
        private void onVerReglas() {

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);

            alerta.setTitle("Reglas del Black Jack");

            alerta.setHeaderText("Reglas del juego");

            alerta.setContentText(
                    "1. El objetivo es acercarse a 21 sin superarlo.\n\n" +
                            "2. El As vale 1 u 11.\n\n" +
                            "3. Las cartas J, Q y K valen 10.\n\n" +
                            "4. El computador pide cartas hasta llegar a 17.\n\n" +
                            "5. La apuesta mínima es de 10 fichas.\n\n" +
                            "6. No puedes apostar más de tu saldo disponible."
            );

            alerta.showAndWait();
        }

        @FXML
        private void onCerrar() {

            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

            alerta.setTitle("Cerrar juego");

            alerta.setHeaderText("¿Estás seguro de que deseas salir?");

            alerta.setContentText("Se cerrará la partida.");

            if (alerta.showAndWait().orElse(ButtonType.CANCEL)
                    == ButtonType.OK) {

                Platform.exit();
            }
        }
    }


