package com.stock_test.View;

import com.stock_test.Controller.BgColorController;

import javax.swing.*;
import java.awt.*;

public class ColorJFrame extends JFrame implements Observer{
    public ColorJFrame(){
        setLayout(new BorderLayout());
        getContentPane().setBackground(BgColorController.getInstance().getColor());
    }

    public ColorJFrame(String title){
        this();
        setTitle(title);
    }

    public void addColorPanel(){
        JPanel colorPanel = new JPanel(new FlowLayout());
        colorPanel.add(BgColorController.getInstance().getView());
        add(colorPanel, BorderLayout.SOUTH);
    }

    @Override
    public void colorUpdate(Color c) {
        getContentPane().setBackground(c);
        repaint();
    }
}
