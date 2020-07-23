
public class Game {	
	public static void main(String[] args) {
		//Draw board
		Board aBoard = new Board();
		aBoard.creatBoard();
		

		HumanPlayer aHuman1 = new HumanPlayer();
		aHuman1.setColor();
		
		HumanPlayer aHuman2 = new HumanPlayer();
		aHuman2.setColor();
		
		aHuman1.play(aBoard);
		
		aHuman2.play(aBoard);
		
		
	}
	
	

}
