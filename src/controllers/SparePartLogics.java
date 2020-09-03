package controllers;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public interface SparePartLogics {
	
	void buildPurchase(JComboBox supplier, JTextField billNumber, JTextField purchaseDate, DefaultTableModel dtm);
	
	void buildStock(JComboBox sparePart, JTextField amount, JTextField unitCost,
			JTextArea notes, JComboBox warehouse);
	
	boolean savePurchase();
	
	boolean saveStock();
	
	void clearAll();
	
	void clearStock();

}
