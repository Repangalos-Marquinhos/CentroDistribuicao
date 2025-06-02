package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.services.Utilidades;

public class ExcluirPedidoController {

    @FXML
    private TextField txtIdPedido;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtDestino;

    private Stage stage;

    // ESTE MÉTODO DEVE SER PÚBLICO!
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button btnExcluir;

    @FXML
    public void initialize() {
        // Opcional: adicionar ação ao botão, caso não use onAction no FXML
        btnExcluir.setOnAction(event -> excluirPedido());
        btnCancelar.setOnAction(event -> stage.close());
    }

    private void excluirPedido() {
        String idPedidoStr = txtIdPedido.getText();
        String destinoStr = txtDestino.getText();

        if (idPedidoStr == null || idPedidoStr.isEmpty()) {
            showAlert("Erro", "Informe o ID do pedido para excluir.", Alert.AlertType.ERROR);
            return;
        }

        try {
            int idPedido = Integer.parseInt(idPedidoStr);
            Utilidades.excluirLote(idPedido, destinoStr);
            showAlert("Sucesso", "Pedido excluído com sucesso!", Alert.AlertType.INFORMATION);
            txtIdPedido.clear();
            txtDestino.clear();
            stage.close();
        } catch (NumberFormatException e) {
            showAlert("Erro", "O ID deve ser um número inteiro.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Erro", "Erro ao excluir pedido: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}