package files.control.admin.usuarios;

import files.modelo.ConexionBase;
import files.modelo.VentanaAvisos;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EstadoUsuariosControl {
    private ConexionBase con;
    private int idUsu;
    private String rolCon;
    private String nomCon;
    private boolean estadoCon;

    public EstadoUsuariosControl(ConexionBase con) {
        this.con = con;
    }
    @FXML   private TextField idUsuario;
    @FXML   private Button buscar;
    @FXML   private TextField nombreUsuario;
    @FXML   private TextField rolUsuario;
    @FXML   private TextField estadoUsuario;
    @FXML   private ChoiceBox<String> estado;
    @FXML   private Button registrar;


    @FXML   void anadir(ActionEvent event) throws SQLException {
        String est = estado.getValue();
        if(est.equals("Activar")){
            est="true";
        }else{
            est="false";
        }
        int in = con.guardar("UPDATE usuarios  SET estadousuario = "+est+" WHERE idusuario = "+idUsu+";");
        Window owner = buscar.getScene().getWindow();
        VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                "Registro Usuarios","Haz editado exitosamente al usario: "+ nomCon);
        clear();

    }
    @FXML   void buscarr(ActionEvent event) throws SQLException {
        clear();
       idUsu =  Integer.parseInt(idUsuario.getText());
        ResultSet rs = con.consultar("Select * from usuarios where idusuario = "+idUsu);
        while (rs.next()){
            nomCon = rs.getString(2);
            rolCon = rs.getString(8);
            estadoCon = rs.getBoolean(9);
        }
        nombreUsuario.setText(nomCon);
        rolUsuario.setText(rolCon);

        if(estadoCon){
            estadoUsuario.setText("Activo");
            estado.setValue("Inactivar");
        }else{
            estadoUsuario.setText("Inactivo");
            estado.setValue("Activar");
        }

    }
    private void clear(){
        nombreUsuario.clear();
        rolUsuario.clear();
        estadoUsuario.clear();
        idUsuario.clear();
    }
}
//-fx-background-color    #14862d #14862d   -fx-font-style