package files.control.operador.cliente;

import files.modelo.ConexionBase;
import files.modelo.VentanaAvisos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    @FXML    void actualizar(ActionEvent event) throws SQLException {
        int i =con.guardar("UPDATE cliente  SET nombreCliente = '"+nombre.getText()+"', cedulacliente = "+cedula.getText()+"," +
                " direccioncliente = '"+direccion.getText()+"', telefonocliente = "+telefono.getText()+",emailusuario ='"+email.getText()+"'" +
                " WHERE idCliente = "+Integer.parseInt(idCliente.getText())+";");
        Window owner = nombre.getScene().getWindow();
        VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                "Registro Usuarios","Haz editado exitosamente al Cliente: "+ nombre.getText());
        idCliente.clear();
        clear();
    }
    @FXML    void buscarCL(ActionEvent event) throws SQLException {
        clear();
        String nomCon = null;
        String dirCon = null;
        String tel=null,doc=null,ema=null;
        int idcli =  Integer.parseInt(idCliente.getText());
        ResultSet rs = con.consultar("Select * from clientes where idCliente = "+idcli);
        while (rs.next()){
            nomCon = rs.getString(2);
            dirCon = rs.getString(4);
            doc = String.valueOf(rs.getInt(3));
            tel=String.valueOf(rs.getInt(5));
            ema = rs.getString("emailCliente");

        }
        nombre.setText(nomCon);
        direccion.setText(dirCon) ;
        telefono.setText(tel);
        email.setText(ema);
        cedula.setText(doc);
    }

    private void clear(){
        nombre.clear();
        direccion.clear();
        telefono.clear();
        email.clear();
        cedula.clear();
    }
}
