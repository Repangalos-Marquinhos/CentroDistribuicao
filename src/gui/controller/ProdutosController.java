package gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.dao.ProdutoDao;
import model.dao.impl.DaoFactory;
import model.dao.impl.ProdutoDaoJDBC;
import model.entities.Produto;
import model.entities.Secao;

import java.util.Date;

public class ProdutosController {
    @FXML
    private TableView<Produto> tabelaProdutos;

    @FXML
    private TableColumn<Produto, Integer> id_produto;

    @FXML
    private TableColumn<Produto, String> descricao;

    @FXML
    private TableColumn<Produto, Secao> secao;

    @FXML
    private TableColumn<Produto, Date> data_armazenamento;

    ProdutoDao produtoDao = DaoFactory.createProdutoDao();

    @FXML
    private Button btnExcluir;

    @FXML
    public void initialize() {

        // Verifique se o botão não é null antes de configurar o evento
        if (btnExcluir != null) {
            btnExcluir.setOnAction(this::handleExcluir);
        } else {
            System.err.println("Erro: btnCadastrarLote não foi injetado pelo FXML");
        }

        id_produto.setCellValueFactory(new PropertyValueFactory<>("id_produto"));
        descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        secao.setCellValueFactory(new PropertyValueFactory<>("secao"));
        data_armazenamento.setCellValueFactory(new PropertyValueFactory<>("data_armazenamento"));

        ObservableList<Produto> produtos = FXCollections.observableArrayList(produtoDao.findAll());
        tabelaProdutos.setItems(produtos);
    }

    private void handleExcluir(ActionEvent event) {

        System.out.println("Botão excluir pressionado");

        abrirModalExcluir();
    }

    private void abrirModalExcluir() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/ExcluirProduto.fxml"));
            AnchorPane root = loader.load();

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initStyle(StageStyle.UNDECORATED);
            modalStage.setScene(new Scene(root));

            ExcluirPedidoController controller = loader.getController();
            controller.setStage(modalStage);

            modalStage.showAndWait();
        } catch (Exception e) {
            System.err.println("Erro ao abrir modal de Excluir Pedido");
            e.printStackTrace();
        }
    }

}