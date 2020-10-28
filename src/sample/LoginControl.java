package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class LoginControl {
    ConeccionBase base;
    public LoginControl(){
        base = new ConeccionBase();
    }
    @FXML
    private Button lgin;

    @FXML
    private PasswordField contraseña;

    @FXML
    private TextField usuario;

    @FXML
    void ConsultaLogin(ActionEvent event) throws SQLException, IOException {

        Window owner = lgin.getScene().getWindow();
        String passwordbase = "as" ; //base.consulta();
        String usserbase = "as"; //base.consulta();
        String password = contraseña.getText() ;
        String usser = usuario.getText();
        if(password.isEmpty()) {
            VentanaAvisos.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Por favor ingresa la contraseña");
            return;
        }
        if(usser.isEmpty()) {
            VentanaAvisos.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "por favor ingresa el usuario");
            return;
        }
         if(passwordbase.equals(password) && usserbase.equals(usser)){
             VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                     "Ingreso con exito","welcome "+ usser);
             Ventana ventana = new Ventana(base.getConect(),"ventanaAdmin.fxml","Admin" );
             Stage win = (Stage) this.contraseña.getScene().getWindow();
             win.close();
         }else {
             VentanaAvisos.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                     "Usuario o contraseña incorrectos por favor verifique la informacion");
             return;
         }

    }

}

