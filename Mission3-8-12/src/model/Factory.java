package model;

import java.util.Random;

public class Factory {
	private int numOfSeats;
	String[] partyNames = {"BQ","CPC","Green","Liberal","NDP","PPC","Rhinoceros"};
	
	public Factory(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}
	public void setPartyNames(String[] names) {
		partyNames = names;
	}
	
	public String[] getPartyNames() {
		return partyNames;
	}
	
	public Party createRandomParty(String partyName, int maximumSeats, int maximumPercent) throws InvalidPartyDataException {
		Random rand = new Random();
		return new Party(partyName, rand.nextInt(numOfSeats), rand.nextFloat());
	}
	
	public Poll createRandomPoll(String name) throws InvalidPartyDataException {
		Poll poll = new Poll(name, partyNames.length);
		
		Random rand = new Random();
		for (String partyName : partyNames) {
			try {
				poll.addParty(new Party(partyName, rand.nextInt(numOfSeats), rand.nextFloat()));
			} catch (PollFullException e) {
				System.out.print(e.getMessage());
				//e.printStackTrace();
			}
		}
		return poll;
	}

	public PollList createRandomPollList(int numOfPolls) throws Throwable {
		PollList list = new PollList(numOfPolls,numOfSeats);
		for (int counter = 0; counter < numOfPolls; counter++) {
			list.addPoll(createRandomPoll("Poll" + counter));
		}
		return list;
	}
	
	public PollList promptForPollList(int numOfPolls) throws Throwable {
		return createRandomPollList(numOfPolls);
	}
	
}
