package model;

import java.util.Random;

/* This class must extend Game */
public class ClearCellGame extends Game 

{
	private Random random;
	int strategy;
	private int rowsCleared=0;
	private int score=0;
	
	
	public ClearCellGame(int maxRows, int maxCols,java.util.Random random,int strategy) 
	{
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
	
	}
	
	public boolean isGameOver() 
	{
		int emptyCount = 0;
		
		for (int i = 0; i < board[0].length; i++)
		{
			if (board[board.length - 1][i] == BoardCell.EMPTY)
			{
				emptyCount++;
			}
		}
		
		if (emptyCount == board[0].length)
		{
			 return false;
		}
		else
		{
			return true;
		}
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void nextAnimationStep() 
	{
		if (isGameOver() == false)
		{
			BoardCell[][] storageArray = copyArray(board);
			rowsCleared++;
			
			for (int i = 1; i < this.board.length; i++)
			{
				for (int j = 0; j < this.board[0].length; j++)
				board[i][j] = storageArray[i - 1][j];
			}
				
			for (int i = 0; i < this.board[0].length; i++)
			{
				board[0][i] = BoardCell.getNonEmptyRandomBoardCell(this.random);
			}
		}
		
	}
	
	public void processCell(int rowIndex, int colIndex)
	{
		BoardCell cell = board[rowIndex][colIndex]; 
	    int increment = 1; 
		 int index = 0;
		 int j = 0; 
		 BoardCell[][] storageArray;
		
		//for clearing to the right of the cell in the same row
		if (cell != BoardCell.EMPTY)
		{
			while (colIndex != board[0].length - 1 && (increment + colIndex) < 
					board[0].length && board[rowIndex][colIndex + increment] == cell)
			{	
				board[rowIndex][colIndex + increment] = BoardCell.EMPTY;
				increment++;
				score++;//increases the score by 1.
			}
			increment = 1;//resetting increment
				
		//for clearing to the left of the cell in the same row
			while (colIndex != 0 && (colIndex - increment) >= 0 && 
		      board[rowIndex][colIndex - increment] == cell)
			{	
				board[rowIndex][colIndex - increment] = BoardCell.EMPTY;
				increment++;
				score++;
			}
			increment = 1;
				
		//for clearing below the cell in the same column
			while (rowIndex != board.length - 1 && (increment + rowIndex) < 
				board.length && board[rowIndex + increment][colIndex] == cell)
			{
				board[rowIndex + increment][colIndex]= BoardCell.EMPTY;
				increment++;
				score++;
			}
			increment = 1;
				
		//for clearing above the cell in the same column
			while (rowIndex != 0 && (rowIndex - increment) >= 0 && 
					board[rowIndex - increment][colIndex] == cell)
			{
				board[rowIndex - increment][colIndex] = BoardCell.EMPTY;
				increment++;
				score++;
			}
			increment = 1;
				
		// for clearing the left to right diagonal above the cell
			while (rowIndex != 0 && colIndex != 0 && (rowIndex - increment) >= 0
		        && (colIndex - increment) >= 0 && board[rowIndex - increment]
		       [colIndex - increment] == cell)
			{
				board[rowIndex - increment][colIndex - increment] = BoardCell.EMPTY;
				increment++;
				score++;
			}
			increment = 1;
			
		// for clearing the left to right diagonal below the cell
		while (rowIndex != board.length - 1 && colIndex != board[0].length - 1 &&
		(rowIndex + increment) < board.length && (colIndex + increment) < board[0].length
		&& board[rowIndex + increment][colIndex + increment] == cell)
		{
			board[rowIndex + increment][colIndex + increment] = BoardCell.EMPTY;
			increment++;
			score++;
		}
		increment = 1;
		
		//for clearing the right to left diagonal above the cell
		while (rowIndex != 0 && colIndex != board[0].length - 1 && (rowIndex - 
		increment) >= 0 && (colIndex + increment) < board[0].length &&
		board[rowIndex - increment][colIndex + increment] == cell)
		{
			board[rowIndex - increment][colIndex + increment] = BoardCell.EMPTY;
			increment++;
			score++;
		}	
		increment = 1;
			
		//for clearing the right to left diagonal below the cell
		while (rowIndex != board.length - 1 && colIndex != 0 && (rowIndex+
		increment) < board.length && (colIndex - increment) >= 0 && 
		board[rowIndex + increment][colIndex - increment] == cell)
		{
			board[rowIndex + increment][colIndex - increment] = BoardCell.EMPTY;
			increment++;
			score++;
		}
			
		board[rowIndex][colIndex] = BoardCell.EMPTY;
		score++;
	}
			       
		storageArray = copyArray(board); 
		index= lastNonEmptyIndex();
		 	       
		//collapsing rows 
		while (containsEmpty(board, index) == true) 
		{
			if (compareArray(board[j]) == true) 
			{ 
				for (int k = j; k < index; k++) 
				{       board[k]=board[k + 1]; 
						board[k + 1] = storageArray[k]; 
						storageArray = copyArray(board); 
				} 
				j = 0; 
			} 
			index = lastNonEmptyIndex();
			j++; 
		} 
	}

		 	        
		
	private boolean compareArray( BoardCell[] bCell) 
	{
		boolean returnVal = false;
		int count = 0;
		
		for (int i = 0; i < bCell.length; i++)
		{
			if (bCell[i] == BoardCell.EMPTY)
			{
				count++;
			}
		}
		
		if (count == bCell.length)
		{
			returnVal = true;
		}
		return returnVal;
	}
	
	private BoardCell[][] copyArray(BoardCell[][] cellArray)
	{
		BoardCell[][] storage = new BoardCell[cellArray.length][cellArray[0].length];
		for (int i = 0; i < cellArray.length; i++)
		{
			for (int j = 0; j < cellArray[0].length; j++)
			{
				storage[i][j] = cellArray[i][j];
			}
		}
	return storage;
	}
	
	private int lastNonEmptyIndex() 
	{
		int m = board.length - 1;
		int index = 0;
		
		while (m >= 0)
		{
			if (compareArray(board[m]) == false)
			{
				index = m;
				m = -100;
			}
			m--;
		}
		return index;
		
	}
	
	private boolean containsEmpty(BoardCell[][] boardArray, int index)
	{
		boolean rv = false;
		for (int i = 0; i < index; i++)
		{ 
			if (compareArray(boardArray[i]) == true)
			{
				rv = true;
			}
		}
		return rv;
	}
}