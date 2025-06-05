package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.dao.PedidoDao;
import model.dao.impl.DaoFactory;
import model.dao.impl.PedidoDaoJDBC;
import db.DB;
import model.entities.Pedido;

public class AlterarStatusController {

    @FXML
    private TextField txtPedidoId;

    @FXML
    private ComboBox<String> comboStatus;

    private PedidoDao pedidoDao;

    @FXML
    public void initialize() {
        // Adicione os status possíveis aqui
        comboStatus.getItems().addAll("PENDENTE", "APROVADO", "ENVIADO", "ENTREGUE", "CANCELADO");
        // Inicialize o DAO (ajuste conforme sua arquitetura)
        pedidoDao = DaoFactory.createPedidoDao();
    }

    @FXML
    public void onAlterarStatus() {
        try {
            int id = Integer.parseInt(txtPedidoId.getText());
            String status = comboStatus.getValue();

            if (status == null || status.isEmpty()) {
                showAlert("Selecione um status!");
                return;
            }

            pedidoDao.updateStatusPedido(id, status);
            showAlert("Status atualizado com sucesso!");

        } catch (NumberFormatException e) {
            showAlert("ID inválido!");
        } catch (Exception e) {
            showAlert("Erro ao atualizar status: " + e.getMessage());
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alterar Status");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}