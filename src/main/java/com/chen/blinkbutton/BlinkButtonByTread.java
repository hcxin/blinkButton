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
    private long delay = 0;
    private Color background = null;
    private Color originalBackground = null;
    int i = 0;
    private JButton button = null;
    Thread t;

    public BlinkButtonByTread(String txt, long delay, Color background) {
        button = this;
        this.delay = delay;
        this.background = background;
        this.setText(txt);
        originalBackground = this.getBackground();
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
        while (button.isEnabled() && !finish) {
            i++;
            if (i % 2 == 0) {
                button.setBackground(background);
            } else {
                button.setBackground(originalBackground);
            }
            try {
                Thread.sleep(delay);
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
