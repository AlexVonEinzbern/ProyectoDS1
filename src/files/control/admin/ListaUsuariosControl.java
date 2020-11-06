package files.control.admin;

import files.modelo.ConexionBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    private ConexionBase con;
    private ObservableList<Person> datos = FXCollections.observableArrayList();

    @FXML   private TableView<Person> tablaUsuarios;
    @FXML   private TableColumn<Person, String> nombreUsuario;
    @FXML   private TableColumn<Person, String> estadoUsuario;
    @FXML   private TableColumn<Person, String> rolUsuario;



    public ListaUsuariosControl(ConexionBase con) throws SQLException {
        this.con = con;




    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreUsuario.setCellValueFactory(new PropertyValueFactory<Person,String>("Nombre"));
       estadoUsuario.setCellValueFactory(new PropertyValueFactory<Person,String>("Estado"));
        rolUsuario.setCellValueFactory(new PropertyValueFactory<Person,String>("Rol"));
        ResultSet rs = null;
        try {
            rs = con.consultar("select * from usuarios");
            String aux="";
            while(rs.next()){
                if( rs.getBoolean("estadousuario")){
                    aux = "Activo";
                }else { aux = "Inactivo";}
              datos.add(new Person( rs.getString("nombreusuario"),aux,rs.getString("rolusuario")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
datos.add(new Person("mi nombre","ture","est"));
        tablaUsuarios.setItems(datos);

    }


    public class Person {
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

