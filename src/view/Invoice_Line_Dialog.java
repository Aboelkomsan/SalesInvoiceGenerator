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

    //Create invoice dialoge frame
     public Invoice_Line_Dialog(SalesFrame frame) {
         //Create new objects
         //1. item name field ,and
         //2. item name label
        nameField = new JTextField(25);
        nameLabel = new JLabel("Item Name");
        //Add the name label and name field to the frame SalesFrame
         add(nameLabel);
         add(nameField);
         //Create new objects Count field and count label
         //3. Count field ,and
         //4. count label
        countField = new JTextField(25);
        countLabel = new JLabel("Item Count");
         //Add the count label and count field to the frame SalesFrame
         add(countLabel);
         add(countField);
         //Create new objects
         //5. Price field ,and
         //6. Price label
        priceField = new JTextField(25);
        priceLabel = new JLabel("Item Price");
         //Add the price label and price field to the frame SalesFrame
         add(priceLabel);
         add(priceField);
         //Create new buttons
         //5. Okay button ,and
         //6. Cancel button
        okayButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
         //Add the Okay button and cancel button to the frame SalesFrame
         add(okayButton);
         add(cancelButton);
         //Set the action commands for Okay and cancel
        okayButton.setActionCommand("createLineOK");
        cancelButton.setActionCommand("createLineCancel");
         //Adding the action listeners to the buttons Okay and Cancel
        okayButton.addActionListener(frame.getController());
        cancelButton.addActionListener(frame.getController());
         //Set the frame layout to 6 rows and 3 columns and pack
        setLayout(new GridLayout(6, 3));
        pack();
    }

    //Declare public Text field method to return/get the name field value
    public JTextField getnameField() {
        return nameField;
    }

    //Declare public Text field method to return/get the count field value
     public JTextField getCountField() {
        return countField;
    }

    //Declare public Text field method to return/get the price field value
     public JTextField getPriceField() {
        return priceField;
    }
    
}
