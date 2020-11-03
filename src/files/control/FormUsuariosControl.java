package files.control;

import files.modelo.ConexionBase;
import files.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormUsuariosControl {
    private Usuario nuevo;
    private ConexionBase con;
    public FormUsuariosControl(ConexionBase con){
        this.con = con;
    }



    @FXML    private TextField nombreUsuario;
    @FXML    private TextField cedulaUsuario;
    @FXML    private TextField direccionUsuario;
    @FXML    private TextField telefonoUsuario;
    @FXML    private TextField emailUsuario;
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

    @FXML    void crearUsuario(ActionEvent event) throws SQLException {
        String nombre = nombreUsuario.getText(); ;
        String cedula = cedulaUsuario.getText() ;
        String direccion = direccionUsuario.getText() ;
        String telefono = telefonoUsuario.getText() ;
        String fechaIngreso = fecha.getValue().toString() ;
        String email = emailUsuario.getText();
        String sede = sedes.getValue().toString();
        String rol = roles.getValue().toString();

       /* nuevo= new Usuario(nombreUsuario.getText(), Integer.parseInt(cedula),
                direccionUsu.getText(), Integer.parseInt(telefonoUsu.getText()),new Date(System.currentTimeMillis()),
                sedes.getValue().toString(),roles.getValue().toString(),true, cedula);

        con.guardar("INSERT INTO usuarios (nombreusuario, cedulausuario," +
        " direccionusuario, telefonousuario, fechaingresousuario, sucursalusuario," +
        " rolusuario, password) VALUES(" +
        "'"+nombreUsuario.getText()+"', "+Integer.parseInt(cedula)+", '"+direccionUsu.getText()+"',"+
        Integer.parseInt(telefonoUsu.getText())+", '"+new Date(System.currentTimeMillis())+"',"+ "'"+
        sedes.getValue().toString()+"', '"+roles.getValue().toString()+"',true,"+ cedula+")");
        */
       // int i = con.guardar("INSERT INTO usuarios (nombreusuario, cedulausuario, direccionusuario, telefonousuario, fechaingresousuario, sucursalusuario, rolusuario, password,emailusuario) " +
         //       "VALUES('"+nombreUsuario.getText()+"', "+cedula+", '"+direccionUsuario.getText()+"',"+ telefonoUsuario+", '2020-10-21', '"+sedes.getValue().toString()+"', '"+roles.getValue().toString()+"', '"+cedula+"' , '"+emailusuario.getText()+"');");
        int in = con.guardar("INSERT INTO usuarios (nombreusuario, cedulausuario, direccionusuario, telefonousuario," +
                "                fechaingresousuario, sucursalusuario, rolusuario, password,estadousuario,emailusuario)" +
                "                VALUES('"+nombre+"', "+cedula+", '"+direccion+"', "+telefono+"," +
                "               '"+fechaIngreso+"', '"+sede+"', '"+rol+"', '"+cedula+"',true,'"+email+"');");
    }

}
