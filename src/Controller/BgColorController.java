package com.stock_test.Controller;

import com.stock_test.View.BgColorPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BgColorController {
    private static BgColorController instance;
    private static List<JFrame> jframes;
    private static Color bgColor;
//    private static BgColorPanel bgView;

    public BgColorController(){
        jframes = new ArrayList<>();
        bgColor = Color.WHITE;
//        bgView = new BgColorPanel();
//        setButtonListeners();
    }

    public static BgColorController getInstance() {
        if (instance == null) {
            instance = new BgColorController();
        }
        return instance;
    }

    public static void registerFrame(JFrame frame) {
        jframes.add(frame);
    }

    public static void changeBackgroundColor(Color color) {
        bgColor = color;
        for (JFrame frame : jframes) {
            frame.getContentPane().setBackground(color);
            frame.repaint();
        }
    }

    public void setButtonListeners(BgColorPanel bgView) {
        JButton[] colorButtons = bgView.buttons();
        for(JButton b: colorButtons)
            b.addActionListener(e -> changeBackgroundColor(b.getBackground()));
    }

    public JPanel getView(){
        BgColorPanel bgView = new BgColorPanel();
        setButtonListeners(bgView);
        return bgView;
    }

    public Color getColor(){
        return bgColor;
    }

}
