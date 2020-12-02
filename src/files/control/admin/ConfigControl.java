package files.control.admin;

import files.modelo.ConexionBase;
import files.modelo.VentanaAvisos;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class ConfigControl {
    ConexionBase con;

    public ConfigControl(ConexionBase con) throws IOException {
        this.con = con;
    }

    @FXML    private TextField idUsuario;
    @FXML    private TextField reconexion;
    @FXML    private TextField unidadEnergia;
    @FXML    private TextField interesMora;
    @FXML    private Button guardarBtn;
    @FXML    private Button limpiarBtn;

    @FXML    void guardarConfig(ActionEvent event) {
    	Window owner = this.idUsuario.getScene().getWindow();
    	if(isFill()) {
    		try {
				int i = con.guardar("INSERT INTO configurarsistema (idUsuario, fechaConfiguracion, interesMora, reconexion, unidadEnergia)"+
  	" VALUES ("+Integer.parseInt(idUsuario.getText())+", current_date, "+
						Double.parseDouble(interesMora.getText())+", "+
						Double.parseDouble(reconexion.getText())+", "+
						Double.parseDouble(unidadEnergia.getText())+");");
				VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
	                    "Configurar Sistema","Configuracion del Sistema Exitosa : ");
			} catch (NumberFormatException|SQLException e) {
				VentanaAvisos.showAlert(Alert.AlertType.ERROR,owner,
	                    "Configurar Sistema","Error al Guardar : "+ e);
			}
    	}else {
    		VentanaAvisos.showAlert(Alert.AlertType.ERROR,owner,
                    "Configurar Sistema","Algunos campos estan vacios por favor llene todos los campos" );
    	}
    	idUsuario.clear();
    	reconexion.clear();
    	unidadEnergia.clear();
    	interesMora.clear();
    }

    @FXML    void limpiarCampos(ActionEvent event) {
    	idUsuario.clear();
    	reconexion.clear();
    	unidadEnergia.clear();
    	interesMora.clear();
    }
    
    private Boolean isFill() {
    return	!(idUsuario.getText().equals("") &&
    		reconexion.getText().equals("") &&
    		unidadEnergia.getText().equals("") &&
    		interesMora.getText().equals(""));}

}
