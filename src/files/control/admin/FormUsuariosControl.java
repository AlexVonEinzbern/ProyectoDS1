package files.control.admin;

import files.modelo.ConexionBase;
import files.modelo.Usuario;
import files.modelo.VentanaAvisos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;


import java.sql.SQLException;

public class FormUsuariosControl {
    private Usuario nuevo;
    private ConexionBase con;
    public FormUsuariosControl(ConexionBase con){
        this.con = con;
    }



    @FXML    private TextField nombre;
    @FXML    private TextField cedula;
    @FXML    private TextField direccion;
    @FXML    private TextField telefono;
    @FXML    private TextField contrasena;
    @FXML    private TextField email;
    @FXML    private ChoiceBox<?> roles;
    @FXML    private ChoiceBox<?> sedes;
    @FXML    private DatePicker fecha;
    @FXML    private Button guardar;
    @FXML    private Button Limpiar;
    @FXML    private Button salir;

    @FXML    void CerrarVentana(ActionEvent event) {

    }

    @FXML    void borrarDatos(ActionEvent event) {

    }

    @FXML    void addUsuario(ActionEvent event) throws SQLException {
        String nom = nombre.getText(); ;
        String ced = cedula.getText() ;
        String dir = direccion.getText() ;
        String tel = telefono.getText() ;
        String feIn = fecha.getValue().toString() ;
        String em = email.getText();
        String cont = contrasena.getText();
        String sede = sedes.getValue().toString();
        String rol = roles.getValue().toString();


       /* nuevo= new Usuario(nombreUsuario.getText(), Integer.parseInt(cedula),
                direccionUsu.getText(), Integer.parseInt(telefonoUsu.getText()),new Date(System.currentTimeMillis()),
                sedes.getValue().toString(),roles.getValue().toString(),true, cedula);
*/
        int in = con.guardar("INSERT INTO usuarios (nombreusuario, cedulausuario, direccionusuario, telefonousuario," +
                "                fechaingresousuario, sucursalusuario, rolusuario, password,estadousuario,emailusuario)" +
                "                VALUES('"+nom+"', "+ced+", '"+dir+"', "+tel+"," +
                "               '"+feIn+"', '"+sede+"', '"+rol+"', '"+cont+"',true,'"+em+"');");
        Window owner = nombre.getScene().getWindow();
        VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                "Registro Usuarios","Haz registrado exitosamente un nuevo usario:"+ nom);
        clear();
    }

    private void clear(){
       nombre.clear();
       cedula.clear();
       direccion.clear();
       telefono.clear();
       contrasena.clear();
       email.clear();
       fecha.setValue(null);
    }

}
