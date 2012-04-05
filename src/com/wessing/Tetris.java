package com.wessing;

import com.wessing.TetrisView.TetrisThread;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.util.Log;

public class Tetris extends Activity 
{
	private TetrisView tetrisView;
	private TetrisThread tetrisThread;
	
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);
        tetrisView = (TetrisView) findViewById(R.id.tetris);
        tetrisThread = tetrisView.getThread();
        //Display display = getWindowManager().getDefaultDisplay();        
        if(savedInstanceState == null)
        {
        	tetrisThread.setState(TetrisThread.STATE_READY);
        }
        else
        {
        	tetrisThread.restoreState(savedInstanceState);
        }
    }
    
    public void onConfigurationChanged(Configuration newConfig) 
    {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    
    protected void onPause() 
    {
        super.onPause();
        tetrisView.getThread().pause(); // pause game when Activity pauses
    }
    
    public void onDestroy()
    {
    	super.onDestroy();
    	//System.exit(0);
    }
    
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	//if(tetrisView.getThread().getState() == TetrisThread.STATE_RUNNING)
    		tetrisView.getThread().pause();
    	return true;
    }
    
}