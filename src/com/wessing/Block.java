package com.wessing;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Block extends SolidObject
{
	
	public Block(int x, int y, int color)
	{
		super(TetrisView.TetrisThread.BLOCK_SIZE, TetrisView.TetrisThread.BLOCK_SIZE);
		this._x = x;
		this._y = y;
		Paint paint = new Paint();
		paint.setColor(color);
		_paint = paint;
	}
	
	public Block(Block block)
	{
		super(TetrisView.TetrisThread.BLOCK_SIZE, TetrisView.TetrisThread.BLOCK_SIZE);
		_x = block.x();
		_y = block.y();
		_paint = block.paint();
	}
	
	public void move(int dx, int dy)
	{
		this._x += dx;
		this._y += dy;
	}
	
	public void drawBlock(Canvas c)
	{
		c.drawRect(TetrisView.TetrisThread.X_OFFSET + (_x * TetrisView.TetrisThread.BLOCK_SIZE), 
				TetrisView.TetrisThread.Y_OFFSET - (_y * TetrisView.TetrisThread.BLOCK_SIZE), 
				TetrisView.TetrisThread.X_OFFSET + (_x * TetrisView.TetrisThread.BLOCK_SIZE) + _width, 
				TetrisView.TetrisThread.Y_OFFSET - (_y * TetrisView.TetrisThread.BLOCK_SIZE) + _height, 
				_paint);
		/* DRAW EDGES
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 25, 2);
		g.fillRect(x, y, 2, 25);
		g.fillRect(x + 23, y, 2, 25);
		g.fillRect(x, y + 23, 25, 2);
		*/
	}

}
