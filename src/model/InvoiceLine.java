/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author taker
 */
public class InvoiceLine {
    private String item;
    private double price;
    private int count;
    private InvoiceHeader invoice;

    public InvoiceLine() {
    }


    public InvoiceLine( String item, double price, int count, InvoiceHeader invoice) {
        this.item = item;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
    }
    
    
    public double getLineTotal() {
        return price* count ; 
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public InvoiceHeader getInvoice() {
        return invoice;
    }

    
    
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Invoice_Line{" + "num=" + invoice.getNum() + ", item=" + item + ", price=" + price + ", count=" + count + '}';
    }
    
    
     public String getAsCSV(){
      return invoice.getNum() + "," + item + "," + price + "," + count;
    
    }
    
    
    
}
