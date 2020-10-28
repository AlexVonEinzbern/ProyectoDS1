package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class Controller {
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
    void press(MouseEvent event) {
        xoffset=event.getSceneX();
        yoffset=event.getSceneY();
    }
}
