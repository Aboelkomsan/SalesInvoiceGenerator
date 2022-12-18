/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 *
 * @author taker
 */
public class Invoice_Line_Dialog  extends JDialog{
     private JLabel nameLabel;
     private JTextField nameField;
     private JTextField countField;
     private JTextField priceField;
     private JLabel countLabel;
     private JLabel priceLabel;
     private JButton okayButton;
     private JButton cancelButton;
        
     public Invoice_Line_Dialog(SalesFrame frame) {
        nameField = new JTextField(25);
        nameLabel = new JLabel("Item Name");
         add(nameLabel);
         add(nameField);
        countField = new JTextField(25);
        countLabel = new JLabel("Item Count");
         add(countLabel);
         add(countField);
        priceField = new JTextField(25);
        priceLabel = new JLabel("Item Price");
         add(priceLabel);
         add(priceField);
        okayButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
         add(okayButton);
         add(cancelButton);
        okayButton.setActionCommand("createLineOK");
        cancelButton.setActionCommand("createLineCancel");

        okayButton.addActionListener(frame.getController());
        cancelButton.addActionListener(frame.getController());
        setLayout(new GridLayout(6, 3));
        pack();
    }
    public JTextField getnameField() {
        return nameField;
    }

     public JTextField getCountField() {
        return countField;
    }
         
     public JTextField getPriceField() {
        return priceField;
    }
    
}
