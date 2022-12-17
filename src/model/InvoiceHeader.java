/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import javafx.scene.shape.Line;

/**
 *
 * @author taker
 */
public class InvoiceHeader {
    private int InvoiceNum;
    private String InvoiceDate;
    private String CustomerName;
    private ArrayList<InvoiceLine>  lines;
    

    public InvoiceHeader() {
    }

    public InvoiceHeader(int num, String Date, String Customer) {
        this.InvoiceNum = num;
        this.InvoiceDate = Date;
        this.CustomerName = Customer;
    }

    
    public double getInvoiceTotal(){
         double total = 0.0 ;
         for (InvoiceLine line : getLines()){
             total +=line.getLineTotal();
         }
         return total ; 
    }
    
    public ArrayList<InvoiceLine> getLines() {
        if (lines == null ){
            lines = new ArrayList<>();
        }
        return lines;
    }

    public String getCustomer() {
        return CustomerName;
    }

    public void setCustomer(String Customer) {
        this.CustomerName = Customer;
    }

    public int getNum() {
        return InvoiceNum;
    }

    public void setNum(int num) {
        this.InvoiceNum = num;
    }

    public String getDate() {
        return InvoiceDate;
    }

    public void setDate(String Date) {
        this.InvoiceDate = Date;
    }

    @Override
    public String toString() {
       String str = "Header{" + "invoice Number =" + InvoiceNum + ", customer Name=" + CustomerName + ", invoice Date=" + InvoiceDate + '}';
        for (InvoiceLine line : getLines()) {
            str += "\n\t" + line;
        }
        return str;
    }
    
    
    public String getDataAsCSV(){
      return InvoiceNum + "," + InvoiceDate + "," + CustomerName ;
    
    }
    
}
