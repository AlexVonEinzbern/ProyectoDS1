package files.control.getente;

import files.control.LoginControl;
import files.modelo.ConexionBase;
import files.modelo.VentanaAvisos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GerenteControl {
    private ConexionBase con;
    public GerenteControl(ConexionBase con){
        this.con = con;
    }

    @FXML    private ImageView logo;
    @FXML    private HBox panelFuncionesGerente;
    @FXML    private Button btonConsultUser;
    @FXML    private Button btonConsultActive;
    @FXML    private Label rol;
    @FXML    private AnchorPane panelPrincipal;
    @FXML    private AnchorPane panelFondo;
    @FXML    private TextField idCliente;
    @FXML    private TextField idActivo;

    @FXML    void reportesActivos(ActionEvent event) {
    	if(!this.idActivo.getText().equals("")) {
    		Map parametros = new HashMap();
    		parametros.put("IDACTIVO", Integer.parseInt(idActivo.getText()));
    		String fileName = "src/files/facturasClientes/plantilla/activo.jrxml" ;
    		JasperPrint jasperPrint;
    		JasperReport jasperDesign;
    		try {
    			jasperDesign = JasperCompileManager.compileReport(fileName);
    			jasperPrint = JasperFillManager.fillReport(jasperDesign, parametros,con.getConect());
    			JasperViewer.viewReport(jasperPrint, true);
    			} catch (JRException e) {
    				// TODO Auto-generated catch block
    				Window owner = this.idCliente.getScene().getWindow();
    				VentanaAvisos.showAlert(Alert.AlertType.ERROR,owner,
    						"Generar Reporte","Error al Generar : "+ e);
    				}
    		}else {
    			String fileName = "src/files/facturasClientes/plantilla/GeneralActivo.jrxml" ;
        		JasperPrint jasperPrint;
        		JasperReport jasperDesign;
        		try {
        			jasperDesign = JasperCompileManager.compileReport(fileName);
        			jasperPrint = JasperFillManager.fillReport(jasperDesign, null,con.getConect());
        			JasperViewer.viewReport(jasperPrint, true);
        			} catch (JRException e) {
        				// TODO Auto-generated catch block
        				Window owner = this.idCliente.getScene().getWindow();
        				VentanaAvisos.showAlert(Alert.AlertType.ERROR,owner,
        						"Generar Reporte","Error al Generar : "+ e);
        				}
    		}
	}

    @FXML    void reportesUsuarios(ActionEvent event) {
       	if(!this.idCliente.getText().equals("")) {
       		Map parametros = new HashMap();
       		parametros.put("IDCLIENTE", Integer.parseInt(idCliente.getText()));
       		String fileName = "src/files/facturasClientes/plantilla/cliente.jrxml" ;
       		JasperPrint jasperPrint;
       		JasperReport jasperDesign;
       		try {
       			jasperDesign = JasperCompileManager.compileReport(fileName);
       			jasperPrint = JasperFillManager.fillReport(jasperDesign, parametros,con.getConect());
       			JasperViewer.viewReport(jasperPrint, true);
       			} catch (JRException e) {
       				// TODO Auto-generated catch block
       				Window owner = this.idCliente.getScene().getWindow();
       				VentanaAvisos.showAlert(Alert.AlertType.ERROR,owner,
       						"Generar Reporte","Error al Generar : "+ e);
       				}
       		}else {
       			String fileName = "src/files/facturasClientes/plantilla/GeneralClient.jrxml" ;
           		JasperPrint jasperPrint;
           		JasperReport jasperDesign;
           		try {
           			jasperDesign = JasperCompileManager.compileReport(fileName);
           			jasperPrint = JasperFillManager.fillReport(jasperDesign, null,con.getConect());
           			JasperViewer.viewReport(jasperPrint, true);
           			} catch (JRException e) {
           				// TODO Auto-generated catch block
           				Window owner = this.idCliente.getScene().getWindow();
           				VentanaAvisos.showAlert(Alert.AlertType.ERROR,owner,
           						"Generar Reporte","Error al Generar : "+ e);
           				}
       		}
	}
    
    @FXML    void cerrarSesion(ActionEvent event) throws IOException {
        Stage ventana = (Stage) btonConsultActive.getScene().getWindow();
        ventana.close();
        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../vista/ventanaLogin.fxml"));
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
