public class Party {
	private String name;
	private float projectedNumberOfSeats;
	private float projectedPercentageOfVotes;
	
	public Party(String partyName) {
		name = partyName;
	}
	
	public Party(String partyName, float projectedNumberOfSeats, float projectedPercentageOfVotes) {
		name = partyName;
		setProjectedNumberOfSeats(projectedNumberOfSeats);
		setProjectedPercentageOfVotes(projectedPercentageOfVotes);
	}
	
	public float getProjectedPercentageOfVotes() {
		return projectedPercentageOfVotes;
	}
	
	public String getName() {
		return name;
	}
	
	public void setProjectedPercentageOfVotes(float projectedPercentageOfVotes) {
		if(projectedPercentageOfVotes>=0&&projectedPercentageOfVotes<=1)
		this.projectedPercentageOfVotes = projectedPercentageOfVotes;
	}

	public float getProjectedNumberOfSeats() {
		return projectedNumberOfSeats;
	}

	public void setProjectedNumberOfSeats(float projectedNumberOfSeats) {
		if(projectedNumberOfSeats>=0)
		this.projectedNumberOfSeats = projectedNumberOfSeats;
	}

	@Override
	public String toString() {
		return getName()+" ("+(int)(getProjectedPercentageOfVotes()*100)+"% of votes, "+getProjectedNumberOfSeats()+" seats)";
	}

	public double projectedPercentOfSeats(int totalNumberOfSeats) {
		if(totalNumberOfSeats<=0)
			return 0.0;
		return (double)(getProjectedNumberOfSeats()/totalNumberOfSeats);
	}
	
	
	
	public static String printStar(int maxStars, int starsNeededForMajority,double index) 
	{
          String str="";
		
		int i;
		for(i=1;i<=maxStars;i++)
		{
			if(i<=index)
			str+="*";
			else
				str+=" ";
			if(i==starsNeededForMajority)
				str+="|";
		}
		return str;
	}
	public String textVisualizationBySeats(int maxStars, int starsNeededForMajority, double numOfSeatsPerStar) {
		
		return printStar(maxStars,starsNeededForMajority,(int)Math.floor(getProjectedNumberOfSeats()/numOfSeatsPerStar))+" "+toString();
	}

	public String textVisualizationByVotes(int maxStars, int starsNeededForMajority, double percentOfVotesPerStar) {
		
		return printStar(maxStars,starsNeededForMajority,(int)Math.floor(getProjectedPercentageOfVotes()*100/percentOfVotesPerStar))+" "+toString();
	}
}
