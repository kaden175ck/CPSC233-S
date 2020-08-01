/**
  *@author Haoyang Shi  30105296
  *@Description:This program suppose to simulate an AI player.
  *I have already test this program on other people's code, and it can work properly.
  *The level of this AI would be about middle.
  *I will keep improving this AI.
  */

import java.util.*;
public class Ai {
	private HashMap<String,Integer> map1=new HashMap<>();
	private HashMap<String,Integer> map2=new HashMap<>();
	private int[][] board;
	private int aiplayer;
	


	public Ai(int[][] board,int aiplayer)
	{
		this.aiplayer =aiplayer;
		this.board=board;
		
		
		/**
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
	}
	 public int[] playChess()
	 {
		 int max=0,mark;
		 
		 int[] coord=new int[2];
		 coord[0]=0;
		 coord[1]=0;
			for(int i=0;i<board.length;i++)
				for(int j=0;j<board.length;j++)
	    	{ 
					
					if(board[i][j]==0)
					{mark=checkMark	(i,j);
					if(max<mark)
					{
						max=mark;
						 coord[0]=i;
						 coord[1]=j;
					}
					}
	    	}
	 
	 return coord;
	 }
	 
	/* public static void main(String[] args)
	 {
		 System.out.println(mark("22220"));
	 }*/
	 
    public static int mark(String str)//give different pattern a value. 
    {
    	int black=0,white=0,mark=0;
    	
    	for(int i=0;i<str.length();i++)
    	{
    		if(str.charAt(i)=='1')//count the number of times black chess appeared
    			black++;
    		if(str.charAt(i)=='2')// same as above for white.
    			white++;	
    			
    	}
    	
    	
    	
    	if(black==0)//if the pattern does not contain any black chess,note that pattern can only contains up to 5 spots
    	{
   if(white==5) 	
    	mark=10000000;//this is the most dangerous situation, because white will win.
   if(white==4) 	
   	mark=15000;//this case, the pattern is 22220
   if(white==3) 	
	   	mark=625;//22200
   if(white==2) 	
	   	mark=25;//22000
   if(white==1) 	
	   	mark=1;//20000
   return mark;
    	}
    	
    	
  if(white==1)
    	{if(black==4)
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
    
    
	public int checkMark	(int a,int b)
	{
		board[a][b]=aiplayer;
		int mark=0;
		for(int x=0;x<board.length-4;x++)
			for(int y=0;y<board[0].length-4;y++)
			{
				mark+=checkRow(x, y)+checkDiag( x, y)+checkCol(x,y);
			}
		board[a][b]=0;
		return mark;
	}
	
	

	public int checkRow(int x,int y)
	{ HashMap<String,Integer> map;
		String str="";
		for(int i=0;i<5;i++)
		str=str+board[x][y+i];
		if(aiplayer==1)
		map=map1;
		else
			map=map2;
		return  mark(str);
			
	}
	public int checkDiag(int x,int y)	{
		HashMap<String,Integer> map;
		String str1="";
		for(int i = 0; i < 5 ;i++)
			str1=str1+board[x+i][y+i];
		String str2="";
		for(int i = 0; i < 5 ;i++)
			str2=str2+board[x+4-i][y+i];
		if(aiplayer==1)
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
		if(aiplayer==1)
			map=map1;
		else
		map=map2;
	return  mark(str);
	
		
	}
	
}
