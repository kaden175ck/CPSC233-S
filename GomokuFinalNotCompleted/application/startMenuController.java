package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import game.Ai;
import game.Game;
import game.HumanPlayer;
import game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
public class startMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label gomokuTitle;

    @FXML
    private Button pVsAIButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button pVsPButton;

    
    //choice is whether player chose vs computer or vs player
    public static int choice;
    
    
    @FXML
    void startPvsAI(ActionEvent event)  {
    	//choice = 1;
    	//call board
    	//create two new player objects (one person and one AI)
    	try {
    	Game game=new Game();
		
    	FXMLLoader loader = new FXMLLoader();
    	Parent boardView = loader.load(new FileInputStream("src/view/VictoryScreen.fxml"));
    	Scene boardViewScene = new Scene(boardView); 
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.setScene(boardViewScene);
    	window.show();
    	
		Player aHuman1 = new HumanPlayer (game.getBoard(),1);//can be either Ai or HumanPlayer

		Player aHuman2 = new Ai (game.getBoard(),2);//can be either Ai or HumanPlayer

		 while(game.play(aHuman1)&&game.play(aHuman2));//keep playing if condition is satisfied
		 }	
    catch(IOException e ) {
    	System.out.println("File not found");
    }
}
    @FXML
    void startPVsPlayer(ActionEvent event) {
    	/*/call board
    	//create two player objects (two people)
    	//choice = 2;
    	
    	try {
    	FXMLLoader loader = new FXMLLoader();
    	Parent boardView = loader.load(new FileInputStream("src/view/VictoryScreen.fxml"));
    	Scene boardViewScene = new Scene(boardView); 
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.setScene(boardViewScene);
    	window.show();
    	Game game=new Game();
		
		Player aHuman1 = new HumanPlayer (game.getBoard(),1);//can be either Ai or HumanPlayer

		Player aHuman2 = new HumanPlayer (game.getBoard(),2);//can be either Ai or HumanPlayer

		 while(game.play(aHuman1)&&game.play(aHuman2));//keep playing if condition is satisfied
		 
    }catch(IOException e) {
    	System.out.println("File not found");
    }*/
    	HvsHboardScene = (new HumanvsHumanBoard()).getboardScene();
    }
    public static int getChoice() {
    	return choice;
    }
    @FXML
    void quitButton(ActionEvent event) {
    	Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert gomokuTitle != null : "fx:id=\"gomokuTitle\" was not injected: check your FXML file 'startMenu.fxml'.";
        assert pVsAIButton != null : "fx:id=\"pVsAIButton\" was not injected: check your FXML file 'startMenu.fxml'.";
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'startMenu.fxml'.";
        assert pVsPButton != null : "fx:id=\"pVsPButton\" was not injected: check your FXML file 'startMenu.fxml'.";

    }
}
