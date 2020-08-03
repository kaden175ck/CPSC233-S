package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class startMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label gomokuTItle;

    @FXML
    private Button pVsAIButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button pVsPButton;

    @FXML
    void startPvsAI(ActionEvent event) {

    }

    @FXML
    void startPVsPlayer(ActionEvent event) {

    }

    @FXML
    void quitButton(ActionEvent event) {
    	Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert gomokuTItle != null : "fx:id=\"gomokuTItle\" was not injected: check your FXML file 'startMenu.fxml'.";
        assert pVsAIButton != null : "fx:id=\"pVsAIButton\" was not injected: check your FXML file 'startMenu.fxml'.";
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'startMenu.fxml'.";
        assert pVsPButton != null : "fx:id=\"pVsPButton\" was not injected: check your FXML file 'startMenu.fxml'.";

    }
}
