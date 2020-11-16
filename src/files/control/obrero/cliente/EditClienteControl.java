package files.control.obrero.cliente;

import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EditClienteControl {
    private ConexionBase con;
    public EditClienteControl(ConexionBase con) {
        this.con=con;
    }

    @FXML    private TextField nombre;
    @FXML    private TextField cedula;
    @FXML    private TextField direccion;
    @FXML    private TextField telefono;
    @FXML    private DatePicker fecha;
    @FXML    private TextField email;
    @FXML    private Button Actualizar;
    @FXML    private TextField idCliente;
    @FXML    private Button buscarBton;

    @FXML    void actualizar(ActionEvent event) {

    }
    @FXML    void buscarCL(ActionEvent event) {

    }

}
