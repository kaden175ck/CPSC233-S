import java.util.Scanner;
/**
  * @ClassName: HumanPlayer
  * @Description: TODO
  * @author Haoyang Shi
  * @date 7.23
  *
  */
//below comment is for my teammate(will be deleted after everything is settled.)

//commenting is not complete(feel free to add more)..the code can be polished more if yall want to
//when yall add the GUI, try to keep the coding style similar to this
//Few things to note here:

//program will default human player to 1 and AI player to 2. program will let human player to go first(if playing with Ai)
public class HumanPlayer extends Player{
	
	private Scanner input = new Scanner(System.in);
	private int row;//user input for x
	private int col;//user input for y


	public HumanPlayer(int [][]board,int color){	
		super(board,color);
		}
	/**
	 * Title: setRow
	 * Description: Prompts user for row 
	 * @return value of row
	 */
	public int setRow() {
		System.out.println("Enter row where you want piece to go: ");
		row = input.nextInt();
		return row;
	}
	/**
	 * Title: setCol
	 * Description: Prompts user for column 
	 * @return value of column
	 */
	public int setCol() {
		System.out.println("Enter column where you want piece to go: ");
		col = input.nextInt();
		return col;
	}
	


	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	

	public int[] playChess() { //this method we will call when it's players turn
		
		do{
		setRow();
		setCol();

		}
		while(board[row][col]!=0);//make sure the spot is empty

		
		int[] coord=new int[2];
		
		coord[0]=row;
		coord[1]=col;
		return coord;//return the spot that AI should take

	
}
}