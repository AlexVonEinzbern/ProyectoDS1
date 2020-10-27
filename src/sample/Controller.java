package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class Controller {

    @FXML private ImageView loginArrow;
    @FXML private ImageView helpArrow;
    @FXML private AnchorPane loginPanel;
    @FXML private AnchorPane helpPanel;

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


}
