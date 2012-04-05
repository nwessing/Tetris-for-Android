package com.wessing;
import android.graphics.Color;


public class ZZPiece1 extends Piece
{
	public ZZPiece1(int x, int y)
	{
		super(x, y, Color.BLUE);
		addBlock(0, -1);
		addBlock(1, -1);
		addBlock(1, 0);
		addBlock(2, 0);	
		
		//			 0
		//	01		21
		//	 23		3
	}

	public void rotate(int dO) 
	{
		if(dO != 0)
		{	
			if(getOrientation() == 1)
			{
				if(GridManager.canMoveBlock(blocks.get(0), 2, -1)
					&& GridManager.canMoveBlock(blocks.get(1), 1, 0)
					&& GridManager.canMoveBlock(blocks.get(2), 0, -1)
					&& GridManager.canMoveBlock(blocks.get(3), -1, 0))
				{
					blocks.get(0).move(2, -1);
					blocks.get(1).move(1, 0);
					blocks.get(2).move(0, -1);
					blocks.get(3).move(-1, 0);
					setOrientation(2);
				}

			}
			else if(getOrientation() == 2)
			{
				if(GridManager.canMoveBlock(blocks.get(0), -2, 1)
					&& GridManager.canMoveBlock(blocks.get(1), -1, 0)
					&& GridManager.canMoveBlock(blocks.get(0), 0, 1)
					&& GridManager.canMoveBlock(blocks.get(3), 1, 0))
				{
					blocks.get(0).move(-2, 1);
					blocks.get(1).move(-1, 0);
					blocks.get(2).move(0, 1);
					blocks.get(3).move(1, 0);
					setOrientation(1);
				}

			}
		}
		
	}
}
