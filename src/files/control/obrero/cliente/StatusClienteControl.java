package files.control.obrero.cliente;

import files.modelo.ConexionBase;
import files.modelo.VentanaAvisos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusClienteControl {
    private ConexionBase con;
    public StatusClienteControl(ConexionBase con) {
        this.con=con;
    }
    @FXML    private TextField idCliente;
    @FXML    private Button buscar;
    @FXML    private ChoiceBox<String> estado;
    @FXML    private Button guardar;
    @FXML    private TextField nombreCliente;
    @FXML    private TextField cedula;
    @FXML    private TextField estadoCliente;

    @FXML    void actualizar(ActionEvent event) throws SQLException {
        int i =con.guardar("UPDATE cliente  SET estadoCliente ='"+estado.getValue()+"'" +
                " WHERE idCliente = "+Integer.parseInt(idCliente.getText())+";");
        Window owner = nombreCliente.getScene().getWindow();
        VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                "Registro Usuarios","Haz editado exitosamente el estado del Cliente: "+ nombreCliente.getText());
        idCliente.clear();
        clear();
    }

    @FXML    void buscarCli(ActionEvent event) throws SQLException {
        clear();
        String nomCon = null;
        String doc=null,est=null;
        int idcli =  Integer.parseInt(idCliente.getText());
        ResultSet rs = con.consultar("Select * from Activos where idCliente = "+idcli);
        while (rs.next()){
            nomCon = rs.getString(2);
            doc = String.valueOf(rs.getInt(3));
            est = String.valueOf(rs.getBoolean("estadoCliente"));

        }
        nombreCliente.setText(nomCon);
        estadoCliente.setText(est);
        cedula.setText(doc);
    }
    private void clear(){
    nombreCliente.clear();
    estadoCliente.clear();
    cedula.clear();
    }
}
