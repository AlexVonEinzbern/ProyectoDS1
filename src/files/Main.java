package files;

import files.control.admin.AdminControl;
import files.control.getente.GerenteControl;
import files.control.obrero.ObreroControl;
import files.modelo.ConexionBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static ConexionBase con;
/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/ventanaLogin.fxml"));
        LoginControl controller = new LoginControl(con = new ConexionBase());
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();

    */ /*
     // operador
    @Override
    public void start(Stage primaryStage) throws Exception{
      //  primaryStage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/ventanaOperador.fxml"));
        ObreroControl controller = new ObreroControl(con=new ConexionBase());
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
       // scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin");
        primaryStage.show();
    }
    /*
    // Admin
    @Override
    public void start(Stage primaryStage) throws Exception{
        //  primaryStage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/ventanaAdmin.fxml"));
        AdminControl controller = new AdminControl(con=new ConexionBase());
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        // scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin");
        primaryStage.show();
    }
*/
    // Gerente
    @Override
    public void start(Stage primaryStage) throws Exception{
        //  primaryStage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/ventanaGerente.fxml"));
        GerenteControl controller = new GerenteControl(con=new ConexionBase());
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        // scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        con.cerrar();
    }


    }
