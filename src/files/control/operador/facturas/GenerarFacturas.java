package files.control.operador.facturas;

import files.control.admin.activos.ListaActivosControl;
import files.modelo.ConexionBase;
import files.modelo.EnviarFacturas;
import files.modelo.VentanaAvisos;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GenerarFacturas implements Initializable {
    private ConexionBase con;
    private double inter,reconexion;
    private int idFactura;    
    private String bufferEmail;
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
    @FXML    private TextField contrato;
    @FXML    private TextField nombreClienteF;
    @FXML    private TextField idFacturaF;
    @FXML    private TextField valorAPagarF;
    @FXML    private Button enviarBtn;

    @FXML    void BuscarFactura(ActionEvent event) {

        try {
            int contr = Integer.parseInt(contrato.getText());
            ResultSet rs = con.consultar(
                    "select nombreCliente,estadoCliente,medida, reconexion,unidadEnergia, " +
                            " fechaVenceFactura - current_date as interes, f.idfactura , emailCliente "+
                            " from factura as f,cliente as c,detalleFactura as " +
                            " d,medida as m,configurarsistema as con " +
                            " where not estadofactura and c.idCliente = "+contr+"and f.idCliente = c.idCliente and " +
                            " f.idfactura = d.idfactura and c.idcliente = m.idcliente " +
                            " and d.idConfiguracion = con.idConfiguracion ;");

            while(rs.next()){
            	if(!rs.getBoolean(2)) {
            		reconexion = rs.getDouble(4);
            	}else {
            		reconexion =0.0;
            	}
            	bufferEmail = rs.getString("emailCliente");
                nombreClienteF.setText(rs.getString(1));
                idFacturaF.setText(String.valueOf(rs.getInt(7)));
                valorAPagarF.setText(String.valueOf(valorAPagar(rs.getDouble("interes"),!rs.getBoolean("estadoCliente"),
    					rs.getDouble("reconexion"),rs.getDouble("unidadEnergia"),rs.getDouble("medida"))));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        
    }
    @FXML    void generarFactura(ActionEvent event) {
    	Window owner = nombreClienteF.getScene().getWindow();
    	String nombreC  = nombreClienteF.getText();
    	idFactura = 0;
    	double valorApagar  = Double.parseDouble(valorAPagarF.getText());
        if(!nombreC.equals("")){
        	try {     
        		idFactura = Integer.parseInt(idFacturaF.getText());
        		Map parametros = new HashMap();
        		parametros.put("IDFACTURA", idFactura);
        		parametros.put("ValorFactura", valorApagar);
        		parametros.put("Interes", inter);
        		parametros.put("Reconexion", reconexion);
        		String fileName = "/files/facturasClientes/plantilla/plantilla.jrxml" ;
        		String outFile = "/files/facturasClientes/Factura"+idFactura+".pdf";
        		JasperPrint jasperPrint;
        		JasperReport jasperDesign = JasperCompileManager.compileReport(fileName);
        		jasperPrint = JasperFillManager.fillReport(jasperDesign, parametros,con.getConect());
        		File file = new File(outFile);
        		OutputStream outputSteam = new FileOutputStream(file);
        		JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);
        		VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
    	                "Generar Factura ","Factura del cliente "+nombreC+", generada Exitosamente");
        		} catch ( JRException | FileNotFoundException throwables) {  
        			throwables.printStackTrace();
        	        VentanaAvisos.showAlert(Alert.AlertType.ERROR,owner,
        	                "Error Generar Factura"," error tipo "+throwables);
        			}
      
        	
        	}else {
        		try {
        			ResultSet rs = con.consultar(
        					"select nombreCliente,estadoCliente,medida,f.idfactura , " +
                                     " reconexion,unidadEnergia, fechaVenceFactura - current_date as interes "+
                                     " from factura as f,cliente as c,detalleFactura as d,medida as m,configurarsistema as con " +
                                     " where not estadofactura and f.idCliente = c.idCliente and " +
                                     " f.idfactura = d.idfactura and c.idcliente = m.idcliente " +
                                     " and d.idConfiguracion = con.idConfiguracion ;");
            		while(rs.next()){      
            			if(!rs.getBoolean(2)) {
                    		reconexion = rs.getDouble(5);
                    	}else {
                    		reconexion =0.0;
                    	}
            			Map parametros = new HashMap();
            			nombreC = rs.getString(2);          			
            			idFactura = rs.getInt(4);
            			valorApagar = valorAPagar(rs.getDouble("interes"),!rs.getBoolean("estadoCliente"),
            					rs.getDouble("reconexion"),rs.getDouble("unidadEnergia"),rs.getDouble("medida"));
            			parametros.put("IDFACTURA", idFactura);
            			parametros.put("ValorFactura", valorApagar);
            			parametros.put("Interes", inter);
            			parametros.put("Reconexion", reconexion);
            			String fileName = "/files/facturasClientes/plantilla/plantilla.jrxml" ;
            			String outFile = "/files/facturasClientes/Factura"+idFactura+".pdf";
            			JasperPrint jasperPrint;
            			JasperReport jasperDesign = JasperCompileManager.compileReport(fileName);
            			jasperPrint = JasperFillManager.fillReport(jasperDesign, parametros,con.getConect());
            			File file = new File(outFile);
            			OutputStream outputSteam = new FileOutputStream(file);
            			JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);
            			VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
            	                "Generar Factura ","Facturas generada Exitosamente");
            			}}catch (SQLException | JRException | FileNotFoundException throwables) {
                	        VentanaAvisos.showAlert(Alert.AlertType.ERROR,owner,
                	                "Error Generar Factura"," error tipo "+throwables);
                			}
                	
                	}
        enviarBtn.setDisable(false);
        	}
        
    

	@FXML	void enviarFacturasClientes(ActionEvent event) {
		Window owner = nombreClienteF.getScene().getWindow();
		if(!nombreClienteF.getText().equals("")){
		
			EnviarFacturas.enviarConGMail(bufferEmail, "Factura De Energia", "", 
					idFacturaF.getText(), nombreClienteF.getText());
			VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
	                "Enviar Factura ","Factura Enviada Exitosamente");
		}else {
			try {
				
    			ResultSet rs = con.consultar(
    					"select   nombreCliente, idFactura , emailCliente"+
                                 " from factura as f,cliente as c"+
                                 " where not estadofactura and f.idCliente = c.idCliente ;");
        		while(rs.next()){      
        			bufferEmail = rs.getString(3);
        			
        			EnviarFacturas.enviarConGMail(bufferEmail, "Factura De Energia", "",
        					Integer.toString(rs.getInt(2)), rs.getString(1));
        			
        			VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
        	                "Enviar Factura ","Facturas Enviadas Exitosamente ");
        			}}catch (SQLException  throwables) {
            	        VentanaAvisos.showAlert(Alert.AlertType.ERROR,owner,
            	                "Enviar Factura"," error al enviar factura: "+throwables);
            			}
            	
			
		}
		
		enviarBtn.setDisable(true);
	  	contrato.clear();;
    	nombreClienteF.clear();
    	idFacturaF.clear();
        valorAPagarF.clear();
	}
    public double valorAPagar(double interes,boolean estado,double reconexion,double costoUnidad,double consumo){
        double valor = costoUnidad * consumo;
        inter = 0.0;
        if (interes < 0) {
            if((interes*-1)>30){
                valor *= 0.30;
                inter = 0.30;
            }else{
            	inter = interes*-1/100;
                valor *= (interes*-1/100);
            }
            if(estado){
                valor+=  reconexion;
            }
        }
        return valor;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	enviarBtn.setDisable(true);
    	contrato.clear();
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
                        String.valueOf(valorAPagar(rs.getDouble("interes"),!rs.getBoolean("estadoCliente"),
                                rs.getDouble("reconexion"),rs.getDouble("unidadEnergia"),rs.getDouble("medida")))            )          );

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tablaFacturas.setItems(datos);
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
