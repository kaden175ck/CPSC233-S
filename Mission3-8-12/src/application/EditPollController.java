package application;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import model.Poll;
import model.PollFullException;
import model.PollList;
import model.Factory;
import model.InvalidPartyDataException;
import model.Party;
import javafx.scene.control.Label;

/**
 * Title: EditPollController
 * Description: Controller class for EditPollView.fxml
 *
 *
 * @author  Kenneth Liu 30066722
 * July 26th
 *
 */
public class EditPollController extends PollTrackerController {
	//Initialize Instance Variables
	private Party partyToEdit;
	private int pollToEditBoxIndex = 0;
	private int partyToUpdateIndex = 0;
	private String thePartyName = "";

    @FXML
    private Label errorseats;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	//initialize the necessary buttons, choiceboxes, and textfields for the application
	@FXML
	private Button updatePartyButton;

	@FXML
	private Button clearButton;

	@FXML
	private ChoiceBox<String> pollToEditBox;//var to add this party to the poll in this controller, intialize this in the refresh methods

	@FXML
	private ChoiceBox<String> partyToUpdateBox;

	@FXML
	private TextField projectedNumOfSeatsTextField;

	@FXML
	private TextField projectedPercentVoteTextField;
	 
	/**
	 * Title: clearButtonClicked
	 * Description: Event based action when user clicks the clear button
	 * calls the refresh method
	 */
	@FXML
	void clearButtonClicked(ActionEvent event){
		errorseats.setText("");
		
		refresh();
	}
	/**
	 * Title: updatePartyClick
	 * Description: Event based action when user clicks the update party button
	 * gets party, poll, projected seats and vote from user input
	 * 
	 */
	@FXML
	void updatePartyClick(ActionEvent event) {		//serperate try catch poll.add party selected party should be used when add is called.
		//Getting PollList object
		PollList aNewPollListObject = getPollList();
		//Get Poll array
		Poll[] aNewPollList = aNewPollListObject.getPolls();
		//Get the Poll selected in choicebox, chosen by the user
		Poll thePollToSet = aNewPollList[pollToEditBoxIndex];
		//Getting the party selected by the choicebox
		Party theParty = thePollToSet.getPartiesSortedBySeats()[partyToUpdateIndex];
		// create a new Party object
		Party partyToAdd = null;
		try {
			
			partyToAdd = new Party(theParty.getName(), Integer.valueOf(projectedNumOfSeatsTextField.getText()),Float.valueOf(projectedPercentVoteTextField.getText()));
		} catch (NumberFormatException e1) {
			System.out.println(e1.getMessage());
		} catch (InvalidPartyDataException e1) {
			System.out.println(e1.getMessage());
			(errorseats).setText("The seates can't be negative and float. The votes nust between 0 and 1.");
		}
		//if the value of the Party is valid
		if(partyToAdd != null) {
		try {
			//Add the new Party to the selected poll
			thePollToSet.addParty(partyToAdd);
		} catch (PollFullException e) {
			errorseats.setText("You are change the information of the selected Party in a full Poll.");
			System.out.println(e.getMessage());
		}
		}
		refresh();
	}


	@FXML
	void initialize() {
		assert pollToEditBox != null : "fx:id=\"pollToEditBox\" was not injected: check your FXML file 'EditPollView.fxml'.";
		assert updatePartyButton != null : "fx:id=\"updatePartyButton\" was not injected: check your FXML file 'EditPollView.fxml'.";
		assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'EditPollView.fxml'.";
		assert partyToUpdateBox != null : "fx:id=\"partyToUpdateBox\" was not injected: check your FXML file 'EditPollView.fxml'.";
		assert projectedNumOfSeatsTextField != null : "fx:id=\"projectedNumOfSeatsTextField\" was not injected: check your FXML file 'EditPollView.fxml'.";
		assert projectedPercentVoteTextField != null : "fx:id=\"projectedPercentVoteTextField\" was not injected: check your FXML file 'EditPollView.fxml'.";
		
	}
	
	public void refresh() {
		
		// getting poll list and naming them based on order
		Poll[] pollToEditBoxList = getPollList().getPolls();				
		String[] pollToEditBoxListName = new String[pollToEditBoxList.length];
		for(int i = 0; i < pollToEditBoxList.length; i++) {
			pollToEditBoxListName[i] = pollToEditBoxList[i].getPollName();
		}
		//Occupy choice box with the namess of the polls
		pollToEditBox.setItems(FXCollections.observableArrayList(pollToEditBoxListName));
		
		
		//adding a listener for the pollToEditBox
	    //getting the index of poll choice box to set the poll to edit
		pollToEditBox.getSelectionModel().selectedIndexProperty().addListener(
				new	ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue observable, Number oldValue, Number newValue) {
						int index = newValue.intValue();
            			if (index >= 0)
            				pollToEditBoxIndex = index;  
	 }});

	    //String[] partyToUpdateBoxListNames = new String[pollToEditBoxList[pollToEditBoxIndex].getNumberOfParties()];
		String[] partyToUpdateBoxListNames = new String[getPollList().getPolls()[pollToEditBoxIndex].getPartiesSortedBySeats().length];
		for(int i=0; i<getPollList().getPolls()[pollToEditBoxIndex].getPartiesSortedBySeats().length; i++) {
			partyToUpdateBoxListNames[i] = getPollList().getPolls()[pollToEditBoxIndex].getPartiesSortedBySeats()[i].toString();
		}
	    //partyToUpdateBoxListNames = getPollList().getPolls()[pollToEditBoxIndex].getPartiesSortedBySeats();
	    // partyToUpdateBoxListNames = getFactory().getPartyNames();
	    partyToUpdateBox.setItems(FXCollections.observableArrayList(partyToUpdateBoxListNames));
		partyToUpdateBox.getSelectionModel().selectedIndexProperty().addListener(
				new	ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue observable, Number oldValue, Number newValue) {
						int index = newValue.intValue();
						//System.out.println("the input value" + newValue);
            			if (index >= 0)
            				partyToUpdateIndex = index;
            			//Get the Party ChoiceBox Selection index 
		}});
		//Get the name of Party which selected in Party Choice Box
		thePartyName = partyToUpdateBoxListNames[partyToUpdateIndex];
		projectedNumOfSeatsTextField.clear();
        projectedPercentVoteTextField.clear();
        partyToUpdateBox.setValue(null);
        pollToEditBox.setValue(null);
	}

}


	

