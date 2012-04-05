package com.wessing;
import android.graphics.Color;


public class ZZPiece2 extends Piece
{
	public ZZPiece2(int x, int y)
	{
		super(x, y, Color.MAGENTA);
		addBlock(0, 0);
		addBlock(1, 0);
		addBlock(1, -1);
		addBlock(2, -1);
		//			3
		//   23		21
		//  01		 0
 	}
	
	public void rotate(int dO)
	{
		if(dO != 0)
		{	
			if(super.getOrientation() == 1)
			{
				if(GridManager.canMoveBlock(blocks.get(0), 2, 0)
					&& GridManager.canMoveBlock(blocks.get(1), 1, -1)
					&& GridManager.canMoveBlock(blocks.get(2), 0, 0)
					&& GridManager.canMoveBlock(blocks.get(3), -1, -1))
				{
					blocks.get(0).move(2, 0);
					blocks.get(1).move(1, -1);
					blocks.get(2).move(0 ,0);
					blocks.get(3).move(-1, -1);
					setOrientation(2);
				}
			}
			else if(getOrientation() == 2)
			{
				if(GridManager.canMoveBlock(blocks.get(0), -2, 0)
					&& GridManager.canMoveBlock(blocks.get(1), -1, 1)
					&& GridManager.canMoveBlock(blocks.get(2), 0, 0)
					&& GridManager.canMoveBlock(blocks.get(3), 1, 1))
				{
					blocks.get(0).move(-2, 0);
					blocks.get(1).move(-1, 1);
					blocks.get(2).move(0, 0);
					blocks.get(3).move(1, 1);
					setOrientation(1);
				}
			}
		}
			
	}
}
