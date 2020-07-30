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
import model.PollList;
import model.Factory;
import model.Party;

/**
 * Title: EditPollController
 * Description: Controller class for EditPollView.fxml
 * Controller that the user picks a poll from a choice box and then a party,
 * user will then enter seats and vote percent to change.
 *
 * @author Kenneth Liu 30066722
 * July 26th
 *
 */
public class EditPollController extends PollTrackerController {
	private Party partyToEdit;
	private Poll pollToEdit;
	/*
	 * Title: setPartyToUpdate
	 * Description: Takes in a party as a parameter (will be the one user selected from choice box)
	 * and sets the party
	 */
	private void setPartyToUpdate(Party aParty) {
		partyToEdit = aParty;
	}
	/*
	 * Title: getPartyToUpdate
	 * Description: returns the party set by the choicebox
	 * Returns: party object
	 */
	private Party getPartyToUpdate() {
		return partyToEdit;
	}
	/*
	 * Title: setPollToUpdate
	 * Description: Takes in a poll as a parameter (from choicebox)
	 *
	 */
	private void setPollToUpdate(Poll aPoll) {
		pollToEdit = aPoll;
	}
	/*
	 * Title: getPollToUpdate
	 * Description: returns the poll object set by the choicebox
	 * Returns: poll object
	 */
	private Poll getPollToUpdate() {
		return pollToEdit;
	}
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
	private ChoiceBox<String> pollToEditBox;

	@FXML
	private ChoiceBox<String> partyToUpdateBox;

	@FXML
	private TextField projectedNumOfSeatsTextField;

	@FXML
	private TextField projectedPercentVoteTextField;

	@FXML
	void clearButtonClicked(ActionEvent event){
		refresh();
	}
	@FXML
	void updatePartyClick(ActionEvent event) {			//event that will update party when button is clicked
		//get partyToEdit from getter method
		partyToEdit = getPartyToUpdate();
		if(partyToEdit != null && isValidSeats(projectedNumOfSeatsTextField) == true &&
				isValidVote(projectedPercentVoteTextField) == true) { //
			partyToEdit.setProjectedNumberOfSeats(Integer.valueOf((projectedNumOfSeatsTextField.getText())));
			partyToEdit.setProjectedPercentageOfVotes((Float.valueOf(projectedPercentVoteTextField.getText())/100));
		}
		refresh();
	}


	/**
	 * Title: isValidSeats
	 * Description: Method to check if user input for seats
	 * is an integer/number, returns true if it is, false if not
	 * @param Textfield input
	 * @return true or false
	 */
	private boolean isValidSeats(TextField input) {
		try {
			int seats = Integer.parseInt(input.getText());
			if(seats >= 0) {
				return true;
			}
			else {
				return false;
			}
		} catch(NumberFormatException e) {
			throw new NumberFormatException("Enter a valid number");
		}
	}
	/**
	 * Title: isValidVote
	 * Description: Method to check if user input for vote %
	 * is an integer/number, returns true if it is, false if not
	 * @param Textfield input
	 * @return true or false
	 */
	private boolean isValidVote(TextField input) {
		try {
			double vote = Double.parseDouble(input.getText());
			if(vote >= 0 && vote <=1) {
				return true;
			}
			else {
				return false;
			}
		} catch(NumberFormatException e) {								//
			throw new NumberFormatException("Enter a valid number of votes");
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
		PollList currentPollList = super.getPollList();			//obtain poll list from superclass
		Poll[] polls = currentPollList.getPolls();				//create an array of polls from poll list
		String[] pollNamesList = new String[polls.length];
		//create string array of names to display in choicebox
		for(int i = 0; i < polls.length; i++) {
			pollNamesList[i] = "Poll" + (i + 1);
		}
		pollToEditBox.setItems(FXCollections.observableArrayList(pollNamesList));

		//get selection model for poll to edit box
		pollToEditBox.getSelectionModel().selectedIndexProperty().addListener(
				new	ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue observable, Number oldValue, Number newValue) {
						//poll to update is based on the index of choicebox
						Poll pollToUpdate = polls[newValue.intValue()];
						setPollToUpdate(pollToUpdate);

						//create a partyNames String array to occupy choicebox, with length of number of parties in poll
						String[] partyNames = new String[getPollToUpdate().getNumberOfParties()];
						partyNames = getFactory().getPartyNames();

						//get parties array from poll selected from choicebox
						Party[] parties = getPollToUpdate().getPartiesSortedBySeats();

						partyToUpdateBox.setItems(FXCollections.observableArrayList(partyNames));

						partyToUpdateBox.getSelectionModel().selectedIndexProperty().addListener(
								new	ChangeListener<Number>() {
									@Override
									public void changed(ObservableValue observable1, Number oldValue1, Number newValue1) {
										//partyToUpdate is equal to the party element at index
										Party partyToUpdate = parties[newValue1.intValue()];
										//set party to update with party selected with choicebox
										setPartyToUpdate(partyToUpdate);
									}});
					}});
		//clearing the values of textfields and boxes when refresh is called
		projectedNumOfSeatsTextField.clear();
		projectedPercentVoteTextField.clear();
		partyToUpdateBox.setValue(null);
		pollToEditBox.setValue(null);
	}

}


	

