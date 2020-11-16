package files.control.obrero.cliente;

import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class StatusClienteControl {
    private ConexionBase con;
    public StatusClienteControl(ConexionBase con) {
        this.con=con;
    }
    @FXML    private TextField idCliente;
    @FXML    private Button buscar;
    @FXML    private ChoiceBox<?> estado;
    @FXML    private Button guardar;
    @FXML    private TextField nombreCliente;
    @FXML    private TextField cedula;
    @FXML    private TextField estadoCliente;

    @FXML    void actualizar(ActionEvent event) {

    }

    @FXML    void buscarCli(ActionEvent event) {

    }
}
