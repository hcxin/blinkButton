/*
 * Copyright(C) 2014.Haichen Xin. All Rights Reserved.
 * Author: Haichen Xin
 */

package com.chen.blinkbutton;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class BlinkButtonByUtilTimer extends JButton {
    Timer timer;
    boolean finish = false;
    private long delay = 0;
    private Color background = null;
    private Color originalBackground = null;
    int i = 0;
    private JButton button_ = null;

    public BlinkButtonByUtilTimer(String txt, long delay, Color background) {
        button_ = this;
        this.delay = delay;
        this.background = background;
        this.setText(txt);
        originalBackground = this.getBackground();
        timer = new Timer();
        timer.schedule(new MyTimerTask(), 0, this.delay);
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            if (isFinish()) {
                if (timer != null) {
                    timer.cancel();
                }
            }
            
            if (button_.isEnabled() && !isFinish()) {
                i++;
                if (i % 2 == 0) {
                    button_.setBackground(background);
                } else {
                    button_.setBackground(originalBackground);
                }
            }
        }
    }

    private boolean isFinish() {
        return finish;
    }

    public void stopBlink() {
        finish = true;
    }

    public void reStartBlink() {
        finish = false;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new Timer();
        timer.schedule(new MyTimerTask(), 0, delay);
    }
}