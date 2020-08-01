/**
 * @Title: Player.java
 * @Package 
 * @Description: TODO
 * @author Haoyang Shi
 * @date 2020   7.28      7:15:11
 * @version V1.0
 */
//newest version without GUI. Every update on this project will be in the branch called "Kaden"

/**
  * @ClassName: Player
  * @Description: TODO
  * @author Haoyang Shi
  * @date 2020   7.29
  */
//below comment is for my teammate(will be deleted after everything is settled.)

//commenting is not complete(feel free to add more). the code can be polished more if yall want to
//when yall add the GUI, try to keep the coding style similar to mine
//TA told us to create a new class called common player which have common method for both AI and players
public abstract class Player{
	  protected int color;
      protected int[][] board;
      
   
	/**
	  * new instance Player. 
	  * <p>Title: Player</p>
	  * <p>Description: initialize</p>
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
	  * @Description: see AI class for details
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
