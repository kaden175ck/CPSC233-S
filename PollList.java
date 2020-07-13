public class PollList {
	private Poll[] polls;
	private int numOfSeats;
	
	public PollList(int numOfPolls, int numOfSeats) {
		if (numOfPolls >= 1) {
		polls = new Poll[numOfPolls];
		}
		else {
			polls = new Poll[5];
		
		}
		if (numOfSeats >=1) {
		this.numOfSeats = numOfSeats;
		}
		
		else {
			this.numOfSeats = 10;
		}
		
	}
	
	public int getNumOfSeats() {
		return numOfSeats;
	}
	
	public Poll[] getPolls() {
		return polls;
	}

	public void addPoll(Poll aPoll) {
		if (aPoll != null) {
		boolean already = false;
		int full = 0;
		// testing to see if the poll already exists in the list and if the list is full 
		for (int index = 0; index < polls.length && polls[index] != null; index++) {	
			if (polls[index] == aPoll) {
				already = true;
			}
			full++;
		}
		//if the poll doesn't exist and the list is not full, add the poll to the end of the list
		if (already == false && full < polls.length) {
			polls[full] = aPoll;
		}
		// if the list is full, print error message saying the list is full
		else if (full == polls.length) {
			System.out.println("List is full, no further polls can be added.");
		}
	}
		// if aPoll is empty, print error message and do not change Poll list
		else {
			System.out.println("Poll empty, no changes will be made.");
		}
	}
	
	public Poll getAggregatePoll(String[] partyNames) {
		return polls[0];
	}
	
	public Party getAveragePartyData(String partyName) {
		Party partyName = new Party();
		//expected number of seats
		
		int numberSeats = 0;
		double percentageOfVotesTotal = 0;
		for (int index = 0;index < polls.length ;index++) {
			int projectedNumberSeats = partyName.getProjectedNumberSeats();
			numberSeats = numberSeats + projectedNumberSeats;
			double percentageOfVotes = partyName.getProjectedPercentageOfVotes();
			percentageOfVotesTotal = percentageOfVotesTotal + percentageOfVotes;
		}
		double avgNumberSeats = (double)numberSeats/polls.length;
		double avgPercentageOfVotes = percentageOfVotesTotal/polls.length;
		return partyName;
	}
	
	public Poll adjustPollToMaximums(Poll aPoll) {
		return aPoll;
	}
	
	
	@Override
	public String toString() {
		return "Number of seats: " + numOfSeats;
	}
}
