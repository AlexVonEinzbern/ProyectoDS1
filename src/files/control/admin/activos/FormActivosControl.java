package files.control.admin.activos;

import files.modelo.ConexionBase;
import files.modelo.VentanaAvisos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.sql.SQLException;

public class FormActivosControl {
    private ConexionBase con;
    public FormActivosControl(ConexionBase con){
        this.con = con;
    }
    @FXML    private TextField nombre;
    @FXML    private TextField idActivo;
    @FXML    private TextField direccion;
    @FXML    private Button guardar;
    @FXML    private Button Limpiar;
    @FXML    private Button salir;

    @FXML    void CerrarVentana(ActionEvent event) {

    }

    @FXML    void borrarDatos(ActionEvent event) {
        clear();
    }

    @FXML    void addActivo(ActionEvent event) throws SQLException {
        int idAct = Integer.parseInt(idActivo.getText());
        String nom = nombre.getText();
        String dir = direccion.getText() ;
        con.guardar("INSERT INTO activos (idActivo,nombreActivo,ubicacionActivo)" +
                "                VALUES(  "+ idAct+",'"+nom+"', '"+dir+"');");
        Window owner = nombre.getScene().getWindow();
        VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                "Registro Activos","Haz registrado exitosamente un nuevo Activo : "+ nom);
        clear();
    }

    private void clear(){
        idActivo.clear();
       nombre.clear();
       direccion.clear();
    }

}
