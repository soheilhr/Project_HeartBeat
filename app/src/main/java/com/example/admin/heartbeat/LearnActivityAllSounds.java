package com.example.admin.heartbeat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;
import com.example.admin.heartbeat.util.TestActivity;


public class LearnActivityAllSounds extends ActionBarActivity {
    MediaPlayer mplayer;
    private int currId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_activity_all_sounds);
        currId = -1;
        populateButtons();
        Log.d("LearnAllSounds","Populated buttons");
        mplayer = MediaPlayer.create(getApplicationContext(),R.raw.hs2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learn_activity_all_sounds, menu);
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

    private void populateButtons() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout_all_sounds);
        String[] condArray = getResources().getStringArray(R.array.cond);

        for(int i=0;i<condArray.length+1;i++) {

            //Horizontal LinearLayout
            LinearLayout layout_horz = new LinearLayout(this);
            layout_horz.setOrientation(LinearLayout.HORIZONTAL);
            layout_horz.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            p2.gravity = Gravity.LEFT;
            layout_horz.setLayoutParams(p2);

            // Button
            Button b = new Button(this);
            b.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.icon_size_small),(int) getResources().getDimension(R.dimen.icon_size_small));
            p.gravity = Gravity.CENTER_HORIZONTAL;
            b.setLayoutParams(p);
            b.setBackgroundResource(R.drawable.playicon);
            b.setId(i);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickPlayButton(v);
                }
            });
            // Text
            TextView t = new TextView(this);
            if (i>0){ t.setText(condArray[i-1]);}
            else if (i==0){ t.setText("Normal");}
            //layout.addView(b);
            layout_horz.addView(b);
            layout_horz.addView(t);
            layout.addView(layout_horz);
        }
    }

    @Override
    public void onPause() {
        if( mplayer.isPlaying() ) {
            mplayer.stop();
        }
        super.onPause();
    }

    private void onClickPlayButton(View view) {
        // Do something in response to button
        int id = view.getId();
        if (mplayer.isPlaying()) {
            // Stop currently playing sound
            mplayer.stop();
            mplayer.prepareAsync();
        }
        if (id == 0) {
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.hs0);

        }
        else if (id == 1) {
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.hs1);

        }
        else if (id == 2) {
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.hs3);

        }
        else if (id == 3) {
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.hs4);

        }
        else if (id == 4) {
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.hs5);

        }
        else if (id == 5) {
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.hs6);

        }
        else if (id == 6) {
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.hs7);

        }
        else if (id == 7) {
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.hs8);

        }
        else if (id == 8) {
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.hs2);

        }
        else if (id ==9) {
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.hs2);

        }
        Log.d("LearnAllSounds","ID: "+id);
        view.startAnimation(buttonAnimation());

        // Set previously playing button to play icon
        if (currId >= 0) { // Pause sound
            String button_id = /*"button"+*/Integer.toString(currId);
            Button b = (Button) this.findViewById(getResources().getIdentifier(button_id, "id", getPackageName()));
            b.setBackgroundResource(R.drawable.playicon);
            currId = -1;
        }
        else { // Play sound
            // mplayer not playing yet, so start playing sound
            mplayer.start();
            Log.d("LearnAllSounds","Play sound");
            mplayer.setLooping(true);
            Button bPlay = (Button) view;
            bPlay.setBackgroundResource(R.drawable.pauseicon);
            currId = id;
        }

    }
    private Animation buttonAnimation(){
        Animation buttonAnim = new AlphaAnimation(0.2f,0.2f);
        buttonAnim.setDuration(50);
        buttonAnim.setFillEnabled(true);
        buttonAnim.setFillAfter(false);
        return buttonAnim;
    }
}
