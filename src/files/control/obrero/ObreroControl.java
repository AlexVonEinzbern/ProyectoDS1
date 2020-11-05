package files.control.obrero;

import files.control.admin.EditarUsuariosControl;
import files.control.admin.EstadoUsuariosControl;
import files.control.admin.FormUsuariosControl;
import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ObreroControl {
    private ConexionBase con;
    public ObreroControl(ConexionBase con){
        this.con = con;
    }
/*
    @FXML
    void addUsuario(ActionEvent event) throws IOException {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/ventanaAdmin/ventanaAdmin.fxml"));
        FormUsuariosControl controller = new FormUsuariosControl(con);
        loader.setController(controller);
        panelAnasdir = loader.load();
        addBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelEditar,panelStatus);
        panelPrincipal.getChildren().add(panelAnadir); ;
    }

    @FXML    void editUsser(ActionEvent event) throws IOException {
        addUserBar.setVisible(false);
        editUserBar.setVisible(true);
        statusUserBar.setVisible(false);
        reportBar.setVisible(false);
        addBton.setDisable(false);
        statBton.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/ventanaAdmin/ventanaAdminEditar.fxml"));
        EditarUsuariosControl controller = new EditarUsuariosControl(con);
        loader.setController(controller);
        panelEditar = loader.load();
        editBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelStatus,panelAnadir);
        panelPrincipal.getChildren().add(panelEditar) ;
    }
    @FXML    void showStatus(ActionEvent event) throws IOException {
        addUserBar.setVisible(false);
        editUserBar.setVisible(false);
        statusUserBar.setVisible(true);
        reportBar.setVisible(false);
        editBton.setDisable(false);
        addBton.setDisable(false);
        panelFondo.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/ventanaAdmin/ventanaAdminEstado.fxml"));
        EstadoUsuariosControl controller = new EstadoUsuariosControl(con);
        loader.setController(controller);
        panelStatus = loader.load();
        statBton.setDisable(true);
        //this.btAnadir.getScene().setRoot(root);
        panelPrincipal.getChildren().removeAll(panelEditar,panelAnadir);
        panelPrincipal.getChildren().add(panelStatus) ;
    }
*/
}
