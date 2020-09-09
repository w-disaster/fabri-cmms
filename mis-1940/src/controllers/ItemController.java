package controllers;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Interface ItemLogics.
 */
public interface ItemController {
	
	/**
	 * Builds the attributes.
	 *
	 * @param serialNO the serial NO
	 * @param name the name
	 * @param model the model
	 * @param maker the maker
	 * @param dimension the dimension
	 * @param year the year
	 * @param unit the unit
	 * @param adr the adr
	 */
	void buildAttributes(JTextField serialNO, JTextField name, JTextField model, JTextField maker, JTextField dimension, 
			JTextField year, JComboBox<String> unit, JTextField adr);
	
	/**
	 * Builds the foreign keys.
	 *
	 * @param family the family
	 * @param subfamily the subfamily
	 * @param city the city
	 * @param address the address
	 * @param supplierName the supplier name
	 */
	void buildForeignKeys(JComboBox<String> family, JComboBox<String> subfamily, JComboBox<String> city, 
			JComboBox<String> address, JComboBox<String> supplierName);
	
	/**
	 * Builds the optionals.
	 *
	 * @param dateConclusion the date conclusion
	 * @param expiration the expiration
	 * @param numWarranty the num warranty
	 * @param date the date
	 * @param record the record
	 * @param notes the notes
	 */
	void buildOptionals(JTextField dateConclusion, JTextField expiration, JTextField numWarranty, JTextField date,
			JTextField record, JTextField notes);
	
	/**
	 * Builds the im txt table.
	 *
	 * @param description the description
	 * @param specifications the specifications
	 * @param dtm the dtm
	 */
	void buildImTxtTable(JTextArea description, JTextArea specifications, DefaultTableModel dtm);

	/**
	 * Upload image.
	 *
	 * @param imagePath the image path
	 */
	void uploadImage(String imagePath);
	
	/**
	 * Upload file.
	 *
	 * @param filePath the file path
	 */
	void uploadFile(String filePath);
	
	/**
	 * Save.
	 *
	 * @return true, if successful
	 */
	boolean save();

	/**
	 * Filter subfamily.
	 *
	 * @param family the family
	 * @return the list
	 */
	List<String> filterSubfamily(JComboBox<String>family);
	
	/**
	 * Filter address.
	 *
	 * @param city the city
	 * @return the list
	 */
	List<String> filterAddress(JComboBox<String> city);
	
}
