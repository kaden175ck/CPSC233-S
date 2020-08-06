/**
  * @ClassName: Party
  * @Description:  represents a single political party
  * @author Haoyang Shi
  * @date 2020 7.9
  */
public class Party  implements Comparable<Party> {
	private String name;//any string
	private float projectedNumberOfSeats;//must be a non-negative value
	private float projectedPercentageOfVotes;//must be a value between 0 and 1 (both inclusive).

	/**
	  * <p>Party constructor with one param </p>
	  * @param partyName
	  */
	public Party(String partyName) {
		name = partyName;
	}

	public Party(String partyName, float projectedNumberOfSeats, float projectedPercentageOfVotes) {
	// ...implementation goes here...
		name = partyName;
		setProjectedNumberOfSeats(projectedNumberOfSeats);
		setProjectedPercentageOfVotes(projectedPercentageOfVotes);
	}

	public float getProjectedPercentageOfVotes() {
	// ...implementation goes here...
		return projectedPercentageOfVotes;
	}

	public String getName() {
	// ...implementation goes here...
		return name;
	}
	/**
 	* ...method setProjectedPercentageOfVotes documentation comment...
 	* @param projectedPercentageOfVotes
 	*/
	public void setProjectedPercentageOfVotes(float projectedPercentageOfVotes) {
	// ...implementation goes here...
		if(projectedPercentageOfVotes >= 0 && projectedPercentageOfVotes <= 1)
		this.projectedPercentageOfVotes = projectedPercentageOfVotes;
	}

	public float getProjectedNumberOfSeats() {
	// ...implementation goes here...
		return projectedNumberOfSeats;
	}

	public void setProjectedNumberOfSeats(float projectedNumberOfSeats) {
	// ...implementation goes here...
		if(projectedNumberOfSeats >= 0)
		this.projectedNumberOfSeats = projectedNumberOfSeats;
	}

	@Override
	public String toString() {
	// ...implementation goes here...
		return getName()+" (" + (int)(getProjectedPercentageOfVotes()*100)+"% of votes, " + getProjectedNumberOfSeats() + " seats)";
	}

	public double projectedPercentOfSeats(int totalNumberOfSeats) {
	// ...implementation goes here...
		if(totalNumberOfSeats <= 0 )
			return 0.0;
		return (double)(getProjectedNumberOfSeats() / totalNumberOfSeats);
	}

	public static String printStar(int maxStars, int starsNeededForMajority, double index) {
	// ...implementation goes here...
        String str="";
		int i;
		for(i=1; i<=maxStars; i++) {
			if(i<=index)
				str+="*";
			else
				str+=" ";
			if(i == starsNeededForMajority)
				str+="|";
		}
		return str;
	}

	public String textVisualizationBySeats(int maxStars, int starsNeededForMajority, double numOfSeatsPerStar) {
	// ...implementation goes here...
		return printStar(maxStars, starsNeededForMajority, (int)Math.floor(getProjectedNumberOfSeats() / numOfSeatsPerStar)) + " " + toString();
	}

	public String textVisualizationByVotes(int maxStars, int starsNeededForMajority, double percentOfVotesPerStar) {
	// ...implementation goes here...
		return printStar(maxStars, starsNeededForMajority, (int)Math.floor(getProjectedPercentageOfVotes()*100 / percentOfVotesPerStar)) + " " + toString();
	}
	/**
 	* ...method compareTo comment...
 	* compareTo method is created for the Arrays.sort(Object) in Poll class
 	*/
	@Override
	public int compareTo(Party o) {
		if(this.getProjectedNumberOfSeats() != o.getProjectedNumberOfSeats()) {
			return (int) (this.getProjectedNumberOfSeats() - o.getProjectedNumberOfSeats());
		}return (int) ((this.getProjectedPercentageOfVotes() - o.getProjectedPercentageOfVotes()) * 100);

	}

}
