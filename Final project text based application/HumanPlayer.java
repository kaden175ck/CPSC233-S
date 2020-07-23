import java.util.Scanner;

public class HumanPlayer{
	
	private char token; //will implement later 
	private Scanner input = new Scanner(System.in);
	//private char board[][] = new char[15][15];//will probably get rid of this, we need to make a board class
	
	
	private int row;
	private int col;
	private int color;
	
	private int userRow;
	private int userCol;
	private int userColor;
	
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
	
	public int getColor() {
		System.out.println("Pick the color of your pieces. 1: Black, 2: White");
		color = input.nextInt();
		if(color ==1 || color == 2)
			userColor = color;
		return userColor;
	}
	

	
	/**
	 * Title: placeToken
	 * Description: Method that tests if coordinates are valid, (coordinate is not occupied and within range)
	 * If valid place token onto coordinates
	 * if not show errors and prompt user again
	 */
	public void placeToken(Board aBoard) {
		try { 								//try statement for placing piece
			char value = aBoard.getValue(row, col);
			if(value == 0) {
			userCol = col;
			userRow = row;
			
			value = token; 		//import from token class
			input.close();
			System.out.println("Putting piece at coordinates: (" + userRow +"," + userCol +")");
			}
			else {							//if place in board is occupied, show error
				System.out.println("Invalid move: coordinates already occupied");
				getRow();
				getCol();
				placeToken(aBoard);
			}
		} 
		catch(ArrayIndexOutOfBoundsException E) { //if user input is beyond board, prompt again
			System.out.println("invalid input, enter a number between 0 and 15");
			getRow();
			getCol();
			placeToken(aBoard);
			}
		}
	
	
	public void play(Board aBoard) { //this method we will call when it's players turn
		getRow();
		getCol();
		placeToken(aBoard);
	}
	
	
}

