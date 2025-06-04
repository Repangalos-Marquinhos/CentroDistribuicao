package gui.view;

import application.Main2;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Produto;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastrarProdutos implements Initializable {

    @FXML
    private TableView<Produto> tableViewProduto;

    @FXML
    private TableColumn<Produto, Integer> tableColumnIdProduto;


    @FXML
    private TableColumn<Produto,String> tableColumnDescricaoProduto;

    @FXML
    private TableColumn<Produto,String> tableColumnSecaoProduto;

    @FXML
    private TableColumn<Produto, String> tableColumnDataArmazenamento;

    @FXML
    private Button btnNovoProduto;

    @FXML
    public void onBtnNovoProdutoAction() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    public void initializeNodes(){
        tableColumnIdProduto.setCellValueFactory(new PropertyValueFactory<>("id_produto"));
        tableColumnDescricaoProduto.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tableColumnSecaoProduto.setCellValueFactory(new PropertyValueFactory<>("secao"));
        tableColumnDataArmazenamento.setCellValueFactory(new PropertyValueFactory<>("data_armazenamento"));

        Stage stage = (Stage) Main2.getMainScene().getWindow();
        tableViewProduto.prefHeightProperty().bind(stage.heightProperty());
    }
}
