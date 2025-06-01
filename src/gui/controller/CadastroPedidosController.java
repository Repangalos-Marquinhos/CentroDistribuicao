package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CadastroPedidosController {
    @FXML
    private Button btnCadastrarLote;

    @FXML
    private void initialize() {
        // Verifique se o botão não é null antes de configurar o evento
        if (btnCadastrarLote != null) {
            btnCadastrarLote.setOnAction(this::handleCadastrarLote);
        } else {
            System.err.println("Erro: btnCadastrarLote não foi injetado pelo FXML");
        }
    }

    private void handleCadastrarLote(ActionEvent event) {
        // Sua lógica para cadastrar novo lote
        System.out.println("Botão Cadastrar Lote pressionado");

        // Adicione aqui a chamada para abrir o modal
        abrirModalCadastroLote();
    }

    private void abrirModalCadastroLote() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/CadastroLoteModal.fxml"));
            AnchorPane root = loader.load();

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initStyle(StageStyle.UNDECORATED);
            modalStage.setScene(new Scene(root));

            CadastroLoteModalController controller = loader.getController();
            controller.setStage(modalStage);

            modalStage.showAndWait();
        } catch (Exception e) {
            System.err.println("Erro ao abrir modal de cadastro de lote");
            e.printStackTrace();
        }
    }
}