/*
 * Copyright(C) 2014.Haichen Xin. All Rights Reserved.
 * Author: Haichen Xin
 */
package com.chen.blinkbutton;

import java.awt.Color;
import java.util.Timer;

import javax.swing.JButton;

public class BlinkButtonByTread extends JButton {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Timer timer;
    boolean finish = false;
    private long delay_ = 0;
    private Color background_ = null;
    private Color originalBackground_ = null;
    int i = 0;
    private JButton button_ = null;
    Thread t;

    public BlinkButtonByTread(String txt, long delay, Color background) {
        button_ = this;
        delay_ = delay;
        background_ = background;
        this.setText(txt);
        originalBackground_ = this.getBackground();
        blinkThread thread = new blinkThread();
        t = new Thread(thread);
        t.start();
    }

    class blinkThread implements Runnable {

        public void run() {
            blinkWork();
        }
    }

    private synchronized void blinkWork() {
        while (button_.isEnabled() && !finish) {
            i++;
            if (i % 2 == 0) {
                button_.setBackground(background_);
            } else {
                button_.setBackground(originalBackground_);
            }
            try {
                Thread.sleep(delay_);
            } catch (InterruptedException e) {
            }
        }

    }

    public void stopBlink() {
        finish = true;
    }

    public void reStartBlink() {
        finish = false;
        blinkThread thread = new blinkThread();
        t = new Thread(thread);
        t.start();
    }
}
