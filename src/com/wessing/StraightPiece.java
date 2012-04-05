package com.wessing;
import android.graphics.Color;


public class StraightPiece extends Piece
{
	public StraightPiece(int x, int y)
	{
		super(x, y, Color.rgb(255,165,0)); //ORANGE
		addBlock(0, 0);
		addBlock(0, -1);
		addBlock(0, -2);
		addBlock(0, -3);
		//	3
		//	2		0123
		//	1
		//	0			
	}

	public void rotate(int dO)
	{
		if(dO != 0)
		{
			if(getOrientation() == 1)
			{
				if(GridManager.canMoveBlock(blocks.get(0), -1, -2)
					&& GridManager.canMoveBlock(blocks.get(1), 0, -1)
					&& GridManager.canMoveBlock(blocks.get(2), 1, 0)
					&& GridManager.canMoveBlock(blocks.get(3), 2, 1))
				{
					blocks.get(0).move(-1, -2);
					blocks.get(1).move(0, -1);
					blocks.get(2).move(1, 0);
					blocks.get(3).move(2, 1);
					setOrientation(2);
				}
			}
			
			else if(getOrientation() == 2)
			{
				if(GridManager.canMoveBlock(blocks.get(0), 1, 2)
					&& GridManager.canMoveBlock(blocks.get(1), 0, 1)
					&& GridManager.canMoveBlock(blocks.get(2), -1, 0)
					&& GridManager.canMoveBlock(blocks.get(2), -2, -1))
				{
					blocks.get(0).move(1, 2);
					blocks.get(1).move(0, 1);
					blocks.get(2).move(-1, 0);
					blocks.get(3).move(-2, -1);
					setOrientation(1);
				}
			}
		}
		
	}
}
