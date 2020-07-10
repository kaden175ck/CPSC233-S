/**
*%W% %E% Haoyang Shi
*Class description goes here(I will write it later)
*Javadoc (/**   */) is used to document classes and method.  
*The audience for this documentation is for other programmers that want to use your class and call your methods in their code. 
*They are interested in how to create and use your class, what you are looking for in the arguments 
*(not the type, but what the argument represents and what are valid values) and what the method returns (again, not the type, but what the value returned represents). 
*It should describe WHAT and WHY, not HOW. 
*
* @author Haoyang Shi
*/
public class Party {
	// ...implementation goes here...
	private String name;
	private float projectedNumberOfSeats;
	private float projectedPercentageOfVotes;
	
	public Party(String partyName) {
	// ...implementation goes here...
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
}
