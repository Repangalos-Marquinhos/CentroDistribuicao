package gui.view;

import application.Main2;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main2ViewController implements Initializable {

    @FXML
    private MenuItem menuItemCadastrarUsuario;

    @FXML
    private MenuItem menuItemCadastrarProduto;

    @FXML
    private MenuItem menuItemCadastrarPedido;

    @FXML
    private MenuItem menuItemCadastrarLote;

    @FXML
    private MenuItem menuItemListarUsuarios;

    @FXML
    private MenuItem menuItemListarProdutos;

    @FXML
    private MenuItem menuItemListarPedidos;

    @FXML
    private MenuItem menuItemListarLotes;


    @FXML
    private MenuItem menuItemSobre;

    @FXML
    public void onMenuItemCadastrarUsuarioAction() {
        loadView("/gui/view/CadastrarUsuarios.fxml");
    }

    @FXML
    public void onMenuItemCadastrarProdutoAction() {
        loadView("/gui/view/CadastrarProdutos.fxml");
    }

    @FXML
    public void onMenuItemCadastrarPedidoAction() {
        System.out.println("Cadastrando novo Pedido");
    }

    @FXML
    public void onMenuItemCadastrarLoteAction() {
        System.out.println("Cadastrando novo Lote");
    }

    @FXML
    public void onMenuItemListarUsuariosAction() {
        System.out.println("Listando Clientes");
    }
    @FXML
    public void onMenuItemListarProdutosAction() {
        System.out.println("Listando Produtos");
    }

    @FXML
    public void onMenuItemListarPedidosAction() {
        System.out.println("Listando Pedidos");
    }

    @FXML
    public void onMenuItemListarLotesAction() {
        System.out.println("Listando Lotes");
    }

    @FXML
    public void onMenuItemSobreAction() {
        loadView("/gui/view/sobre.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private synchronized void loadView(String absoluteName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVbox = loader.load();

            Scene mainScene = Main2.getMainScene();
            VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVbox.getChildren());

        }
        catch (IOException e) {
            Alerts.showAlert("IO Exception", "Erro ao carregar FXML view", e.getMessage(), Alert.AlertType.ERROR);
        }

    }
}
