package com.example.admin.heartbeat;

import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class LearnActivityBasics extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_activity_basics);
        this.populateButtons();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learn_activity_basics, menu);
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
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout_basics);
        String[] optionArray = getResources().getStringArray(R.array.string_array_learn_basics);

        for(int i=0;i<optionArray.length;i++) {
            Button b = new Button(this);
            //b.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.icon_size_rect_w),(int) getResources().getDimension(R.dimen.icon_size_rect_h));
            p.gravity = Gravity.CENTER_HORIZONTAL;
            b.setLayoutParams(p);
            b.setText(optionArray[i]);
            b.setTextColor(getResources().getColor(R.color.white));
            b.setBackgroundResource(R.drawable.blankicon);
            b.setId(i);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickOption(v);
                }
            });

            layout.addView(b);
        }
    }
    private void onClickOption(View view) {
        // Do something in response to button
        int id = view.getId();
        if (id == 0) {
            Intent intent = new Intent(this, LearnActivityCardiacCycle.class);
            startActivity(intent);
        }
        else if (id == 1) {
            Intent intent = new Intent(this, LearnActivityS1.class);
            startActivity(intent);
        }
        else if (id == 2) {
            Intent intent = new Intent(this, LearnActivityS2.class);
            startActivity(intent);
        }
        else if (id == 3) {
            Intent intent = new Intent(this, LearnActivityS3.class);
            startActivity(intent);
        }
        else if (id == 4) {
            Intent intent = new Intent(this, LearnActivityS4.class);
            startActivity(intent);
        }
        else if (id == 5) {
            Intent intent = new Intent(this, LearnActivityAorticStenosis.class);
            startActivity(intent);
        }
        else if (id == 6) {
            Intent intent = new Intent(this, LearnActivityMitralStenosis.class);
            startActivity(intent);
        }
        else if (id == 7) {
            Intent intent = new Intent(this, LearnActivityAorticRegurgitation.class);
            startActivity(intent);
        }
        else if (id == 8) {
            Intent intent = new Intent(this, LearnActivityMitralRegurgitation.class);
            startActivity(intent);
        }

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
