package com.example.admin.heartbeat;

import com.example.admin.heartbeat.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.media.MediaPlayer.*;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class Practice extends ActionBarActivity implements View.OnClickListener  {
    private TextView scoreBlue,scoreRed,condition,remTime,redtext,timetext,scoretext;
    private ProgressBar bluebar,redbar;
    private int score1,score2,correctbutton,level,cnt,cnt1,cnt2;
    private MediaPlayer mediaPlayer1,mediaPlayer2;
    private double[] measures,tmpmeasures;
    private int[] currentcondition=new int[]{-1,-2,-3};
    private CountDownTimer ti1,ti2;
    private Boolean flag1=false,mp1ready=false,mp2ready=false;



    private Thread t1 = new Thread(new Runnable() {
        public void run() {
            /////
            while(true){bluebar.setProgress((mediaPlayer1.getCurrentPosition()/30));}
        }
    });
    private Thread t2 = new Thread(new Runnable() {
        public void run() {
            /////
            while(true){redbar.setProgress((mediaPlayer2.getCurrentPosition()/30));}
        }
    });

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        scoreBlue = (TextView) findViewById(R.id.blueScore);
        scoretext = (TextView) findViewById(R.id.textView8);
        scoreRed = (TextView) findViewById(R.id.redScore);
        redtext=(TextView) findViewById(R.id.textView9);
        remTime = (TextView) findViewById(R.id.textTime);
        timetext = (TextView) findViewById(R.id.timetextView);
        condition = (TextView) findViewById(R.id.textCondition);
        bluebar = (ProgressBar) findViewById(R.id.progressBarBlue);
        redbar = (ProgressBar) findViewById(R.id.progressBarRed);

        scoretext.setText("Winning Streak");




        ((ImageButton) findViewById(R.id.redButton)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.blueButton)).setOnClickListener(this);
        tmpmeasures=new double[]{0,0,0,0,0,0};
        getcondition(1);



        score1=0;
        score2=0;
        cnt1=0;
        cnt2=0;
        level=1;

        scoreRed.setVisibility(View.INVISIBLE);
        redtext.setVisibility(View.INVISIBLE);
        remTime.setVisibility(View.INVISIBLE);
        timetext.setVisibility(View.INVISIBLE);



        ti1= new CountDownTimer(100000, 1000) {
            public void onTick(long millisUntilFinished) {

                remTime.setText(""+millisUntilFinished / 1000);
                if(flag1) {
                    cnt1 = (cnt1 + 1) % 4;
                    //remTime.setText(""+millisUntilFinished / 1000);
                    ///scoreRed.setText("222");
                    if (cnt1 == 0) {
                        if (mediaPlayer1.isPlaying()) {
                            mediaPlayer1.stop();
                        }
                        if (mediaPlayer2.isPlaying()) {
                            mediaPlayer2.stop();
                        }

                        if (cnt2 == 0)
                        {
                            if(mp1ready) {
                                mediaPlayer1.start();

                            }
                        }
                        else
                        {
                            if(mp2ready)mediaPlayer2.start();


                        }
                        cnt2 = (cnt2 + 1) % 2;
                    }
                }
                else
                {
                    if (mediaPlayer1.isPlaying()) {
                        mediaPlayer1.stop();
                    }
                    if (mediaPlayer2.isPlaying()) {
                        mediaPlayer2.stop();
                    }
                }
            }



            public void onFinish() {
                // set
                ///measures = ((MyApplication) getApplication()).getMetrics();
                ///calculatemeasures();
                ///((MyApplication) getApplication()).setMetrics(measures);
                /// finish();
                ti1.start();
            }
        };

        ti1.start();
        flag1=true;
        t1.start();
        t2.start();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(!flag1)flag1=true;
    }

    /**
     * Update timer on seekbar
     * */


    @Override
    protected void onResume() {
        super.onResume();
        if(!flag1)
        {
            getcondition(1);
            flag1=true;

        }




    }

    @Override
    protected void onPause() {
        super.onPause();

        flag1=false;
    }

    /**
     * Background Runnable thread
     * */





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

    private void getcondition(int level) {
        Random r = new Random();
        int i1 = (r.nextInt(8-0)+0);
        int i2=(r.nextInt(8-0)+0);
        while(i2==i1)i2=(r.nextInt(9-1));

        TypedArray ids = getResources().obtainTypedArray(R.array.hsound);
        int id1,id2;
        id1=ids.getResourceId(i1,-1);
        id2=ids.getResourceId(i2,-1);
        currentcondition[1]=i1;
        currentcondition[2]=i2;
        mp1ready=false;
        mediaPlayer1=MediaPlayer.create(this, id1);
        mediaPlayer1.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp1ready=true;

            }
        });



        mp2ready=false;
        mediaPlayer2=MediaPlayer.create(this, id2);
        mediaPlayer2.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp2ready=true;

            }
        });




        TypedArray ids2 = getResources().obtainTypedArray(R.array.bwave);
        TypedArray ids3 = getResources().obtainTypedArray(R.array.rwave);
        bluebar.setBackgroundResource(ids2.getResourceId(i1,-1));
        redbar.setBackgroundResource(ids3.getResourceId(i2,-1));
        correctbutton=(r.nextInt(3-1)+1);

        TypedArray ids4 = getResources().obtainTypedArray(R.array.cond);
        /// idtmp=ids3.getString(i1);//getResourceId(i1,-1);
        if(correctbutton==1)condition.setText(ids4.getString(i1));
        else condition.setText(ids4.getString(i2));

        //condition =
        //redbar =
        //bluebar =
        //correctbutton =

    }

    void gotcorrect(int choice){
        score1 = score1+1;
        TypedArray tmpids = getResources().obtainTypedArray(R.array.meas);
        tmpmeasures[tmpids.getInt(currentcondition[choice],-1)]=(tmpmeasures[tmpids.getInt(currentcondition[choice],-1)]+1);
        tmpmeasures[5]=tmpmeasures[5]+1;
        scoreBlue.setText(String.format("%d",score1));
        getcondition(level);
    }

    void gotfalse(int choice){
        score1=0;
        TypedArray tmpids = getResources().obtainTypedArray(R.array.meas);
        ///condition.setText(""+currentcondition[choice]+"s"+choice+"d"+correctbutton);
        tmpmeasures[tmpids.getInt(currentcondition[choice],-1)]=(tmpmeasures[tmpids.getInt(currentcondition[choice],-1)]-1);
        tmpmeasures[5]=tmpmeasures[5]+1;
        Intent intent = new Intent(this, Listeningtips.class);
        intent.putExtra("Id",currentcondition[choice]);
        startActivity(intent);
        scoreBlue.setText(String.format("%d",score1));
        flag1=false;
        Vibrator v = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(500);
        getcondition(level);
    }
    void calculatemeasures()
    {
        int tmpsign,tmpsum=0;
        double tmpnum;
        tmpnum=tmpmeasures[5];
        if(tmpnum==0)return;
        for(int i=1;i<5;i++)
        {
            if((tmpmeasures[i]/tmpnum)<measures[i])tmpsign=-1;
            else tmpsign=1;
            measures[i]=measures[i]+0.1*tmpsign;
            tmpsum=tmpsum+(int)tmpmeasures[i];
        }
        if((tmpsum/tmpnum)<measures[5])tmpsign=-1;
        else tmpsign=1;
        measures[5]=measures[5]+0.1*tmpsign;
        if((tmpnum/20)<measures[6])tmpsign=-1;
        else tmpsign=1;
        measures[6]=measures[6]+0.1*tmpsign;
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(buttonAnimation());
        int selectedButton=0;

        switch(v.getId()) {
            case R.id.redButton:
                selectedButton=2;


                break;
            case R.id.blueButton:
                selectedButton=1;

                ///chronometer.stop();
                break;
        }
        if(selectedButton==correctbutton)
        {
            gotcorrect(correctbutton);
        }else {
            gotfalse(correctbutton);
        }
        ///else ;

        //int i1 = (r.nextInt(8-0));
        //score1=i1;

    }
    private Animation buttonAnimation(){
        Animation buttonAnim = new AlphaAnimation(0.2f,0.2f);
        buttonAnim.setDuration(50);
        buttonAnim.setFillEnabled(true);
        buttonAnim.setFillAfter(false);
        return buttonAnim;
    }
}
