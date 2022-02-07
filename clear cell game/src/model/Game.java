package model;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * @author Dept of Computer Science, UMCP
 */

public abstract class Game {
	protected BoardCell[][] board;

	/**
	 * Defines a board with BoardCell.EMPTY cells.
	 * 
	 * @param maxRows
	 * @param maxCols
	 */
	public Game(int maxRows, int maxCols) 
	{
		board =  new BoardCell[maxRows][maxCols];
		
		for (int i = 0; i < board.length; i++)
		{	for (int j = 0; j < board[0].length; j++)
			{
				board[i][j] = BoardCell.EMPTY;//initializes board[][] with empty cells.
			}
		}
		
	}

	public int getMaxRows() 
	{
		return board.length;
	}

	public int getMaxCols()
	{
		return board[0].length;
	}

	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell)
	{
		board[rowIndex][colIndex] = boardCell;
	}

	public BoardCell getBoardCell(int rowIndex, int colIndex) 
	{
		return board[rowIndex][colIndex];
	}

	/**
	 * Initializes row with the specified color.
	 * @param rowIndex
	 * @param cell
	 */
	public void setRowWithColor(int rowIndex, BoardCell cell) 
	{
		for (int i = 0; i < board[0].length; i++)
		{
			board[rowIndex][i] = cell;//assigns row to specified color.
		}
	}
	
	/**
	 * Initializes column with the specified color.
	 * @param colIndex
	 * @param cell
	 */
	public void setColWithColor(int colIndex, BoardCell cell)
	{
		for (int i = 0; i < board.length; i++)
		{
			board[i][colIndex] = cell;//assigns column to specified color.
		}
	}
	
	/**
	 * Initializes the board with the specified color.
	 * @param cell
	 */
	public void setBoardWithColor(BoardCell cell) 
	{
		for (int i = 0; i < board.length; i++)
		{	for (int j = 0; j < board[0].length; j++)
			{	
				board[i][j] = cell;
			}
		}
	}	
	
	public abstract boolean isGameOver();

	public abstract int getScore();

	/**
	 * Advances the animation one step.
	 */
	public abstract void nextAnimationStep();

	/**
	 * Adjust the board state according to the current board state and the
	 * selected cell.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 */
	public abstract void processCell(int rowIndex, int colIndex);
}