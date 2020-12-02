package files.control.operador;

import files.control.LoginControl;
import files.control.operador.cliente.AddClienteControl;
import files.control.operador.cliente.EditClienteControl;
import files.control.operador.cliente.StatusClienteControl;
import files.control.operador.facturas.GenerarFacturas;
import files.control.operador.facturas.RegistrarMedidas;
import files.control.operador.facturas.RegistrarPagos;
import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OperadorControl implements Initializable {
    private ConexionBase con;
    private AnchorPane panelAddCliente,panelEditCliente,panelStatusCliente,panelGenerarFacturas,panelRegistrarPago,panelMedidas;
    public OperadorControl(ConexionBase base) throws SQLException {
        this.con = base;
        // busca a los clientes que tienen mas de 60 dias sin pagar la factura y los inablilita ;
        ResultSet rs = con.consultar(
                "Select idcliente,estadoCliente from Cliente where idCliente =" +
                        "(select idCliente from factura where " +
                        "idFactura not in (select idFactura from RegistroPago )" +
                        "and fechaVenceFactura <= (current_date - '2 month'::interval));");
        while (rs.next()){
           if(rs.getBoolean(2)){
               int i = con.guardar("update cliente set estadocliente = false" +
                       "where idcliente ="+ rs.getInt(1)+";");
           }
        }
    }
    @FXML    private ImageView LOGO;
    @FXML    private HBox panelFuncionesFacturas;
    @FXML    private Button registrarBtn;
    @FXML    private Button generarbtn;
    @FXML    private HBox panelFuncionesCliete;
    @FXML    private Button addBton;
    @FXML    private Button editBton;
    @FXML    private Button statBton;
    @FXML    private Button allBton;
    @FXML    private Button clienteBton;
    @FXML    private Button facturasBton;
    @FXML    private Circle clienteCircle;
    @FXML    private Circle facturasCircle;
    @FXML    private AnchorPane bienvenida;
    @FXML    private AnchorPane panelPrincipal;
    @FXML    private AnchorPane panelFondo;
    @FXML    private Button medidasBtn;

    @FXML    void panelCliente(ActionEvent event) {
    	addBton.setDisable(false);
    	editBton.setDisable(false);
    	statBton.setDisable(false);
    	this.clienteCircle.setVisible(true);
    	this.facturasCircle.setVisible(false);
        facturasBton.setDisable(false);
        clienteBton.setDisable(true);
        bienvenida.setVisible(false);
        panelFuncionesCliete.setVisible(true);
        panelFuncionesFacturas.setVisible(false);
        panelPrincipal.getChildren().removeAll(panelGenerarFacturas,panelRegistrarPago,panelMedidas);
    }

    @FXML    void addCliente(ActionEvent event) throws IOException {
        panelFondo.setVisible(false);
        enableBtonClientes();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/files/vista/ventanaOperador/clientes/ventanaAddClient.fxml"));
        AddClienteControl controller = new AddClienteControl(con);
        loader.setController(controller);
        panelAddCliente = loader.load();
        addBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelStatusCliente,panelEditCliente);
        panelPrincipal.getChildren().add(panelAddCliente);
    }

    @FXML    void editCliente(ActionEvent event) throws IOException {
        enableBtonClientes();
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/files/vista/ventanaOperador/clientes/ventanaEditClient.fxml"));
        EditClienteControl controller = new EditClienteControl(con);
        loader.setController(controller);
        panelEditCliente= loader.load();
        editBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelAddCliente,panelStatusCliente);
        panelPrincipal.getChildren().add(panelEditCliente);
    }
    @FXML    void statusCliente(ActionEvent event) throws IOException {
        enableBtonClientes();
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/files/vista/ventanaOperador/clientes/ventanaEstadoCliente.fxml"));
        StatusClienteControl controller = new StatusClienteControl(con);
        loader.setController(controller);
        panelStatusCliente= loader.load();
        statBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelAddCliente,panelEditCliente);
        panelPrincipal.getChildren().add(panelStatusCliente);
    }
 
    private void enableBtonClientes(){
        statBton.setDisable(false);
        addBton.setDisable(false);
        editBton.setDisable(false);
    }

    @FXML    void panelFacturas(ActionEvent event) {
      	this.clienteCircle.setVisible(false);
    	this.facturasCircle.setVisible(true);
        bienvenida.setVisible(false);
        facturasBton.setDisable(true);
        clienteBton.setDisable(false);
        panelFuncionesCliete.setVisible(false);
        panelFuncionesFacturas.setVisible(true);
        registrarBtn.setDisable(false);
        generarbtn.setDisable(false);
        medidasBtn.setDisable(false);
        panelPrincipal.getChildren().removeAll(panelStatusCliente,panelAddCliente,panelEditCliente);
    }

    @FXML    void verGenerarFacturasPanel(ActionEvent event) throws IOException {
        registrarBtn.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/vista/ventanaOperador/facturas/generarFacturas.fxml"));
        GenerarFacturas controller = new GenerarFacturas(con);
        loader.setController(controller);
        panelGenerarFacturas= loader.load();
        generarbtn.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelRegistrarPago,panelMedidas);
        panelPrincipal.getChildren().add(panelGenerarFacturas);
    }

    @FXML    void verRegistrarpanel(ActionEvent event) throws IOException {
        generarbtn.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/files/vista/ventanaOperador/facturas/RegistarPago.fxml"));
        RegistrarPagos controller = new RegistrarPagos(con);
        loader.setController(controller);
        panelRegistrarPago= loader.load();
        registrarBtn.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelGenerarFacturas,panelMedidas);
        panelPrincipal.getChildren().add(panelRegistrarPago );
    }
    
    @FXML    void showPanelMedidas(ActionEvent event) throws IOException {
    	registrarBtn.setDisable(false);
    	generarbtn.setDisable(false);
    	panelFondo.setVisible(false);
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(
    			"/files/vista/ventanaOperador/facturas/RegistrarMedida.fxml"));
    	RegistrarMedidas controller = new RegistrarMedidas(con);
    	loader.setController(controller);
    	panelMedidas= loader.load();
    	medidasBtn.setDisable(true);
    	panelPrincipal.getChildren().removeAll(panelGenerarFacturas,panelRegistrarPago);
    	panelPrincipal.getChildren().add( panelMedidas);
    	
    }

    
    @FXML    void cerrarSesion(ActionEvent event) throws IOException {
        Stage ventana = (Stage) registrarBtn.getScene().getWindow();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
        panelFuncionesCliete.setVisible(false);
        panelFuncionesFacturas.setVisible(false);
    }
}
