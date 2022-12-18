/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.InvoiceHeader;
import model.Invoices_Table_model;
import model.InvoiceLine;
import model.Line_Table_Model;
import view.Invoice_Header_Dialog;
import view.Invoice_Line_Dialog;
import view.SalesFrame;

/**
 *
 * @author taker
 */
public class Controller implements ActionListener, ListSelectionListener {
    
    private SalesFrame frame;
    private Invoice_Header_Dialog invDialog;
    private Invoice_Line_Dialog lineDialog ;
    
    public Controller(SalesFrame frame){
        this.frame = frame ; 
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
       System.out.println("Action:  "+ actionCommand);
       switch (actionCommand){
           case "Load File":
                System.out.println("Load Files");
               loadFile();
               break;
               case "Save File":
                   System.out.println("Save Files");
                   saveFile();
               break;
               case "Create New Invoice":
                   System.out.println("Create New Invoice");
                   createNewInvoice();
               break;
               case "Delete Invoice":
                   System.out.println("Delete Selected Invoice");
                   deleteInvoice();
               break;
               case "Create New Item":
                   System.out.println("Add New Item");
                   createNewItem();
               break;
               case "Delete Item":
                   System.out.println("Delete Selected Item");
                   deleteItem();
               break;
               case "createInvoiceOK":
                    createInvoiceOK();
               break;
               case "createInvoiceCancel":
                    createInvoiceCancel();
                break; 
               case "createLineOK":
                   createLineOK();
                   break;
               case "createLineCancel":
                   createLineCancel();
                   break ;
                    
       }
       
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = frame.getInvoiceTable() .getSelectedRow();
       if ( selectedIndex != -1 ){
           System.out.println(" You Have Selected Row : "+ selectedIndex);
           InvoiceHeader currentInvoice = frame.getInvoices().get(selectedIndex);
           frame.getInvoiceNumLabel().setText(""+ currentInvoice.getNum());
           frame.getInvoiceDateLabel().setText( currentInvoice.getDate());
           frame.getCustomerNameLable().setText( currentInvoice.getCustomer());
           frame.getInvoiceTotalLabel().setText(""+ currentInvoice.getInvoiceTotal());
           Line_Table_Model linesTableModel = new Line_Table_Model (currentInvoice.getLines());
           frame.getLineTable().setModel(linesTableModel);
            linesTableModel.fireTableDataChanged();
        }
          
    }
    
    private void loadFile() {
         JOptionPane.showMessageDialog(null," Please , Choose File Header First ","Invoice Header",JOptionPane.INFORMATION_MESSAGE);
        JFileChooser openFile = new JFileChooser();
        try {
            int result = openFile.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = openFile.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                System.out.println("Invoices Have Been Read");

                ArrayList<InvoiceHeader> invoicesArray = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] headerParts = headerLine.split(",");
                    int invoiceNum = Integer.parseInt(headerParts[0]);
                    String invoiceDate = headerParts[1];
                    String customerName = headerParts[2];

                    InvoiceHeader invoice = new InvoiceHeader(invoiceNum, invoiceDate, customerName);
                    invoicesArray.add(invoice);
                }

                JOptionPane.showMessageDialog(frame, "Please, Choose Line File !", "Invoice Lines", JOptionPane.INFORMATION_MESSAGE);
                result = openFile.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = openFile.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> LineLines = Files.readAllLines(linePath);
                    System.out.println("Lines Have Been Read");
                    for (String LineLine : LineLines) {
                        String lineParts[] = LineLine.split(",");
                        int invoiceNum = Integer.parseInt(lineParts[0]);
                        String itemName = lineParts[1];
                        double itemPrice = Double.parseDouble(lineParts[2]);
                        int count = Integer.parseInt(lineParts[3]);
                        InvoiceHeader inv = null;
                        for (InvoiceHeader invoice : invoicesArray) {
                            if (invoice.getNum() == invoiceNum) {
                                inv = invoice;
                                break;
                            }
                        }

                        InvoiceLine line = new InvoiceLine(itemName, itemPrice, count, inv);
                        inv.getLines().add(line);
                    }
                    System.out.println("check point");
                }

                frame.setInvoices(invoicesArray);
                Invoices_Table_model invoicesTableModel = new Invoices_Table_model(invoicesArray);
                frame.setInvoicesTableModel(invoicesTableModel);
                frame.getInvoiceTable().setModel(invoicesTableModel);
                frame.getInvoicesTableModel().fireTableDataChanged();

            }

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Number Format Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "File Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Read Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Date Format Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFile() {
        ArrayList<InvoiceHeader> invoices = frame.getInvoices();
        String headers = "";
        String lines = "";
        for (InvoiceHeader invoice : invoices ) {
           String invCSV = invoice.getDataAsCSV();
           headers += invCSV;
           headers += "\n";
           
        for ( InvoiceLine line : invoice.getLines()){
           String lineCSV = line.getAsCSV();
           lines += lineCSV;
           lines += "\n";
           
           }
        }
        try {
         JOptionPane.showMessageDialog(frame, "Please,  Save Header File Data (Save Changes For New Or Update Invoice)", "Header File", JOptionPane.INFORMATION_MESSAGE);
         JFileChooser fc = new JFileChooser();
         int result = fc.showSaveDialog(frame);
         if ( result == JFileChooser.APPROVE_OPTION){
            File headerFile = fc.getSelectedFile();
            FileWriter hfw = new FileWriter(headerFile);
            hfw.write(headers);
            hfw.flush();
            hfw.close();

            JOptionPane.showMessageDialog(frame, "Please,  Save Lines File Data (Save Changes For New Or Update Lines)", "Lines File", JOptionPane.INFORMATION_MESSAGE);
            result = fc.showSaveDialog(frame);
            if ( result == JFileChooser.APPROVE_OPTION){
               File lineFile = fc.getSelectedFile();
               FileWriter lfw = new FileWriter(lineFile);
               lfw.write(lines);
               lfw.flush();
               lfw.close();
             
            }
         
         }
        } catch (Exception ex ){
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(frame, "Data saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void createNewInvoice() {
        
        invDialog = new Invoice_Header_Dialog(frame);
        invDialog.setVisible(true);
        
   }

    private void deleteInvoice() {
       int selectedRow =  frame.getInvoiceTable().getSelectedRow();
       if ( selectedRow != -1 ) {
          frame.getInvoices().remove(selectedRow);
          frame.getInvoicesTableModel().fireTableDataChanged();
       }
        
   }

    private void createNewItem() {
        lineDialog =  new Invoice_Line_Dialog(frame);
        lineDialog.setVisible(true);
        
    }

    private void deleteItem() {
        int selectedInv = frame.getInvoiceTable().getSelectedRow();
        int selectedRow =  frame.getLineTable().getSelectedRow();
        
        if ( selectedInv != -1 && selectedRow != -1  ) {
            InvoiceHeader invoice = frame.getInvoices().get(selectedInv);
            invoice.getLines().remove(selectedRow);
            Line_Table_Model line_Table_Model = new Line_Table_Model (invoice.getLines());
            frame.getLineTable().setModel(line_Table_Model);
            line_Table_Model.fireTableDataChanged();
            frame.getInvoicesTableModel().fireTableDataChanged();
           
            
        }
        
   }

    private void createInvoiceOK() {
        String date = invDialog.getInvDateField().getText();
        String customer = invDialog.getCustNameField().getText();
        int num = frame.getNextInvoiceNum();
        InvoiceHeader invoice = new InvoiceHeader( num , date , customer );
        frame.getInvoices().add(invoice);
        frame.getInvoicesTableModel().fireTableDataChanged();
        invDialog.setVisible(false);
        invDialog.dispose();
        invDialog = null ;
        
    }

    private void createInvoiceCancel() {
        invDialog.setVisible(false);
        invDialog.dispose();
        invDialog = null ;
      
    }

    private void createLineOK() {
        String item = lineDialog.getnameField().getText();
        String countStr = lineDialog.getCountField().getText();
        String priceStr = lineDialog.getPriceField().getText();
        int count = Integer.parseInt(countStr);
        double price = Double.parseDouble(priceStr);
        int selectedInvoice = frame.getInvoiceTable().getSelectedRow();
        if (selectedInvoice != -1 ){
           InvoiceHeader invoice = frame.getInvoices().get(selectedInvoice);
           InvoiceLine line = new InvoiceLine( item , price , count , invoice );
           invoice.getLines().add(line);
           Line_Table_Model line_Table_Model = (Line_Table_Model) frame.getLineTable().getModel();    
           line_Table_Model.fireTableDataChanged();
           frame.getInvoicesTableModel().fireTableDataChanged();
        } 
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
        
    }

    private void createLineCancel() {
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
        
       
    }

    
    
}
