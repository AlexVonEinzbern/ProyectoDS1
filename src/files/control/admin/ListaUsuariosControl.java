package files.control.admin;

import files.modelo.ConexionBase;
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

public class ListaUsuariosControl implements Initializable {
    private final ConexionBase con;
    private final ObservableList<Person> datos = FXCollections.observableArrayList();

    @FXML   private TableView<Person> tablaUsuarios;
    @FXML   private TableColumn<Person, String> nombreUsuario;
    @FXML   private TableColumn<Person, String> estadoUsuario;
    @FXML   private TableColumn<Person, String> rolUsuario;



    public ListaUsuariosControl(ConexionBase con) {
        this.con = con;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreUsuario.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
       estadoUsuario.setCellValueFactory(new PropertyValueFactory<>("Estado"));
        rolUsuario.setCellValueFactory(new PropertyValueFactory<>("Rol"));
        try {
            ResultSet rs = con.consultar("select * from usuarios");
            String aux;
            while(rs.next()){
                if( rs.getBoolean("estadousuario")){
                    aux = "Activo";
                }else { aux = "Inactivo";}
              datos.add(new Person(rs.getString("nombreusuario"), aux, rs.getString("rolusuario")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tablaUsuarios.setItems(datos);

    }


    public static class Person {
        private final SimpleStringProperty nombre;
        private final SimpleStringProperty estado;
        private final SimpleStringProperty rol;

        private Person(String nombre, String estado, String rol) {
            this.nombre = new SimpleStringProperty(nombre);
            this.estado = new SimpleStringProperty(estado);
            this.rol = new SimpleStringProperty(rol);
        }

        public String getNombre() {
            return nombre.get();
        }
        public void setNombre(String fName) {
            nombre.set(fName);
        }
        public String getEstado() {
            return estado.get();
        }
        public void setEstado(String fName) {
            estado.set(fName);
        }
        public String getRol() {
            return rol.get();
        }
        public void setRol(String fName) {
            rol.set(fName);
        }
    }
}

