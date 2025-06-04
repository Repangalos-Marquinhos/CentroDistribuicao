package gui.view;

import application.Main2;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastrarUsuarios implements Initializable {

    @FXML
    private TableView<Usuario> tableViewUsuarios;

    @FXML
    private TableColumn<Usuario, Integer> tableColumnIdUsuario;

    @FXML
    private TableColumn<Usuario,String> tableColumnSenha;

    @FXML
    private TableColumn<Usuario,String> tableColumnPermissao;

    @FXML
    private Button btnNovoUsuario;

    @FXML
    public void onBtnNovoUsuarioAction(){
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnIdUsuario.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        tableColumnSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        tableColumnPermissao.setCellValueFactory(new PropertyValueFactory<>("permissao"));

        Stage stage = (Stage) Main2.getMainScene().getWindow();
        tableViewUsuarios.prefHeightProperty().bind(stage.heightProperty());
    }
}
