package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Factory;
import model.Poll;

import model.Poll;
import model.PollList;
import model.Party;
import model.Factory;

public class MyController extends PollTrackerController{
	
	
		
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    ObservableList possiblePlacements = FXCollections.observableArrayList();
    
    
    @FXML
    private ChoiceBox<?> PlacementSpot;
    
    @FXML
    private Button AddPoll;

    @FXML
    private Button ClearButton;

    @FXML
    private TextField GetPollName;
    
  

    @FXML
    void AddThePoll(ActionEvent event) {
    	
    	/* Creates a copy of our old poll to be updated and replaced later
    			Poll[] newPollCopy = super.getPollList().getPolls();
    		
    			// This contains an integer from what selected in the drop down
    			int userInputFromDropDown = (int) PlacementSpot.getValue();

    			// This contains a string from what the user inputed
    			String userInputFromTextField = GetPollName.getText();
    			//creating a new poll object
    			 //have to get the size for the poll not polllist
    			  int size = super.getPollList().getPolls().length;
    			Poll newPoll = new Poll(userInputFromTextField,size);

    			// This uses the index of from the drop down and user given text to find the location and replace the old value with the users
    			//newPollCopy.set(userInputFromDropDown, userInputFromTextField);
    			newPollCopy[userInputFromDropDown - 1] = newPoll;

    			// Set the poll to be the new poll we have updated
    			super.setPollList(newPollCopy);
    		*/
    		}

    @FXML
    void Cleared(ActionEvent event) {
    	refresh();
    	//getPollName.clear();
    }
    
    void loadChoiceBox() {
    	possiblePlacements.removeAll(possiblePlacements);
    	//for (int index = 0; index < super.getPollList().length(); index++) {
    		//maybe use this to get items in choice box
    		//choiceBox.getItems().add("Choice 1");
    	
    	//can I use getPollList to get the names of the polls?
    	//int size = super.getPollList().getPolls().length;
    	//System.out.println(size + "    length");
    	//returns poll array 
    	//length is the number of polls
    	
    	//how do you get the number of elements in the PollList array?
    	//for (int index = 0;index < size; index++) {
    		//PlacementSpot.getItems().add(index + 1, null);
    	}
    	/*int a = 1;
    	int b = 2;
    	int c = 3;
    	int d = 4;
    	int e = 5;
    	possiblePlacements.addAll(a,b,c,d,e);
    	PlacementSpot.getItems().addAll(possiblePlacements);*/
    	//}
    

    @FXML
    void initialize() {
        assert AddPoll != null : "fx:id=\"AddPoll\" was not injected: check your FXML file 'AddPollView.fxml'.";
        assert ClearButton != null : "fx:id=\"ClearButton\" was not injected: check your FXML file 'AddPollView.fxml'.";
        assert GetPollName != null : "fx:id=\"GetPollName\" was not injected: check your FXML file 'AddPollView.fxml'.";

    }

	@Override
	public void refresh() {
		loadChoiceBox();
		// TODO Auto-generated method stub
		
	}
}
