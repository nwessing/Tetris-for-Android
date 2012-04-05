package com.wessing;
import android.graphics.Color;


public class LPiece2 extends Piece
{
	public LPiece2(int x, int y)
	{
		super(x, y, Color.YELLOW);
		addBlock(0, 0);
		addBlock(0, 1);
		addBlock(1, 0);
		addBlock(2, 0);
		//		   10	  1		3
		//	023		2	320		2
		//	1		3			01
		
	}

	public void rotate(int dO) 
	{
		if(dO != 0)
		{
			if(getOrientation() == 1)
			{
				if(GridManager.canMoveBlock(blocks.get(0), 1, -1)
					&& GridManager.canMoveBlock(blocks.get(1), 0, -2)
					&& GridManager.canMoveBlock(blocks.get(2), 0, 0)
					&& GridManager.canMoveBlock(blocks.get(3), -1, 1))
				{
					blocks.get(0).move(1, -1);
					blocks.get(1).move(0, -2);
					blocks.get(2).move(0, 0);
					blocks.get(3).move(-1, 1);
					setOrientation(2);
				}
			}
			else if(getOrientation() == 2)
			{
				if(GridManager.canMoveBlock(blocks.get(0), 1, 1)
					&& GridManager.canMoveBlock(blocks.get(1), 2, 0)
					&& GridManager.canMoveBlock(blocks.get(2), 0, 0)
					&& GridManager.canMoveBlock(blocks.get(3), -1, -1))
				{
					blocks.get(0).move(1, 1);
					blocks.get(1).move(2, 0);
					blocks.get(2).move(0, 0);
					blocks.get(3).move(-1, -1);
					setOrientation(3);
				}
			}
			else if(getOrientation() == 3)
			{
				if(GridManager.canMoveBlock(blocks.get(0), -1, 1)
					&& GridManager.canMoveBlock(blocks.get(1), 0, 2)
					&& GridManager.canMoveBlock(blocks.get(2), 0, 0)
					&& GridManager.canMoveBlock(blocks.get(3), 1, -1))
				{
					blocks.get(0).move(-1, 1);
					blocks.get(1).move(0, 2);
					blocks.get(2).move(0, 0);
					blocks.get(3).move(1, -1);
					setOrientation(4);
				}
			}
			else if(getOrientation() == 4 )//&& collisionCheck(-1, grid))
			{
				if(GridManager.canMoveBlock(blocks.get(0), -1, -1)
					&& GridManager.canMoveBlock(blocks.get(1), -2, 0)
					&& GridManager.canMoveBlock(blocks.get(2), 0, 0)
					&& GridManager.canMoveBlock(blocks.get(3), 1, 1))
				{
					blocks.get(0).move(-1, -1);
					blocks.get(1).move(-2, 0);
					blocks.get(2).move(0, 0);
					blocks.get(3).move(1, 1);
					setOrientation(1);
				}
			}
			
		}
		
	}
	
}
