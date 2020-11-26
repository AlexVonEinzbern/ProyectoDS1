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
    @FXML    private TextField idUsuario;


    @FXML    void BuscarFactura(ActionEvent event) throws SQLException {
        int idFac= Integer.parseInt(idFactura.getText());
        int valor;
        ResultSet rs = con.consultar(
                "select c.idCliente,nombreCliente,cedulaCliente,estadoCliente,medida,fechaVenceFactura," +
                        "reconexion,unidadEnergia, fechaVenceFactura - current_date as interes"+
                        "from factura as f,cliente as c,detalleFactura as d,medida as m,configurarsistema as con" +
                        " where f.idfactura = "+idFac+" and f.idCliente = c.idCliente and " +
                        "f.idfactura = d.idfactura and c.idcliente = m.idcliente " +
                        "and d.idConfiguracion = con.idConfiguracion");
        if(rs.next()){
            idCliente.setText(""+rs.getInt(1));
            nombreCliente.setText(rs.getString(2));
            cedulaCLiente.setText(rs.getString(3));
            fechaDeVencimiento.setText(String.valueOf(rs.getDate(6)));
            valor = rs.getInt(5)*rs.getInt(8);
            int interes = rs.getInt(9);
            if(interes<0){
                if((interes*-1)>30){
                    valor *= 0.30;
                }else{
                    valor *= (interes*-1/100);
                }
            }
            if(!rs.getBoolean(4)){
                valor+=  rs.getInt("7");
            }

            valorApagar.setText(""+valor);
        }
    }
// la ultima configuracion del sistema es (select * from configurarsistema order by idconfiguracion desc limit 1)
    @FXML    void registrarPago(ActionEvent event) throws SQLException {
        if(isFill()) {
            int i = con.guardar("insert into RegistroPago (idUsuario,idFactura,pago,fechaPago)" +
                    "values ()");
        }
        clear();
    }
    @FXML    void limpiarCampos(ActionEvent event) {
        clear();
    }
    @FXML    void imprimirRecibo(ActionEvent event) {
        clear();
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
