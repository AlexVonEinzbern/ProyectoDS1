package files.control;
import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminControl {
    private ConexionBase con;
    public AdminControl(ConexionBase con){
        this.con = con;
    }

    @FXML    private Button btAnadir;

    @FXML    private AnchorPane panelPrincipal;


    @FXML
    void addUsuario(ActionEvent event) throws IOException {
        AnchorPane panel;
        panel = new AnchorPane();
        Label a = new Label("hola mundo");
        panel.getChildren().addAll(a);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/ventanaAdmin/ventanaAdmin.fxml"));
        FormUsuariosControl controller = new FormUsuariosControl(con);
        loader.setController(controller);
        Parent root = loader.load();
        btAnadir.setDisable(true);
        this.btAnadir.getScene().setRoot(root);
        //panelPrincipal.getChildren().add(root) ;
    }


}