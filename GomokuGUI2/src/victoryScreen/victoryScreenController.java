package victoryScreen;


import java.net.URL;
import java.util.ResourceBundle;

import application.startMenuController;
import game.Game;
import game.HumanPlayer;
import game.Player;
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
    	//call the board again
    	//keep settings 
    	if(startMenuController.getChoice() == 1) {
    	Game game=new Game();
		
		Player aHuman1 = new HumanPlayer (game.getBoard(),1);//can be either Ai or HumanPlayer

		Player aHuman2 = new HumanPlayer (game.getBoard(),2);//can be either Ai or HumanPlayer

		 while(game.play(aHuman1)&&game.play(aHuman2));//keep playing if condition is satisfied
    	}
    	if(startMenuController.getChoice() == 2) {
        	Game game=new Game();
    		
    		Player aHuman1 = new HumanPlayer (game.getBoard(),1);//can be either Ai or HumanPlayer

    		Player aHuman2 = new HumanPlayer (game.getBoard(),2);//can be either Ai or HumanPlayer

    		 while(game.play(aHuman1)&&game.play(aHuman2));//keep playing if condition is satisfied
        	}
    }

    @FXML
    void initialize() {
        assert playAgainButton != null : "fx:id=\"playAgainButton\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
        assert victoryLabel != null : "fx:id=\"victoryLabel\" was not injected: check your FXML file 'VictoryScreen.fxml'.";

    }
}

