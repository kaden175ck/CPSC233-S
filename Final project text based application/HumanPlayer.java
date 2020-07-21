/**
 * Gomoku iteration 1 
 * Title: Human Player
 * Description: Player turn for Gomoku game 
 * Prompts user to get coordinates of piece and places piece on board if valid move.
 */
import java.util.Scanner;
public class HumanPlayer{
	
	private String token; //will implement later 
	private static Scanner input = new Scanner(System.in);
	private static char board[][] = new char[15][15];//will probably get rid of this, we need to make a board class
	
	
	private static int row;
	private static int col;
	
	private static int userRow;
	private static int userCol;
	
	/**
	 * Title: getRow
	 * Description: Prompts user for row 
	 * @return value of row
	 */
	public static int getRow() {
		System.out.println("Enter row where you want piece to go: ");
		row = input.nextInt();
		return row;
	}
	/**
	 * Title: getCol
	 * Description: Prompts user for column 
	 * @return value of column
	 */
	public static int getCol() {
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
	public static void placeToken() {
		try { 								//try statement for placing piece
			if(board[row][col] ==0) {
			userCol = col;
			userRow = row;
			board[row][col] = 'X'; 
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
	
	public static void printBoard() { //printing board
		for(int i=0;i<15;i++) {
			System.out.println();	
			for(int j=0;j<15;j++) {
				System.out.print(board[i][j] +"O");
			}
		}
		System.out.println();
	}
	public static void play() { //this method we will call when it's players turn
		printBoard();
		getRow();
		getCol();
		placeToken();
		printBoard();
	}
	
	public static void main(String[] args) {
		play();
	}
}
