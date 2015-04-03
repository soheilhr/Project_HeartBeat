package com.example.admin.heartbeat;

import android.app.Application;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see com.example.admin.heartbeat.util.SystemUiHider
 */
public class GlobalClass extends Application {

    private String speed;
    private String accuracy;

    public String getSpeed() {

        return speed;
    }

    public void setSpeed(String aSpeed) {

        speed = aSpeed;
    }

}
