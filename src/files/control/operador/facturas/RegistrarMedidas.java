package files.control.operador.facturas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import files.modelo.CSVReader;
import files.modelo.ConexionBase;
import files.modelo.VentanaAvisos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegistrarMedidas {
	private ConexionBase con;
	private CSVReader csvRead;
	
	public RegistrarMedidas(ConexionBase base) {
        this.con = base;
        csvRead = new CSVReader(con);
	}
    @FXML    private TextField idCliente;
    @FXML    private TextField idUsuario;
    @FXML    private TextField idActivo;
    @FXML    private TextField fechaMedicion;
    @FXML    private TextField Medida;
    @FXML    private Button guardarBtn;
    @FXML    private Button cargarArchivoBtn;
    @FXML    private Button limpiarBtn;
    
    
    @FXML    void cargarMedidaArchivo(ActionEvent event)  {
    	FileChooser fileChooser = new FileChooser();
    	Stage stage = (Stage) idCliente.getScene().getWindow();
    	File file = fileChooser.showOpenDialog(stage);
    	
    	if(file != null) {
    		try {
				csvRead.CSVReaderMedida(file);
				VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,stage,
	                    "Registro Medida","Registro de Medida Exitoso!");
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				VentanaAvisos.showAlert(Alert.AlertType.ERROR,stage,
	                    "Registro Medida","Error al alcanzar archivo : "+ e);
			}
    	}
    	
    }

    @FXML    void guardarMedida(ActionEvent event) {
    	Window owner = idCliente.getScene().getWindow();
    	if(!isFill()) {
    		try {
				con.guardar("INSERT INTO medida (idcliente, idusuario,fechamedida, medida, idActivo) VALUES ("+
						idCliente.getText()+", "+idUsuario.getText()+", "+
						fechaMedicion.getText()+", "+Medida.getText()+", "+idActivo.getText()+");");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				VentanaAvisos.showAlert(Alert.AlertType.ERROR,owner,
	                    "Registro Medida","Error en "+ e);;
			}            
            VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                    "Registro Medida","Registro de Medida Exitoso!");
    	}else {
    		VentanaAvisos.showAlert(Alert.AlertType.ERROR,owner,
                    "Registro MEdida","No hay Medida que registrar verifique los campos!");
    	}
    }

    @FXML    void limpiarCampos(ActionEvent event) {
    	idCliente.clear();
        idUsuario.clear();
        idActivo.clear();
        fechaMedicion.clear();
        Medida.clear();
    }
    
    private Boolean isFill() {
    	return idCliente.getText().equals("") &&
        idUsuario.getText().equals("") &&
        idActivo.getText().equals("") &&
        fechaMedicion.getText().equals("") &&
        Medida.getText().equals("") ;
    }

}




