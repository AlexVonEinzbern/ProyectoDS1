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


        String password = contraseña.getText() ;
        String usser = usuario.getText();
        String usserbase = base.consulta
                ("Select idusuario from usuarios where idusuario = "+usser,"idusuario");
        String passwordbase = base.consulta
                ("select password from usuarios where idusuario = "+usser,"password");
        Boolean activo = base.consultaBool
                ("select estaactivo from usuarios where idusuario ="+usser,"estaactivo");

        if(password.isEmpty()|| usser.isEmpty()) {
            VentanaAvisos.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "usuario o contraceña vacios");
            return;
        }
        if(passwordbase.equals(password) && usserbase.equals(usser) && activo){
            Ventana ventana = new Ventana(base.getConect(),"ventanaAdmin.fxml","Admin" );
            owner = ventana.getScene().getWindow();
            VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                    "Ingreso con exito","welcome "+ usser);
            Stage win = (Stage) this.contraseña.getScene().getWindow();
            win.close();
         }else {
             VentanaAvisos.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                     "Usuario o contraseña incorrectos por favor verifique la informacion");
             return;
         }

    }

}

