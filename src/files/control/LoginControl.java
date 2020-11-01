package files.control;
import files.modelo.VentanaAvisos;
import files.modelo.ConexionBase;
import files.modelo.Ventana;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class LoginControl {
    ConexionBase con;
    public LoginControl(ConexionBase con){
       this.con = con;
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
        int usser = 0;
        try {
            usser = Integer.parseInt(usuario.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            VentanaAvisos.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Ingrese un usuario valido ");
        }


        if(password.isEmpty() || (usser == 0)){
            VentanaAvisos.showAlert(Alert.AlertType.ERROR, owner, " Error!",
                    "usuario o contraceña vacios");
            return;
        }

        ResultSet rs = con.consultar
                ("Select * from usuarios where idusuario = "+usser);
        int usserbase = 0;
        String passwordbase = null;
        Boolean activo= null;
        String rol= null ;
        while(rs.next()){
             usserbase = rs.getInt("idusuario");
             passwordbase = rs.getString("password");
             activo = rs.getBoolean("estaactivo");
             rol = rs.getString("rolusuario");
        }
         //   System.out.println(usserbase +"  "+ passwordbase +"  " + activo +"   "+ rol );
         //   System.out.println(passwordbase.equals(password) +"  "+ usserbase==usser +"   "+ activo);
        if(passwordbase.equals(password)  && activo){
            switch(rol){
                case "Admin":
            Ventana ventana = new Ventana(con,"../vista/ventanaAdmin.fxml",rol );
            owner = ventana.getScene().getWindow();
            VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                    "Ingreso con exito","welcome "+ usser);
            Stage win = (Stage) this.contraseña.getScene().getWindow();

            win.close();
            }
         }else {
             VentanaAvisos.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                     "Usuario o contraseña incorrectos por favor verifique la informacion");
             return;
         }

    }

}

