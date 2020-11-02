package files.modelo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
// USELESS
public class Ventana extends Stage {

    private ConexionBase con;
    public Ventana(ConexionBase conexion,String fxmlrute,String title) throws IOException {
        this.con=conexion;
        Parent root = FXMLLoader.load(getClass().getResource(fxmlrute));
        Scene scene = new Scene(root);
        this.setScene(scene);
        this.setTitle(title);
        this.show();
    }

}
