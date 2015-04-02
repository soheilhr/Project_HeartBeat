package com.example.admin.heartbeat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class LearnActivityMurmurs extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_activity_murmurs);
        this.populateButtons();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learn_activity_murmurs, menu);
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
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout_murmurs);
        String[] optionArray = getResources().getStringArray(R.array.string_array_learn_murmurs);

        for(int i=0;i<optionArray.length;i++) {
            Button b = new Button(this);
            //b.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.icon_size_rect_w),(int) getResources().getDimension(R.dimen.icon_size_rect_h));
            p.gravity = Gravity.CENTER_HORIZONTAL;
            b.setLayoutParams(p);
            b.setText(optionArray[i]);
            b.setTextColor(getResources().getColor(R.color.white));
            b.setBackgroundResource(R.drawable.blankicon);


            layout.addView(b);
        }
    }

}
