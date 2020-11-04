package files.control;

import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstadoUsuariosControl {
    private ConexionBase con;

    public EstadoUsuariosControl(ConexionBase con) {
        this.con = con;
    }
    @FXML   private TextField idUsuario;

    @FXML   private Button buscar;
    @FXML   private TextArea estadoDelUsuario;
    @FXML   private ChoiceBox<?> estado;
    @FXML   private Button registrar;

    @FXML   void anadir(ActionEvent event) {

    }
    @FXML   void buscarr(ActionEvent event) {

    }
}