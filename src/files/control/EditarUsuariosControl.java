package files.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

    public class EditarUsuariosControl {

        @FXML
        private TextField editNombre;

        @FXML
        private TextField editDireccion;

        @FXML
        private DatePicker Editfecha;

        @FXML
        private ChoiceBox<?> editRoles;

        @FXML
        private TextField editEmail;

        @FXML
        private TextField editCedula;

        @FXML
        private TextField editTelefono;

        @FXML
        private ChoiceBox<?> editSedes;

        @FXML
        private TextField editEstado;

        @FXML
        private TextField editContrasena1;

        @FXML
        private Button registrar;

        @FXML
        void editUsuario(ActionEvent event) {

        }

    }


