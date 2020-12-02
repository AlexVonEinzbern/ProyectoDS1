package files.control.operador.facturas;

import files.modelo.VentanaAvisos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import files.modelo.CSVReader;
import files.modelo.ConexionBase;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RegistrarPagos {
    private ConexionBase con;
    private CSVReader csvRead;

    public RegistrarPagos(ConexionBase base) {
        this.con = base;
        csvRead = new CSVReader(con);
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


    @FXML    void BuscarFactura(ActionEvent event)  {
        int idCli= Integer.parseInt(idCliente.getText());
        int valor;
        ResultSet rs;
		try {
			rs = con.consultar(
			        "select c.idCliente,nombreCliente,cedulaCliente,estadoCliente,medida,fechaVenceFactura," +
			                "reconexion,unidadEnergia, fechaVenceFactura - current_date as interes, f.idFactura "+
			                "from factura as f,cliente as c,detalleFactura as d,medida as m,configurarsistema as con" +
			                " where not estadofactura and f.idCliente = "+idCli+" and f.idCliente = c.idCliente and " +
			                "f.idfactura = d.idfactura and c.idcliente = m.idcliente " +
			                "and d.idConfiguracion = con.idConfiguracion");
			  if(rs.next()){
				  idFactura.setText(String.valueOf(rs.getInt(10)));
				  idCliente.setText(""+rs.getInt(1));
				  nombreCliente.setText(rs.getString(2));
				  cedulaCLiente.setText(rs.getString(3));
				  fechaDeVencimiento.setText(String.valueOf(rs.getDate(6)));
				  valorApagar.setText(""+valorAPagar(rs.getDouble(9),!rs.getBoolean(4),
						  rs.getDouble(7),rs.getDouble(5),rs.getDouble(8)));
				  registrar.setDisable(false);
				  limpiar.setDisable(false);
		        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Window owner = nombreCliente.getScene().getWindow();
			VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
					"Buscar Factura","Error Factura no encontrada: "+ e);
			System.out.println(e);
		}
      

    }

    public Double valorAPagar(Double interes,boolean estado,Double reconexion,Double costoUnidad,Double consumo){
        Double valor = costoUnidad * consumo;
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
        
        registrar.setDisable(true);
        imprimir.setDisable(false);
        limpiar.setDisable(false);
    }
    
    @FXML
    void registrarArchivo(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	Stage stage = (Stage) idCliente.getScene().getWindow();
    	File file = fileChooser.showOpenDialog(stage);
    	
    	if(file != null) {
    		try {
				csvRead.CSVReaderMedida(file);
				VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,stage,
	                    "Registro Pago","Pagos Registrados Exitosamente!");
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				VentanaAvisos.showAlert(Alert.AlertType.ERROR,stage,
	                    "Registro Pago","Error al alcanzar archivo : "+ e);
			}
    	}
    	
    }
    
    @FXML    void limpiarCampos(ActionEvent event) {
        clear();
        registrar.setDisable(true);
        imprimir.setDisable(true);
        limpiar.setDisable(true);
    }
    @FXML    void imprimirRecibo(ActionEvent event) {
    	if(isFill()) {
    		Map parametros = new HashMap();
    		parametros.put("IDFACTURA", Integer.parseInt(idFactura.getText()));	
    		String fileName = "src/files/facturasClientes/plantilla/recibo.jrxml" ;
    		JasperPrint jasperPrint;
    		JasperReport jasperDesign;
			try {
				jasperDesign = JasperCompileManager.compileReport(fileName);
				jasperPrint = JasperFillManager.fillReport(jasperDesign, parametros,con.getConect());
	    		JasperViewer.viewReport(jasperPrint, true);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				Window owner = this.idFactura.getScene().getWindow();
				VentanaAvisos.showAlert(Alert.AlertType.ERROR,owner,
	                    "Generar Recivo","Error al Generar : "+ e);
			}
    		
    	}
        clear();
        
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
