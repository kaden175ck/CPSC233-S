/**
 * @Title: Player.java
 * @Package 
 * @Description: this is a abstract class that includes the common methods for both human players and AI player
 * @author Haoyang Shi
 * @date 2020   7.28      7:15:11
 * @version V1.0
 */


/**
  * @ClassName: Player
  * @Description: a abstract class that have common method for both AI and players
  * @author Haoyang Shi
  * @date 2020   7.29
  */
public abstract class Player{
	  protected int color;//indicate different player's symbol
      protected int[][] board;
      
   
	/**
	  * new instance Player. 
	  * <p>Title: Player</p>
	  * <p>Description: initialize plays and board</p>
	  * @param board 
	  * @param color
	  */
      
	public Player(int [][]board,int color){
		this.color =color;
		this.board =board;
	}

	
	/**
	  * playChess()
	  * TODO:
	  * @Title: playChess
	  * @Description: see HumanPlayer class for details
	  * @param @return   
	  * @return int[]   
	  * @throws
	  */
	public abstract int[] playChess();
	
	
	/**
	  * getColor()
	  * TODO:
	  * @Title: getColor
	  * @Description: TODO
	  * @param @return    
	  * @return int    
	  * @throws
	  */
	public int getColor(){
		return color;
	}
	
}
