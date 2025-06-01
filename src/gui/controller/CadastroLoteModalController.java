package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroLoteModalController {
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtUserName;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void confirmarCadastro() {
        // Aqui você pode adicionar a lógica para salvar os dados
        System.out.println("Data selecionada: " + datePicker.getValue());
        System.out.println("ID do Usuário: " + txtUserId.getText());
        System.out.println("Nome do Usuário: " + txtUserName.getText());

        // Fecha o modal
        stage.close();
    }
}