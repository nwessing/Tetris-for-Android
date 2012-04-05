package com.wessing;
import android.graphics.Color;


public class LPiece1 extends Piece
{
	public LPiece1(int x, int y)
	{
		super(x, y, Color.rgb(255, 20, 147)); //PINK
		addBlock(0, 0);
		addBlock(1, 0);
		addBlock(2, 0);
		addBlock(2, 1);
		//			 0	3	23
		//	012		 1	210	1
		//	  3		32		0
		
	}

	public void rotate(int dO) 
	{
		if(dO != 0)
		{
			if(getOrientation() == 1)
			{
				if(GridManager.canMoveBlock(blocks.get(0), 1, -1)
					&& GridManager.canMoveBlock(blocks.get(1), 0, 0)
					&& GridManager.canMoveBlock(blocks.get(2), -1, 1)
					&& GridManager.canMoveBlock(blocks.get(3), -2, 0))
				{
					blocks.get(0).move(1, -1);
					blocks.get(1).move(0, 0);
					blocks.get(2).move(-1, 1);
					blocks.get(3).move(-2, 0);
					setOrientation(2);
				}
			}
			else if(getOrientation() == 2 )
			{
				if(GridManager.canMoveBlock(blocks.get(0), 1, 1)
					&& GridManager.canMoveBlock(blocks.get(1), 0, 0)
					&& GridManager.canMoveBlock(blocks.get(2), -1, -1)
					&& GridManager.canMoveBlock(blocks.get(3), 0, -2))
				{
					blocks.get(0).move(1, 1);
					blocks.get(1).move(0, 0);
					blocks.get(2).move(-1, -1);
					blocks.get(3).move(0, -2);
					setOrientation(3);
				}
			}
			else if(getOrientation() == 3)
			{
				if(GridManager.canMoveBlock(blocks.get(0), -1, 1)
					&& GridManager.canMoveBlock(blocks.get(1), 0, 0)
					&& GridManager.canMoveBlock(blocks.get(2), 1, -1)
					&& GridManager.canMoveBlock(blocks.get(3), 2, 0))
				{
					blocks.get(0).move(-1, 1);
					blocks.get(1).move(0, 0);
					blocks.get(2).move(1, -1);
					blocks.get(3).move(2, 0);
					setOrientation(4);
				}
			}
			else if(getOrientation() == 4)
			{
				if(GridManager.canMoveBlock(blocks.get(0), -1, -1)
					&& GridManager.canMoveBlock(blocks.get(1), 0, 0)
					&& GridManager.canMoveBlock(blocks.get(2), 1, 1)
					&& GridManager.canMoveBlock(blocks.get(3), 0, 2))
				{
					blocks.get(0).move(-1, -1);
					blocks.get(1).move(0, 0);
					blocks.get(2).move(1, 1);
					blocks.get(3).move(0, 2);
					setOrientation(1);
				}
			}
		}
		
	}
}
