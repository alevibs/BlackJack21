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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

                ImageView vistaCarta = crearVistaCarta(carta);

                hboxCartasJugador.getChildren().add(vistaCarta);
            }

            hboxCartasComputador.getChildren().clear();

            for (Carta carta : juego.getManoComputador().getCartas()) {

                ImageView vistaCarta = crearVistaCarta(carta);

                hboxCartasComputador.getChildren().add(vistaCarta);
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

            int puntajeJugador = juego.getManoJugador().calcularPuntaje();
            int puntajeComputador = juego.getManoComputador().calcularPuntaje();

            if (juego.getEstado() == BlackJack.Estado.TURNO_JUGADOR) {

                if (juego.getManoJugador().esVeinteYMedio()) {
                    lblMensaje.setText("¡Veinte y medio! Tienes 14. Puedes pedir carta o plantarte.");
                } else {
                    lblMensaje.setText("Tu turno: pide carta o plántate.");
                }

            } else if (juego.getEstado() == BlackJack.Estado.TERMINO_RONDA) {

                String detalle = " (Tú: " + puntajeJugador + " - Computador: " + puntajeComputador + ")";
                String mensaje = "";

                switch (juego.getResultado()) {
                    case GANO_RONDA:
                        mensaje = "¡Ganaste la ronda!" + detalle;
                        break;
                    case PERDIO_RONDA:
                        if (puntajeJugador > 21) {
                            mensaje = "Te pasaste de 21. Perdiste la ronda.";
                        } else if (juego.getManoComputador().tieneBlackJack()) {
                            mensaje = "El computador tiene Blackjack. Perdiste la ronda.";
                        } else {
                            mensaje = "Perdiste la ronda." + detalle;
                        }
                        break;
                    case EMPATE:
                        if (juego.getManoJugador().tieneBlackJack()) {
                            mensaje = "Empate: ambos tienen Blackjack. Se devuelve la apuesta.";
                        } else {
                            mensaje = "Empate: se devuelve la apuesta." + detalle;
                        }
                        break;
                    case BLACK_JACK:
                        mensaje = "¡Blackjack! Ganaste 1,5 veces tu apuesta.";
                        break;
                }

                if (juego.getSaldo() < 10) {
                    mensaje += " No tienes saldo suficiente para otra ronda.";
                }

                lblMensaje.setText(mensaje);
            }
        }
        @FXML
        private void onVerReglas() {

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);

            alerta.setTitle("Reglas del Black Jack");

            alerta.setHeaderText("Reglas del juego");

                alerta.setContentText(
                        "OBJETIVO: sumar más que el computador sin pasar de 21.\n\n" +
                                "VALORES: del 2 al 10 su número; J, Q y K valen 10; el As vale 1 u 11.\n\n" +
                                "APUESTAS: empiezas con 1.000 fichas. Mínimo 10, múltiplo de 10 y sin superar tu saldo.\n\n" +
                                "JUGADAS ESPECIALES:\n" +
                                "- Blackjack: As + carta de 10 en las dos primeras. Le gana a todo.\n" +
                                "- Veinte y medio: figura + 4 (14 puntos). Le gana a 20 o menos, pierde contra 21.\n" +
                                "- Pasarse: más de 21, pierdes.\n\n" +
                                "PAGOS: victoria = 2 veces la apuesta; Blackjack = 2,5 veces; empate = se devuelve.\n\n" +
                                "COMPUTADOR: pide carta mientras tenga menos de 17 y se planta con 17 o más."
                );
            alerta.showAndWait();
        }

        @FXML
        public boolean confirmarCierre() {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Cerrar juego");
            alerta.setHeaderText("¿Estás seguro de que deseas salir?");
            alerta.setContentText("Se perderá la partida actual.");
            return alerta.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
        }

        @FXML
        private void onCerrar() {
            if (confirmarCierre()) {
                Platform.exit();
            }
        }

        private ImageView crearVistaCarta(Carta carta) {

            String paloImagen = switch (carta.getPalo()) {
                case "P" -> "spades";
                case "C" -> "hearts";
                case "D" -> "diamonds";
                case "T" -> "clubs";
                default -> throw new IllegalArgumentException("Palo desconocido");
            };

            String rangoImagen = carta.getRango();

            if (rangoImagen.equals("As")) {
                rangoImagen = "A";
            }

            String nombreArchivo = rangoImagen + "_" + paloImagen + ".png";

            String ruta = "/org/example/blackjack21/imagenes/cartas/" + nombreArchivo;

            var recurso = getClass().getResource(ruta);

            if (recurso == null) {
                throw new IllegalArgumentException("No se encontró la imagen: " + ruta);
            }

            Image imagen = new Image(recurso.toExternalForm());

            ImageView vista = new ImageView(imagen);
            vista.setFitWidth(90);
            vista.setFitHeight(130);
            vista.setPreserveRatio(true);

            return vista;
        }
    }


