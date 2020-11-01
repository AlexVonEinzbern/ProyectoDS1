package files.control;
import files.modelo.Ventana;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminControl {

    @FXML
    private Button btAnadir;

    @FXML
    void addUsuario(ActionEvent event) throws IOException {
        Ventana formulario =
                new Ventana(null,"../vista/formularioUsuarios.fxml","FORMULARIO DE NUEVOS USUARIOS");
    }


}