package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.services.Utilidades;

public class CadastroUsuarioController {

    @FXML
    private TextField txtId;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private ComboBox<String> comboPermissao;

    @FXML
    private Button btnCadastrar;

    @FXML
    public void initialize() {
        // Popula a ComboBox com opções de permissão
        comboPermissao.getItems().addAll("Administrador", "Funcionário", "Visitante");
    }

    @FXML
    private void onCadastrar() {
        String idStr = txtId.getText();
        String senha = txtSenha.getText();
        String permissao = comboPermissao.getValue();

        if (idStr == null || idStr.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty() ||
                permissao == null) {

            mostrarAlerta("Erro", "Todos os campos devem ser preenchidos.");
            return;
        }

        int id = Integer.parseInt(idStr);

        // Aqui você pode adicionar lógica para salvar no banco, por exemplo
        System.out.println("Usuario cadastrado: ");
        System.out.println("ID: " + id);
        System.out.println("senha " + senha);
        System.out.println("permissao: " + permissao);

        Utilidades.cadastrarUsuario(senha, id);

        mostrarAlerta("Sucesso", "Usuário cadastrado com sucesso!");

        // Limpa os campos
        txtId.clear();
        txtSenha.clear();
        comboPermissao.setValue(null);
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
