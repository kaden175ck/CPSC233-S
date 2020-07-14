/**
  * @ClassName: Party
  * @Description:  represents a single political party
  * @author Haoyang Shi
  * @date 2020 7.9 
  */
public class Party {
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
	  * <p>Description: Another Party constructor with three parameters</p>
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
	  * @Description: check the range of projected percentage of votes
	  * @param projectedPercentageOfVotes  
	  * @return void    
	  */	
	public void setProjectedPercentageOfVotes(float projectedPercentageOfVotes) {
		//make sure the percentage of votes are value between 0 and 1 (both inclusive)
		if(projectedPercentageOfVotes >= 0 && projectedPercentageOfVotes <= 1)
		this.projectedPercentageOfVotes = projectedPercentageOfVotes;
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
	  * @Description: check the range of projected Number Of Seats
	  * @param projectedNumberOfSeats 
	  * @return void   
	  */
	public void setProjectedNumberOfSeats(float projectedNumberOfSeats) {
		if(projectedNumberOfSeats >= 0)
		this.projectedNumberOfSeats = projectedNumberOfSeats;
		else {
			System.out.println("impossible value");
		}
	}
	

	/**
	  * <p>Title: toString</p>
	  * <p>Description: express the message</p>
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
	  * @Description: projected Percent Of Seat
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
	  * @Description: this is the piece of code that both textVisualizationByVotes and textVisualizationBySeats methods have in common.
	  * displaying a row of stars that represents the number of seats 
	  * and a bar to indicate the number of seats needed for a majority in parliament. 
	  * @param @param maxStars
	  * @param @param starsNeededForMajority
	  * @param @param index
	  * @param @return    
	  * @return String    
	  */
	public static String printStar(int maxStars, int starsNeededForMajority, double index) {
		/**starsNeededForMajority representing the minimum number of stars that would represent a majority.
		  *maxStars representing the maximum number of stars that should be displayed on a single line.
		  */
        String str= "";
		int i;
		for(i = 1; i <= maxStars; i++){
			if(i <= index)
				str+="*";
			else
				str+=" ";
			if(i == starsNeededForMajority)
				str+="|";
		}
		return str;
	}
	
	
	/**
	  * text Visualization By Seats
	  * @Title: textVisualizationBySeats
	  * @Description: gives a visual representation of the data relevant to this party
	  * @param @param maxStars
	  * @param @param starsNeededForMajority
	  * @param @param numOfSeatsPerStar
	  * @param @return   
	  * @return String    
	  */
	public String textVisualizationBySeats(int maxStars, int starsNeededForMajority, double numOfSeatsPerStar) {
		// numOfSeatsPerStar indicates how many seats are represented by a single star
		return printStar(maxStars, starsNeededForMajority, (int)Math.floor(getProjectedNumberOfSeats() / numOfSeatsPerStar)) + " " + toString();
	}

	
	/**
	  * text Visualization By Votes
	  * @Title: textVisualizationByVotes
	  * @Description:  visual representation to represent the percentage of votes the party is expected to win
	  * @param @param maxStars
	  * @param @param starsNeededForMajority
	  * @param @param percentOfVotesPerStar
	  * @return String    
	  */
	public String textVisualizationByVotes(int maxStars, int starsNeededForMajority, double percentOfVotesPerStar) {	
		return printStar(maxStars, starsNeededForMajority, (int)Math.floor(getProjectedPercentageOfVotes()*100 / percentOfVotesPerStar)) + " " + toString();
	} 
}
