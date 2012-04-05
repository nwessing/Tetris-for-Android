package com.wessing;
import android.graphics.Color;


public class SquarePiece extends Piece
{
	public SquarePiece(int x, int y)
	{
		super(x, y, Color.GREEN);
		addBlock(0, 0);
		addBlock(1, 0);
		addBlock(0, -1);
		addBlock(1, -1);
	}

	public void rotate(int dO)
	{
		
		
	}
}
