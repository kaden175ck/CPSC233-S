
public class Game extends GameConfiguration {	
	public static void main(String[] args) {
		//Draw board
		Board aBoard = new Board();
		aBoard.creatBoard();
		

		HumanPlayer aHuman1 = new HumanPlayer();
		aHuman1.setColor();
		
		//HumanPlayer aHuman2 = new HumanPlayer();
		//aHuman2.setColor();
		
		 int counter = 0;
		
		 while((counter<=112)) {
			 aHuman1.play(aBoard);
			 
			 if(checkWon(aHuman1.getRow(), aHuman1.getCol(), aBoard) == true) {
				System.out.println("You win!");
				}
			 
			 counter++;	
		}
		//aHuman2.play(aBoard);
		
		
	}
	
	

}
