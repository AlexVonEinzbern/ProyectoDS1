package files.control.admin.activos;

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


public class EditarActivosControl {
    private ConexionBase con;
    private int idAct;
    private String nom,dir;
    public EditarActivosControl(ConexionBase con){
        this.con = con;
    }
    @FXML   private Button buscar;
    @FXML   private TextField editNombre;
    @FXML   private TextField editDireccion;
    @FXML   private TextField idActivo;
    @FXML   private Button registrar;


    @FXML   void editUsuario(ActionEvent event) throws SQLException {
        getValues();

       con.guardar("UPDATE Activos  SET nombreActivo = '"+nom+"', ubicacionActivo = "+dir+"," +
                " WHERE idActivo = "+idAct+";");
        Window owner = editNombre.getScene().getWindow();
        VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                "Registro Usuarios","Haz editado exitosamente el Activo:"+ nom);
        clear();
    }

    @FXML   void busquedaUsuario(ActionEvent event) throws SQLException {
        String nomCon = null;
        String dirCon = null;
         idAct =  Integer.parseInt(idActivo.getText());
        ResultSet rs = con.consultar("Select * from Activos where idActivo = "+idAct);
        while (rs.next()){
            nomCon = rs.getString(2);
            dirCon = rs.getString(3);

        }
       // System.out.println(nomCon+"  "+cedCon+"  "+dirCon+"  "+telCon +"  "+feInCon +"  "+emCon
      //          +"  "+ contCon +"  "+ sedeCon+"  "+rolCon+"   " +estCon );
        editNombre.setText(nomCon);
        editDireccion.setText(dirCon) ;
    }

    private void getValues(){
        nom = editNombre.getText();
        dir = editDireccion.getText() ;

    }

    private void clear(){
        editNombre.clear();
        editDireccion.clear();
    }


}




