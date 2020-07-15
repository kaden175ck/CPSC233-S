import java.util.Scanner;

public class TextApplication {
	public static final int MAX_NUMBER_OF_STARS = 25;
	private PollList polls;
	
	public TextApplication() {
	}
	
	public TextApplication(PollList polls) {
		this.polls = polls;
	}
	
	public void displayPollsBySeat(String[] partyNames) {
		for(Poll a : polls.getPolls()) {
			displayPollDataBySeat(a);
		}
	}
	
	public PollList getPolls() {
		return polls;
	}

	public void displayPollDataBySeat(Poll aPoll) {
		int starsNeededForMajority = MAX_NUMBER_OF_STARS/2 + 1;
		System.out.println(aPoll.getPollName());
		for (Party poll : aPoll.getPartiesSortedBySeats()) {
			System.out.println(poll.textVisualizationBySeats(MAX_NUMBER_OF_STARS, starsNeededForMajority, this.polls.getNumOfSeats()/MAX_NUMBER_OF_STARS));
		}
		System.out.println("\n");
	}
	
	public void run() {
Scanner userinp = new Scanner(System.in);
		
		System.out.println("Welcome to the poll tracker");
		System.out.print  ("How many seats are available in the election? ");
		int     numSeats = userinp.nextInt();
		Factory factory  = new Factory(numSeats);
		userinp.nextLine();
		
		System.out.println("\nWhich parties are in the election (provide names, comma separated): ");
		String[] partyNames = userinp.nextLine().split(",");
		factory.setPartyNames(partyNames);
		
		System.out.print  ("How many polls do you want to track with this application? ");
		int numPolls = userinp.nextInt();
		userinp.nextLine();
		
		System.out.print  ("\nWould you like to create a random set of polls? [y/n] ");
		String isRandom = userinp.nextLine();
		/**
		 * Factory handles both random and prompt-based generation of the poll list
		 */
		if (isRandom.equals("y")) {
			polls = new PollList(numPolls, numSeats);
			for (int i = 0; i < numPolls; i++) {	
				polls.addPoll(factory.createRandomPoll("Poll " + i));
			}
		}
		else {
			polls = factory.promptForPollList(numPolls);
		}
		
		String userOption;
		do {
			System.out.println("Options: all (show result of all polls), aggregate (show aggregate result), quit (end application)");
			System.out.print("Choose an option: ");
			userOption = userinp.nextLine();
			if (userOption.equals("all")) {
				displayPollsBySeat(partyNames);
			}
			else if (userOption.equals("aggregate")) {
				System.out.println("Aggregate");
				displayPollDataBySeat(polls.getAggregatePoll(partyNames));
			}
		} while (userOption.equals("quit") == false);
		
		userinp.close();
	}
	
	
	public static void main(String[] args) {
		TextApplication app = new TextApplication(null);
		app.run();
		
	}
}
