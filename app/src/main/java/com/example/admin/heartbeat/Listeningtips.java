package com.example.admin.heartbeat;

import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.Format;


public class Listeningtips extends ActionBarActivity {
    private TextView condition,waitTime,tiptext;
    private MediaPlayer mediaPlayer3;
    private ProgressBar bar;
    private int timecnt=10;
    private Button readybutton;
    private Thread t1 = new Thread(new Runnable() {
        public void run() {
            /////
            /// while(true){bar.setProgress((mediaPlayer1.getCurrentPosition()/30));}
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeningtips);
        condition = (TextView) findViewById(R.id.Condtextview);
        waitTime = (TextView) findViewById(R.id.waitTimeTextview);
        tiptext = (TextView) findViewById(R.id.Tipstextview);
        bar = (ProgressBar) findViewById(R.id.CorrectBar);
        readybutton =(Button) findViewById(R.id.readyButton);
        ///waitTime.setText("salam");
        ///readybutton.setVisibility(Button.INVISIBLE);
        /// ((Button) findViewById(R.id.readyButton));
        ///readybutton.setOnClickListener(this);

        Intent intent = getIntent();
        int i1=intent.getIntExtra("Id",-1);

        TypedArray ids = getResources().obtainTypedArray(R.array.hsound);
        int id1=ids.getResourceId(i1,-1);
        mediaPlayer3=MediaPlayer.create(this, id1);
        mediaPlayer3.setLooping(true);
        mediaPlayer3.start();
        TypedArray ids2 = getResources().obtainTypedArray(R.array.blwave);
        bar.setBackgroundResource(ids2.getResourceId(i1,-1));
        TypedArray ids3 = getResources().obtainTypedArray(R.array.tips);
        tiptext.setText(ids3.getString(i1));
        TypedArray ids4 = getResources().obtainTypedArray(R.array.cond);
        condition.setText(ids4.getString(i1));


        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                timecnt--;
                waitTime.setText(""+timecnt);
            }

            @Override
            public void onFinish() {
                timecnt--;
                waitTime.setText(""+timecnt);
                readybutton.setVisibility(Button.VISIBLE);
            }
        }.start();

    }

    public void readyfunc(View view)
    {
        mediaPlayer3.stop();
        onStop();
        finish();

    }

    @Override
    public void onBackPressed()
    {

        // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }

    @Override
    protected void onStop() {
        super.onStop();
        ///mediaPlayer1.stop();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar, menu);
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

}
