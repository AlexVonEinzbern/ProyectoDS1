package files;

import files.control.LoginControl;
import files.modelo.ConexionBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static ConexionBase con;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/ventanaLogin.fxml"));
        LoginControl controller = new LoginControl(con=new ConexionBase());
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 300, 265));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        con.cerrar();
    }
}
