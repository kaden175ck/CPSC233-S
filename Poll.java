public class Poll {
	private String name = "Unnamed Poll";
	private Party[] parties;
	private int numberOfParties = 0;

	public Poll(String name, int maxNumberOfParties) {
		this.name = name;
		if(maxNumberOfParties >= 1){
			parties = new Party[maxNumberOfParties];
		}else {
			parties = new Party[10];
		}


	}

	public String getPollName() {
		return name;
	}

	public int getNumberOfParties() {
		return numberOfParties;
	}

	public void addParty(Party aParty) {
		if(aParty == null)
		System.out.print("The enter message is empty.");
		for(Party party: parties) {
			if (party.getName().equalsIgnoreCase(aParty.getName())){
				party = aParty;
			}
		}
		if(numberOfParties < parties.length) {
			parties[numberOfParties] = aParty;
			numberOfParties++;
		}else {
			System.out.println("No room.");
		}
	}



	public Party getParty(String name) {
		for(Party party:parties) {
		if(party.getName().equals(name)) {
			return party;
			}
		}
		return null;
	}

	public Party[] getPartiesSortedBySeats() {
		return parties;
	}

	public Party[] getPartiesSortedByVotes() {
		return parties;
	}

	@Override
	public String toString() {
		String result = name +"\n";
		for(Party aParty: parties) {
			if(aParty!= null)
			result += (aParty + "\n");
		}
		return result;
	}
}
