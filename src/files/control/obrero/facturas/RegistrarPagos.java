package files.control.obrero.facturas;

import files.modelo.VentanaAvisos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import files.modelo.ConexionBase;
import javafx.stage.Window;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrarPagos {
    private ConexionBase con;

    public RegistrarPagos(ConexionBase base) {
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
    @FXML    private TextField idUsuario;
    @FXML    private Button registrarArch;


    @FXML    void BuscarFactura(ActionEvent event) throws SQLException {
        int idFac= Integer.parseInt(idFactura.getText());
        int valor;
        ResultSet rs = con.consultar(
                "select c.idCliente,nombreCliente,cedulaCliente,estadoCliente,medida,fechaVenceFactura," +
                        "reconexion,unidadEnergia, fechaVenceFactura - current_date as interes "+
                        "from factura as f,cliente as c,detalleFactura as d,medida as m,configurarsistema as con" +
                        " where f.idfactura = "+idFac+" and f.idCliente = c.idCliente and " +
                        "f.idfactura = d.idfactura and c.idcliente = m.idcliente " +
                        "and d.idConfiguracion = con.idConfiguracion");
        if(rs.next()){
            idCliente.setText(""+rs.getInt(1));
            nombreCliente.setText(rs.getString(2));
            cedulaCLiente.setText(rs.getString(3));
            fechaDeVencimiento.setText(String.valueOf(rs.getDate(6)));
            valorApagar.setText(""+valorAPagar(rs.getInt(9),!rs.getBoolean(4),
                    rs.getInt("7"),rs.getInt(5),rs.getInt(8)));
            registrar.setDisable(false);
            imprimir.setDisable(false);
            limpiar.setDisable(false);
        }

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
// la ultima configuracion del sistema es (select * from configurarsistema order by idconfiguracion desc limit 1)
    @FXML    void registrarPago(ActionEvent event) throws SQLException {
        if(isFill()) {
            int i = con.guardar("insert into RegistroPago (idUsuario,idFactura,pago,fechaPago)" +
                    "values ("+idUsuario.getText()+", "+idFactura.getText()+", "+valorApagar.getText()+", current_date)");
            Window owner = nombreCliente.getScene().getWindow();
            VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                    "Registro Pago","Haz registrado exitosamente un nuevo Pago del cliente : "+ nombreCliente.getText());
        }else{
            Window owner = nombreCliente.getScene().getWindow();
            VentanaAvisos.showAlert(Alert.AlertType.ERROR,owner,
                    "Registro Pago","No hay pago que registrar verifique los campos!");
        }
        clear();
        registrar.setDisable(true);
        imprimir.setDisable(true);
        limpiar.setDisable(true);
    }
    
    @FXML
    void registrarArchivo(ActionEvent event) {

    }
    
    @FXML    void limpiarCampos(ActionEvent event) {
        clear();
        registrar.setDisable(true);
        imprimir.setDisable(true);
        limpiar.setDisable(true);
    }
    @FXML    void imprimirRecibo(ActionEvent event) {

        clear();
        registrar.setDisable(true);
        imprimir.setDisable(true);
        limpiar.setDisable(true);
    }
    private Boolean isFill(){
        return (!(idFactura.getText().equals("")) &&
                !(idCliente.getText().equals(""))&&
               !(idUsuario.getText().equals("")) );
    }
    private void clear(){
        idCliente.clear();
        idFactura.clear();
        nombreCliente.clear();
        idUsuario.clear();
        cedulaCLiente.clear();
        fechaDeVencimiento.clear();
        valorApagar.clear();
    }
}
