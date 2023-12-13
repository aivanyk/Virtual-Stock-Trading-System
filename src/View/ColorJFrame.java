package com.stock_test.View;

import com.stock_test.Controller.BgColorController;

import javax.swing.*;
import java.awt.*;

public class ColorJFrame extends JFrame{
    public ColorJFrame(){
        getContentPane().setBackground(BgColorController.getInstance().getColor());
    }

    public void addColorPanel(){
        JPanel colorPanel = new JPanel(new FlowLayout());
        colorPanel.add(BgColorController.getInstance().getView());
        add(colorPanel, BorderLayout.SOUTH);
    }
}
