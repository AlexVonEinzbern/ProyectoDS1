package files.control.obrero.facturas;

import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistrarMedidas {
	private ConexionBase con;
	
	public RegistrarMedidas(ConexionBase base) {
        this.con = base;
	}
    @FXML    private TextField idCliente;
    @FXML    private TextField idUsuario;
    @FXML    private TextField idActivo;
    @FXML    private TextField fechaMedicion;
    @FXML    private TextField Medida;
    @FXML    private Button guardarBtn;
    @FXML    private Button cargarArchivoBtn;
    @FXML    private Button limpiarBtn;
    
    
    @FXML    void cargarMedidaArchivo(ActionEvent event) {

    }

    @FXML    void guardarMedida(ActionEvent event) {

    }

    @FXML    void limpiarCampos(ActionEvent event) {
    	idCliente.clear();
        idUsuario.clear();
        idActivo.clear();
        fechaMedicion.clear();
        Medida.clear();
    }

}




