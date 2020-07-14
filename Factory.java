/**
 * Team F Factory Class for Assignment 1
 * @author kenneth Liu 30066722
 */

//import random class
import java.util.Random;

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
		if(numOfSeats >=1) {
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
		int seats = rand.nextInt(maximumSeats);
		float partyPercent = rand.nextInt(maximumPercent)/100;
		float seatPercent = seats/maximumSeats;		//calculating seatpercent

		//if seatPercent and partyPercent differ by 5% (0.05) create the randomParty object
		if ((seatPercent-partyPercent) <= 0.05) {
			return new Party(partyName, seats, partyPercent);
		}
		return null;		//otherwise return null
	}

	public Poll createRandomPoll(String name) {
		Poll poll = new Poll(name, partyNames.length);

		Random rand = new Random();
		for (String partyName : partyNames) {
			poll.addParty(new Party(partyName, rand.nextInt(numOfSeats), rand.nextFloat()));
		}
		return poll;
	}

	public PollList createRandomPollList(int numOfPolls) {
		PollList list = new PollList(numOfPolls,numOfSeats);
		for (int counter = 0; counter < numOfPolls; counter++) {
			list.addPoll(createRandomPoll("Poll" + counter));
		}
		return list;
	}

	public PollList promptForPollList(int numOfPolls) {

		return createRandomPollList(numOfPolls);
	}

}
