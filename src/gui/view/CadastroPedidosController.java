package gui.view;

import gui.controller.CadastroLoteModalController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.services.Utilidades;


public class CadastroPedidosController {

    @FXML
    private TextField txtIdProduto;

    @FXML
    private TextField txtDestino;

    @FXML
    private TextField txtCep;

    @FXML
    private ComboBox<String> comboBoxCidade;

    @FXML
    private ComboBox<String> comboBoxEstado;

    @FXML
    private TextField txtIdPedido;

    @FXML
    private Button btnCadastrarLote;

    @FXML
    private Button btnCadastrarPedido;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        comboBoxCidade.getItems().addAll("São Paulo", "Rio de Janeiro", "Curitiba", "Porto Alegre", "Belo Horizonte", "Outra");
        comboBoxEstado.getItems().addAll("SP", "RJ", "PR", "RS", "MG", "Outro");

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


    @FXML
    private void onCadastrarPedido() {
        try {
            int idProduto = Integer.parseInt(txtIdProduto.getText());
            String destino = txtDestino.getText();
            String cep = txtCep.getText();
            String cidade = comboBoxCidade.getValue();
            String estado = comboBoxEstado.getValue();
            int idPedido = Integer.parseInt(txtIdPedido.getText());

            // Aqui você pode mapear cidade/estado para seus respectivos IDs
            int idCidade = mapCidadeToId(cidade);
            int idEstado = mapEstadoToId(estado);

            if (destino == null || destino.isEmpty() || cep == null || cep.isEmpty() || cidade == null || estado == null) {
                showAlert("Preencha todos os campos!", Alert.AlertType.ERROR);
                return;
            }

            Utilidades.cadastrarLote(idProduto, destino, idCidade, idEstado, cep, idPedido);

            showAlert("Lote cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            if (stage != null) stage.close();

        } catch (NumberFormatException e) {
            showAlert("IDs devem ser números inteiros válidos.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Erro ao cadastrar lote: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private int mapCidadeToId(String cidade) {
        // Exemplo simples, substitua pelo seu mapeamento real
        switch (cidade) {
            case "São Paulo": return 1;
            case "Rio de Janeiro": return 2;
            case "Curitiba": return 3;
            case "Porto Alegre": return 4;
            case "Belo Horizonte": return 5;
            default: return 0;
        }
    }

    private int mapEstadoToId(String estado) {
        switch (estado) {
            case "SP": return 1;
            case "RJ": return 2;
            case "PR": return 3;
            case "RS": return 4;
            case "MG": return 5;
            default: return 0;
        }
    }



    private void showAlert(String msg, Alert.AlertType type) {
        Alert alert = new Alert(type, msg);
        alert.showAndWait();
    }
}