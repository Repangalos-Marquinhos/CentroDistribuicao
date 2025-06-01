package gui.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.dao.LoteDao;
import model.dao.impl.DaoFactory;
import model.entities.Lote;
import model.entities.Pedido;
import model.entities.Produto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PedidosController {
    @FXML
    private TableView<Lote> tabelaPedidos;

    @FXML
    private TableColumn<Lote, Integer> numero_pedido;

    @FXML
    private TableColumn<Lote, Produto> Produto;

    @FXML
    private TableColumn<Lote, String> Endereco;

    @FXML
    private TableColumn<Lote, Date> data_Pedido;

    LoteDao loteDao = DaoFactory.createLoteDao();

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    public void initialize() {
        // Configurar a coluna do número do pedido
        numero_pedido.setCellValueFactory(data -> {
            Lote lote = data.getValue();
            Pedido pedido = lote.getPedido(); // Supondo que Lote tem um método getPedido()
            return new SimpleIntegerProperty(pedido != null ? pedido.getId() : 0).asObject();
        });

        // Configurar a coluna de produto para exibir apenas o nome do produto
        Produto.setCellValueFactory(data -> {
            Lote lote = data.getValue();
            return new SimpleObjectProperty<>(lote.getProduto());
        });

        Produto.setCellFactory(column -> {
            return new TableCell<Lote, Produto>() {
                @Override
                protected void updateItem(Produto produto, boolean empty) {
                    super.updateItem(produto, empty);
                    if (empty || produto == null) {
                        setText(null);
                    } else {
                        // Exibir apenas o nome do produto, assumindo que Produto tem um método getNome()
                        setText(produto.getDescricao());
                    }
                }
            };
        });

        // Configurar a coluna de endereço
        Endereco.setCellValueFactory(data -> {
            Lote lote = data.getValue();
            return new SimpleStringProperty(lote.getDestino());
        });

        // Configurar a coluna de data do pedido com formatação
        data_Pedido.setCellValueFactory(data -> {
            Lote lote = data.getValue();
            Pedido pedido = lote.getPedido();
            return new SimpleObjectProperty<>(pedido != null ? pedido.getData_pedido() : null);
        });

        data_Pedido.setCellFactory(column -> {
            return new TableCell<Lote, Date>() {
                @Override
                protected void updateItem(Date data, boolean empty) {
                    super.updateItem(data, empty);
                    if (empty || data == null) {
                        setText(null);
                    } else {
                        // Formatar a data para exibição
                        setText(sdf.format(data));
                    }
                }
            };
        });

        // Carregar os dados na tabela
        ObservableList<Lote> lotes = FXCollections.observableArrayList(loteDao.findAll());
        tabelaPedidos.setItems(lotes);
    }
}