package files.control.operador.cliente;
import files.modelo.VentanaAvisos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import files.modelo.ConexionBase;
import javafx.stage.Window;

import java.sql.SQLException;

public class AddClienteControl {
    public ConexionBase con ;
    public AddClienteControl(ConexionBase con){
        this.con = con;
    }
    @FXML    private TextField nombre;
    @FXML    private TextField cedula;
    @FXML    private TextField direccion;
    @FXML    private TextField telefono;
    @FXML    private DatePicker fecha;
    @FXML    private TextField email;
    @FXML    private Button registrar;


    @FXML    void registrarCliente(ActionEvent event) throws SQLException {
        //insert into cliente values (1,'cliente',12345,'mi casa',31565,'2014-05-02',true,'cliente@email.com');
        String nom = nombre.getText(); ;
        String ced = cedula.getText() ;
        String dir = direccion.getText() ;
        String tel = telefono.getText() ;
        String feIn = fecha.getValue().toString() ;
        String em = email.getText();

        int i =con.guardar("INSERT INTO cliente (nombreCliente, cedulaCliente, direccionCliente, telefonoCLiente," +
                "                fechaAfiliacionCliente, estadoCliente, emailCliente)" +
                "                VALUES('"+nom+"', "+ced+", '"+dir+"', "+tel+"," +
                "               '"+feIn+"',true,'"+em+"');");
        clear();
        Window owner = nombre.getScene().getWindow();
        VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                "Registro Clientes","Haz registrado exitosamente un nuevo Cliente:"+ nom);
    }
    private void clear(){
        nombre.clear();
        cedula.clear();
        direccion.clear();
        telefono.clear();
        email.clear();
        fecha.setValue(null);
    }
}
