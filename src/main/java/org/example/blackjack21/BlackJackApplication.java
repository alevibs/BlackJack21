package org.example.blackjack21;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.blackjack21.controller.BlackJackController;

import java.io.IOException;

public class BlackJackApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BlackJackApplication.class.getResource("juego-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BLACK JACK 21");
        stage.setScene(scene);
        BlackJackController controlador = fxmlLoader.getController();
        stage.setOnCloseRequest(evento -> {
            if (!controlador.confirmarCierre()) {
                evento.consume();
            }
        });
        stage.show();

    }
}
