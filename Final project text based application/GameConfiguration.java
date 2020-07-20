package application;

public class GameConfiguration extends Tile {
	protected static Tile[] [] board = new Tile[15] [15];
	
	public int getTileRowIndex() {
		return 0;
	}
	
	public int getTilecolumnIndex() {
		return 0;
	}
	
	public static void Board() {
		System.out.println(" Gomoku");// Prompt to user game start.
		for(int i=0;i<15;i++) {
			System.out.println();
			for(int j=0;j<15;j++) {
				System.out.print(board[i][j] + "0");
			}
		}
	}
	
	public boolean isValidTile(Tile aTile) {
		boolean tileVaild = false;
		//the xcoordinate and ycoordinate of 'tile' is the same as its index of array 'board'. 
		if(0<=aTile.getxCoord() && aTile.getxCoord()<=15 && 0<=aTile.getyCoord() && aTile.getyCoord()<=15 && aTile != null) {
			tileVaild = true;
		}
		return tileVaild;
	}
	
	public boolean hasWon(Tile aTile) {
		return (checkDiagonalDownLeftToTopRight(aTile) || checkDiagonalTopLeftToDownRight(aTile) || checkRow(aTile) || checkCol(aTile)); 
	}
	
	//
    public boolean checkRow(Tile aTile){

        //identify which color
        int color = aTile.getColor();
       //the xcoordinate and ycoordinate of 'tile' is the same as its index of array 'board'. 
        int xIndex = aTile.getxCoord();
        int yIndex = aTile.getyCoord();
        //count how many tiles in row
        int count=1;
        // check result
        boolean checkResult = false;
        
        //row right
        for(int i=1;(i<5) && (color==board[yIndex][xIndex+i].getColor() && board[yIndex][xIndex+i]!=null);i++) {
            if((xIndex+i)<=15) {
                count++;
            }
        }
        //row left
        
        for(int i=1;(i<5) && (color==board[yIndex][xIndex-i].getColor() && board[yIndex][xIndex+i]!=null);i++) {
            if((xIndex-i)>=0) {
                count++;
            }
        }
        if (count >= 5) {
        	checkResult = true;
        } 
    	return checkResult;
    }
       
     public boolean checkCol(Tile aTile){
    	 //identify which color
         int color = aTile.getColor();
        //the xcoordinate and ycoordinate of tiles is the same as the index of their array index. 
         int xIndex = aTile.getxCoord();
         int yIndex = aTile.getyCoord();
         //count how many tiles in row
         int count=1;
         // check result
         boolean checkResult = false;
            
         //column down
         for(int i=1;(i<5) && (color==board[yIndex+i][xIndex].getColor() && board[yIndex+i][xIndex]!=null);i++) {
             if((yIndex+i)<=15) {
                 count++;
             }
         }
        //column up
         for(int i=1;(i<5) && (color==board[yIndex-i][xIndex].getColor() && board[yIndex-i][xIndex]!=null);i++) {
             if((yIndex-i)>=0) {
                 count++;
             }
         }
         if (count >= 5) {
             checkResult = true;
         } 
         return checkResult;
     }
         
    public boolean checkDiagonalDownLeftToTopRight(Tile aTile) {
    	 //identify which color
         int color = aTile.getColor();
        //the xcoordinate and ycoordinate of tiles is the same as the index of their array index. 
         int xIndex = aTile.getxCoord();
         int yIndex = aTile.getyCoord();
         //count how many tiles in row
         int count=1;
         // check result
         boolean checkResult = false;
         
         //upright
         for(int i=1;(i<5) && (color==board[yIndex-i][xIndex+i].getColor() && board[yIndex-i][xIndex+i]!=null);i++) {
             if(((yIndex-i)>=0) && (xIndex+i<=15)) {
                 count++;
             }
         }
         //downleft
         for(int i=1;(i<5) && (color==board[yIndex+i][xIndex-i].getColor() && board[yIndex+i][xIndex-i]!=null);i++) {
             if(((yIndex+i)<=15) && (xIndex-i>=0)) {
                  count++;
             } 
         }
         if (count >= 5) {
             checkResult = true;
         } 
         return checkResult;
     }
    
    public boolean checkDiagonalTopLeftToDownRight(Tile aTile) {
    	 //identify which color
        int color = aTile.getColor();
       //the xcoordinate and ycoordinate of tiles is the same as the index of their array index. 
        int xIndex = aTile.getxCoord();
        int yIndex = aTile.getyCoord();
        //count how many tiles in row
        int count=1;
        // check result
        boolean checkResult = false;
        
        //upleft
        for(int i=1;(i<5) && (color==board[yIndex-i][xIndex-i].getColor() && board[yIndex-i][xIndex-i]!=null);i++) {
            if(((yIndex-i)>=0) && (xIndex-i>=0)) {
                count++;
            }
        }
        //downright
        for(int i=1;(i<5) && (color==board[yIndex+i][xIndex+i].getColor() && board[yIndex+i][xIndex+i]!=null);i++) {
            if(((yIndex+i)<=15) && (xIndex+i<=15)) {
                 count++;
            } 
        }
        if (count >= 5) {
            checkResult = true;
        } 
        return checkResult;
    } 
        
        
	public static void main(String[] args) {
		Board();
	}

}
