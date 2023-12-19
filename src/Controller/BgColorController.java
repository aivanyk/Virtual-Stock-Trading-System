package com.stock_test.Controller;

import com.stock_test.View.BgColorPanel;
import com.stock_test.View.ColorJFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Controller responsible for managing background color changes
public class BgColorController {
    private static BgColorController instance;
    private static List<ColorJFrame> jframes;
    private static Color bgColor;

    // Constructor
    public BgColorController(){
        jframes = new ArrayList<>();
        bgColor = Color.WHITE;
    }

    // Singleton getInstance method to retrieve the single instance of the controller
    public static BgColorController getInstance() {
        if (instance == null) {
            instance = new BgColorController();
        }
        return instance;
    }

    // Method to register ColorJFrame instances
    public static void registerFrame(ColorJFrame frame) {
        jframes.add(frame);
    }

    // Method to change the background color
    public static void changeBackgroundColor(Color color) {
        bgColor = color;
        for (ColorJFrame frame : jframes) {
            frame.colorUpdate(color);
        }
    }

    // Method to set button listeners for color change
    public void setButtonListeners(BgColorPanel bgView) {
        JButton[] colorButtons = bgView.buttons();
        for(JButton b: colorButtons)
            b.addActionListener(e -> changeBackgroundColor(b.getBackground()));
    }

    // Method to get the change color panel
    public JPanel getView(){
        BgColorPanel bgView = new BgColorPanel();
        setButtonListeners(bgView);
        return bgView;
    }

    // Get the current color
    public Color getColor(){
        return bgColor;
    }

}
