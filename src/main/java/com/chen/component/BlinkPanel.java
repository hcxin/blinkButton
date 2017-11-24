package com.chen.component;
/*
 * Copyright(C) 2014.Haichen Xin. All Rights Reserved.
 * Author: Haichen Xin
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class BlinkPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    Image image = Toolkit.getDefaultToolkit().getImage("icon/bg.jpg");

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getSize().width, getSize().height, this);
    }
}