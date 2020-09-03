package controllers;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public interface ItemLogics {
	
	void buildAttributes(JTextField serialNO, JTextField name, JTextField model, JTextField maker, JTextField dimension, 
			JTextField year, JComboBox<String> unit, JTextField adr);
	
	void buildForeignKeys(JComboBox<String> family, JComboBox<String> subfamily, JComboBox<String> city, 
			JComboBox<String> address, JComboBox<String> supplierName);
	
	void buildOptionals(JTextField dateConclusion, JTextField expiration, JTextField numWarranty, JTextField date,
			JTextField record, JTextField notes);
	
	void buildImTxtTable(JTextArea description, JTextArea specifications, DefaultTableModel dtm);

	void uploadImage(String imagePath);
	
	void uploadFile(String filePath);
	
	boolean save();

	List<String> filterSubfamily(JComboBox<String>family);
	
	List<String> filterAddress(JComboBox<String> city);
	
}
