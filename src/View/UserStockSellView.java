package com.stock_test.View;

import com.stock_test.Util.IntFilter;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// User view to sell stocks
public class UserStockSellView extends ColorJFrame{
    private static Dimension buttonSize = new Dimension(100, 30);
    private static int[] frameSize = new int[]{500, 400};
    private static int[] size = new int[]{500, 370};

    private JPanel mainPanel;
    private JLabel moneyLabel;
    private JComboBox stockList;
    private int selectedIdx;
    private JTextField amountField;
    private JButton sellButton;
    private JButton cancelButton;

    // Constructor
    public UserStockSellView() {
        super();
        setTitle("Sell Stock Page");
        mainPanel = new TransPanel();
        setSize(frameSize[0], frameSize[1]);
        mainPanel.setSize(new Dimension(size[0], size[1]-30));
        setLayout(new BorderLayout());
        mainPanel.setLayout(null);
        selectedIdx = -1;

        setMoneyPanel();
        setStocksPanel();
        setAmountPanel();

        sellButton = new JButton("Sell");
        sellButton.setPreferredSize(buttonSize);
        sellButton.setBounds((size[0]-sellButton.getPreferredSize().width)/2, size[1]*4/6, sellButton.getPreferredSize().width, sellButton.getPreferredSize().height);
        mainPanel.add(sellButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(buttonSize);
        cancelButton.setBounds((size[0]-cancelButton.getPreferredSize().width)/2, size[1]*5/6, cancelButton.getPreferredSize().width, cancelButton.getPreferredSize().height);
        mainPanel.add(cancelButton);

        add(mainPanel, BorderLayout.CENTER);
    }

    // Set the money panel to show
    private void setMoneyPanel(){

        JPanel panel = new TransPanel();
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(200, 30));


        double moneyAmount = 0.0;

        moneyLabel = new JLabel("Money: $" + moneyAmount);

        panel.add(moneyLabel);
        panel.setBounds((size[0]-panel.getPreferredSize().width)/2, size[1]/6, panel.getPreferredSize().width, panel.getPreferredSize().height);
        mainPanel.add(panel);
    }

    // Set the amount of money
    public void setMoneyValue(double val){
        moneyLabel.setText("Money: $" + String.format("%.2f", val));
    }

    // Set the stock panel to show
    private void setStocksPanel(){
        String[] data = {};
        stockList = new JComboBox(data);
        stockList.setEditable(true);
        stockList.setMaximumSize( stockList.getPreferredSize() );
        stockList.setBounds((size[0]-stockList.getPreferredSize().width)/2, size[1]*2/6, stockList.getPreferredSize().width, stockList.getPreferredSize().height);
        stockList.addItemListener(e -> selectedIdx = stockList.getSelectedIndex());

        mainPanel.add(stockList);
    }

    // Set the values of the stocks
    public void setStocksValue(String[] data){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>( data );
        stockList.setModel( model );
        stockList.setSelectedIndex(-1);
    }

    // Set the amount panel to show
    private void setAmountPanel(){
        JPanel panel = new TransPanel();

        JLabel label = new JLabel("Amount: ");
        amountField = new JTextField(10);
        PlainDocument doc = (PlainDocument) amountField.getDocument();
        doc.setDocumentFilter(new IntFilter());

        panel.add(label);
        panel.add(amountField);
        panel.setBounds((size[0]-panel.getPreferredSize().width)/2, size[1]*3/6, panel.getPreferredSize().width, panel.getPreferredSize().height);

        mainPanel.add(panel);
    }

    // Setters for button listeners
    public void setSellButtonListener(ActionListener listener){
        sellButton.addActionListener(listener);
    }

    public void setCancelButtonListener(ActionListener listener){
        cancelButton.addActionListener(listener);
    }

    // Get the selected stock
    public int getSelectionIdx(){
        return selectedIdx;
    }

    // Get amount input
    public int getAmount(){
        if(amountField.getText().isEmpty()) return 0;
        return Integer.parseInt(amountField.getText());
    }

    // Refresh the inputs
    public void refresh(){
        selectedIdx = -1;
        stockList.setSelectedIndex(-1);
        amountField.setText("");
    }

    public static void main(String[] args){
        UserStockSellView sv = new UserStockSellView();

        String[] tmp = new String[]{"1", "2", "3", "4"};
        sv.setStocksValue(tmp);
        sv.setVisible(true);
    }
}
