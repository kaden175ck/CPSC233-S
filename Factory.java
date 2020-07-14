/**
 * Team F Factory Class for Assignment 1
 * @author kenneth Liu 30066722
 */

//import random class
import java.util.Random;
/**
 * Factory class creates Parties, Polls and PollLists either randomly or by user input
 *
 */
public class Factory {
	//instance variables

	// number of seats available in the election
	private int numOfSeats;
	//names of all the parties participating in the election
	private String[] partyNames = {"BQ","CPC","Green","Liberal","NDP","PPC","Rhinoceros"};

	/**
	 * Constructor for Factory class
	 * @param numOfSeats - number of seats in the election
	 *
	 */
	public Factory(int numOfSeats) {
		if(numOfSeats >=1) { 		//num of seats must be a positive number
			this.numOfSeats = numOfSeats;
		}
	}

	/**
	 * setter method for party names
	 * @param names - names for parties
	 * sets Factory object party name to argument
	 */
	public void setPartyNames(String[] names) {
		if(names != null) {
			this.partyNames = names;
		}
	}

	/**
	 * getter method for partyNames
	 * @return partyNames
	 */
	public String[] getPartyNames() {
		return partyNames;
	}

	/**
	 * method to create a random party, seats and percent will be randomized based on the parameters
	 * party object will only return if seat percent and vote percent differ by at most 5%
	 * @param partyName - name of the party created
	 * @param maximumSeats - max seats the party can have
	 * @param maximumPercent - max percent of votes the party can have
	 * @return new party object with parameters of partyName, seats and percent based on maximum seats and percent
	 */
	public Party createRandomParty(String partyName, int maximumSeats, int maximumPercent) {

		Random rand = new Random(); 	//creating rand object from random class

		/*
		 * generating seats and party percent using randomizer based on
		 * bounds - maximumSeats and maximumPercent parameters passed in method
		 */
		int actualMaxSeats = maximumSeats + 1;		//actualMaxSeats and actualMaxPercentfor
		//random int generator to be inclusive
		int actualMaxPercent = maximumPercent + 1;
		int seats = rand.nextInt(actualMaxSeats);
		float partyPercent = rand.nextInt(actualMaxPercent)/100;
		float seatPercent = seats/actualMaxSeats;		//calculating seatpercent

		//if seatPercent and partyPercent differ by at most 5% (0.05) create the randomParty object
		if ((seatPercent-partyPercent) <= 0.05) {
			return new Party(partyName, seats, partyPercent);
		}
		return null;		//otherwise return null
	}

	/**
	 * Method that creates a random poll object and returns it
	 * @param name - String that is the name of the poll
	 * @return the random poll object that was generated
	 */
	public Poll createRandomPoll(String name) {
		Poll poll = new Poll(name, partyNames.length);		//creating new poll object
		String[] copy = getPartyNames();					//creating new array for partyNames
		Random rand = new Random();
		int maxPercent = 100;
		int numberOfSeats = this.numOfSeats;

		for (String partyName : copy) {			//iterating through copy array
			Party p = createRandomParty(partyName,numberOfSeats, maxPercent);
			poll.addParty(p);		//adds party object p created from create random Party to poll
			/*
			 * for loop that scrambles the copied partyNames array
			 * method from:
			 * https://www.journaldev.com/32661/shuffle-array-java
			 */
			for(int i = 0; i <copy.length; i++) {
				int randomIndex = rand.nextInt(copy.length);
				String randomPartyName = copy[randomIndex];
				copy[randomIndex] = copy[i];
				copy[i] = randomPartyName;
			}
			//updating number of maximum seats and percent each party can have
			//done by subtracting initial number of seats by the amount party has
			numberOfSeats -= (int)p.getProjectedNumberOfSeats();
			maxPercent -=(int) p.getProjectedPercentageOfVotes();
		}

		return poll;
	}

	/**
	 * Creating a random poll list and returning said list
	 * @param numOfPolls - number of polls conducted
	 * @return poll list
	 */
	public PollList createRandomPollList(int numOfPolls) {
		PollList list = new PollList(numOfPolls,numOfSeats);
		for (int counter = 0; counter < numOfPolls; counter++) {
			list.addPoll(createRandomPoll("Poll" + counter));
		}
		return list;
	}
	//bonus
	public PollList promptForPollList(int numOfPolls) {
		return createRandomPollList(numOfPolls);
	}
	public static void main(String[] args) {

	}
}

