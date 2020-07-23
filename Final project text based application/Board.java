
public class Board {
public int[][] board=new int[15][15];
	
	//public void main(String[] args) {
		//creatBoard();
	//}
	
	public void creatBoard() {
		//print the board
		System.out.println(" Welcom to Gomoku!");// print a new board everytime a player does something.
		for(int i=0;i<15;i++) {
			System.out.println();	
			for(int j=0;j<15;j++) {
				System.out.print(board[i][j] +" O");
			}
		}
		System.out.println();
	}

	public int getValue(int row, int col) {
		return board[row][col];
	}
}
