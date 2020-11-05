package files.control.admin;

import files.modelo.ConexionBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListaUsuariosControl {
    private ConexionBase con;
    private ObservableList<Person> datos = FXCollections.observableArrayList();

    @FXML   private TableView<Person> tablaUsuarios;
    @FXML   private TableColumn<Person, String> nombreUsuario;
    @FXML   private TableColumn<Person, String> estadoUsuario;
    @FXML   private TableColumn<Person, String> rolUsuario;


    public ListaUsuariosControl(ConexionBase con) throws SQLException {
        this.con = con;
        ResultSet rs = con.consultar("select nombreusuario,estadousuario,rolusuario from usuarios");
        while(rs.next()){
            datos.add(new Person( rs.getString(1),rs.getString(2),rs.getString(3)));
        }
        tablaUsuarios.setItems(datos);


    }



    public class Person {
        private final SimpleStringProperty nombre;
        private final SimpleStringProperty estado;
        private final SimpleStringProperty rol;

        private Person(String fName, String estado, String rol) {
            this.nombre = new SimpleStringProperty(fName);
            this.estado = new SimpleStringProperty(estado);
            this.rol = new SimpleStringProperty(rol);
        }

        public String getnombre() {
            return nombre.get();
        }

        public void setnombre(String fName) {
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

