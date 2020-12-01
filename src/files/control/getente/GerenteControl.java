package files.control.getente;

import files.control.LoginControl;
import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GerenteControl {
    private ConexionBase con;
    public GerenteControl(ConexionBase con){
        this.con = con;
    }

    @FXML    private ImageView logo;
    @FXML    private HBox panelFuncionesGerente;
    @FXML    private Button btonConsultUser;
    @FXML    private Button btonConsultActive;
    @FXML    private Label rol;
    @FXML    private AnchorPane panelPrincipal;
    @FXML    private AnchorPane panelFondo;

    @FXML    void reportesActivos(ActionEvent event) {

    }

    @FXML    void reportesUsuarios(ActionEvent event) {

    }
    @FXML    void cerrarSesion(ActionEvent event) throws IOException {
        Stage ventana = (Stage) btonConsultActive.getScene().getWindow();
        ventana.close();
        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../vista/ventanaLogin.fxml"));
        LoginControl controller = new LoginControl(con = new ConexionBase());
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @FXML
    void salirApp(ActionEvent event) {
        System.exit(0);
    }

}
