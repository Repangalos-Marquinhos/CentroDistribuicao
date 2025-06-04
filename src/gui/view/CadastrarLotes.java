package gui.view;

import application.Main2;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Lote;
import model.entities.Usuario;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class CadastrarLotes implements Initializable {

    @FXML
    private TableView<Lote> tableViewLotes;

    @FXML
    private TableColumn<Lote, Integer> tableColumnIdLote;

    @FXML
    private TableColumn<Lote, Date> tableColumnData;

    @FXML
    private TableColumn<Lote, Usuario> tableColumnIdUsuario;

    @FXML
    private Button btnNovoLote;

    @FXML
    public void onBtnNovoLoteAction(){

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnIdLote.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data_pedido"));
        tableColumnIdUsuario.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));

        Stage stage = (Stage) Main2.getMainScene().getWindow();
        tableViewLotes.prefHeightProperty().bind(stage.heightProperty());
    }
}
