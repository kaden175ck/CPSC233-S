
public class Game extends GameConfiguration {	
	public static void main(String[] args) {
		//Draw board
		Board aBoard = new Board();
		aBoard.creatBoard();
		

		HumanPlayer aHuman1 = new HumanPlayer();
		int human1Color = aHuman1.setColor();
		
		HumanPlayer aHuman2 = new HumanPlayer();
		aHuman2.setOpponentColor(human1Color);
		
		 int counter = 0;
		 while((counter<=112)) {
			 aHuman1.play(aBoard);
			 
			 if(checkWon(aHuman1.getRow(), aHuman1.getCol(), aBoard) == true) {
				 if(human1Color == 1) {
					 System.out.println("Black win!");
				 }
				 else if(human1Color == 2) {
					 System.out.println("White win!");
				 }
				break;
				}
			 
			 aHuman2.play(aBoard);
			 if(checkWon(aHuman2.getRow(), aHuman2.getCol(), aBoard) == true) {
				 if(human1Color == 1) {
					 System.out.println("White win!");
				 }
				 else if(human1Color == 2) {
					 System.out.println("Black win!");
				 }
				break;
				}
			 
			 
			 counter++;	
		}
		//aHuman2.play(aBoard);
		
		
	}
	
	

}
