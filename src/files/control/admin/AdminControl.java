package files.control.admin;
import files.control.LoginControl;
import files.control.admin.activos.EditarActivosControl;
import files.control.admin.activos.FormActivosControl;
import files.control.admin.activos.ListaActivosControl;
import files.control.admin.usuarios.EditarUsuariosControl;
import files.control.admin.usuarios.EstadoUsuariosControl;
import files.control.admin.usuarios.FormUsuariosControl;
import files.control.admin.usuarios.ListaUsuariosControl;
import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private AnchorPane panelConfig;

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
        enable();
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/vista/ventanaAdmin/usuario/ventanaAdmin.fxml"));
        FormUsuariosControl controller = new FormUsuariosControl(con);
        loader.setController(controller);
        panelAnadir = loader.load();
        addBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelEditar,panelStatus,panelUsuarios);
        panelPrincipal.getChildren().add(panelAnadir); ;
    }

    @FXML    void editUsser(ActionEvent event) throws IOException {
        enable();
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/vista/ventanaAdmin/usuario/ventanaAdminEditar.fxml"));
        EditarUsuariosControl controller = new EditarUsuariosControl(con);
        loader.setController(controller);
        panelEditar = loader.load();
        editBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelStatus,panelAnadir,panelUsuarios);
        panelPrincipal.getChildren().add(panelEditar) ;
    }

    @FXML    void showStatus(ActionEvent event) throws IOException {
        enable();
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/vista/ventanaAdmin/usuario/ventanaAdminEstado.fxml"));
        EstadoUsuariosControl controller = new EstadoUsuariosControl(con);
        loader.setController(controller);
        panelStatus = loader.load();
        statBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelEditar,panelAnadir,panelUsuarios);
        panelPrincipal.getChildren().add(panelStatus) ;
    }

    @FXML    void showUssers(ActionEvent event) throws IOException, SQLException {
        enable();
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/vista/ventanaAdmin/usuario/PanelUsuarios.fxml"));
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
        panelPrincipal.getChildren().removeAll(panelUsuarios,panelStatus,panelAnadir,panelEditar,panelConfig);
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
        panelPrincipal.getChildren().removeAll(panelAllActivos,panelAnadirActivos,panelEditarActivos,panelConfig);
        activosBton.setDisable(false);
        usuarioBton.setDisable(true);
        enable();
        panelFuncionesActivos.setVisible(false);
        panelFuncionesUsuario.setVisible(true);
    }

    @FXML    void addActivos(ActionEvent event) throws IOException {
        editBtonActivos.setDisable(false);
        allBtonActivos.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/vista/ventanaAdmin/activos/addActive.fxml"));
        FormActivosControl controller = new FormActivosControl(con);
        loader.setController(controller);
        panelAnadirActivos = loader.load();
        addBtonActivos.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelAllActivos,panelEditarActivos);
        panelPrincipal.getChildren().add(panelAnadirActivos) ;
    }

    @FXML    void editActivos(ActionEvent event) throws IOException {
        addBtonActivos.setDisable(false);
        allBtonActivos.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/vista/ventanaAdmin/activos/editActive.fxml"));
        EditarActivosControl controller = new EditarActivosControl(con);
        loader.setController(controller);
        panelEditarActivos = loader.load();
        editBtonActivos.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelAllActivos,panelAnadirActivos);
        panelPrincipal.getChildren().add(panelEditarActivos);
    }

    @FXML    void showActivos(ActionEvent event) throws IOException {
        editBtonActivos.setDisable(false);
        allBtonActivos.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/vista/ventanaAdmin/activos/allActive.fxml"));
        ListaActivosControl controller = new ListaActivosControl(con);
        loader.setController(controller);
        panelAllActivos = loader.load();
        allBtonActivos.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelAnadirActivos,panelEditarActivos);
        panelPrincipal.getChildren().add(panelAllActivos);
    }

    @FXML    void abrirConfiguraciones(MouseEvent event) throws IOException {
        editBtonActivos.setDisable(false);
        allBtonActivos.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/vista/ventanaAdmin/ventanaConfig.fxml"));
        ConfigControl controller = new ConfigControl(con);
        loader.setController(controller);
        panelConfig = loader.load();
        panelFuncionesActivos.setVisible(false);
        panelFuncionesUsuario.setVisible(false);
        activesCircle.setVisible(false);
        userCircle.setVisible(false);
        activosBton.setDisable(false);
        usuarioBton.setDisable(false);
        panelPrincipal.getChildren().removeAll(panelStatus,panelAnadir,panelEditar,
                panelAnadirActivos,panelEditarActivos,panelAllActivos);
        panelPrincipal.getChildren().add(panelConfig);
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
    @FXML    void cerrarSesion(ActionEvent event) throws IOException {
        Stage ventana = (Stage) allBton.getScene().getWindow();
        ventana.close();
        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/vista/ventanaLogin.fxml"));
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