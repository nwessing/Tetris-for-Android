package com.wessing;
import java.util.LinkedList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TetrisView extends SurfaceView implements SurfaceHolder.Callback
{
	class TetrisThread extends Thread
	{
		//State tracking
		public static final int STATE_LOSE = 1;
		public static final int STATE_PAUSE = 2;
		public static final int STATE_READY = 3;
		public static final int STATE_RUNNING = 4;
		public static final int STATE_WIN = 5;
		
		//Maintenance variables
		private Context context;
		private SurfaceHolder surfaceHolder;
		private Handler handler;
		private int mode;
		private boolean run;
		
		//Game variables
		private static final int WALL_THICKNESS = 5;
		private static final int DEFAULT_START_X = 2; //TODO
		private static final int DEFAULT_START_Y = -1; //TODO
		public static final int X_OFFSET = 40;
		public static final int Y_OFFSET = 5;
		public static final int BLOCK_SIZE = 25;
		private LinkedList<Block> blocks = new LinkedList<Block>();
		private Piece activePiece;
		private Piece nextPiece;
		private SolidObject leftWall;
		private SolidObject rightWall;
		private SolidObject bottomWall;
		private boolean reset;
		private boolean gameOver;
		private boolean pause;
		private long timeLastForceMove = 0;
		private long timeLastTouchEvent = 0;
		private int dx = 0;
		private int dy = 0;
		private int dOrientation = 0;
		private int rate = 500;
		private int lines = 0;
		
		public TetrisThread(SurfaceHolder surfaceHolder, Context context, Handler handler)
		{
			this.surfaceHolder = surfaceHolder;
			this.context = context;
			this.handler = handler;
					
	        GridManager.initialize();
			Paint white = new Paint();
			white.setColor(Color.WHITE);
			int height = GridManager.height() - 2;
			int width = GridManager.width() - 2;
			leftWall = new SolidObject(X_OFFSET + BLOCK_SIZE - WALL_THICKNESS,
					Y_OFFSET - DEFAULT_START_Y * BLOCK_SIZE, 
					WALL_THICKNESS,
					height * BLOCK_SIZE + WALL_THICKNESS,
					white);
			bottomWall = new SolidObject(X_OFFSET + BLOCK_SIZE - WALL_THICKNESS,
					Y_OFFSET + height * BLOCK_SIZE - DEFAULT_START_Y * BLOCK_SIZE,
					(width * BLOCK_SIZE) + (2* WALL_THICKNESS),
					WALL_THICKNESS,
					white);
			rightWall = new SolidObject(X_OFFSET + (width + 1) * BLOCK_SIZE,
					Y_OFFSET - DEFAULT_START_Y * BLOCK_SIZE,
					WALL_THICKNESS,
					height * BLOCK_SIZE + WALL_THICKNESS,
					white );
			activePiece = randomPiece();
		}
		
		public void doStart()
		{
			synchronized(surfaceHolder)
			{
				setState(STATE_RUNNING);
			}
		}
		
		public void pause()
		{
			synchronized(surfaceHolder)
			{
				if(mode == STATE_RUNNING)
					setState(STATE_PAUSE);
			}
		}
		
		//TODO
		public synchronized void restoreState(Bundle bundle)
		{
			synchronized(surfaceHolder)
			{
				
			}
		}
		
		public void run()
		{
			Canvas c = null;
			while(run)
			{
				try
				{
					c = surfaceHolder.lockCanvas();
					synchronized(surfaceHolder)
					{
						if(mode == STATE_RUNNING)
							gameLogic();
						doDraw(c);
					}
				}
				finally
				{
					if(c != null)
						surfaceHolder.unlockCanvasAndPost(c);
				}
			}
		
			/*
			if(reset)
			{
				//activePiece = generatePiece();
				//nextPiece = generatePiece();		
				nextPiece.move(9, 14);
				reset = false;
			}
				
			if(!gameOver && !pause)
				;//gameLogic();
				
			*/
			//gameLogic();
			//handler.sleep(LOOP_LENGTH);	
			//time += LOOP_LENGTH;
			//Exit Game;
		}
		
		//TODO
		public Bundle saveState(Bundle map)
		{
			synchronized(surfaceHolder)
			{
				
			}
			return map;
		}
		
		public void setRunning(boolean isRunning)
		{
			run = isRunning;
		}
		
		public void setState(int state) 
		{
			synchronized(surfaceHolder)
			{
				mode = state;
			}
		}
		
		public void unpause()
		{
			synchronized(surfaceHolder)
			{
				if(mode == STATE_PAUSE)
				{
					setState(STATE_RUNNING);
					timeLastForceMove = System.currentTimeMillis() + 100;
				}
			}
			setState(STATE_RUNNING);
		}
		
		private void gameLogic()
		{
			if(GridManager.isGridOverFlowing())
			{
				setState(STATE_LOSE);
			}
			if(mode == STATE_RUNNING)
			{	
				long time = System.currentTimeMillis();
				if(time - timeLastForceMove >= rate)
				{
					dy = -1;
					timeLastForceMove = time;
				}
				if(dx != 0)
					activePiece.move(dx, 0);
				if(dy != 0)
					activePiece.move(0, dy);
				if(dOrientation != 0)
					activePiece.rotate(dOrientation);
				dx = 0;
				dy = 0;
				dOrientation = 0;
				if(activePiece.hasReachedBottom())
				{
					for(Block block: activePiece.blocks)
					{
						blocks.add(block);
						GridManager.addBlock(block);
					}
					activePiece = randomPiece();
					lines += GridManager.clearLines(blocks);
				}
			}
		}
		
	    private Piece randomPiece()
	    {
			int type = (int)(Math.random() * 7);
			switch (type)
			{
				case 0:
					return new LPiece1(DEFAULT_START_X, DEFAULT_START_Y);
				case 1:
					return new LPiece2(DEFAULT_START_X, DEFAULT_START_Y);
				case 2:
					return new SquarePiece(DEFAULT_START_X, DEFAULT_START_Y);
				case 3:
					return new StraightPiece(DEFAULT_START_X, DEFAULT_START_Y);
				case 4:
					return new TPiece(DEFAULT_START_X, DEFAULT_START_Y);
				case 5:
					return new ZZPiece1(DEFAULT_START_X, DEFAULT_START_Y);
				case 6:
					return new ZZPiece2(DEFAULT_START_X,DEFAULT_START_Y);
				default:
					return null;
					
			}
		}
	    
		public void doDraw(Canvas c)
		{
			c.drawRGB(0, 0, 0);
			if(mode == STATE_RUNNING || mode == STATE_READY)
				drawGame(c);
			if(mode == STATE_LOSE)
			{
				drawGame(c);
				drawGameOver(c);
			}
			if(mode == STATE_PAUSE)
			{
				drawPause(c);
			}
		}

		private void drawGame(Canvas c)
		{
			rightWall.draw(c);
			bottomWall.draw(c);
			leftWall.draw(c);
			activePiece.drawPiece(c);
	        for(Block b: blocks)
	        	b.drawBlock(c);
	        Paint white = new Paint();
	        white.setColor(Color.WHITE);
	        //GridManager.drawTestGrid(c);
	        c.drawText("Lines " + lines, bottomWall.x() , bottomWall.y() + WALL_THICKNESS * 4, white);
		}
		
		private void drawGameOver(Canvas c)
		{
			//c.drawRGB(0, 0, 0);
		}
		
		private void drawPause(Canvas c) 
		{
			rightWall.draw(c);
			bottomWall.draw(c);
			leftWall.draw(c);
	        Paint white = new Paint();
	        white.setColor(Color.WHITE);
	        c.drawText("Lines " + lines, bottomWall.x() , bottomWall.y() + WALL_THICKNESS * 4, white);
	        white.setTextSize(20);
	        c.drawText("Paused", bottomWall.x() + BLOCK_SIZE * 2, leftWall.y() + Y_OFFSET, white);
		}
			
		public boolean onTouch(MotionEvent event) 
		{
			synchronized(surfaceHolder)
			{
				long time = System.currentTimeMillis();
				if(time - timeLastTouchEvent >= 150)
				{
					float x = event.getX();
					float y = event.getY();
					if(x < leftWall.right() && y < bottomWall.top())
						dx = -1;
					else if(x > rightWall.left() && y < bottomWall.top())
						dx = 1;
					else if(y < bottomWall.top())
						dOrientation = 1;
					else
						dy = -1;
					timeLastTouchEvent = time;
				}
				if(mode == STATE_READY)
					doStart();
				return true;
			}
		}
	}
	
	private TetrisThread thread;
	private Context context;
	
	public TetrisView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		SurfaceHolder holder = this.getHolder();
		holder.addCallback(this);
		
		thread = new TetrisThread(holder, context, new Handler(){
			
			public void handleMessages(Message m)
			{
				//Do nothing
			}
		});
		setFocusable(true);
	}
	
	public boolean onTouchEvent(MotionEvent event)
	{
		return thread.onTouch(event);
	}
	
	public TetrisThread getThread()
	{
		return thread;
	}
	
	public void onWindowFocusChanged(boolean hasWindowFocus)
	{
		if(!hasWindowFocus)
		{
			thread.pause();
		}
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{
		// do nothing
	}
	
	public void surfaceCreated(SurfaceHolder holder)
	{
		thread.setRunning(true);
		thread.start();
		//thread.doStart();
	}
	
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		boolean retry = true;
		thread.setRunning(false);
		while(retry)
		{
			try
			{
				thread.join();
				retry = false;
			}
			catch(InterruptedException ex){ }
		}
	}
}
