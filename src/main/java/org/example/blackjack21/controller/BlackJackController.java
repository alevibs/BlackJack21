package org.example.blackjack21.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BlackJackController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
