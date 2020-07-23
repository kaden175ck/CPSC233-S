
public class GameConfiguration{

	public int humanVSAI() {
		return 0;
	}
	
	public int humanVShuman(HumanPlayer human1, HumanPlayer human2) {
		return 0;
	}
	
	
	public static boolean checkWon(int row,int col, Board aBoard) {
		return (checkDiagonalDownLeftToTopRight(row,col,aBoard) || checkDiagonalTopLeftToDownRight(row,col,aBoard) || checkRow(row,col,aBoard) || checkCol(row,col,aBoard)); 
	}
	
	//
    public static boolean checkRow(int row, int col, Board aBoard){

        //identify which color
        int color = aBoard.getValue(row, col);
        //count how many tiles in row
        int count=1;
        // check result
        boolean checkResult = false;
        
        //row right
        for(int i=1;(i<5) && (color==aBoard.getValue(row, col+i));i++) {
            if((col+i)<=15) {
                count++;
            }
        }
        //row left
        for(int i=1;(i<5) && (color==aBoard.getValue(row, col-i));i++) {
            if((col-i)>=0) {
                count++;
            }
        }
        if (count >= 5) {
        	checkResult = true;
        } 
    	return checkResult;
    }
       
     public static boolean checkCol(int row, int col, Board aBoard){
    	 //identify which color
         int color = aBoard.getValue(row, col);
        //the xcoordinate and ycoordinate of tiles is the same as the index of their array index. 
         //count how many tiles in row
         int count=1;
         // check result
         boolean checkResult = false;
            
         //column down
         for(int i=1;(i<5) && (color==aBoard.getValue(row+i, col));i++) {
        	 if((row+i)<=15) {
                 count++;
             }
         }
        //column up
         for(int i=1;(i<5) && (color==aBoard.getValue(row-i, col));i++) {
             if((row-i)>=0) {
                 count++;
             }
         }
         if (count >= 5) {
             checkResult = true;
         } 
         return checkResult;
     }
         
    public static boolean checkDiagonalDownLeftToTopRight(int row, int col, Board aBoard) {
    	 //identify which color
         int color = aBoard.getValue(row, col);
        //the xcoordinate and ycoordinate of tiles is the same as the index of their array index. 
         //count how many tiles in row
         int count=1;
         // check result
         boolean checkResult = false;
         
         //upright
         for(int i=1;(i<5) && (color==aBoard.getValue(row-i, col+1));i++) {
             if(((row-i)>=0) && (col+i<=15)) {
                 count++;
             }
         }
         //downleft
         for(int i=1;(i<5) && (color==aBoard.getValue(row+i, col-1));i++) {
             if(((row+i)<=15) && (row-i>=0)) {
                  count++;
             } 
         }
         if (count >= 5) {
             checkResult = true;
         } 
         return checkResult;
     }
    
    public static boolean checkDiagonalTopLeftToDownRight(int row, int col, Board aBoard) {
    	 //identify which color
        int color = aBoard.getValue(row, col);
       //the xcoordinate and ycoordinate of tiles is the same as the index of their array index. 
        //count how many tiles in row
        int count=1;
        // check result
        boolean checkResult = false;
        
        //upleft
        for(int i=1;(i<5) && (color==aBoard.getValue(row-i, col-1));i++) {
            if(((row-i)>=0) && (col-i>=0)) {
                count++;
            }
        }
        //downright
        for(int i=1;(i<5) && (color==aBoard.getValue(row+i, col+1));i++) {
            if(((row+i)<=15) && (col+i<=15)) {
                 count++;
            } 
        }
        if (count >= 5) {
            checkResult = true;
        } 
        return checkResult;
    } 

}
