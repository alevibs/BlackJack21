module org.example.blackjack21 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.blackjack21 to javafx.fxml;
    exports org.example.blackjack21;
}