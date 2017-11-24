/*
 * Copyright(C) 2014.Haichen Xin. All Rights Reserved.
 * Author: Haichen Xin
 */

package com.chen.blinkbutton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;

public class BlinkButtonBySwingTimer extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BlinkTask task_ = null;
    private long delay_ = 0;
    private Color background_ = null;
    int i = 0;
    Timer timer;

    public BlinkButtonBySwingTimer(String txt, long delay, Color background) {
        delay_ = delay;
        background_ = background;
        this.setText(txt);
        task_ = new BlinkTask(this, delay_, background_, this.getBackground());
    }

    class BlinkTask {
        private JButton button_ = null;
        private long delay_;
        private int i = 0;
        private Color background_ = null;
        private Color originalBackground_ = null;

        public BlinkTask(JButton button, long delay, final Color background, final Color originalBackground) {
            button_ = button;
            delay_ = delay;
            background_ = background;
            originalBackground_ = originalBackground;

            timer = new Timer((int) delay_, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (button_.isEnabled()) {
                        i++;
                        if (i % 2 == 0) {
                            button_.setBackground(background_);
                        } else {
                            button_.setBackground(originalBackground_);
                        }
                    }
                }
            });

            timer.start();
        }
    }

    public void stopBlink() {
        if (timer != null) {
            timer.stop();
        }
    }

    public void reStartBlink() {
        if (timer != null) {
            timer.restart();
        }

    }
}
