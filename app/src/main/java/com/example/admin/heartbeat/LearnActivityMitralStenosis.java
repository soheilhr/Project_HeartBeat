package com.example.admin.heartbeat;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;


public class LearnActivityMitralStenosis extends ActionBarActivity {

    MediaPlayer mplayer;
    private ProgressBar soundBar;
    private int soundBarStatus;
    private Handler soundHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_activity_mitral_stenosis);
        mplayer = MediaPlayer.create(getApplicationContext(),R.raw.hs7);
        // Set up progress bar
        soundBar = (ProgressBar) findViewById(R.id.progressBar3);
        soundBar.setMax(mplayer.getDuration());
        soundBar.setProgress(0);

        // Start progress bar thread
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (mplayer.isPlaying()) {
                        // Update the progress bar
                        soundHandler.post(new Runnable() {
                            public void run() {
                                soundBar.setProgress(mplayer.getCurrentPosition());
                            }
                        });
                    }
                }
            }
        }).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learn_activity_mitral_stenosis, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        if( mplayer.isPlaying() ) {
            mplayer.stop();
        }
        super.onPause();
    }

    public void onButtonClickPlay(View view){
        if (mplayer.isPlaying()){
            mplayer.stop();
            mplayer.prepareAsync();
            ImageButton b = (ImageButton)view;
            b.setBackgroundResource(R.drawable.playicon);
        }
        else {
            mplayer.start();
            mplayer.setLooping(true);
            ImageButton b = (ImageButton) view;
            b.setBackgroundResource(R.drawable.pauseicon);
        }
    }
}
