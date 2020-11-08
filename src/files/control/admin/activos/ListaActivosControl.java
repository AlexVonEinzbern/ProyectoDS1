package files.control.admin.activos;

import files.modelo.ConexionBase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListaActivosControl implements Initializable {
    private final ConexionBase con;
    private final ObservableList<Activo> datos = FXCollections.observableArrayList();

    @FXML   private TableView<Activo> tablaActivos;
    @FXML   private TableColumn<Activo, Integer> idActivo;
    @FXML   private TableColumn<Activo, String> nombreActivo;
    @FXML   private TableColumn<Activo, String> direccionActivo;



    public ListaActivosControl(ConexionBase con) {
        this.con = con;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idActivo.setCellValueFactory(new PropertyValueFactory<>("IdActivo"));
       nombreActivo.setCellValueFactory(new PropertyValueFactory<>("NombreActivo"));
        direccionActivo.setCellValueFactory(new PropertyValueFactory<>("DireccionActivo"));
        try {
            ResultSet rs = con.consultar("select * from Activos");
            while(rs.next()){
              datos.add(new Activo(rs.getInt("idActivo"), rs.getString("nombreActivo"), rs.getString("ubicacionActivo")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tablaActivos.setItems(datos);

    }


    public static class Activo {
        private final SimpleIntegerProperty idActivo;
        private final SimpleStringProperty nombreActivo;
        private final SimpleStringProperty direccionActivo;

        private Activo(int idActivo, String nombreActivo, String direccionActivo) {
            this.idActivo = new SimpleIntegerProperty(idActivo);
            this.nombreActivo = new SimpleStringProperty(nombreActivo);
            this.direccionActivo = new SimpleStringProperty(direccionActivo);
        }

        public int getIdActivo() {
            return idActivo.get();
        }
        public void setIdActivo(int fName) {
            idActivo.set(fName);
        }
        public String getNombreActivo() {
            return nombreActivo.get();
        }
        public void setNombreActivo(String fName) {
            nombreActivo.set(fName);
        }
        public String getireccionActivo() {
            return direccionActivo.get();
        }
        public void setDireccionActivo(String fName) {
            direccionActivo.set(fName);
        }
    }
}

