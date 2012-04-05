package com.wessing;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;

public abstract class Piece
{
	public List<Block> blocks;
	private int color;
	private int orientation = 1;
	private int _x;
	private int _y; 
	private boolean _hasReachedBottom = false;
	
	public abstract void rotate(int dO);
	
	public Piece(int x, int y, int color)
	{
		blocks = new LinkedList<Block>();
		this._x = x;
		this._y = y;
		this.color = color;
	}
	
	public int getOrientation()
	{
		return orientation;
	}
	
	public void setOrientation(int dO)
	{
		orientation = dO;
	}
	
	/**
	 * Add new block with regard to location of piece.
	 * @param dx distance x
	 * @param dy distance y
	 */
	public void addBlock(int dx, int dy)
	{
		blocks.add(new Block(_x + dx, _y + dy, color));
	}
	
	public void move(int dx, int dy)
	{
		if(GridManager.moveTry(this, dx, dy))
		{
			_x += dx;
			_y += dy;
			for(Block block: blocks)
			{
				block.move(dx, dy);
			}
			return;
		}
		else if(dy < 0)
		{
			_hasReachedBottom = true;
		}
	}
	
	public boolean hasReachedBottom()
	{
		return _hasReachedBottom;
	}
	
	public void drawPiece(Canvas c)
	{
		for(Block block: blocks)
			block.drawBlock(c);
	}
}
