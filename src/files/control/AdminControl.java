package files.control;
import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import java.io.IOException;

public class AdminControl {
    private ConexionBase con;
    private AnchorPane panelAnadir;
    private AnchorPane panelEditar;
    private AnchorPane panelStatus;
    public AdminControl(ConexionBase con){
        this.con = con;
    }

    @FXML    private Button addBton;
    @FXML    private Button editBton;
    @FXML    private Button statBton;
    @FXML    private AnchorPane panelPrincipal;
    @FXML    private AnchorPane panelFondo;
    @FXML    private Line addUserBar;
    @FXML    private Line editUserBar;
    @FXML    private Line statusUserBar;
    @FXML    private Line reportBar;




    @FXML    void addUsuario(ActionEvent event) throws IOException {

        addUserBar.setVisible(true);
        editUserBar.setVisible(false);
        statusUserBar.setVisible(false);
        reportBar.setVisible(false);

        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/ventanaAdmin/ventanaAdmin.fxml"));
        FormUsuariosControl controller = new FormUsuariosControl(con);
        loader.setController(controller);
        panelAnadir = loader.load();
        addBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelEditar,panelStatus);
        panelPrincipal.getChildren().add(panelAnadir); ;
    }

    @FXML    void editUsser(ActionEvent event) throws IOException {
        addUserBar.setVisible(false);
        editUserBar.setVisible(true);
        statusUserBar.setVisible(false);
        reportBar.setVisible(false);

        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/ventanaAdmin/ventanaAdminEditar.fxml"));
        FormUsuariosControl controller = new FormUsuariosControl(con);
        loader.setController(controller);
        panelEditar = loader.load();
        addBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelStatus,panelAnadir);
        panelPrincipal.getChildren().add(panelEditar) ;
    }
    @FXML    void showStatus(ActionEvent event) throws IOException {
        addUserBar.setVisible(false);
        editUserBar.setVisible(false);
        statusUserBar.setVisible(true);
        reportBar.setVisible(false);

        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/ventanaAdmin/ventanaAdminEstado.fxml"));
        FormUsuariosControl controller = new FormUsuariosControl(con);
        loader.setController(controller);
        panelStatus = loader.load();
        addBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelEditar,panelAnadir);
        panelPrincipal.getChildren().add(panelStatus) ;
    }
}