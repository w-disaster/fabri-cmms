package controllers;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Interface WorkDescriptionLogics.
 */
public interface WorkDescriptionController {
	
	/**
	 * Builds the work description.
	 *
	 * @param itemSerialNumber the item serial number
	 * @param description the description
	 * @param tasks the tasks
	 * @param notes the notes
	 * @param dtm the dtm
	 */
	void buildWorkDescription(JTextField itemSerialNumber, JTextField description, JTextArea tasks, 
			JTextArea notes, DefaultTableModel dtm);
	
	/**
	 * Builds the uses.
	 *
	 * @param supplier the supplier
	 * @param billNumber the bill number
	 * @param sparePart the spare part
	 * @param amount the amount
	 */
	void buildUses(JComboBox<String> supplier, JTextField billNumber, JComboBox<String> sparePart, JTextField amount);
	
	/**
	 * Builds the man hours.
	 *
	 * @param fiscalCode the fiscal code
	 * @param hours the hours
	 * @param costPerHour the cost per hour
	 */
	void buildManHours(JComboBox<String> fiscalCode, JTextField hours, JTextField costPerHour);
	
	/**
	 * Save work description.
	 *
	 * @return true, if successful
	 */
	boolean saveWorkDescription();
	
	/**
	 * Save uses.
	 *
	 * @return true, if successful
	 */
	boolean saveUses();
	
	/**
	 * Save man hours.
	 *
	 * @return true, if successful
	 */
	boolean saveManHours();
	
	/**
	 * Clear all.
	 */
	void clearAll();

}
