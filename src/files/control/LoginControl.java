package files.control;
import files.modelo.VentanaAvisos;
import files.modelo.ConexionBase;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginControl {
    ConexionBase con;
    public LoginControl(ConexionBase con){
       this.con = con;
    }
    @FXML
    private Button entrar;


    @FXML
    private TextField usuario;

    private double xoffset;
    private double yoffset;
    @FXML private ImageView loginArrow;
    @FXML private ImageView helpArrow;
    @FXML private AnchorPane loginPanel;
    @FXML private AnchorPane helpPanel;
    @FXML private AnchorPane ventanaLogin;

    public void botonExit(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }

    public void botonLogin(MouseEvent event){

        loginPanel.setVisible(true);
        helpPanel.setVisible(false);
        loginArrow.setVisible(true);
        helpArrow.setVisible(false);
    }

    public void BotonHelp(MouseEvent event){

        loginPanel.setVisible(false);
        helpPanel.setVisible(true);
        loginArrow.setVisible(false);
        helpArrow.setVisible(true);

    }

    @FXML
    void move(MouseEvent event) {
        Stage as = (Stage) this.loginPanel.getScene().getWindow();
        as.setX(event.getScreenX()-xoffset);
        as.setY(event.getScreenY()-yoffset);


    }

    @FXML
    private TextField contraseña;

    @FXML
    void press(MouseEvent event) {
        xoffset=event.getSceneX();
        yoffset=event.getSceneY();
    }
    @FXML
    void ConsultaLogin(ActionEvent event) throws SQLException, IOException {


        Window owner = entrar.getScene().getWindow();
        String password = contraseña.getText() ;
        int usser = 0;
        try {
            usser = Integer.parseInt(usuario.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            VentanaAvisos.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Ingrese un usuario valido ");
        }


        if(password.isEmpty() || (usser == 0)){
            VentanaAvisos.showAlert(Alert.AlertType.ERROR, owner, " Error!",
                    "usuario o contraceña vacios");
            return;
        }

        ResultSet rs = con.consultar
                ("Select * from usuarios where idusuario = "+usser);
        int usserbase = 0;
        String passwordbase = null;
        Boolean activo= null;
        String rol= null ;
        while(rs.next()){
             usserbase = rs.getInt("idusuario");
             passwordbase = rs.getString("password");
             activo = rs.getBoolean("estaactivo");
             rol = rs.getString("rolusuario");
        }
         //   System.out.println(usserbase +"  "+ passwordbase +"  " + activo +"   "+ rol );
         //   System.out.println(passwordbase.equals(password) +"  "+ usserbase==usser +"   "+ activo);
        if(passwordbase.equals(password)  && activo){
            switch(rol){
                case "Admin":
                    //Ventana ventana = new Ventana(con,"../vista/ventanaAdmin.fxml",rol );
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/ventanaAdmin.fxml"));
                    AdminControl controller = new AdminControl(con);
                    loader.setController(controller);
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage ventana = new Stage();
                    ventana.setScene(scene);
                    ventana.setTitle("Admin");
                    ventana.show();
                    owner = ventana.getScene().getWindow();
                    VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                            "Ingreso con exito","welcome "+ usser);
                    Stage win = (Stage) this.contraseña.getScene().getWindow();
                    win.close();
                    break;
                case "Obrero":
                    loader = new FXMLLoader(getClass().getResource("../vista/ventanaaObreo.fxml"));
                    ObreroControl contro = new ObreroControl(con);
                    loader.setController(contro);
                    root = loader.load();
                    scene = new Scene(root);
                    ventana = new Stage();
                    ventana.setScene(scene);
                    ventana.setTitle("Obrero");
                    ventana.show();
                    owner = ventana.getScene().getWindow();
                    VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                            "Ingreso con exito","welcome "+ usser);
                    win = (Stage) this.contraseña.getScene().getWindow();
                    win.close();
                    break;
                case "Gerente":
                     loader = new FXMLLoader(getClass().getResource("../vista/ventanaGerente.fxml"));
                    GerenteControl contr = new GerenteControl(con);
                    loader.setController(contr);
                    root = loader.load();
                    scene = new Scene(root);
                    ventana = new Stage();
                    ventana.setScene(scene);
                    ventana.setTitle("Gerente");
                    ventana.show();

                    owner = ventana.getScene().getWindow();
                    VentanaAvisos.showAlert(Alert.AlertType.CONFIRMATION,owner,
                            "Ingreso con exito","welcome "+ usser);
                    win = (Stage) this.contraseña.getScene().getWindow();
                    win.close();
                    break;
            }
         }else {
             VentanaAvisos.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                     "Usuario o contraseña incorrectos por favor verifique la informacion");
             return;
         }

    }

}

