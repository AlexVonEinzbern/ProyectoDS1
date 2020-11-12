package files.control.obrero;

import files.control.admin.activos.EditarActivosControl;
import files.control.obrero.cliente.AddClienteControl;
import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class ObreroControl {
    private ConexionBase con;
    private AnchorPane panelAddCliente;
    public ObreroControl(ConexionBase base){
        this.con = base;
    }

    @FXML
    private ImageView LOGO;

    @FXML
    private HBox panelFuncionesFacturas;

    @FXML
    private Button addBtonActivos;

    @FXML
    private Button editBtonActivos;

    @FXML
    private Button allBtonActivos;

    @FXML
    private HBox panelFuncionesCliete;

    @FXML
    private Button addBton;

    @FXML
    private Button editBton;

    @FXML
    private Button statBton;

    @FXML
    private Button allBton;

    @FXML
    private Button clienteBton;

    @FXML
    private Button facturasBton;

    @FXML
    private Circle clienteCircle;

    @FXML
    private Circle facturasCircle;

    @FXML
    private AnchorPane bienvenidaOPerador;

    @FXML
    private AnchorPane panelPrincipal;

    @FXML
    private AnchorPane panelFondo;

    @FXML    void panelCliente(ActionEvent event) {
        facturasBton.setDisable(false);
        clienteBton.setDisable(true);
        panelFuncionesCliete.setVisible(true);
        panelFuncionesFacturas.setVisible(false);
    }
    @FXML
    void addActivos(ActionEvent event) {

    }

    @FXML
    void addCliente(ActionEvent event) throws IOException {

        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../vista/ventanaOperador/clientes/ventanaAddClient.fxml"));
        AddClienteControl controller = new AddClienteControl(con);
        loader.setController(controller);
        panelAddCliente = loader.load();
        addBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll();
        panelPrincipal.getChildren().add(panelAddCliente);
    }

    @FXML
    void editActivos(ActionEvent event) {

    }

    @FXML
    void editCliente(ActionEvent event) {

    }

    @FXML
    void mostrarClientes(ActionEvent event) {

    }



    @FXML
    void showActivos(ActionEvent event) {

    }

    @FXML
    void statusCliente(ActionEvent event) {

    }


    @FXML
    void panelFacturas(ActionEvent event) {
        facturasBton.setDisable(true);
        clienteBton.setDisable(false);
        panelFuncionesCliete.setVisible(false);
        panelFuncionesFacturas.setVisible(true);
    }

}
