package game;

import java.util.Scanner;

//newest version without GUI. Every update on this project will be in the branch called "Kaden"

/**
  * @ClassName: Game
  * @Description: TODO, remember this class is the main class.
  * @author Haoyang Shi
  * @date 2020   7.28 
  *
  */
//below comment is for my teammate(will be deleted after everything is settled.)

//commenting is not complete(feel free to add more)..the code can be polished more if yall want to
//when yall add the GUI, try to keep the coding style similar to mine
//Few things to note here:

//this class, the "Game class" is the main class. few important things will be done in this class
//will keep asking for input when user's input position is already token
//for GUI, if user click on the token spot, then nothing should happen, until the user click on the empty spot
//thus, if the user enter out of bounds value like (88,666), then the program will crash. 
//if yall want to add error handling method for this one, feel free to do so,
//but i think it's unnecessary.(because eventually everything will be done on a window(clicking, not entering numbers)
public class Game{	
	//private Ai ai;
	private int[][] board;
	
	public static boolean checkWon(int row,int col, int [][] board) {
		return (checkDiagonalDownLeftToTopRight(row,col,board) || checkDiagonalTopLeftToDownRight(row,col,board) || checkRow(row,col,board) || checkCol(row,col,board));
		
	}
	
	public int[][] getBoard(){
		return board;
	}
	
    public static boolean checkRow(int row, int col, int [][]board){

        //identify which color
        int color = board[row][ col];
        //count how many tiles in row
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
        
        for(int i = 1;i < 5&&row-i>=0;i++) {
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
       
     public static boolean checkCol(int row, int col, int[][]board){
    	 //identify which color
         int color = board[row][col];
        //the xcoordinate and ycoordinate of tiles is the same as the index of their array index. 
         //count how many tiles in row
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
         
    public static boolean checkDiagonalDownLeftToTopRight(int row, int col, int[][]board) {
    	 //identify which color
         int color = board[row][col];
        //the xcoordinate and ycoordinate of tiles is the same as the index of their array index. 
         //count how many tiles in row
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
    
    public static boolean checkDiagonalTopLeftToDownRight(int row, int col, int[][]board) {
    	 //identify which color
        int color = board[row][col];
       //the xcoordinate and ycoordinate of tiles is the same as the index of their array index. 
        //count how many tiles in row
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
	
	

	public static void main(String[] args) {
		System.out.println("Who do u want to play with? Enter 1 for Ai, 2 for Another human player:");
		Scanner keyboard=new Scanner(System.in);
		int choice=keyboard.nextInt();
		
		 if (choice==1) {//to play with Ai
		Game game=new Game();
		
		Player aHuman1 = new HumanPlayer (game.board,1);//can be either Ai or HumanPlayer

		Player AI = new Ai (game.board,2);//can be either Ai or HumanPlayer

		 while(game.play(aHuman1)&&game.play(AI));//keep playing if condition is satisfied
		 }
		 
		 
		 
		 if (choice==2) {//play with another player
			Game game=new Game();
			
			Player aHuman1 = new HumanPlayer (game.board,1);//can be either Ai or HumanPlayer

			Player aHuman2 = new HumanPlayer (game.board,2);//can be either Ai or HumanPlayer

			 while(game.play(aHuman1)&&game.play(aHuman2));//keep playing if condition is satisfied
			 
		 }
	}
	public  boolean play(Player player) {
		
		
		 int[] coord=player.playChess();//call playchess method, see details about this method in AI class
		
		 int row=coord[0];
		 int col=coord[1];
		 board[row][col]=player.getColor();
		 
		for(int i=0;i<15;i++) {
			System.out.println();	
			for(int j=0;j<15;j++) {
				System.out.print(board[i][j] + " ");
			}
		}
		
				System.out.println();
				System.out.println();

				if(checkWon(coord[0], coord[1], board) ) {
					 if( player.getColor() == 1) {
						 System.out.println("Black win!");
					 }
					 else if( player.getColor() == 2) {
						 System.out.println("White win!");
					 }
					 return false;
					}

	return true;
		}
	}
		 
		 
		 
		 
