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
 *
 *
 * @author Kenneth Liu 30066722
 * July 26th
 *
 */
public class EditPollController extends PollTrackerController {
	private Party partyToEdit;
	private int pollToEditBoxIndex = 0;
	private int partyToUpdateIndex = 0;
	private String thePartyName = "";
	

	private void setPartyToUpdate(Party aParty) {
		partyToEdit = aParty;
	}
	private Party getPartyToUpdate() {
		return partyToEdit;
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
	void updatePartyClick(ActionEvent event) {		
		

		//PollList
		PollList aNewPollListObject = getPollList();
		//Poll List
		Poll[] aNewPollList = aNewPollListObject.getPolls();
		//Poll
		Poll thePollToSet = aNewPollList[pollToEditBoxIndex];
		//Party
		System.out.println("Does button get "+thePartyName);
		Party thePartyToSet = thePollToSet.getParty(thePartyName);
		
		//set seats
		thePartyToSet.setProjectedNumberOfSeats(Integer.valueOf(projectedNumOfSeatsTextField.getText()));
		System.out.println("Does the seats be set" + thePartyToSet.getProjectedNumberOfSeats());
		//set votes
		thePartyToSet.setProjectedPercentageOfVotes(Integer.valueOf(projectedPercentVoteTextField.getText()));
		System.out.println("Does the votes be set" + thePartyToSet.getProjectedPercentageOfVotes());
		setPollList(aNewPollListObject);
		
		
		
		/**partyToEdit = getPartyToUpdate();
		if(partyToEdit != null && isValidSeats(projectedNumOfSeatsTextField) == true &&
				isValidVote(projectedPercentVoteTextField) == true) {
			partyToEdit.setProjectedNumberOfSeats(Integer.valueOf((projectedNumOfSeatsTextField.getText())));
			partyToEdit.setProjectedPercentageOfVotes((Float.valueOf(projectedPercentVoteTextField.getText())/100));
		}
		refresh();*/
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
			System.out.println("New number of seats: " + vote);
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
		
	
		//pollToEditBox
		
	}



	public void refresh() {
		
		Poll[] pollToEditBoxList = getPollList().getPolls();				
		String[] pollToEditBoxListName = new String[pollToEditBoxList.length];
		for(int i = 0; i < pollToEditBoxList.length; i++) {
			pollToEditBoxListName[i] = "Poll" + (i + 1);
		}
		pollToEditBox.setItems(FXCollections.observableArrayList(pollToEditBoxListName));
		
		
		
		pollToEditBox.getSelectionModel().selectedIndexProperty().addListener(
				new	ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue observable, Number oldValue, Number newValue) {
						int index = newValue.intValue();
            			if (index >= 0)
            				pollToEditBoxIndex = index;  
            			//Get the Poll ChoiceBox Selection index 
	 }});

	    String[] partyToUpdateBoxListNames = new String[pollToEditBoxList[pollToEditBoxIndex].getNumberOfParties()];
	    partyToUpdateBoxListNames = getFactory().getPartyNames();
	    partyToUpdateBox.setItems(FXCollections.observableArrayList(partyToUpdateBoxListNames));
	       //Party[] parties = pollToEditBoxList[pollToEditBoxIndex].getPartiesSortedBySeats();  
		partyToUpdateBox.getSelectionModel().selectedIndexProperty().addListener(
				new	ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue observable, Number oldValue, Number newValue) {
						int index = newValue.intValue();
						System.out.println("the input value" + newValue);
            			if (index >= 0)
            				partyToUpdateIndex = index;
            			//Get the Party ChoiceBox Selection index 
		}});
		//Get the name of Party which selected in Party Choice Box
		thePartyName = partyToUpdateBoxListNames[partyToUpdateIndex];
		
	}

}


	

