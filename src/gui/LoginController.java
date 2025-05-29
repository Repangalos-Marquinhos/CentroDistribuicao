package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtSenha;

    @FXML
    public void onEntrar(ActionEvent event) {
        String usuario = txtUsuario.getText();
        String senha = txtSenha.getText();

        // Aqui você pode validar com o banco ou hardcoded:
        if ("admin".equals(usuario) && "123".equals(senha)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CadastrarProdutoView.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Cadastrar Produto");
                stage.setScene(new Scene(root));
                stage.show();

                // Fechar janela de login
                Stage loginStage = (Stage) txtUsuario.getScene().getWindow();
                loginStage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usuário ou senha inválidos!");
        }
    }
}
