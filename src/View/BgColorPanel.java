package com.stock_test.View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BgColorPanel extends JPanel{
    private JButton[] colorButtons;
    private static int buttonLen = 15;
    private static int buttonGap = 7;

    public BgColorPanel() {
        setLayout(new GridLayout(1, 3, buttonGap, buttonGap));

        colorButtons = new JButton[]{
                createColorButton(Color.decode("#0077be")),
                createColorButton(Color.decode("#ff6f61")),
                createColorButton(Color.decode("#50c878")),
                createColorButton(Color.decode("#ff7f50")),
                createColorButton(Color.decode("#9966cc")),
                createColorButton(Color.LIGHT_GRAY),
                createColorButton(Color.WHITE)
        };

        setPreferredSize(new Dimension(colorButtons.length * buttonLen + (colorButtons.length-1) * buttonGap, buttonLen));

        for (JButton button : colorButtons) {
            add(button);
        }
    }

    // Method to create color-changing buttons
    private JButton createColorButton(Color color) {
        JButton button = new JButton();
        Border blackBorder = new LineBorder(Color.WHITE);
        button.setBorder(blackBorder);
        button.setBackground(color);
//        button.setBorderPainted(false);
        button.setOpaque(true);
        return button;
    }

    public JButton[] buttons(){
        return colorButtons;
    }

    // Testing the panel
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Color Changer Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 100);
            frame.setLayout(new FlowLayout());

            BgColorPanel colorPanel = new BgColorPanel();
            frame.add(colorPanel);

            frame.setVisible(true);
        });
    }
}
