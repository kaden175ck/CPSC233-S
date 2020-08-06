import java.util.Arrays;
import java.util.Collections;

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
		if(aParty == null) {
			System.out.print("The enter message is empty.");
			return;
		}
		for(int i = 0; (i < parties.length)&&(parties[i] != null); i++) {
			if(parties[i].getName().toUpperCase().equals(aParty.getName().toUpperCase())){
				parties[i] = aParty;
				return;
			}
		}
		if(numberOfParties < parties.length){
			parties[numberOfParties] = aParty;
		    numberOfParties++;
		}
	}

	public Party getParty(String name) {
		for(Party party:parties) {
		if((party != null) && (party.getName().toUpperCase().equals(name.toUpperCase()))) {
			return party;
			}
		}
		return null;
	}



	public Party[] getPartiesSortedBySeats() {
		Arrays.sort(parties, Collections.reverseOrder());
		return parties;
	}

	public Party[] getPartiesSortedByVotes() {
		Arrays.sort(parties, Collections.reverseOrder());
		return parties;
	}

	public String toString() {
		String result = name +"\n";
		for(Party aParty: parties) {
			if(aParty!= null)
			result += (aParty + "\n");
		}
		return result;
	}
}
