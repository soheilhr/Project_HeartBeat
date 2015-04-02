package com.example.admin.heartbeat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;


public class LearnHomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_home);
    }

    // Actionbar menu
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

    private Animation buttonAnimation(){
        Animation buttonAnim = new AlphaAnimation(0.2f,0.2f);
        buttonAnim.setDuration(50);
        buttonAnim.setFillEnabled(true);
        buttonAnim.setFillAfter(false);
        return buttonAnim;
    }
    // Button handles
    public void onBasicsButtonClick(View view){
        // Do something in response to button
        Intent intent = new Intent(this, LearnActivityBasics.class);
        startActivity(intent);
        view.startAnimation(buttonAnimation());
    }
    // Button handles
    public void onMurmursButtonClick(View view){
        // Do something in response to button
        Intent intent = new Intent(this, LearnActivityMurmurs.class);
        startActivity(intent);
        view.startAnimation(buttonAnimation());
    }

}
