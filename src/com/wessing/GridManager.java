package com.wessing;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GridManager 
{
	private static int[][] grid;
	private static boolean gridOverflow;
	

	public static void initialize()
	{
		grid = new int[12][22];
		for(int i = 0; i < GridManager.grid.length; i++)
		{	
			for(int j = 0; j < grid[i].length; j++)
			{	
				if(j == grid[i].length - 1)
					GridManager.grid[i][j] = 1;
				else
					GridManager.grid[i][j] = 0;
				if(i == 0 || i == GridManager.grid.length - 1)
					GridManager.grid[i][j] = 1;
			}
		}
	}
	
	/**
	 * Will attempt to move a piece.
	 * @return success or fail
	 */
	public static boolean moveTry(Piece piece, int dx, int dy)
	{
		for(Block block: piece.blocks)
		{
			if(!canMoveBlock(block, dx, dy))
				return false;
		}
		return true;
	}
	
	public static void addBlock(Block block)
	{			
		++grid[block.x()][-block.y()];
		overFlowCheck();
	}
	
	public static void overFlowCheck()
	{
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				if(grid[i][j] > 1)
					gridOverflow = true;
			}
		}
	}
	
	public static void removeBlock(Block block)
	{
		--grid[block.x()][-block.y()];
	}
	
	public static void forceMoveBlock(Block block, int dx , int dy)
	{
		--grid[block.x()][-block.y()];
		block.move(dx, dy);
		++grid[block.x()][-block.y()];
	}
	
	public static boolean canMoveBlock(Block block, int dx, int dy)
	{
		if(block.x() + dx >= grid.length || -block.y() - dy >= grid[0].length
				|| block.x() + dx < 0 || -block.y() - dy < 0)
				return false; //TODO examine this effect
		else if(grid[block.x() + dx][-block.y() - dy] != 0)
		{
				return false;
		}
		return true;
	}
	
	
	public static void drawTestGrid(Canvas c)
	{
		Paint white = new Paint();
		white.setColor(Color.WHITE);
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				c.drawText("" + grid[i][j],
						TetrisView.TetrisThread.X_OFFSET + (i * TetrisView.TetrisThread.BLOCK_SIZE),
						TetrisView.TetrisThread.Y_OFFSET + (j * TetrisView.TetrisThread.BLOCK_SIZE),
						white);
			}
		}
	}
	
	public static int clearLines(LinkedList<Block> blocks)
	{
		int lines = 0;
		for(int j = 0; j < grid[0].length - 1; j++) // subtract 1 to ignore borders
		{
			int sum = 0;
			for(int i = 1; i < grid.length - 1; i++) //subtract 1 to ignore borders
			{	
				sum += grid[i][j];
				if(sum == 10)
				{
					clearLine(j, blocks);
					++lines;
				}
			}	
		}
		return lines;
	}
	
	private static void clearLine(int row, LinkedList<Block> blocks)
	{
		for(int i = 0; i < blocks.size(); i++)
		{
			if(-blocks.get(i).y() == row)
			{
				removeBlock(blocks.get(i));
				blocks.remove(i);
				--i;
			}
			else if(-blocks.get(i).y() < row)
			{
				forceMoveBlock(blocks.get(i), 0, -1);
			}
		}
	}
	
	public static boolean isGridOverFlowing()
	{
		return gridOverflow;
	}
	
	public static int width()
	{
		return grid.length;
	}
	
	public static int height()
	{
		return grid[0].length;
	}
}
