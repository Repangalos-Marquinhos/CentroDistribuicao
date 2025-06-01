package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
        carregarTela("HistoricoPedidos.fxml");
    }

    @FXML public void abrirGerenciarProdutos() {
        carregarTela("Produtos.fxml");
    }

    @FXML public void abrirGerenciarPedidos() {
        carregarTela("Pedidos.fxml");
    }

    @FXML public void abrirAlterarStatus() {
        carregarTela("AlterarStatus.fxml");
    }
}