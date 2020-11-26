package files.control.obrero.facturas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import files.modelo.ConexionBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenerarFacturas {
    private ConexionBase con;

    public GenerarFacturas(ConexionBase base) {
        this.con = base;
    }
    @FXML    private Button buscar;
    @FXML    private TextField idFactura;
    @FXML    private TextField idCliente;
    @FXML    private TextField nombreCliente;
    @FXML    private TextField cedulaCLiente;
    @FXML    private TextField fechaDeVencimiento;
    @FXML    private TextField valorApagar;
    @FXML    private Button registrar;
    @FXML    private Button imprimir;
    @FXML    private Button limpiar;

    @FXML    void BuscarFactura(ActionEvent event) throws SQLException {
        ResultSet rs = con.consultar("select ");
    }

    @FXML    void registrarPago(ActionEvent event) {

    }
    @FXML    void limpiarCampos(ActionEvent event) {

    }
    @FXML    void imprimirRecibo(ActionEvent event) {

    }
}
