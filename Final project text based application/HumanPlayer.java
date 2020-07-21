/**
 * Gomoku iteration 1 
 * Title: Human Player
 * Description: Player turn for Gomoku game 
 * Prompts user to get coordinates of piece and places piece on board if valid move.
 */
import java.util.Scanner;
public class HumanPlayer{
	
	private String token; //will implement later 
	private Scanner input = new Scanner(System.in);
	private char board[][] = new char[15][15];//will probably get rid of this, we need to make a board class
	
	
	private int row;
	private int col;
	
	private int userRow;
	private int userCol;
	
	/**
	 * Title: getRow
	 * Description: Prompts user for row 
	 * @return value of row
	 */
	public int getRow() {
		System.out.println("Enter row where you want piece to go: ");
		row = input.nextInt();
		return row;
	}
	/**
	 * Title: getCol
	 * Description: Prompts user for column 
	 * @return value of column
	 */
	public int getCol() {
		System.out.println("Enter column where you want piece to go: ");
		col = input.nextInt();
		return col;
	}
	
	/**
	 * Title: placeToken
	 * Description: Method that tests if coordinates are valid, (coordinate is not occupied and within range)
	 * If valid place token onto coordinates
	 * if not show errors and prompt user again
	 */
	public void placeToken() {
		try { 								//try statement for placing piece
			if(board[row][col] ==0) {
			userCol = col;
			userRow = row;
			board[row][col] = token; 		//import from token class
			input.close();
			System.out.println("Putting piece at coordinates: (" + userRow +"," + userCol +")");
			}
			else {							//if place in board is occupied, show error
				System.out.println("Invalid move: coordinates already occupied");
				getRow();
				getCol();
				placeToken();
			}
		} 
		catch(ArrayIndexOutOfBoundsException E) { //if user input is beyond board, prompt again
			System.out.println("invalid input, enter a number between 0 and 15");
			getRow();
			getCol();
			placeToken();
			}
		}
	
	
	public void play() { //this method we will call when it's players turn
		getRow();
		getCol();
		placeToken();
	}
	
	
}
