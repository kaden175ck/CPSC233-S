package model;
/**
  * ClassName: Poll
  * Description:  This class represents the data of poll.
  * @author Yuan Liu
  * @since 2020 7.14
  */
import java.util.Arrays;
import java.util.Comparator;

public class Poll {
	private String name = "Unnamed Poll";//A string, the name of poll. Have getter, No setter.
	private Party[] parties;//An array of party objects. No getter and setter.
	private int numberOfParties = 0;//An integer represent the number of unique parties that have been added to the poll to date
	                                //(Not the size of the parties array). Have getter, No setter.


	/**
	  * Title: Poll
	  * Description: Poll constructor that takes the name of the poll and the maximum number of parties as argument.
	  * @param aName, Any string is considered a valid string for a name.
	  * @param maxNumberOfParties, The maxNumberOfParties should be at least 1. If an invalid number is provided, set the maxNumberOfParties to 10.
	  */
	public Poll(String aName, int maxNumberOfParties) {
		this.name = aName;
		if(maxNumberOfParties >= 1){
			parties = new Party[maxNumberOfParties];
		}else {
			parties = new Party[10];
		}
	}


	/**
	  * Title: getPollName
	  * Description: Get name of the poll
	  * @return name: a String value of the poll name.
	  */
	public String getPollName() {
		return name;
	}


	/**
	  * Title: getNumberOfParties
	  * Description: Get the number of unique party objects that have been added to the parties array to date.
	  * @return numberOfParties: an integer value with current added number of party objects.
	  */
	public int getNumberOfParties() {
		return numberOfParties;
	}


	/**
	  * Title: getParty
	  * Description: To find and returns the party in the poll with a specific name.
	  * @param aName The name of the party which need to be found.
	  * @return aParty or null: aParty is the party object in parties array with the required name.
	  */
	public Party getParty(String aName) {
		//If no party of aName exists in the array it will return null.
		Party result = null;
		//If any party has name same as aName, it will return the party.
		for(Party aParty:parties) {
		if((aParty != null) && (aParty.getName().toUpperCase().equals(aName.toUpperCase()))) {
			result = aParty;
			}
		}
		return result;
	}


	/**
	  * Title: addParty
	  * Description: To add valid party objects to parties array if the array doesn't full.
	  * @param aParty The party which we want to add in the array.
	  */
	public void addParty(Party aParty) {
		//This state is to avoid add null value to parties array.
		if(aParty == null) {
			System.out.print("The enter message is empty.");
		}
		//If aParty has the same name as another party which is already in the parties array, then the existing party should be replaced by aParty.
		int replaceHappened = 0;//This is a counter to record the times of replacement.
		for(int i = 0; (i < parties.length)&&(parties[i] != null); i++) {
			//The replacement happens with no case sensitive.
			if(parties[i].getName().toUpperCase().equals(aParty.getName().toUpperCase())){
				parties[i] = aParty;
				replaceHappened++;
			}
		}
		//Add aParty to the end of the list if the array still has room.
		if(numberOfParties < parties.length){
			parties[numberOfParties] = aParty;
		    numberOfParties++;
		    numberOfParties -= replaceHappened;
		}
	}


	/**
	  * Title: getPartiesSortedBySeats
	  * Description: To sort all the party objects in the poll from the party with the most projected seats to the least projected seats.
	  * @return parties: The array of party objects.
	  */
	public Party[] getPartiesSortedBySeats() {
		//Use comparator and static method Array.sort to sort party object.
		Arrays.sort(parties, new sortPartiesComparator());
		return parties;
	}


	/**
	  * Title: getPartiesSortedByVotes
	  * Description: To sort all the party objects in the poll by projected percentage of votes with the party with the most votes first in the list.
	  * @return parties: The array of party objects.
	  */
	public Party[] getPartiesSortedByVotes() {
		Arrays.sort(parties, new sortPartiesComparator());
		return parties;
	}


	/**
	  * Title: toString
	  * Description: A method return the poll information in multiple lines.
	  * @return String
	  */
	public String toString() {
		//The first line, poll name.
		String result = name +"\n";
		//The rest part, every party object in parties array will take a line.
		for(Party aParty: parties) {
			if(aParty!= null)
			result += (aParty + "\n");
		}
		return result;
	}
}



/**
  * Title: sortPartiesComparator
  * Description: This is a inner class implements comparator. It is designed to work with Array.sort, and sort party object in Poll class.
  */
class sortPartiesComparator implements Comparator<Party> {

	/**
	  * Title: compare
	  * Description: This is a override to compare two party objects.
	  * @return A positive integer or a negative integer which will be used in comparator.
	  */
	@Override
	public int compare(Party o1, Party o2) {
		if(o1.getProjectedNumberOfSeats() != o2.getProjectedNumberOfSeats()) {
			//Compare the projected number of seats between two party objects.
			return (int) (o2.getProjectedNumberOfSeats() - o1.getProjectedNumberOfSeats());
		}
		//Compare the projected percentage of votes of two party objects.
		return (int) ((o2.getProjectedPercentageOfVotes() - o1.getProjectedPercentageOfVotes()) * 100);
	}

}
