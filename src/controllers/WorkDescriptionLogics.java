package controllers;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public interface WorkDescriptionLogics {
	
	void buildWorkDescription(JTextField itemSerialNumber, JTextField description, JTextArea tasks, 
			JTextArea notes, DefaultTableModel dtm);
	
	void buildUses(JComboBox supplier, JTextField billNumber, JComboBox sparePart, JTextField amount);
	
	void buildManHours(JComboBox fiscalCode, JTextField hours, JTextField costPerHour);
	
	boolean saveWorkDescription();
	
	boolean saveUses();
	
	boolean saveManHours();
	
	void clearAll();

}
