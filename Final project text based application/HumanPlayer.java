import java.util.Scanner;

public class HumanPlayer{
	
	private int token; //will implement later 
	private Scanner input = new Scanner(System.in);
	//private char board[][] = new char[15][15];//will probably get rid of this, we need to make a board class
	
	
	private int row;
	private int col;
	private int color;
	
	private int userRow;
	private int userCol;
	private int userColor;
	
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
	
	public int setColor() {
		System.out.println("Pick the color of your pieces. 1: Black, 2: White");
		color = input.nextInt();
		if(color ==1 || color == 2)
			userColor = color;
		else {
			System.out.println("Please select valid color. (1: White, 2: Black)");
		}
		return userColor;
	}
	
	public void setOpponentColor(int aColor) {
		if (aColor == 1) userColor = 2;
		if (aColor == 2) userColor = 1;
	}
	

	
	/**
	 * Title: placeToken
	 * Description: Method that tests if coordinates are valid, (coordinate is not occupied and within range)
	 * If valid place token onto coordinates
	 * if not show errors and prompt user again
	 */
	public void placeToken(Board aBoard) {
		try { 								//try statement for placing piece
			int value = aBoard.getValue(row, col);
			if(value == 0) {
			userCol = col;
			userRow = row;
			
			value = token; 		//import from token class
			input.close();
			}
			else {							//if place in board is occupied, show error
				System.out.println("Invalid move: coordinates already occupied");
				setRow();
				setCol();
				placeToken(aBoard);
			}
		} 
		catch(ArrayIndexOutOfBoundsException E) { //if user input is beyond board, prompt again
			System.out.println("invalid input, enter a number between 0 and 15");
			setRow();
			setCol();
			placeToken(aBoard);
			}
		}
	
	
	public void play(Board aBoard) { //this method we will call when it's players turn
		setRow();
		setCol();
		placeToken(aBoard);
		aBoard.setValue(userRow, userCol, userColor);
		for(int i=0;i<15;i++) {
			System.out.println();	
			for(int j=0;j<15;j++) {
				System.out.print(aBoard.getValue(i, j) + " ");
			}
		}
		System.out.println();
		System.out.println();
	
	}
	
	
}

