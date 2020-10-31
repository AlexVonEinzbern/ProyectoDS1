package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class VentanaAdminControl {

    @FXML
    private Button btAnadir;

    @FXML
    void addUsuario(ActionEvent event) throws IOException {
        Ventana formulario =
                new Ventana(null,"formularioUsuarios.fxml","FORMULARIO DE NUEVOS USUARIOS");
    }


}
