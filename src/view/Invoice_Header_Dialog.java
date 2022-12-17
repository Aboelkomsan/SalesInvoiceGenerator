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
public class Invoice_Header_Dialog extends JDialog{
     private JTextField custNameField;
     private JTextField invDateField;
     private JLabel custNameLbl;
     private JLabel invDateLbl;
     private JButton OK;
     private JButton Cancel;
     
     public Invoice_Header_Dialog(SalesFrame frame) {
        custNameLbl = new JLabel("Customer Name:");
        custNameField = new JTextField(20);
        invDateLbl = new JLabel("Invoice Date:");
        invDateField = new JTextField(20);
        OK = new JButton("OK");
        Cancel = new JButton("Cancel");
        
        OK.setActionCommand("createInvoiceOK");
        Cancel.setActionCommand("createInvoiceCancel");
        
        OK.addActionListener(frame.getController());
        Cancel.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 2));
        
        add(invDateLbl);
        add(invDateField);
        add(custNameLbl);
        add(custNameField);
        add(OK);
        add(Cancel);
        
        pack();
}
     
 public JTextField getCustNameField() {
        return custNameField;
    }
 
 public JTextField getInvDateField() {
        return invDateField;
    }
}
