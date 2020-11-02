package files.control;
import files.modelo.ConexionBase;
import files.modelo.Ventana;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane panelAccion;

    @FXML
    void addUsuario(ActionEvent event) throws IOException {
        AnchorPane panel;
        panel = new AnchorPane();
        Label a = new Label("hola mundo");
        panel.getChildren().addAll(a);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/formularioUsuarios.fxml"));
        FormUsuariosControl controller = new FormUsuariosControl(con);
        loader.setController(controller);
        AnchorPane root = loader.load();
        btAnadir.setDisable(true);
        panelAccion.getChildren().add(root) ;

       /* Scene scene = new Scene();
        Stage ventana = new Stage();
        ventana.setScene(scene);
        ventana.setTitle("Formulario para la creacion de usuarios");
        ventana.show();*/
    }


}