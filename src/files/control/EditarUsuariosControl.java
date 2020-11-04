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
import java.time.LocalDate;

public class EditarUsuariosControl {
    private ConexionBase con;
    public EditarUsuariosControl(ConexionBase con){
        this.con = con;
    }

    @FXML   private Button buscar;
    @FXML   private TextField editNombre;
    @FXML   private TextField editDireccion;
    @FXML   private TextField editFecha;
    @FXML   private ChoiceBox<String> editRoles;
    @FXML   private TextField editEmail;
    @FXML   private TextField editCedula;
    @FXML   private TextField editTelefono;
    @FXML   private ChoiceBox<String> editSedes;
    @FXML   private ChoiceBox<String> editEstado;
    @FXML   private TextField editContrasena;
    @FXML   private TextField idUsuario;
    @FXML   private Button registrar;
    String nom = null;
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
    String feInCon = null;
    String emCon =null;
    String contCon = null;
    String sedeCon =null;
    String rolCon =null;
    boolean estCon ;
    int idUsu ;

    @FXML   void editUsuario(ActionEvent event) throws SQLException {
        getValues();
        if(est.equals("Activo")){
            est="true";
        }else{
            est="false";
        }
        int in = con.guardar("UPDATE usuarios  SET nombreusuario = '"+nom+"', cedulausuario = "+ced+"," +
                " direccionusuario = '"+dir+"', telefonousuario = "+tel+",sucursalusuario = '"+ sede+"'," +
                " rolusuario = '"+rol+"' , password = '"+cont+"' ,estadousuario = "+est+",emailusuario ='"+em+"'" +
                " WHERE idusuario = "+idUsu+";");
        Window owner = editNombre.getScene().getWindow();
        VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                "Registro Usuarios","Haz editado exitosamente un usario:"+ nom);
        clear();
    }

    @FXML   void busquedaUsuario(ActionEvent event) throws SQLException {
         idUsu =  Integer.parseInt(idUsuario.getText());
        ResultSet rs = con.consultar("Select * from usuarios where idusuario = "+idUsu);
        while (rs.next()){
            nomCon = rs.getString(2);
            cedCon = rs.getInt(3) ;
            dirCon = rs.getString(4);
            telCon = rs.getInt(5) ;
            feInCon = rs.getDate(6).toString() ;
            emCon = rs.getString(10);
            contCon = rs.getString(11);
            sedeCon = rs.getString(7);
            rolCon = rs.getString(8);
            estCon = rs.getBoolean(9);
        }
       // System.out.println(nomCon+"  "+cedCon+"  "+dirCon+"  "+telCon +"  "+feInCon +"  "+emCon
      //          +"  "+ contCon +"  "+ sedeCon+"  "+rolCon+"   " +estCon );
        setValues();
    }

    private void setValues(){
        clear();
        editNombre.setText(nomCon);
        editCedula.setText(String.valueOf(cedCon)) ;
        editDireccion.setText(dirCon) ;
        editTelefono.setText(String.valueOf(telCon)) ;
        editEmail.setText(emCon);
       // editContrasena.setText(contCon);  encriptada
        editSedes.setValue(sedeCon);
        editRoles.setValue(rolCon);
        if(estCon){
            editEstado.setValue("Activo");
        }else{
            editEstado.setValue("Inactivo");
        }
        editFecha.setText(feInCon);


    }
    private void getValues(){
        nom = editNombre.getText();
        ced = editCedula.getText() ;
        dir = editDireccion.getText() ;
        tel = editTelefono.getText() ;
        em = editEmail.getText();
        cont = editContrasena.getText();
        sede = editSedes.getValue();
        rol = editRoles.getValue();
        est = editEstado.getValue();
    }

    private void clear(){
        editNombre.clear();
        editCedula.clear();
        editDireccion.clear();
        editTelefono.clear();
       // editContrasena.clear();
        editEmail.clear();
        //editFecha.setValue(null);
    }


}




