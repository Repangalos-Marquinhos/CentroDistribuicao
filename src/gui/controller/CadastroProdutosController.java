package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

public class CadastroProdutosController {
    @FXML
    private Button btnCadastrar; // Certifique-se que o fx:id corresponde ao FXML

    @FXML
    private void initialize() {
        // Configurações iniciais se necessário
    }

    @FXML
    private void onCadastrar(ActionEvent event) {
        // Método que será chamado pelo FXML
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
            e.printStackTrace();
        }
    }
}
