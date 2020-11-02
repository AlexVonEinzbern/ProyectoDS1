package files.control;
import files.modelo.ConexionBase;
import files.modelo.Ventana;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class AdminControl {
    private ConexionBase con;
    public AdminControl(ConexionBase con){
        this.con = con;
    }

    @FXML
    private Button btAnadir;

    @FXML
    void addUsuario(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/formularioUsuarios.fxml"));
        FormUsuariosControl controller = new FormUsuariosControl(con);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage ventana = new Stage();
        ventana.setScene(scene);
        ventana.setTitle("Formulario para la creacion de usuarios");
        ventana.show();
    }


}