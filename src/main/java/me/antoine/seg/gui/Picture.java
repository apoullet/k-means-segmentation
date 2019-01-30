package me.antoine.seg.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Picture extends JPanel {
    private BufferedImage pic;

    Picture(BufferedImage image) {
        this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

        this.pic = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(pic, 0, 0, null);
    }
}
