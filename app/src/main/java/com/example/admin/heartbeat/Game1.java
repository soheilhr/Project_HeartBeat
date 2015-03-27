package com.example.admin.heartbeat;

import android.media.AudioManager;
import android.media.*;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;


public class Game1 extends ActionBarActivity implements View.OnClickListener {
    private TextView scoreBlue,scoreRed,condition,remTime;
    private ProgressBar bluebar,redbar;
    private int score1,score2,correctbutton,level,cnt,cnt1,cnt2;
    private MediaPlayer mediaPlayer1,mediaPlayer2;
    private class myCase{
        int correctanswer=1;
    }
    private myCase case1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        scoreBlue = (TextView) findViewById(R.id.blueScore);
        scoreRed = (TextView) findViewById(R.id.redScore);
        remTime = (TextView) findViewById(R.id.textTime);
        condition = (TextView) findViewById(R.id.textCondition);
        bluebar = (ProgressBar) findViewById(R.id.progressBarBlue);
        redbar = (ProgressBar) findViewById(R.id.progressBarRed);
        ((ImageButton) findViewById(R.id.redButton)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.blueButton)).setOnClickListener(this);
        getcondition(1);
        score1=0;
        cnt1=0;
        cnt2=0;
        level=1;
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                cnt1=(cnt1+1)%3;
                remTime.setText(""+millisUntilFinished / 1000);
                scoreRed.setText("222");
                if(cnt1==0) {
                    if (cnt2 == 0) mediaPlayer1.start();
                    else mediaPlayer2.start();
                    cnt2 = (cnt2 + 1) % 2;
                }
            }

            public void onFinish() {
                scoreRed.setText("666");
            }
        }.start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game1, menu);
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
    public void getcondition(int level){
        mediaPlayer1=MediaPlayer.create(this, R.raw.hs1);
        mediaPlayer2=MediaPlayer.create(this, R.raw.hs2);
        //condition =
        //redbar =
        //bluebar =
        //correctbutton =
    }
    @Override
    public void onClick(View v) {
        int selectedButton=0;

        switch(v.getId()) {
            case R.id.redButton:
                selectedButton=0;


                break;
            case R.id.blueButton:
                selectedButton=1;
                ///chronometer.stop();
                break;
        }
        if(selectedButton==correctbutton)
        {
            score1=score1+level*100;
            level++;
        }
        else getcondition(level);
        scoreBlue.setText(String.format("%03d",score1));
    }
}
