package com.stock_test.View;

import com.stock_test.Controller.BgColorController;

import javax.swing.*;
import java.awt.*;

// Jframe that contains the selecting color panel
public class ColorJFrame extends JFrame implements Observer{

    // Constructor
    public ColorJFrame(){
        setLayout(new BorderLayout());
        getContentPane().setBackground(BgColorController.getInstance().getColor());
    }

    public ColorJFrame(String title){
        this();
        setTitle(title);
    }

    // Add the panel to the frame
    public void addColorPanel(){
        JPanel colorPanel = new JPanel(new FlowLayout());
        colorPanel.add(BgColorController.getInstance().getView());
        add(colorPanel, BorderLayout.SOUTH);
    }

    // Observer, update the background color
    @Override
    public void colorUpdate(Color c) {
        getContentPane().setBackground(c);
        repaint();
    }
}
