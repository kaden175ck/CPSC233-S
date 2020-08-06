import java.util.*;
/**
  * @ClassName: Ai 
  * @Description: simulate a computer turn
  * @author Haoyang Shi
  * @date 2020   7.24
  *
  */


/**
  * @ClassName: Ai
  * @Description: simulate a computer's play by giving value to different pattern of placing
  * @author Haoyang Shi
  * @date 2020 8.5    7:14:19
  *
  */

public class Ai extends Player{
	
	private HashMap<String,Integer> map1=new HashMap<>();//stores for 1
	private HashMap<String,Integer> map2=new HashMap<>();//stores for 2

	/**
	  * instance Ai. 
	  * <p>Title: </p>
	  * <p>Description: Ai constructor that take 2 parameters</p>
	  * @param board     the board
	  * @param color     indicate different turns/players
	  */
	public Ai(int[][] board,int color){
		super(board,color);//from Player(parents class)
	}
	
	
	/**
	 * this block doesn't mean anything right now
	 *  I keep this for future reference.
	map1.put("11111", 100000000);
	map1.put("11112", 20000000);
	map1.put("12111", 20000000);
	map1.put("21111", 20000000);
	map1.put("11211", 20000000);
	map1.put("11121", 20000000);
	map1.put("11110", 4000000);
	map1.put("11101", 4000000);
	map1.put("11011", 4000000);
	map1.put("10111", 4000000);
	map1.put("01111", 4000000);
	map1.put("21110", 800000);
	map1.put("21101", 800000);
	map1.put("21011", 800000);
	*/
	
	
	 /*
	  * <p>Title: playChess</p>
	  * <p>Description: this method will return the spot that AI should take</p>
	  * @return coord 
	  */
	
	public int[] playChess(){
		 //maximum means the best value to put the chess
		 int max=0,mark;//set maximum=0 and set mark
		 int[] coord=new int[2];//contain 2 spots
		 coord[0]=0;// set first spot to 0
		 coord[1]=0;// set second spot to 0
		 
			for(int i=0;i<board.length;i++) {
				for(int j=0;j<board.length;j++){ 
					if(board[i][j]==0){//check if its empty
					mark=checkMark(i,j);
					if(max<mark){
						max=mark;
						 coord[0]=i;
						 coord[1]=j;
					}
					}
	         	}
			}
	 return coord;
	 }
	 
	
	/* public static void main(String[] args)
	 {
		 System.out.println(mark("22220"));
	 }*/
	 
    public static int mark(String str){
    	
    	int black=0,white=0,mark=0;
    	
    	for(int i=0;i<str.length();i++)
    	{
    		if(str.charAt(i)=='1')//remember we create an empty string below and record every directions of placing? this is to count the number of time 1 and 2 appears(for the next block of code)
    			black++;
    		if(str.charAt(i)=='2')
    			white++;	
    			
    	}
    	
    	
   // just know that different type of placing will return a different value of mark.(which is the core of this AI)	
   if(black==0){
   if(white==5) 	
    	mark=10000000;
   if(white==4) 	
   	mark=15000;
   if(white==3) 	
	   	mark=625;
   if(white==2) 	
	   	mark=25;
   if(white==1) 	
	   	mark=1;
   return mark;
    	}
    	
    	
  if(white==1){
  if(black==4)
    mark=1500000;
  if(black==3)
    mark=3000;
   if(black==2)
    mark=125;
   if(black==1)
    mark=5;
    	}
    			
  return mark;
    }
    
    
	public int checkMark	(int a,int b){
		
		board[a][b]=color;
		int mark=0;
		for(int x=0;x<board.length-4;x++)
			for(int y=0;y<board[0].length-4;y++)
			{
				mark+=checkRow(x, y)+checkDiag( x, y)+checkCol(x,y);
			}
		board[a][b]=0;
		return mark;
	}
	
	

	public int checkRow(int x,int y){
		HashMap<String,Integer> map;// ignore the warning.
		String str="";
		for(int i=0;i<5;i++)
		str=str+board[x][y+i];
		if(color==1)
		map=map1;
		else
			map=map2;
		return  mark(str);
			
	}
	
	
	public int checkDiag(int x,int y)	{
		HashMap<String,Integer> map ;
		String str1="";
		for(int i = 0; i < 5 ;i++)
			str1=str1+board[x+i][y+i];
		String str2="";
		for(int i = 0; i < 5 ;i++)
			str2=str2+board[x+4-i][y+i];
		if(color==1)
			map=map1;
		else
		map=map2;
	return  mark(str1)+mark(str2);
		
}
	
	
	public int checkCol(int x,int y)	{
		HashMap<String,Integer> map;
		String str="";
		for(int i = 0; i < 5 ;i++)
			str=str+board[x+i][y];
		if(color==1)
			map=map1;
		else
		map=map2;
	return  mark(str);	
	}
	
	
}
