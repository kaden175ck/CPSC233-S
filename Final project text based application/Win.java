

/**
  * @ClassName: Win
  * @Description: TODO
  * @author Haoyang Shi
  * @date 2020 7.20    5:26:17
  *
  */

public boolean checkWin(int x,int y)
		{   	
			if(checkDiagonal(x,y) || checkRow(x,y) || checkCol(x,y)) 
				return true;
			return false;

		}

	
	public boolean checkRow(int x, int y) {
		int counter = 0;
		for (int i = x + 1; i < 15; i++)
		{
			if (Board[i][y] ==Board[x][y])
				counter++;
				else break;
		}
		
		for (int i = x; i >= 0; i--) {
			if (Board[i][y] ==Board[x][y])
				counter++;
         else break;
		}
     if(counter>=5)
		return true;
     return false;
		
	}
	
	public boolean checkCol(int x, int y) 
	{
		int counter = 0;//
		for (int j = y + 1; j < 15; j++)
		{
			if (Board[x][j] ==Board[x][y])
				counter++;
				else break;
		}
		
		for (int j = x; j >= 0; j--) {
			if (Board[x][j] ==Board[x][y])
				counter++;
         else break;
		}
     if(counter>=5)
		return true;
     return false;
		
	}
	public boolean checkDiagonal(int x, int y)
	{
			int counter = 0;
			for (int i = x + 1,j =y+1; i < 15 && j < 15;i++,j++)
			{
				if (Board[i][j] ==Board[x][y])
					counter++;
					else break;
			}
			
			for (int i = x,j =y; i >=0 && j >=0;i--,j--) {
				if (Board[i][j] ==Board[x][y])
					counter++;
	            else break;
			}
	        if(counter>=5)
			return true;
	        
	        
	        counter = 0;
			for (int i = x + 1,j =y-1; i < 15 && j>0;i++,j--)
			{
				if (Board[i][j] ==Board[x][y])
					counter++;
					else break;
			}
			
			for (int i = x,j =y; i >=0 && j <15;i--,j++) 
			{
				if (Board[i][j] ==Board[x][y])
					counter++;
	            else break;
			}
	        
			 if(counter>=5)
			return true;
	        return false;	
	}
	 
	 