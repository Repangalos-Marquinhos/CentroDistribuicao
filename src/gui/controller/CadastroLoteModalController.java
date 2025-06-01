package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.services.Utilidades;

import java.sql.Date;


public class CadastroLoteModalController {
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtPedidoId;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void confirmarCadastro() {
        try {
            // Pega os valores dos campos
            String userIdStr = txtUserId.getText();
            String pedidoIdStr = txtPedidoId.getText();
            java.time.LocalDate localDate = datePicker.getValue();

            // Validação básica
            if (userIdStr == null || userIdStr.isEmpty() ||
                    pedidoIdStr == null || pedidoIdStr.isEmpty() ||
                    localDate == null) {
                System.out.println("Preencha todos os campos!");
                return;
            }

            int userId = Integer.parseInt(userIdStr);
            int pedidoId = Integer.parseInt(pedidoIdStr);
            Date dataPedido = Date.valueOf(localDate);

            // Chama a função utilitária para cadastrar o pedido
            Utilidades.cadastrarPedido(pedidoId, userId, dataPedido);

            System.out.println("Pedido cadastrado com sucesso!");
            // Fecha o modal
            stage.close();
        } catch (NumberFormatException e) {
            System.out.println("IDs devem ser números inteiros válidos.");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar pedido: " + e.getMessage());
        }
    }
}