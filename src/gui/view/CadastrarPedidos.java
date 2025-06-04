package gui.view;

import application.Main2;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Cidade;
import model.entities.Estado;
import model.entities.Pedido;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastrarPedidos implements Initializable {

    @FXML
    private TableView<Pedido> tableViewPedido;

    @FXML
    private TableColumn<Pedido,String> tableColumnProduto;

    @FXML
    private TableColumn<Pedido,String> tableColumnDestino;

    @FXML
    private TableColumn<Pedido, Cidade> tableColumnCidade;

    @FXML
    private TableColumn<Pedido, Estado> tableColumnEstado;

    @FXML
    private TableColumn<Pedido, String> tableColumnCEP;

    @FXML
    private TableColumn<Pedido, Pedido> tableColumnLote;  // Depois que mudar o banco e as classes, alterar o nome "Pedido" para "Lote"

    @FXML
    private Button btnNovoPedido;

    @FXML
    public void onBtnNovoPedidoAction(){

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        tableColumnDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        tableColumnCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        tableColumnEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tableColumnCEP.setCellValueFactory(new PropertyValueFactory<>("cep"));
        tableColumnLote.setCellValueFactory(new PropertyValueFactory<>("lote"));

        Stage stage = (Stage) Main2.getMainScene().getWindow();
        tableViewPedido.prefHeightProperty().bind(stage.heightProperty());

    }
}
