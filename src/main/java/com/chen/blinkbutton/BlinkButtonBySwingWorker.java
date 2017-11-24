/*
 * Copyright(C) 2014.Haichen Xin. All Rights Reserved.
 * Author: Haichen Xin
 */

package com.chen.blinkbutton;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingWorker;

public class BlinkButtonBySwingWorker extends JButton {

    boolean finish = false;
    private long delay = 0;
    private Color background = null;
    private Color originalBackground = null;
    int i = 0;
    private JButton button = null;
    MySwingWorker worker;

    public BlinkButtonBySwingWorker(String txt, long delay, Color background) {
        button = this;
        this.delay = delay;
        this.background = background;
        this.setText(txt);
        originalBackground = this.getBackground();
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
                    Thread.sleep(delay);
                } catch (InterruptedException e) {

                }
            }
            finish = true;

            return finish;

        }

        @Override
        protected void process(List<Integer> list) {
            for (int n : list) {
                if (button.isEnabled() && !isFinish()) {
                    if (n % 2 == 0) {
                        button.setBackground(background);
                    } else {
                        button.setBackground(originalBackground);
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
