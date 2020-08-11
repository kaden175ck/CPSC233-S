package game;
import java.util.Scanner;
/**
  * @ClassName: HumanPlayer
  * @Description: this class takes input from user and will keep asking for input if player's spot is already token
  * @author Haoyang Shi
  * @date 7.23
  *
  */

//program will default human player to 1 and AI player to 2. 
//program will let human player to go first(if playing with Ai)
public class HumanPlayer extends Player{
	
	private Scanner input = new Scanner(System.in);
	private int row;//user input for x
	private int col;//user input for y


	/**
	
	  * new instances HumanPlayer. 
	  * <p>Title: </p>
	  * <p>Description: HumanPlayer constructor that takes board and color</p>
	  * @param board
	  * @param color
	  */
	public HumanPlayer(int [][]board,int color){	
		super(board,color);
		}
	
	
	/**
	 * Title: setRow
	 * Description: Prompts user for row 
	 * @return value of row
	 */
	public int setRow() {
		System.out.println("Enter row where you want to place your chess: ");
		row = input.nextInt();
		return row;
	}
	
	
	/**
	 * Title: setCol
	 * Description: Prompts user for column 
	 * @return value of column
	 */
	public int setCol() {
		System.out.println("Enter column where you want to place your chess: ");
		col = input.nextInt();
		return col;
	}
	


	
	/**
	  * getRow
	  * @Title: getRow
	  * @param @return    
	  * @return int    
	  * @throws
	  */
	
	public int getRow() {
		return row;
	}
	
	
	
	/**
	  * getCol
	  * @Title: getCol
	  * @param @return    
	  * @return int    
	  * @throws
	  */
	public int getCol() {
		return col;
	}
	

	/*
	  * <p>Title: playChess</p>
	  * <p>Description: player's position</p>
	  * @return
	  * @see Player#playChess()
	  */
	
	public int[] playChess() { 
		
		do{
		setRow();
		setCol();
		}
		while(board[row][col]!=0);//make sure the spot is empty

		
		int[] coord=new int[2];
		coord[0]=row;
		coord[1]=col;
		return coord;	
        }
		
}
