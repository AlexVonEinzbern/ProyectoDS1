package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.SQLException;

public class LoginConrol {
    ConeccionBase base;
    @FXML
    private Button lgin;

    @FXML
    private PasswordField contraseña;

    @FXML
    private TextField usuario;

    @FXML
    void ConsultaLogin(ActionEvent event) throws SQLException {

        Window owner = lgin.getScene().getWindow();
        String passwordbase = "" ; //base.consulta();
        String usserbase = ""; //base.consulta();
        String password = contraseña.getText() ;
        String usser = usuario.getText();
         if(passwordbase.equals(password) && usserbase.equals(usser)){
             VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                     "Ingreso con exito","welcome "+ usser);
         }
    }

}

