/*
 * Copyright(C) 2014.Haichen Xin. All Rights Reserved.
 * Author: Haichen Xin
 */

package com.chen.blinkbutton;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

public class BlinkButtonBySwingWorker extends JButton {

    boolean finish = false;
    private long delay_ = 0;
    private Color background_ = null;
    private Color originalBackground_ = null;
    int i = 0;
    private JButton button_ = null;
    MySwingWorker worker;

    public BlinkButtonBySwingWorker(String txt, long delay, Color background) {
        button_ = this;
        delay_ = delay;
        background_ = background;
        this.setText(txt);
        originalBackground_ = this.getBackground();
        worker = new MySwingWorker();// java version>=1.6
        worker.execute();
        System.out.println(" end ");
    }

    class MySwingWorker extends SwingWorker<Boolean, Integer> {

        @Override
        protected Boolean doInBackground() {
            int n = 0;
            while (!isFinish()) {
                n++;
                publish(n);
                
                try {
                    Thread.sleep(delay_);
                } catch (InterruptedException e) {

                }
            }
            finish = true;

            return finish;

        }

        @Override
        protected void process(List<Integer> list) {
            for (int n : list) {
                if (button_.isEnabled() && !isFinish()) {
                    if (n % 2 == 0) {
                        button_.setBackground(background_);
                    } else {
                        button_.setBackground(originalBackground_);
                    }
                }
            }

        }

        @Override
        protected void done() {

//            try {
//                boolean finish = get();
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }

        }
    }

    public void stopBlink() {
        if (worker != null) {
            worker.cancel(true);
            finish = true;
        }
    }

    public void reStartBlink() {
        finish = false;
        if (worker != null) {
            worker.cancel(true);
            worker = null;
        }
        worker = new MySwingWorker();
        worker.execute();
    }

    private boolean isFinish() {

        return finish;

    }

}
