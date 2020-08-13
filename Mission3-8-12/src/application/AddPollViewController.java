/*
 *ClassName: AddPollViewController
 *Description: 
 * @Author Angus Milne 30071887
 * @Date August 3, 2020
 * 
 */

package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Factory;
import model.InvalidPartyDataException;
import model.Poll;

import model.Poll;
import model.PollList;
import model.Party;
import model.Factory;

public class AddPollViewController extends PollTrackerController{
	String[] pollNumber;
	//private Poll[] listOfPolls = super.getPollList().getPolls();
	
		
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    
    
    
    @FXML
    private ChoiceBox<String> PlacementSpot;
    
    @FXML
    private Button AddPoll;

    @FXML
    private Button ClearButton;

    @FXML
    private TextField GetPollName;
    
  
    /**
	  * Title: AddThePoll
	  * Description: This method will take the text from the textfield and the index of the 
	  * dropdown and add a new poll with that name at the speified location
	  * 
	  * @param event
     * @throws InvalidPartyDataException 
	  */

    @FXML
    void AddThePoll(ActionEvent event) throws InvalidPartyDataException {
    	//get the size of the poll array
    	
    	//how to get the selected index from the choice box
    	int indexOfPollChange = PlacementSpot.getSelectionModel().getSelectedIndex();
    	int size = getFactory().getPartyNames().length;
    	//getting the drop down choice
    	int numOfSeats = getPollList().getNumOfSeats();
    	
    	//getting the text entered from the text field
    	String userInputFromTextField = GetPollName.getText();
    	
    	
    	//call factory class to populate the poll
    	Poll newPoll = getFactory().createRandomPoll(userInputFromTextField);
    	Poll[] listOfPolls = getPollList().getPolls();
    	//the Poll needs to be put into the PollList 
    	listOfPolls[indexOfPollChange] = newPoll;
    	//PollList newPollList = new PollList(size, numOfSeats);
    	//how do you make the new pollList have the new poll object being created?
    	
    	//needs to set the PollList with the new pollList
    	//setPollList(newPollList);
    	
    		}
    /**
	  * Title: Cleared
	  * Description: This method will clear the text field and the choice box
	  * 
	  * @param event
	  */
    @FXML
    void Cleared(ActionEvent event) {
    	refresh();
    		
    			
    			
    			
    	
    	
    }
    
    
    
    
    /**
	  * Title: loadChoiceBox
	  * Description: This method populates a String array and places those values in the choice box
	  * 
	  * @param none
	  */
   ObservableList possiblePlacements = FXCollections.observableArrayList();
    void loadChoiceBox() { 
    	
    	
    	
    	//make an array of the locations of the polls
    			int size = super.getPollList().getPolls().length;
    			String[] pollNumber = new String[size];
    			int pollLocation = 1;
    			for (int index = 0; index < size; index++) {
    				String pollLocationString = String.valueOf(pollLocation);
    				pollNumber[index] = pollLocationString;
    				pollLocation++;
    				
    			}
    			
        		PlacementSpot.setItems(FXCollections.observableArrayList(pollNumber));
    }
    

    @FXML
    void initialize() {
        assert AddPoll != null : "fx:id=\"AddPoll\" was not injected: check your FXML file 'AddPollView.fxml'.";
        assert ClearButton != null : "fx:id=\"ClearButton\" was not injected: check your FXML file 'AddPollView.fxml'.";
        assert GetPollName != null : "fx:id=\"GetPollName\" was not injected: check your FXML file 'AddPollView.fxml'.";
      
    }

    /**
	  * Title: ClearButtonClicked
	  * Description: This method clears the text field and choice box and reloads the choice box with the most recent data
	  * 
	  * @param none
	  */
	@Override
	public void refresh() {
		GetPollName.clear();
		PlacementSpot.setValue(null);
		loadChoiceBox();
		
		// TODO Auto-generated method stub
		
	}
}
