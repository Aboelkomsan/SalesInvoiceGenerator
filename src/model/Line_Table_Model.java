/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author taker
 */
public class Line_Table_Model extends AbstractTableModel {
     
    private ArrayList<InvoiceLine> lines;
    private String [] columns = {"NO.", "Item Name", "Item Price", "Count", "Item Total"};

    public Line_Table_Model(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    
    public ArrayList<InvoiceLine> getLines() {
        return lines;
    }
    
    
    @Override
    public int getRowCount() {
       return lines.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
     @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      InvoiceLine line = lines.get(rowIndex);
      
      switch (columnIndex){
          case 0 : return line.getInvoice().getNum();
          case 1 : return line.getItem();
          case 2 : return line.getPrice();
          case 3 : return line.getCount();
          case 4 : return line.getLineTotal();
          default : return "";
      }
    }
    
}
