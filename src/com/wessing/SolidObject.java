package com.wessing;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class SolidObject 
{
	protected int _x = 0;
	protected int _y = 0;
	protected int _height;
	protected int _width;
	protected Paint _paint;
	private static int DEFAULT_COLOR = Color.WHITE;
	
	public SolidObject(int width, int height)
	{
		_width = width;
		_height =height;
		_paint = new Paint();
		_paint.setColor(DEFAULT_COLOR);
	}
	
	public SolidObject(int width, int height, Paint paint)
	{
		_width = width;
		_height = height;
		_paint = paint;
	}
	
	public SolidObject(int x, int y, int width, int height, Paint paint)
	{
		this(width, height, paint);
		_x = x;
		_y = y;
	}
	
	public int x()
	{
		return _x;
	}
	
	public int y()
	{
		return _y;
	}
	
	public int height()
	{
		return _height;
	}
	
	public int width()
	{
		return _width;
	}
	
	public int left()
	{
		return _x;
	}
	
	public int right()
	{
		return _x + _width;
	}
	
	public int top()
	{
		return _y;
	}
	
	public int bottom()
	{
		return _y + _height;
	}
	
	public Paint paint()
	{
		return _paint;
	}
	
	public void draw(Canvas c)
	{
		c.drawRect(_x, _y, right(), bottom(), _paint);
	}
}
