package model;
/**
  * ClassName: Party
  * Description: This class represents the data of a single political party by using stars and bars.
  * @author Haoyang Shi 30105296
  * @date 2020 7.9 
  */
public class Party extends InvalidPartyDataException{
	private String name;
	private float projectedNumberOfSeats;//the projected number of seats must be a non-negative value
	private float projectedPercentageOfVotes;//the projected percentage of votes must be a value between 0 and 1 (both inclusive). 
	
	
	/**
	  * Title: Party
	  * Description: Party constructor that takes the name of the party as argument
	  * @param partyName
	  */
	public Party(String partyName) {
		name = partyName;
	}
	

	/**
	  * Title: Party
	  * Description: Another Party constructor that takes three parameters
	  * @param partyName
	  * @param projectedNumberOfSeats
	  * @param projectedPercentageOfVotes
	 * @throws InvalidPartyDataException 
	  */
	public Party(String partyName, float projectedNumberOfSeats, float projectedPercentageOfVotes) throws InvalidPartyDataException {
		name = partyName;
		setProjectedNumberOfSeats(projectedNumberOfSeats);
		setProjectedPercentageOfVotes(projectedPercentageOfVotes);
	}
	
		
	/** 
	  * Title: getProjectedPercentageOfVotes
	  * Description: get projected percentage of votes
	  * @return float   
	  */
	public float getProjectedPercentageOfVotes() {
		return projectedPercentageOfVotes;
	}
	
	
	/** 
	  * Title: getName
	  * Description: get the name of the party
	  * @return String  
	  */
	public String getName() {
		return name;
	}
	
	
	/**
	  * Title: setProjectedPercentageOfVotes
	  * Description: set the projected percentage of votes and check its validation
	  * @param projectedPercentageOfVotes  
	  * @return void    
	 * @throws InvalidPartyDataException 
	  */	
	public void setProjectedPercentageOfVotes(float projectedPercentageOfVotes) throws InvalidPartyDataException {
		//make sure the percentage of votes are value between 0 and 1 (both inclusive)
		if(projectedPercentageOfVotes >= 0 && projectedPercentageOfVotes <= 1) {
		this.projectedPercentageOfVotes = projectedPercentageOfVotes;
		}
		else throw new InvalidPartyDataException("Invalid input for percentage of votes");
	}
	

	/**
	  * Title: getProjectedNumberOfSeats  
	  * Description: get Projected Number Of Seats
	  * @return float   
	  */
	public float getProjectedNumberOfSeats() {
		return projectedNumberOfSeats;
	}
	

	/**
	  * Title: setProjectedNumberOfSeats
	  * Description: check the validation of projected Number Of Seats
	  * @param projectedNumberOfSeats 
	  * @return void   
	 * @throws InvalidPartyDataException 
	  */
	public void setProjectedNumberOfSeats(float projectedNumberOfSeats) throws InvalidPartyDataException {
		//number of seats can not be negative
		if(projectedNumberOfSeats >= 0) {
		this.projectedNumberOfSeats = projectedNumberOfSeats;
		}
		else throw new InvalidPartyDataException("Invalid input for number of seats");
	}
	

	/**
	  * Title: toString
	  * Description: a sentence that says the name of the party and the percentage of votes and number of seats
	  * @return String
	  * @see java.lang.Object#toString()
	  */
	@Override
	public String toString() {
		return getName() + " (" + (int)(getProjectedPercentageOfVotes()*100) + "% of votes, " + getProjectedNumberOfSeats() + " seats)";
	}

	
	/**
	  * Title: projectedPercentOfSeats
	  * Description: calculate the Percentage Of Seats
	  * @param totalNumberOfSeats
	  * @return double   
	  */
	public double projectedPercentOfSeats(int totalNumberOfSeats) {
		if(totalNumberOfSeats <= 0)
			return 0.0;
		return (double)(getProjectedNumberOfSeats() / totalNumberOfSeats);
	}	
	
	
	/**
	  * Title: printStar
	  * Description: displaying a row of stars that represents the number of seats 
	  * and a bar to indicate the number of seats needed for a majority in parliament. 
	  * @param maxStars                  maxStars is the maximum number of stars that a single line can contain
	  * @param starsNeededForMajority    starsNeededForMajority is the number of stars that a party need to become majority
	  * @param index      
	  * @return String    
	  */
	public static String printStar(int maxStars, int starsNeededForMajority, double index) {
	/**
	  * this is the method that both textVisualizationBySeats and textVisualizationByVotes have in common
	  * displaying a row of stars that represents the number of seats and a bar to indicate the number of seats needed for a majority in parliament.
	  */
        String str= "";
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
	  * Title: textVisualizationBySeats
	  * Description: gives a visual representation of the seats relevant to this party 
	  * @param maxStars
	  * @param starsNeededForMajority
	  * @param numOfSeatsPerStar          numOfSeatsPerStar is the number of seats that a star represent   
	  * @return String    
	  */
	public String textVisualizationBySeats(int maxStars, int starsNeededForMajority, double numOfSeatsPerStar) {
		return printStar(maxStars, starsNeededForMajority, (int)Math.floor(getProjectedNumberOfSeats() / numOfSeatsPerStar)) + " " + toString();
	}

	
	/**
	  * Title: textVisualizationByVotes
	  * Description: gives a visual representation of the votes relevant to this party
	  * @param maxStars
	  * @param starsNeededForMajority
	  * @param percentOfVotesPerStar       percentOfVotesPerStar is the percentage of votes that a star represent
	  * @return String    
	  */
	public String textVisualizationByVotes(int maxStars, int starsNeededForMajority, double percentOfVotesPerStar) {	
		return printStar(maxStars, starsNeededForMajority, (int)Math.floor(getProjectedPercentageOfVotes()*100 / percentOfVotesPerStar)) + " " + toString();
	} 
}
