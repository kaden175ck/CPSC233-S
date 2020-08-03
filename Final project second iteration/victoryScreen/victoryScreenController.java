package victoryScreen;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class victoryScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button playAgainButton;

    @FXML
    private Button quitButton;

    @FXML
    private Label victoryLabel;

    @FXML
    void getWinnerLabel() {
    	
    }
    
    @FXML
    void quitButtonClicked(ActionEvent event) {
    	Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void playAgainButtonClicked(ActionEvent event) {
    	
    }

    @FXML
    void initialize() {
        assert playAgainButton != null : "fx:id=\"playAgainButton\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
        assert victoryLabel != null : "fx:id=\"victoryLabel\" was not injected: check your FXML file 'VictoryScreen.fxml'.";

    }
}

