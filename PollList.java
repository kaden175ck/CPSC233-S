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
		System.out.println("aPoll   "+aPoll);
		if (aPoll != null) {
		boolean already = false;
		int full = 0;
		// testing to see if the poll already exists in the list and if the list is full
		for (int index = 0; index < polls.length && polls[index] != null && already != true; index++) {
			String pollName = polls[index].getPollName();
			System.out.println("poll name    "+pollName);
			if (pollName.equalsIgnoreCase(aPoll.getPollName())) {
				already = true;
			}
			full++;
		}
		System.out.println("already     " + already);
		//if the poll doesn't exist and the list is not full, add the poll to the end of the list
		if (already == true) {
			polls[full-1] = aPoll;
		}
		else {

		if (full < polls.length) {
			// if the list is not full add the new poll to the end of the list
			polls[full] = aPoll;

		}
		// if the list is full, print error message saying the list is full
		else if (full == polls.length) {
			//if the list is full and no duplicate it present, print an error message
			System.out.println("List is full, no further polls can be added.");

		}

		}
	}
		// if aPoll is empty, print error message and do not change Poll list
		else
			System.out.println("Poll empty, no changes will be made.");

		}



	public Poll getAggregatePoll(String[] partyNames){
		//make a new aggregate poll named "Aggregate"
		Poll Aggregate = new Poll("Aggregate",partyNames.length);
		for (int index = 0; index < partyNames.length; index++) {
			Aggregate.addParty(getAveragePartyData(partyNames[index]));
		}
			return adjustPollToMaximum(Aggregate);



	}

	public Party getAveragePartyData(String partyName) {
		Party party = new Party(partyName);
		//expected number of seats

		int numberSeats = 0;
		double percentageOfVotesTotal = 0;
		int length = 0;
		for (int index = 0;index < polls.length ;index++) {
			//inside polls getParty from party class --> argument party name
			// how to get the projected number of seats to the party created
			Party partyObject = polls[index].getParty(partyName);
			if (partyObject != null) {
			int projectedNumberSeats = (int)(partyObject.getProjectedNumberOfSeats());
			numberSeats = numberSeats + projectedNumberSeats;
			double percentageOfVotes = partyObject.getProjectedPercentageOfVotes();
			percentageOfVotesTotal = percentageOfVotesTotal + percentageOfVotes;
			length++;

		}
		}

		float avgNumberSeats = (float)numberSeats/length;
		float avgPercentageOfVotes = (float)percentageOfVotesTotal/length;


		// create new party object
		party.setProjectedNumberOfSeats(avgNumberSeats);
		party.setProjectedPercentageOfVotes(avgPercentageOfVotes);
		return party;
	}

	public Poll adjustPollToMaximums(Poll aPoll) {
		// test if the total number of possible seats is different
			//from the actual number of seats
			float totalSeats = 0;
			float totalPercent = 0;
		for (int index = 0; index < aPoll.getNumberOfParties(); index++) {
			totalSeats = totalSeats + aPoll[index].getProjectedNumberOfSeats();

		}
		return aPoll;
	}


	@Override
	public String toString() {
		return "Number of seats: " + numOfSeats;
	}
}
