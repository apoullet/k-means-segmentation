package me.antoine.seg.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Window extends JFrame {

    public Window() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        BufferedImage image = this.readImage("car.jpg");
        Picture pic = new Picture(image);
        this.add(pic);
        this.pack();
        this.setVisible(true);
    }

    private BufferedImage readImage(String path) {
        ClassLoader loader = this.getClass().getClassLoader();
        BufferedImage image = null;

        try {
            InputStream stream = loader.getResourceAsStream(path);

            image = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
