package files.control;

import files.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormUsuariosControl {
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


        String cedula = cedulaUsuario.getText();
        nuevo= new Usuario(nombreUsuario.getText(), Integer.parseInt(cedula),
                direccionUsu.getText(), Integer.parseInt(telefonoUsu.getText()),new Date(System.currentTimeMillis()),
                sedes.getValue().toString(),roles.getValue().toString(),true, cedula);
    }

}
