package files.control.admin;
import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.sql.SQLException;

public class AdminControl {
    private ConexionBase con;
    private AnchorPane panelAnadir;
    private AnchorPane panelEditar;
    private AnchorPane panelStatus;
    private AnchorPane panelUsuarios;
    public AdminControl(ConexionBase con) throws IOException {
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
    @FXML    private Button allBton;




    @FXML    void addUsuario(ActionEvent event) throws IOException {

        addUserBar.setVisible(true);
        editUserBar.setVisible(false);
        statusUserBar.setVisible(false);
        reportBar.setVisible(false);
        editBton.setDisable(false);
        statBton.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../vista/ventanaAdmin/ventanaAdmin.fxml"));
        FormUsuariosControl controller = new FormUsuariosControl(con);
        loader.setController(controller);
        panelAnadir = loader.load();
        addBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelEditar,panelStatus,panelUsuarios);
        panelPrincipal.getChildren().add(panelAnadir); ;
    }

    @FXML    void editUsser(ActionEvent event) throws IOException {
        addUserBar.setVisible(false);
        editUserBar.setVisible(true);
        statusUserBar.setVisible(false);
        reportBar.setVisible(false);
        addBton.setDisable(false);
        statBton.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../vista/ventanaAdmin/ventanaAdminEditar.fxml"));
        EditarUsuariosControl controller = new EditarUsuariosControl(con);
        loader.setController(controller);
        panelEditar = loader.load();
        editBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelStatus,panelAnadir,panelUsuarios);
        panelPrincipal.getChildren().add(panelEditar) ;
    }
    @FXML    void showStatus(ActionEvent event) throws IOException {
        addUserBar.setVisible(false);
        editUserBar.setVisible(false);
        statusUserBar.setVisible(true);
        reportBar.setVisible(false);
        editBton.setDisable(false);
        addBton.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../vista/ventanaAdmin/ventanaAdminEstado.fxml"));
        EstadoUsuariosControl controller = new EstadoUsuariosControl(con);
        loader.setController(controller);
        panelStatus = loader.load();
        statBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelEditar,panelAnadir,panelUsuarios);
        panelPrincipal.getChildren().add(panelStatus) ;
    }

    @FXML
    void showUssers(ActionEvent event) throws IOException, SQLException {

        addUserBar.setVisible(false);
        editUserBar.setVisible(false);
        statusUserBar.setVisible(false);
        reportBar.setVisible(false);
        editBton.setDisable(false);
        addBton.setDisable(false);
        statBton.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../vista/ventanaAdmin/PanelUsuarios.fxml"));
        ListaUsuariosControl controller = new ListaUsuariosControl(con);
        loader.setController(controller);
        panelUsuarios = loader.load();
        allBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelEditar,panelAnadir,panelStatus);
        panelPrincipal.getChildren().add(panelUsuarios) ;
    }

}