package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Conexion;
import org.jetbrains.annotations.NotNull;

public class Main extends Application {
    private double xoffset;
    private double yoffset;
    @Override
    public void start(@NotNull Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        //esto es para que la interfaz se pueda mover con el mouse
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xoffset=event.getSceneX();
                yoffset=event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX()-xoffset);
                primaryStage.setY(event.getScreenY()-yoffset);
            }
        });


        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("login");
        primaryStage.show();

        Conexion con = new Conexion();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
