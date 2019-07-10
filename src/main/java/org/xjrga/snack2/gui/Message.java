package org.xjrga.snack2.gui;

import org.xjrga.snack2.other.ImageUtilities;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Message {
    private static final BufferedImage logo = ImageUtilities.readImage("resources/apple_red.png");

    public Message() {

    }

    public static void showMessage(String message) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog("Snack");
        dialog.setIconImage(logo);
        dialog.setVisible(true);

    }

    public static int showOptionDialog(JComponent[] inputs, String title) {
        JOptionPane optionPane = new JOptionPane(inputs, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog(title);
        dialog.setIconImage(logo);
        dialog.setVisible(true);
        //0 - Ok
        //2 - Cancel
        //null - x
        Object optionValue = optionPane.getValue();
        int value = -1;
        if (optionValue != null) {
            value = (int) optionValue;
        }
        return value;
    }
}