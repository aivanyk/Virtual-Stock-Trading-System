package com.stock_test.View;

import javax.swing.*;
import java.awt.*;

// Panel that are transparent to show the frame color
class TransPanel extends JPanel {

    // Constructor
    public TransPanel() {
        setOpaque(false);
    }

    public TransPanel(LayoutManager layout) {
        this();
        setLayout(layout);
    }

    // Change the transparent ratio
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));

        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }


}