package files.control;

import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstadoUsuariosControl {
    private ConexionBase con;
    private int idUsu;
    private String rolCon;
    private boolean estadoCon;

    public EstadoUsuariosControl(ConexionBase con) {
        this.con = con;
    }
    @FXML   private TextField idUsuario;
    @FXML   private Button buscar;
    @FXML   private TextArea estadoDelUsuario;
    @FXML   private ChoiceBox<?> estado;
    @FXML   private Button registrar;
    @FXML   private TableColumn<?, ?> nombreTabla;
    @FXML   private TableColumn<?, ?> estadoTabla;
    @FXML   private TableColumn<?, ?> rolTabla;

    @FXML   void anadir(ActionEvent event) {

    }
    @FXML   void buscarr(ActionEvent event) throws SQLException {
       idUsu =  Integer.parseInt(idUsuario.getText());
        ResultSet rs = con.consultar("Select * from usuarios where idusuario = "+idUsu);
        while (rs.next()){
            rolCon = rs.getString(8);
            estadoCon = rs.getBoolean(9);
        }
    }
}

//-fx-background-color    #14862d #14862d   -fx-font-style