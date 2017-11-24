package com.chen.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.chen.blinkbutton.BlinkButtonBySwingTimer;
import com.chen.blinkbutton.BlinkButtonBySwingWorker;
import com.chen.blinkbutton.BlinkButtonByTread;
import com.chen.blinkbutton.BlinkButtonByUtilTimer;
import com.chen.component.MyPanel;
import com.chen.layout.SGLayout;

/**
 *  类的功能描述：程序入口
 *
 * @ClassName:  RunBlinkButton
 * @Author  haichen
 */
public class RunBlinkButton extends JFrame {

    private static final String SWING_TIMER_BLINK_BUTTON = "Swing Timer";
    private static final String SWING_WORKER_BLINK_BUTTON = "Swing Worker";
    private static final String UTIL_TIMER_BLINK_BUTTON = "Util Timer";
    private static final String THREAD_BLINK_BUTTON = "Thread";
    private static final String START_BUTTON_STR = "Start";
    private static final String STOP_BUTTON_STR = "Stop";
    private static final String EXIT_BUTTON_STR = "Exit";
    private static BlinkButtonBySwingTimer swingTimerButton = new BlinkButtonBySwingTimer(SWING_TIMER_BLINK_BUTTON,
        500, Color.red);
    private static BlinkButtonBySwingWorker swingWorkerButton = new BlinkButtonBySwingWorker(SWING_WORKER_BLINK_BUTTON,
        500, Color.orange);
    private static BlinkButtonByUtilTimer utilTimerButton = new BlinkButtonByUtilTimer(UTIL_TIMER_BLINK_BUTTON, 500,
        Color.green);
    private static BlinkButtonByTread threadButton = new BlinkButtonByTread(THREAD_BLINK_BUTTON, 500, Color.blue);
    private static JButton startButton = new JButton(START_BUTTON_STR);
    private static JButton stopButton = new JButton(STOP_BUTTON_STR);
    private static JButton exitButton = new JButton(EXIT_BUTTON_STR);
    private static JPanel contentPanel = new MyPanel();
    private static JPanel blinkButtonPanel = new JPanel();
    private static JPanel otherButtonPanel = new JPanel();

    public RunBlinkButton() {
        System.out.println(""+System.getProperty("user.dir"));
        this.getContentPane().add(contentPanel);
        SGLayout blinkLayout = new SGLayout(1, 4, 4, 4, 10, 10);
        blinkLayout.setMargins(15, 0, 20, 0);
        blinkButtonPanel.setLayout(blinkLayout);
        SGLayout otherLayout = new SGLayout(1, 3, 4, 4, 20, 10);
        otherLayout.setMargins(15, 20, 25, 20);
        otherButtonPanel.setLayout(otherLayout);
        SGLayout contentLayout = new SGLayout(2, 1, 4, 4, 50, 10);
        contentLayout.setRowScale(0, 1);
        contentLayout.setMargins(5, 5, 5, 5);
        contentPanel.setLayout(contentLayout);
        contentPanel.add(blinkButtonPanel);
        contentPanel.add(otherButtonPanel);
        contentPanel.setOpaque(false);
        blinkButtonPanel.setOpaque(false);
        otherButtonPanel.setOpaque(false);
        blinkButtonPanel.add(swingTimerButton);
        blinkButtonPanel.add(swingWorkerButton);
        blinkButtonPanel.add(utilTimerButton);
        blinkButtonPanel.add(threadButton);
        otherButtonPanel.add(startButton);
        otherButtonPanel.add(stopButton);
        otherButtonPanel.add(exitButton);
        startButton.addActionListener(new BlinkListener());
        stopButton.addActionListener(new BlinkListener());
        exitButton.addActionListener(new BlinkListener());
        this.setSize(550, 220);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        RunBlinkButton test = new RunBlinkButton();
    }

    static class BlinkListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(EXIT_BUTTON_STR)) {
                System.exit(0);
            } else if (e.getActionCommand().equals(STOP_BUTTON_STR)) {
                swingTimerButton.stopBlink();
                swingWorkerButton.stopBlink();
                utilTimerButton.stopBlink();
                threadButton.stopBlink();
            } else if (e.getActionCommand().equals(START_BUTTON_STR)) {
                swingTimerButton.reStartBlink();
                swingWorkerButton.reStartBlink();
                utilTimerButton.reStartBlink();
                threadButton.reStartBlink();
            }
        }
    }

}
