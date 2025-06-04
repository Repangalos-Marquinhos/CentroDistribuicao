package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.stage.Stage;
import model.dao.UsuarioDao;
import model.dao.impl.DaoFactory;
import model.entities.Usuario;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtSenha;

    @FXML
    public void onEntrar(ActionEvent event) {
        String usuarioStr = txtUsuario.getText();
        String senha = txtSenha.getText();

        int usuario;
        try {
            usuario = Integer.parseInt(usuarioStr);
        } catch (NumberFormatException e) {
            mostrarAlerta("Usuário inválido!");
            txtUsuario.clear();
            txtSenha.clear();
            return;
        }

        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
        Usuario usuarioObj = usuarioDao.findByUsuarioAndSenha(usuario, senha);

        if (usuarioObj != null) {
            // Login OK, abre a próxima tela
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Main.fxml"));
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
            mostrarAlerta("Usuário ou senha inválidos!");
            txtUsuario.clear();
            txtSenha.clear();
        }
    }

    // Método auxiliar para mostrar o pop-up
    private void mostrarAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de Login");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}