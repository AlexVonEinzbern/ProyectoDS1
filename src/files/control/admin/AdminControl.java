package files.control.admin;
import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminControl implements Initializable {
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
    @FXML    private Button allBton;
    @FXML    private AnchorPane panelPrincipal;
    @FXML    private AnchorPane panelFondo;
    @FXML    private Line addUserBar;
    @FXML    private Line editUserBar;
    @FXML    private Line statusUserBar;
    @FXML    private Line reportBar;
    @FXML    private HBox panelFuncionesUsuario;
    @FXML    private HBox panelFuncionesActivos;
    @FXML    private Button addBtonActivos;
    @FXML    private Button editBtonActivos;
    @FXML    private Button statBtonActivos;
    @FXML    private Button allBtonActivos;
    @FXML    private Button activosBton;
    @FXML    private Button usuarioBton;



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

    @FXML    void accionesActivos(ActionEvent event) {
        activosBton.setDisable(true);
        usuarioBton.setDisable(false);
        panelFuncionesActivos.setVisible(true);
        panelFuncionesUsuario.setVisible(false);
    }

    @FXML    void accionesUsuario(ActionEvent event) {
        activosBton.setDisable(false);
        usuarioBton.setDisable(true);
        panelFuncionesActivos.setVisible(false);
        panelFuncionesUsuario.setVisible(true);
    }
    @FXML    void addActivos(ActionEvent event) {

    }

   @FXML
    void editActivos(ActionEvent event) {

    }



    @FXML
    void showActivos(ActionEvent event) {

    }





    @FXML
    void statActivos(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panelFuncionesActivos.setVisible(false);
        panelFuncionesUsuario.setVisible(false);
    }
}