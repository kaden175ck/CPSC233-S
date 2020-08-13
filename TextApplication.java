package application;
/**
 * @author Jungsu Kim
 * @Classname: TextApplication
 * @Description: This class gets other class "Party", "Poll", "PollList", and "Factory" and implements them together
 */
/*
 * 
 * Exception handling done by: Kenneth Liu 30066722
 * 2020/08/07, TextApplication updated to handle data input error
 */
import java.util.Scanner;

import model.Factory;
import model.InvalidSetUpDataException;
import model.Party;
import model.Poll;
import model.PollList;
import java.lang.NumberFormatException;
public class TextApplication {
	public static final int MAX_NUMBER_OF_STARS = 25;
	private PollList polls;
	String[] parties;
	
	boolean error = true;
	int seats = 0;
	Factory factory = new Factory(seats);
	int numPolls = 0;
	String partyInput;
	/**
	 * Title: TextApplication
	 * Description: This a default constructor of the class 
	 */
	public TextApplication() {
	}
	
	/**
	 * Title: TextApplication
	 * Description: Another constructor which initializes the PollList object "Polls"
	 * @param polls
	 */
	public TextApplication(PollList polls) {
		this.polls = polls;
	}
	
	/**
	 * Title: displayPollBySeat
	 * Description: This displays each poll and the parties included, they are arranged by seats
	 * @param partyNames
	 */
	public void displayPollsBySeat(String[] partyNames) {
		for(Poll a : polls.getPolls()) {
			displayPollDataBySeat(a);
		}
	}
	
	/**
	 * Title: PollList getPolls
	 * Getter method
	 * Description: Gets the PollList object "Polls"
	 * @return polls
	 */
	public PollList getPolls() {
		return polls;
	}

	/**
	 * Title: displayPollDataBySeat
	 * Description: Takes in poll as an argument and displays each party and arranges them by seat
	 * @param aPoll
	 */
	public void displayPollDataBySeat(Poll aPoll) {
		int starsNeededForMajority = MAX_NUMBER_OF_STARS/2 + 1;
		System.out.println(aPoll.getPollName());
		for (Party poll : aPoll.getPartiesSortedBySeats()) {
			System.out.println(poll.textVisualizationBySeats(MAX_NUMBER_OF_STARS, starsNeededForMajority, this.polls.getNumOfSeats()/MAX_NUMBER_OF_STARS));
		}
		System.out.println("\n");
	}
	
	/**
	 * Title: Run
	 * Description: This body of code is to initializes all the methods made above and creates an application allowing user input and output
	 * @throws InvalidSetUpDataException 
	 */
	public void run()  {
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Welcome to the poll tracker!");
		
		try { //try block for handling errors for seats
			System.out.println("How many seats are available in the election? ");
			String userSeats = userInput.nextLine();
			 seats = Integer.parseInt(userSeats);
			 factory = new Factory(seats);

			 // if seats is not a positive integer, throw an InvalidSetUpDataException
			if(seats > 0) {
				factory = new Factory(seats);
			}else {
				throw new InvalidSetUpDataException();
			}
			
		}catch(InvalidSetUpDataException ISDE){					//catch the invalid setup data exception and reprompt user to enter the number
			while(seats <= 0) {
			System.out.println("Invalid number of seats, please enter a number greater than 0: ");
			String userSeats = userInput.nextLine();
			 seats = Integer.parseInt(userSeats);
			 factory = new Factory(seats);
			}
		}
		catch(NumberFormatException e) {						//if user inputs anything other than a number, reprompt user
			while(seats <= 0) {
			System.out.println("Invalid number! You must enter a number");
			System.out.println("Please enter a number greater than 0: ");
			String userSeats = userInput.nextLine();
			 seats = Integer.parseInt(userSeats);
			 factory = new Factory(seats);
		}
		}
		try { 		//try block for adding party names to poll
		System.out.println("\nWhich parties are in the election (provide names, separate by comma): ");
		String partyInput = userInput.nextLine().trim();
		if(partyInput.isEmpty() == false ) {		//if user input is empty throw InvalidSetUpDataException, otherwise split party names into array
			String[] parties = partyInput.split(",");
			factory.setPartyNames(parties);
		}else {
			throw new InvalidSetUpDataException();
		}
		
		} catch(InvalidSetUpDataException ISDE) {		//reprompt user for party data
			System.out.println("Invalid party list, please enter names of parties separated by commas: ");
			String partyInput = userInput.nextLine().trim();
			if(partyInput.isEmpty() == false) {
			parties = partyInput.split(",");
			factory.setPartyNames(parties);
			} 

		}
		
		
		
		try {		//try block for prompting user for number of polls
			System.out.print  ("\nHow many polls do you want to track with this application? ");
			String userNumPolls = userInput.nextLine();
			 numPolls = Integer.parseInt(userNumPolls);
			if(numPolls < 0) {		//if user input is less than 0 throw InvalidSetUpDataException
				throw new InvalidSetUpDataException();
			}

		}
		catch(InvalidSetUpDataException ISDE){		//catch block to reprompt user for # of polls
			while(numPolls <= 0) {
			System.out.println("Invalid number of polls, enter a number greater than 0: ");
			
			String userNumPolls = userInput.nextLine();
			numPolls = Integer.parseInt(userNumPolls);
			}
		}catch(NumberFormatException NFE) {			//catch block if user input is not an integer value, reprompt user for data
			while(numPolls <= 0) {
				System.out.println("You didn't enter a number! Or a number less than 1 is entered!");
				System.out.println("Invalid number of polls, enter a number greater than 0: ");
				
				String userNumPolls = userInput.nextLine();
				numPolls = Integer.parseInt(userNumPolls);
				}
		}
		
		
		System.out.print  ("\nWould you like to create a random set of polls? yes or no ");
		String randomPoll = userInput.nextLine();

		if (randomPoll == ("yes")) {
			polls = new PollList(numPolls, seats);
			int counter = 0;
			while (counter < numPolls) {	
				polls.addPoll(factory.createRandomPoll("Poll " + counter));
				counter ++;
			}
		}
		else {
			polls = factory.promptForPollList(numPolls);
		}
		
		String option = "";
		int counter = 0;
		while(counter == 0) {
			System.out.println("Options: all (show result of all polls), aggregate (show aggregate result), quit (end application)");
			System.out.println("Choose an option: ");
			option = userInput.nextLine();
			if (option.equals("all")) {
				displayPollsBySeat(parties);
			}
			if (option.equals("aggregate")) {
				System.out.println("Aggregate");
				displayPollDataBySeat(polls.getAggregatePoll(factory.getPartyNames()));
			}
			if (option.equals("quit")) {
				counter ++;
			}
		}
		userInput.close();
	}
	
	/**
	 * Title: main
	 * Description: runs the java code and initializes the TextApplication class 
	 * @param args
	 * @throws InvalidSetUpDataException 
	 */
	public static void main(String[] args) {
		TextApplication app = new TextApplication(null);
		app.run();
		
	}
}