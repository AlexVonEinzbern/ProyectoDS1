package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class FormularioUsuariosControl {
    Usuario nuevo;

    @FXML
    private TextField nombreUsuario;

    @FXML
    private TextField cedulaUsuario;

    @FXML
    private TextField direccionUsu;

    @FXML
    private TextField telefonoUsu;

    @FXML
    private ChoiceBox<?> roles;

    @FXML
    private Button guardar;

    @FXML
    private Button Limpiar;

    @FXML
    private Button salir;

    @FXML
    private ChoiceBox<?> sedes;

    @FXML
    void CerrarVentana(ActionEvent event) {

    }

    @FXML
    void borrarDatos(ActionEvent event) {

    }

    @FXML
    void crearUsuario(ActionEvent event) {

    }

}
