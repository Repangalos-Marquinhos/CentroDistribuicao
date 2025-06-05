package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class MainController {

    @FXML
    private StackPane conteudoArea;

    private void carregarTela(String caminho) {
        try {
            Node tela = FXMLLoader.load(getClass().getResource("/gui/view/" + caminho));
            conteudoArea.getChildren().setAll(tela);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void abrirCadastroUsuario() {
        carregarTela("CadastroUsuario.fxml");
    }

    @FXML public void abrirCadastroProdutosPedidos() {
        carregarTela("CadastroProdutosPedidos.fxml");
    }

    @FXML public void abrirHistoricoPedidos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Pedidos.fxml"));
            Parent pedidosView = loader.load();
            conteudoArea.getChildren().clear();
            conteudoArea.getChildren().add(pedidosView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void abrirGerenciarProdutos() {
        carregarTela("Produtos.fxml");
    }

    @FXML public void abrirGerenciarPedidos() {
        carregarTela("Pedidos.fxml");
    }

    @FXML public void abrirAlterarStatus() {
        carregarTela("/gui/view/AlterarStatus.fxml");
    }
}