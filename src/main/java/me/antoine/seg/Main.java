package me.antoine.seg;

import me.antoine.seg.gui.Window;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Window::new);
    }
}
