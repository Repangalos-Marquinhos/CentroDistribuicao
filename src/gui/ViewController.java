package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ViewController {
    @FXML
    private Button myButton;

    @FXML
    public void onMyButtonAction() {
        System.out.println("Olá Mundo!");
    }

}
