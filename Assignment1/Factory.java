/**
 * Class Name: Factory
 * Description: This class creates random parties, polls, and pollLists randomly
 * @author kenneth Liu 30066722
 * @date 2020 7.9
 */

//import java util classes
import java.util.*;

public class Factory {
	//instance variables
	private int numOfSeats;		// number of seats available in the election
	private String[] partyNames = {"BQ","CPC","Green","Liberal","NDP","PPC","Rhinoceros"};	//names of all the parties participating in the election


	/**
	 * Title: Factory
	 * Description: Constructor for Factory class that takes the number of seats in the election
	 * @param numOfSeats - number of seats in the election hence must be a positive number
	 */
	public Factory(int numOfSeats) {
		if(numOfSeats >=1) { 		//num of seats must be a positive number
			this.numOfSeats = numOfSeats;
		}
	}

	/**
	 * Title: setPartyNames
	 * Description: method to set partyNames array
	 * @param names array of type String - must not be null
	 * @return void
	 */
	public void setPartyNames(String[] names) {
		if(names != null) {
			this.partyNames = names;
		}
	}

	/**
	 * Title: getPartyNames
	 * Description: method that retrieves the partyNames array
	 * @return partyNames array of type String
	 */
	public String[] getPartyNames() {
		return partyNames;
	}

	/**
	 * Title: createRandomParty
	 * Description: method to create a random party, seats and percent will be randomized based
	 * on the parameters party object will only return if seat percent and vote percent differ by at most 5%
	 * @param partyName - name of the party created
	 * @param maximumSeats - max seats the party can have
	 * @param maximumPercent - max percent of votes the party can have
	 * @return new party object with parameters of partyName, seats and percent based on maximum seats and percent
	 */
	public Party createRandomParty(String partyName, int maximumSeats, int maximumPercent) {

		Random rand = new Random(); 	//creating rand object from random class

		//generating seats and party percent using randomizer based on
		//bounds - maximumSeats and maximumPercent parameters passed in method
		int totalAvailableSeats = numOfSeats;
		float maxPercent = maximumPercent;
		int seats = rand.nextInt(maximumSeats + 1);
		float partyPercent = (rand.nextFloat());
		float projectedNumberOfVotes = ((float)(rand.nextInt((int)maxPercent + 1))/100);
		float seatPercent = (seats/totalAvailableSeats);
		float difference =(seatPercent-partyPercent); 	//calculating difference of percents
		//if seatPercent and partyPercent differ by at most 5% create the randomParty object
		if ((Math.abs(difference)) <= 5 ) {
			return new Party(partyName, seats, projectedNumberOfVotes);
		}
		else {
			return null;		//otherwise return null
		}
	}

	/**
	 * Title: createRandomPoll
	 * Description: Method that creates a random poll object and returns it
	 * @param name - String that is the name of the poll
	 * @return the random poll object that was generated
	 */
	public  Poll createRandomPoll(String name) {
		Poll poll = new Poll(name, partyNames.length);		//creating new poll object
		String[] copy = getPartyNames();					//creating new array for partyNames
		Random rand = new Random();
		float maxPercent = 100;
		int numberOfSeats = this.numOfSeats;

		int i = 0;
		for (i = 0; i < partyNames.length; i++) {	//iterating through partyNames array
			for(int j = 0; j < copy.length; j++) {
				int randomIndex = rand.nextInt((copy.length));
				String randomPartyName = copy[randomIndex];
				copy[randomIndex] = copy[j];
				copy[j] = randomPartyName;
			}
			Party p = createRandomParty(copy[i],numberOfSeats, (int)maxPercent);
			//updating number of maximum seats and percent each party can have
			numberOfSeats -= p.getProjectedNumberOfSeats();
			maxPercent -= p.getProjectedPercentageOfVotes();
			poll.addParty(p);		//adds party object p created from create random Party to poll

		}


		return poll;
	}
	/**
	 * Title: createRandomPollList
	 * Description: Creating a random poll list and returning said list
	 * @param numOfPolls - number of polls conducted
	 * @return poll list that was randomly generated
	 */
	public PollList createRandomPollList(int numOfPolls) {
		PollList list = new PollList(numOfPolls,numOfSeats);		//creating new pollList object
		for (int counter = 0; counter < numOfPolls; counter++) {
			list.addPoll(createRandomPoll("Poll" + counter));		//adding polls using createRandomPoll method to list object
		}
		return list;
	}
	//bonus
	public PollList promptForPollList(int numOfPolls) {
		return createRandomPollList(numOfPolls);
	}
}





