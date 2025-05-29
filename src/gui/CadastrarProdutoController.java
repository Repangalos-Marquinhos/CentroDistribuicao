package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastrarProdutoController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPreco;

    @FXML
    public void onCadastrar() {
        String nome = txtNome.getText();
        String preco = txtPreco.getText();
        System.out.println("Produto cadastrado: " + nome + " - R$" + preco);
        // Aqui você pode chamar DAO para salvar no banco
    }
}
