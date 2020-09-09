package controllers;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Interface SparePartLogics.
 */
public interface SparePartController {
	
	/**
	 * Builds the purchase.
	 *
	 * @param supplier the supplier
	 * @param billNumber the bill number
	 * @param purchaseDate the purchase date
	 * @param dtm the dtm
	 */
	void buildPurchase(JComboBox<String> supplier, JTextField billNumber, JTextField purchaseDate, DefaultTableModel dtm);
	
	/**
	 * Builds the stock.
	 *
	 * @param sparePart the spare part
	 * @param amount the amount
	 * @param unitCost the unit cost
	 * @param notes the notes
	 * @param warehouse the warehouse
	 */
	void buildStock(JComboBox<String> sparePart, JTextField amount, JTextField unitCost,
			JTextArea notes, JComboBox<String> warehouse);
	
	/**
	 * Save purchase.
	 *
	 * @return true, if successful
	 */
	boolean savePurchase();
	
	/**
	 * Save stock.
	 *
	 * @return true, if successful
	 */
	boolean saveStock();
	
	/**
	 * Clear all.
	 */
	void clearAll();
	
	/**
	 * Clear stock.
	 */
	void clearStock();

}
