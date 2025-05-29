package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.ProdutoDao;
import model.dao.impl.DaoFactory;
import model.dao.impl.ProdutoDaoJDBC;
import model.entities.Produto;
import model.entities.Secao;

import java.util.Date;

public class ProdutoController {
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
    public void initialize() {
        id_produto.setCellValueFactory(new PropertyValueFactory<>("id_produto"));
        descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        secao.setCellValueFactory(new PropertyValueFactory<>("secao"));
        data_armazenamento.setCellValueFactory(new PropertyValueFactory<>("data_armazenamento"));

        ObservableList<Produto> produtos = FXCollections.observableArrayList(produtoDao.findAll());
        tabelaProdutos.setItems(produtos);
    }

}
