/**
  * @ClassName: Party
  * @Description: This class represents the data of a single political party by using stars and bars.
  * @author Haoyang Shi
  * @date 2020 7.9 
  */
public class Party{
	private String name;//any string
	private float projectedNumberOfSeats;//the projected number of seats must be a non-negative value
	private float projectedPercentageOfVotes;//the projected percentage of votes must be a value between 0 and 1 (both inclusive). 
	
	
	/**
	  * <p>Title: Party</p>
	  * <p>Description: Party constructor that takes the name of the party as argument</p>
	  * @param partyName
	  */
	public Party(String partyName) {
		name = partyName;
	}
	

	/**
	  * <p>Title: Party</p>
	  * <p>Description: Another Party constructor takes three parameters</p>
	  * @param partyName
	  * @param projectedNumberOfSeats
	  * @param projectedPercentageOfVotes
	  */
	public Party(String partyName, float projectedNumberOfSeats, float projectedPercentageOfVotes) {
		name = partyName;
		setProjectedNumberOfSeats(projectedNumberOfSeats);
		setProjectedPercentageOfVotes(projectedPercentageOfVotes);
	}
	
		
	/**
	  * @Title: getProjectedPercentageOfVotes
	  * @Description: get projected percentage of votes
	  * @return float   
	  */
	public float getProjectedPercentageOfVotes() {
		return projectedPercentageOfVotes;
	}
	
	
	/** 
	  * getName
	  * @Title: getName
	  * @Description: get the name of the party
	  * @return String  
	  */
	public String getName() {
		return name;
	}
	
	
	/**
	  * setter method:setProjectedPercentageOfVotes
	  * @Title: setProjectedPercentageOfVotes
	  * @Description: check the validation of projected percentage of votes
	  * @param projectedPercentageOfVotes  
	  * @return void    
	  */	
	public void setProjectedPercentageOfVotes(float projectedPercentageOfVotes) {
		//make sure the percentage of votes are value between 0 and 1 (both inclusive)
		if(projectedPercentageOfVotes >= 0 && projectedPercentageOfVotes <= 1) {
		this.projectedPercentageOfVotes = projectedPercentageOfVotes;
		}
		else {
			System.out.println("out of range");
		}
	}
	

	/**
	  * getter method: get Projected Number Of Seats
	  * @Title: getProjectedNumberOfSeats  
	  * @return float   
	  */
	public float getProjectedNumberOfSeats() {
		return projectedNumberOfSeats;
	}
	

	/**
	  * setter method: set Projected Number Of Seats
	  * @Title: setProjectedNumberOfSeats
	  * @Description: check the validation of projected Number Of Seats
	  * @param projectedNumberOfSeats 
	  * @return void   
	  */
	public void setProjectedNumberOfSeats(float projectedNumberOfSeats) {
		//number of seats can not be negative
		if(projectedNumberOfSeats >= 0) {
		this.projectedNumberOfSeats = projectedNumberOfSeats;
		}
		else {
			System.out.println("impossible value");
		}
	}
	

	/**
	  * <p>Title: toString</p>
	  * <p>Description: a sentence that says the percentage of votes and number of seats</p>
	  * @return
	  * @see java.lang.Object#toString()
	  */
	@Override
	public String toString() {
		return getName() + " (" + (int)(getProjectedPercentageOfVotes()*100) + "% of votes, " + getProjectedNumberOfSeats() + " seats)";
	}

	
	/**
	  * projectedPercentOfSeats
	  * @Title: projectedPercentOfSeats
	  * @Description: projected Percentage Of Seat
	  * @param totalNumberOfSeats
	  * @return double   
	  */
	public double projectedPercentOfSeats(int totalNumberOfSeats) {
		if(totalNumberOfSeats <= 0)
			return 0.0;
		return (double)(getProjectedNumberOfSeats() / totalNumberOfSeats);
	}	
	
	
	/**
	  * printStar
	  * @Title: printStar
	  * @Description: displaying a row of stars that represents the number of seats 
	  * and a bar to indicate the number of seats needed for a majority in parliament. 
	  * @param @param maxStars
	  * @param @param starsNeededForMajority
	  * @param @param index
	  * @param @return    
	  * @return String    
	  */
	public static String printStar(int maxStars, int starsNeededForMajority, double index) {
		/**
		 * this is the method that both 
		 * maxStars is the maximum number of stars that a single line can contain
		 * starsNeededForMajority is the number of stars that a party need to become majority
		 */
        String str= "";//create an empty string
		int i;
		for(i = 1; i <= maxStars; i++){
			if(i <= index)
				str+="*";
			else
				str+=" ";
			if(i == starsNeededForMajority)
				//only print "|" when i reaches the number of Majority
				str+="|";
		}
		return str;
	}
	
	
	/**
	  * text Visualization By Seats
	  * @Title: textVisualizationBySeats
	  * @Description: gives a visual representation of the data relevant to this party and return a String
	  * @param @param maxStars
	  * @param @param starsNeededForMajority
	  * @param @param numOfSeatsPerStar
	  * @param @return   
	  * @return String    
	  */
	public String textVisualizationBySeats(int maxStars, int starsNeededForMajority, double numOfSeatsPerStar) {
		//numOfSeatsPerStar is the the number of seats that a star represent
		return printStar(maxStars, starsNeededForMajority, (int)Math.floor(getProjectedNumberOfSeats() / numOfSeatsPerStar)) + " " + toString();
	}

	
	/**
	  * text Visualization By Votes
	  * @Title: textVisualizationByVotes
	  * @Description: gives a visual representation of the data relevant to this party
	  * @param @param maxStars
	  * @param @param starsNeededForMajority
	  * @param @param percentOfVotesPerStar
	  * @return String    
	  */
	public String textVisualizationByVotes(int maxStars, int starsNeededForMajority, double percentOfVotesPerStar) {	
		//percentOfVotesPerStar is the percentage of votes that a star represent
		return printStar(maxStars, starsNeededForMajority, (int)Math.floor(getProjectedPercentageOfVotes()*100 / percentOfVotesPerStar)) + " " + toString();
	} 
}
