package com.example.admin.heartbeat;

import com.example.admin.heartbeat.SettingsActivity;
import com.example.admin.heartbeat.util.SystemUiHider;


import android.annotation.TargetApi;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.util.Log;
/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class Mainpage extends ActionBarActivity {
    double[] measures= new double[]{0.5, 0.5, 0.5, 0.5, 0.5, 0.5};
    radarGraph radar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mainpage);
        ((MyApplication) this.getApplication()).setMetrics(measures);
    }

    @Override
    protected void onStart() {
        super.onStart();
        double [] measures = ((MyApplication) getApplication()).getMetrics();
        for (int i=0; i<measures.length; i++)
            Log.i("TestMeasureOnStart",Integer.toString(i) + ":" + measures[i]);
        radar = (radarGraph) findViewById(R.id.radar_graph);
        radar.setScore(measures);
    }

    @Override
    protected void onResume() {
        super.onResume();
        double [] measures = ((MyApplication) getApplication()).getMetrics();
        for (int i=0; i<measures.length; i++)
            Log.i("TestMeasureOnResume",Integer.toString(i) + ":" + measures[i]);
        radar = (radarGraph) findViewById(R.id.radar_graph);
        radar.setScore(measures);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) { // Open settings window
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_exit){ // Close app by returning to Home screen
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onPracticeButtonClick(View view){

        // Do something in response to button
        Intent intent = new Intent(this, Practice.class);
        //((MyApplication) this.getApplication()).setMetrics(measures);
        startActivity(intent);
        view.startAnimation(buttonAnimation());

    }

    public void onBeatButtonClick(View view){
        // Do something in response to button
        Intent intent = new Intent(this, Game1.class);
        //((MyApplication) this.getApplication()).setMetrics(measures);
        startActivity(intent);
        view.startAnimation(buttonAnimation());
    }

    public void onLearnButtonClick(View view){
        // Do something in response to button
        Intent intent = new Intent(this, LearnActivityBasics.class);
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        view.startAnimation(buttonAnimation());
    }

    private Animation buttonAnimation(){
        Animation buttonAnim = new AlphaAnimation(0.2f,0.2f);
        buttonAnim.setDuration(50);
        buttonAnim.setFillEnabled(true);
        buttonAnim.setFillAfter(false);
        return buttonAnim;
    }
}

