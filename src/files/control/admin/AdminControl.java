package files.control.admin;
import files.control.admin.usuarios.EditarUsuariosControl;
import files.control.admin.usuarios.EstadoUsuariosControl;
import files.control.admin.usuarios.FormUsuariosControl;
import files.control.admin.usuarios.ListaUsuariosControl;
import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
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
    private AnchorPane panelAnadirActivos;
    private AnchorPane panelEditarActivos;
    private AnchorPane panelAllActivos;

    public AdminControl(ConexionBase con) throws IOException {
        this.con = con;
         }

    @FXML    private Button addBton;
    @FXML    private Button editBton;
    @FXML    private Button statBton;
    @FXML    private Button allBton;
    @FXML    private AnchorPane panelPrincipal;
    @FXML    private AnchorPane panelFondo;
    @FXML    private HBox panelFuncionesUsuario;
    @FXML    private HBox panelFuncionesActivos;
    @FXML    private Button addBtonActivos;
    @FXML    private Button editBtonActivos;
    @FXML    private Button allBtonActivos;
    @FXML    private Button activosBton;
    @FXML    private Button usuarioBton;
    @FXML    private Circle userCircle;
    @FXML    private Circle activesCircle;
    @FXML    private AnchorPane bienvenida;



    @FXML    void addUsuario(ActionEvent event) throws IOException {

        editBton.setDisable(false);
        statBton.setDisable(false);
        allBton.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../vista/ventanaAdmin/usuario/ventanaAdmin.fxml"));
        FormUsuariosControl controller = new FormUsuariosControl(con);
        loader.setController(controller);
        panelAnadir = loader.load();
        addBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelEditar,panelStatus,panelUsuarios);
        panelPrincipal.getChildren().add(panelAnadir); ;
    }

    @FXML    void editUsser(ActionEvent event) throws IOException {

        addBton.setDisable(false);
        statBton.setDisable(false);
        allBton.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../vista/ventanaAdmin/usuario/ventanaAdminEditar.fxml"));
        EditarUsuariosControl controller = new EditarUsuariosControl(con);
        loader.setController(controller);
        panelEditar = loader.load();
        editBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelStatus,panelAnadir,panelUsuarios);
        panelPrincipal.getChildren().add(panelEditar) ;
    }

    @FXML    void showStatus(ActionEvent event) throws IOException {

        editBton.setDisable(false);
        addBton.setDisable(false);
        allBton.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../vista/ventanaAdmin/usuario/ventanaAdminEstado.fxml"));
        EstadoUsuariosControl controller = new EstadoUsuariosControl(con);
        loader.setController(controller);
        panelStatus = loader.load();
        statBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelEditar,panelAnadir,panelUsuarios);
        panelPrincipal.getChildren().add(panelStatus) ;
    }

    @FXML    void showUssers(ActionEvent event) throws IOException, SQLException {


        editBton.setDisable(false);
        addBton.setDisable(false);
        statBton.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../vista/ventanaAdmin/usuario/PanelUsuarios.fxml"));
        ListaUsuariosControl controller = new ListaUsuariosControl(con);
        loader.setController(controller);
        panelUsuarios = loader.load();
        allBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelEditar,panelAnadir,panelStatus);
        panelPrincipal.getChildren().add(panelUsuarios) ;
    }

    @FXML    void accionesActivos(ActionEvent event) {
        bienvenida.setVisible(false);
        activesCircle.setVisible(true);
        userCircle.setVisible(false);
        panelPrincipal.getChildren().removeAll(panelUsuarios,panelStatus,panelAnadir,panelEditar,
                panelAllActivos,panelAnadirActivos,panelEditarActivos);
        activosBton.setDisable(true);
        usuarioBton.setDisable(false);
        enable();
        panelFuncionesActivos.setVisible(true);
        panelFuncionesUsuario.setVisible(false);
    }

    @FXML    void accionesUsuario(ActionEvent event) {

        bienvenida.setVisible(false);
        activesCircle.setVisible(false);
        userCircle.setVisible(true);
        panelPrincipal.getChildren().removeAll(panelUsuarios,panelStatus,panelAnadir,panelEditar,
                panelAllActivos,panelAnadirActivos,panelEditarActivos);
        activosBton.setDisable(false);
        usuarioBton.setDisable(true);
        enable();
        panelFuncionesActivos.setVisible(false);
        panelFuncionesUsuario.setVisible(true);
    }

    @FXML    void addActivos(ActionEvent event) {

    }

    @FXML    void editActivos(ActionEvent event) {

    }

    @FXML    void showActivos(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panelFuncionesActivos.setVisible(false);
        panelFuncionesUsuario.setVisible(false);
    }

    public void enable(){
        addBton.setDisable(false);
        editBton.setDisable(false);
        statBton.setDisable(false);
        allBton.setDisable(false);
        addBtonActivos.setDisable(false);
        editBtonActivos.setDisable(false);
        allBtonActivos.setDisable(false);
        }
}