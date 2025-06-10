package gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.services.Utilidades;

import java.sql.Date;
import java.time.LocalDate;

public class CadastroProdutosController {

    @FXML
    private TextField txtNome;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button btnCadastrar;

    @FXML
    public void initialize() {
        // Inicializa o ComboBox, por exemplo com seções
        comboBox.getItems().addAll("Eletrônicos", "Alimentos", "Limpeza", "Outros");
    }

    @FXML
    private void onCadastrar() {
        String nome = txtNome.getText();
        LocalDate data = datePicker.getValue();
        String categoria = comboBox.getValue();

        if (nome == null || nome.isEmpty() || data == null || categoria == null) {
            Alert alert = new Alert(AlertType.ERROR, "Por favor, preencha todos os campos!");
            alert.show();
            return;
        }

        int idsecaocontrole;

        if (categoria.equals("Eletrônicos")) { idsecaocontrole = 1; }
        else if (categoria.equals("Alimentos")) { idsecaocontrole = 2; }
        else if (categoria.equals("Limpeza")) { idsecaocontrole = 3; }
        else { idsecaocontrole = 4;}

        Utilidades.cadastrarProduto(nome, Date.valueOf(data), idsecaocontrole);
        // Aqui você pode adicionar lógica para salvar no banco, por exemplo



        System.out.println("Produto cadastrado:");
        System.out.println("Nome: " + nome);
        System.out.println("Data: " + data);
        System.out.println("Categoria: " + categoria);

        Alert alert = new Alert(AlertType.INFORMATION, "Produto cadastrado com sucesso!");
        alert.show();
    }
}