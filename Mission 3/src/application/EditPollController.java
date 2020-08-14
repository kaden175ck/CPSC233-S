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
	//declare Instance Variables:
	private int pollToEditBoxIndex;
	private int partyToUpdateIndex;
	
	//initialization of the listener's variables choice box. make the poll index and party index to default -1.
	private int pollindex = -1;
	private int partyindex = -1;
	
	

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
	void updatePartyClick(ActionEvent event) throws PollFullException {		//serperate try catch poll.add party selected party should be used when add is called.
		if(pollindex < 0 || partyindex < 0) {
			errorseats.setText("Please select the Poll and the Party first.");
			System.out.println("Please select the Poll and the Party first. The current Poll is full.");
		}
		//Getting PollList object
		PollList aNewPollListObject = getPollList();
		//Get Poll array
		Poll[] aNewPollList = aNewPollListObject.getPolls();
		/*
		 * 
		 * This try-catch block is used to handle the event when the Poll choice box and Party box didn't be selected.
		 */
		try {
			//Get the Poll selected in choicebox, chosen by the user
			Poll thePollToSet = aNewPollList[pollToEditBoxIndex];
			//Getting the party selected by the choicebox
			Party theParty = thePollToSet.getPartiesSortedBySeats()[partyToUpdateIndex];
			// create a new Party object
			Party partyToAdd = null;
			//set seats
			/*
			 * This try-catch block make sure that invalid number is handled and error message is printed.
			 */
			try {
				theParty.setProjectedNumberOfSeats(Integer.valueOf(projectedNumOfSeatsTextField.getText()));
			} catch (NumberFormatException e) {
				errorseats.setText("The seats should be non negative integer, and the vote should be a float between 0 and 1.");
				System.out.println(e.getMessage());
			} catch (InvalidPartyDataException e) {
				errorseats.setText("The seats should be non negative integer, and the vote should be a float between 0 and 1.");
				System.out.println(e.getMessage());
			}
			//set votes
			/*
			 * This try-catch block make sure that invalid number is handled and error message is printed.
			 */
			try {
				theParty.setProjectedPercentageOfVotes(Float.valueOf(projectedPercentVoteTextField.getText()));
			} catch (NumberFormatException e) {
				errorseats.setText("The seats should be non negative integer, and the vote should be a float between 0 and 1.");
				System.out.println(e.getMessage());
			} catch (InvalidPartyDataException e) {
				errorseats.setText("The seats should be non negative integer, and the vote should be a float between 0 and 1.");
				System.out.println(e.getMessage());
			}

		}catch(NullPointerException e) {
			errorseats.setText("Please select the Poll and the Party first.");
			System.out.println(e.getMessage());
		}
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
						//int pollindex = newValue.intValue();
						pollindex = newValue.intValue();
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
						//int partyindex = newValue.intValue();
						partyindex = newValue.intValue();
						//System.out.println("the input value" + newValue);
            			if (partyindex >= 0)
            				partyToUpdateIndex = partyindex;
            			//Get the Party ChoiceBox Selection index 
		}});
		
		//Get the name of Party which selected in Party Choice Box
		projectedNumOfSeatsTextField.clear();
        projectedPercentVoteTextField.clear();
        partyToUpdateBox.setValue(null);
        pollToEditBox.setValue(null);
	}
}


	

