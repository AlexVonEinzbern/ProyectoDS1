package files.control.getente;

import files.modelo.ConexionBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class GerenteControl {
    private ConexionBase con;
    public GerenteControl(ConexionBase con){
        this.con = con;
    }

    @FXML    private ImageView logo;
    @FXML    private HBox panelFuncionesGerente;
    @FXML    private Button btonConsultUser;
    @FXML    private Button btonConsultActive;
    @FXML    private Label rol;
    @FXML    private AnchorPane panelPrincipal;
    @FXML    private AnchorPane panelFondo;

    @FXML    void reportesActivos(ActionEvent event) {

    }

    @FXML    void reportesUsuarios(ActionEvent event) {

    }

}
