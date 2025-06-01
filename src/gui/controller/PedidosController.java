package gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.LoteDao;
import model.dao.ProdutoDao;
import model.dao.impl.DaoFactory;
import model.dao.impl.ProdutoDaoJDBC;
import model.entities.Lote;
import model.entities.Pedido;
import model.entities.Produto;
import model.entities.Secao;

import java.util.Date;



public class PedidosController {
    @FXML
    private TableView<Lote> tabelaPedidos;

    @FXML
    private TableColumn<Pedido, Integer> numero_pedido;

    @FXML
    private TableColumn<Lote, Produto> Produto;

    @FXML
    private TableColumn<Lote, String> Endereco;

    @FXML
    private TableColumn<Lote, Secao> Secao;

    @FXML
    private TableColumn<Lote, Date> data_Pedido;

    LoteDao loteDao = DaoFactory.createLoteDao();

    @FXML
    public void initialize() {
        numero_pedido.setCellValueFactory(new PropertyValueFactory<>("numero_pedido"));
        Produto.setCellValueFactory(new PropertyValueFactory<>("id_produto"));
        Endereco.setCellValueFactory(new PropertyValueFactory<>("destino"));
        Secao.setCellValueFactory(new PropertyValueFactory<>("secao"));
        data_Pedido.setCellValueFactory(new PropertyValueFactory<>("data_pedido"));

        ObservableList<Lote> lotes = FXCollections.observableArrayList(loteDao.findAll());
        tabelaPedidos.setItems(lotes);
    }

}