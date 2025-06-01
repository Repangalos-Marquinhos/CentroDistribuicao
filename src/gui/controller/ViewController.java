package gui.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.entities.Secao;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    @FXML
    private ComboBox<Secao> comboBox;
    @FXML
    private ObservableList<Secao> obsSecoes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
