package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class Ventana extends Stage {

    private Connection base;
    public Ventana(Connection base,String fxmlrute,String title) throws IOException {
        this.base=base;
        Parent root = FXMLLoader.load(getClass().getResource(fxmlrute));
        Scene scene = new Scene(root);
        this.setScene(scene);
        this.setTitle(title);
        this.show();
    }
}
