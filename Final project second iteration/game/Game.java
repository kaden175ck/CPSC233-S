import java.util.Scanner;
/**
  * @ClassName: Game
  * @Description: this is the main class for the project, control the flow of the games.
  * @author Haoyang Shi
  * @date 2020   7.28 
  *
  */


//will keep asking for input when user's input position is already token
//for GUI, if user click on the token spot, then nothing should happen, until the user click on the empty spot
//if the user enter out of bounds value like (88,666), then the program will crash. 
public class Game{	
	private int[][] board;
	
	
	/**
	  *
	  * @Title: checkWon
	  * @Description: check the winning situation from all the directions
	  * @param @param row
	  * @param @param col
	  * @param @param board
	  * @param @return    
	  * @return boolean    
	  * @throws
	  */
	
	
	public static boolean checkWon(int row,int col, int [][] board) {
		return (checkDiagonalDownLeftToTopRight(row,col,board) || checkDiagonalTopLeftToDownRight(row,col,board) || checkRow(row,col,board) || checkCol(row,col,board));
		
	}
	
	
    /**
      * @Title: checkRow
      * @Description: check the winning situation in row
      * @param @param row
      * @param @param col
      * @param @param board
      * @param @return    
      * @return boolean    
      * @throws
      */
    
    
    public static boolean checkRow(int row, int col, int [][]board){
        //identify which color
        int color = board[row][ col];
        int count=1;
        // check result
        boolean checkResult = false;
        
        //row right
        for(int i=1;i<5&&row+i<15;i++) {
            if(board[row+i][col]==color) 
                count++;
            else
           	 break;
        }
        
        for(int i=1;i<5&&row-i>=0;i++) {
            if(board[row-i][col]==color) 
                count++;
            else
           	 break;
        }
        
        if (count >= 5) {
        	checkResult = true;
        } 
    	return checkResult;
    }
       
    
     /**
      *
      * @Title: checkCol
      * @Description: check the winning situation in col
      * @param @param row
      * @param @param col
      * @param @param board
      * @param @return    
      * @return boolean    
      * @throws
      */
    
    
    public static boolean checkCol(int row, int col, int[][]board){
    	 //identify which color
         int color = board[row][col];
         int count=1;
         // check result
         boolean checkResult = false;
            
         //column down
         for(int i=1;i<5&&col+i<15;i++) {
             if(board[row][col+i]==color) 
                 count++;
             else
            	 break;
         }
         
         for(int i=1;i<5&&col-i>=0;i++) {
             if(board[row][col-i]==color) 
                 count++;
             else
            	 break;
         }

         if (count >= 5) {
             checkResult = true;
         } 
         return checkResult;
         
     }
     
     
         
    /**
      *
      * @Title: checkDiagonalDownLeftToTopRight
      * @Description: check the winning situation from DownLeft To TopRight
      * @param @param row
      * @param @param col
      * @param @param board
      * @param @return    
      * @return boolean    
      * @throws
      */
    
    
    public static boolean checkDiagonalDownLeftToTopRight(int row, int col, int[][]board) {
    	 //identify which color
         int color = board[row][col];
         int count=1;
         // check result
         boolean checkResult = false;
         
         //upright
         for(int i=1;i<5&&row-i>=0&&col+i<15;i++) {
             if(board[row-i][col+i]==color) 
                 count++;
             else
            	 break;
         }
         
         for(int i=1;i<5&&row+i<15&&col-i>=0;i++) {
             if(board[row+i][col-i]==color) 
                 count++;
             else
            	 break;
         }

         if (count >= 5) {
             checkResult = true;
         } 
         return checkResult;
         
     }
    
    
    
    /**
      *
      * @Title: checkDiagonalTopLeftToDownRight
      * @Description: check the winning situation from TopLeft To DownRight
      * @param @param row
      * @param @param col
      * @param @param board
      * @param @return    
      * @return boolean    
      * @throws
      */
    
    
    public static boolean checkDiagonalTopLeftToDownRight(int row, int col, int[][]board) {
    	 //identify which color
        int color = board[row][col];
        int count=1;
        // check result
        boolean checkResult = false;
        
        for(int i=1;i<5&&row-i>=0&&col-i>=0;i++) {
            if(board[row-i][col-i]==color) 
                count++;
            else
           	 break;
        }
        
        for(int i=1;i<5&&row+i<15&&col+i<15;i++) {
            if(board[row+i][col+i]==color) 
                count++;
            else
           	 break;
        }

        
        if (count >= 5) {
            checkResult = true;
        } 
        return checkResult;
        
    } 


	
 // print a 15*15 board
	public Game() {
		board=new int[15][15];
			for(int i=0;i<15;i++) {
				System.out.println();	
				for(int j=0;j<15;j++) {
					System.out.print(0 + " ");
				}
			}
			System.out.println();
			System.out.println();
	}
	
	
	//the main method will control the flow of the game(pass on the parameters) and display any message if needed
	public static void main(String[] args) {
		
		System.out.println("Welcom to Gomoku!");
		System.out.println("First one to form an unbroken chain of five pieces horizontally, vertically, or diagonally win the game.\n");
		System.out.println("Who do u want to play Gomoku with? Enter 1 for Ai, 2 for Another human player:");
	
		Scanner keyboard=new Scanner(System.in);
		int choice=keyboard.nextInt();
		
		
		 if (choice==1) {//to play with Ai
		 Game game=new Game();
		 System.out.println("1 will represent your chess\n2 will represent Ai's chess");
		
		Player aHuman1 = new HumanPlayer (game.board,1);//refresh the board;human player is represented by 1
		Player aHuman2 = new Ai (game.board,2);//refresh the board;Ai is represented by 2
		
		while(game.play(aHuman1)&&game.play(aHuman2));//keep playing if condition is satisfied
		 }
		 
		 
		 
		 if (choice==2) {//play with another player
			Game game=new Game();
			System.out.println("1 will represent Player1\n2 will represent player2");
			
			Player aHuman1 = new HumanPlayer (game.board,1);//refresh the board;first human player1(goes first)
			Player aHuman2 = new HumanPlayer (game.board,2);//refresh the board;second human player2(goes second)

			while(game.play(aHuman1)&&game.play(aHuman2));//keep playing if condition is satisfied 
		 }
		 
	}

		 
		 
		 
		 
/**
  * play
  * @Title: play
  * @Description: this method will reprint the board and check who has won the game and display any message if needed
  * @param @param player
  * @param @return   
  * @return boolean    
  * @throws
  */


public  boolean play(Player player) {
	
	 int[] coord=player.playChess();//see details about this method in AI class
	
	 int row=coord[0];//player's x coord
	 int col=coord[1];//player's y coord
	 board[row][col]=player.getColor();//change the representation in that position
	 
    //reprint the board
	for(int i=0;i<15;i++) {
		System.out.println();	
		for(int j=0;j<15;j++) {
			System.out.print(board[i][j] + " ");
		}
	}
			System.out.println();
			System.out.println();//so that there are two empty lines to separate the next board

			
			if(checkWon(coord[0], coord[1], board) ) {
				 if( player.getColor() == 1) {
					 System.out.println("1 win");
				 }
				 else if( player.getColor() == 2) {
					 System.out.println("2 win");
				 }
				 return false;
				}
    return true;
	}

}