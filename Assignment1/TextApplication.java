/**
 * @author Jungsu Kim
 * @Classname: TextApplication
 * @Description: This class gets other class "Party", "Poll", "PollList", and "Factory" and implements them together
 */
import java.util.Scanner;

public class TextApplication {
	public static final int MAX_NUMBER_OF_STARS = 25;
	private PollList polls;
	
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
	 */
	public void run() {
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Welcome to the poll tracker!");
		System.out.println("How many seats are available in the election? ");
		int seats = userInput.nextInt();
		Factory factory  = new Factory(seats);
		userInput.nextLine();
		
		System.out.println("\nWhich parties are in the election (provide names, separate by comma): ");
		String[] parties = userInput.nextLine().split(",");
		factory.setPartyNames(parties);
		
		System.out.print  ("\nHow many polls do you want to track with this application? ");
		int numPolls = userInput.nextInt();
		userInput.nextLine();
		
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
				displayPollDataBySeat(polls.getAggregatePoll(parties));
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
	 */
	public static void main(String[] args) {
		TextApplication app = new TextApplication(null);
		app.run();
		
	}
}
