package files.control.obrero.facturas;

import files.control.admin.activos.ListaActivosControl;
import files.modelo.ConexionBase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GenerarFacturas implements Initializable {
    private ConexionBase con;
    private final ObservableList<GenerarFacturas.ListaFactura> datos = FXCollections.observableArrayList();

    public GenerarFacturas(ConexionBase base) {
        this.con = base;
    }

    @FXML    private TableView<ListaFactura> tablaFacturas;
    @FXML    private TableColumn<ListaFactura, String> cliente;
    @FXML    private TableColumn<ListaFactura, String> facturaTable;
    @FXML    private TableColumn<ListaFactura, String> valorAPagar;
    @FXML    private Button generarBtn;
    @FXML    private Button buscar;
    @FXML    private TextField factura;
    @FXML    private TextField nombreClienteF;
    @FXML    private TextField idFacturaF;
    @FXML    private TextField valorAPagarF;

    @FXML    void BuscarFactura(ActionEvent event) {

        try {
            int fact = Integer.parseInt(factura.getText());
            ResultSet rs = con.consultar(
                    "select nombreCliente,estadoCliente,medida, reconexion,unidadEnergia, " +
                            " fechaVenceFactura - current_date as interes, f.idfactura "+
                            " from factura as f,cliente as c,detalleFactura as " +
                            " d,medida as m,configurarsistema as con " +
                            " where not estadofactura and f.idfactura = "+fact+"and f.idCliente = c.idCliente and " +
                            " f.idfactura = d.idfactura and c.idcliente = m.idcliente " +
                            " and d.idConfiguracion = con.idConfiguracion ;");

            while(rs.next()){
                nombreClienteF.setText(rs.getString(2));
                idFacturaF.setText(String.valueOf(rs.getInt(10)));
                valorAPagarF.setText(String.valueOf(valorAPagar(rs.getInt("interes"),
                        !rs.getBoolean("estadoCliente"),rs.getInt("reconexion"),
                        rs.getInt("unidadEnergia"),rs.getInt("medida"))));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML    void generarFactura(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cliente.setCellValueFactory(new PropertyValueFactory<>("Cliente"));
        facturaTable.setCellValueFactory(new PropertyValueFactory<>("FacturaTable"));
        valorAPagar.setCellValueFactory(new PropertyValueFactory<>("ValorAPagar"));
        try {
            ResultSet rs = con.consultar(
                    "select c.idCliente,nombreCliente,cedulaCliente,estadoCliente,medida,fechaVenceFactura, " +
                            " reconexion,unidadEnergia, fechaVenceFactura - current_date as interes,f.idfactura "+
                            " from factura as f,cliente as c,detalleFactura as d,medida as m,configurarsistema as con " +
                            " where not estadofactura and f.idCliente = c.idCliente and " +
                            " f.idfactura = d.idfactura and c.idcliente = m.idcliente " +
                            " and d.idConfiguracion = con.idConfiguracion ;");

            while(rs.next()){
               datos.add(new GenerarFacturas.ListaFactura(
                        rs.getString(2),
                        String.valueOf(rs.getInt(10)),
                        String.valueOf(valorAPagar(rs.getInt("interes"),!rs.getBoolean("estadoCliente"),
                                rs.getInt("reconexion"),rs.getInt("unidadEnergia"),rs.getInt("medida")))            )          );

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tablaFacturas.setItems(datos);
    }
    public int valorAPagar(int interes,boolean estado,int reconexion,int costoUnidad,int consumo){
        int valor = costoUnidad * consumo;
        if (interes < 0) {
            if((interes*-1)>30){
                valor *= 0.30;
            }else{
                valor *= (interes*-1/100);
            }
            if(estado){
                valor+=  reconexion;
            }
        }
        return valor;
    }

    public static class ListaFactura {
        private final SimpleStringProperty cliente;
        private final SimpleStringProperty facturaTable;
        private final SimpleStringProperty valorAPagar;

        private ListaFactura(String idCliente, String facturaTable, String valorApagar) {
            this.cliente = new SimpleStringProperty(idCliente);
            this.facturaTable = new SimpleStringProperty(facturaTable);
            this.valorAPagar = new SimpleStringProperty(valorApagar);
        }

        public String getCliente() {
            return cliente.get();
        }
        public void setCliente(String fName) {
            cliente.set(fName);
        }
        public String getFacturaTable() {
            return facturaTable.get();
        }
        public void setFacturaTable(String fName) {
            facturaTable.set(fName);
        }
        public String getValorAPagar() {
            return valorAPagar.get();
        }
        public void setValorAPagar(String fName) {
            valorAPagar.set(fName);
        }
    }

}
