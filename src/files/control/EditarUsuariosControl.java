package files.control;

import files.modelo.ConexionBase;
import files.modelo.VentanaAvisos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditarUsuariosControl {
    private ConexionBase con;
    public EditarUsuariosControl(ConexionBase con){
        this.con = con;
    }

    @FXML   private Button buscar;
    @FXML   private TextField editNombre;
    @FXML   private TextField editDireccion;
    @FXML   private DatePicker editFecha;
    @FXML   private ChoiceBox<?> editRoles;
    @FXML   private TextField editEmail;
    @FXML   private TextField editCedula;
    @FXML   private TextField editTelefono;
    @FXML   private ChoiceBox<?> editSedes;
    @FXML   private ChoiceBox<?> editEstado;
    @FXML   private TextField editContrasena;
    @FXML   private TextField idUsuario;
    @FXML   private Button registrar;
    String nom = null; ;
    String ced = null ;
    String dir = null;
    String tel = null ;
    String feIn = null ;
    String em = null;
    String cont = null;
    String sede = null;
    String rol = null;
    String est = null;
    String nomCon = null;
    int cedCon ;
    String dirCon = null;
    int telCon ;
    Date feInCon = null;
    String emCon =null;
    String contCon = null;
    String sedeCon =null;
    String rolCon =null;
    boolean estCon ;

    @FXML   void editUsuario(ActionEvent event) throws SQLException {
        getValues();
        int in = con.guardar("INSERT INTO usuarios (nombreusuario, cedulausuario, direccionusuario, telefonousuario," +
                "                fechaingresousuario, sucursalusuario, rolusuario, password,estadousuario,emailusuario)" +
                "                VALUES('"+nom+"', "+ced+", '"+dir+"', "+tel+"," +
                "               '"+feIn+"', '"+sede+"', '"+rol+"', '"+cont+"',"+est+",'"+em+"');");
        Window owner = editNombre.getScene().getWindow();
        VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                "Registro Usuarios","Haz editado exitosamente un usario:"+ nom);
        clear();
    }

    @FXML   void busquedaUsuario(ActionEvent event) throws SQLException {
        int idUsu =  Integer.parseInt(idUsuario.getText());
        ResultSet rs = con.consultar("Select * from usuarios where idusuario = "+idUsu);
        while (rs.next()){
            nomCon = rs.getString(2);
            cedCon = rs.getInt(3) ;
            dirCon = rs.getString(4);
            telCon = rs.getInt(5) ;
            feInCon = rs.getDate(6) ;
            emCon = rs.getString(10);
            contCon = rs.getString(11);
            sedeCon = rs.getString(7);
            rolCon = rs.getString(8);
            estCon = rs.getBoolean(9);
        }

    }

    private void setValues(){
        editNombre.setText(""); ;
        editCedula.setText("") ;
        editDireccion.setText("") ;
        editTelefono.setText("") ;
        //editFecha.setValue();
        editEmail.setText("");
        editContrasena.setText("");
        //editSedes.setValue();
        //editRoles.getValue().toString();
        //editEstado.getValue().toString();
    }
    private void getValues(){
        nom = editNombre.getText(); ;
        ced = editCedula.getText() ;
        dir = editDireccion.getText() ;
        tel = editTelefono.getText() ;
        feIn = editFecha.getValue().toString() ;
        em = editEmail.getText();
        cont = editContrasena.getText();
        sede = editSedes.getValue().toString();
        rol = editRoles.getValue().toString();
        est = editEstado.getValue().toString();
    }

    private void clear(){
        editNombre.clear();
        editCedula.clear();
        editDireccion.clear();
        editTelefono.clear();
        editContrasena.clear();
        editEmail.clear();
        editFecha.setValue(null);
    }


}




