package controllers;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Pair;
import model.Purchase;
import model.Stock;

// TODO: Auto-generated Javadoc
/**
 * The Class SparePartLogicsImpl.
 */
public class SparePartControllerImpl implements SparePartController {
	
	/** The supplier. */
	private JComboBox<String> supplier;
	
	/** The bill number. */
	private JTextField billNumber;
	
	/** The purchase date. */
	private JTextField purchaseDate;
	
	/** The spare part. */
	private JComboBox<String> sparePart;
	
	/** The amount. */
	private JTextField amount;
	
	/** The unit cost. */
	private JTextField unitCost;
	
	/** The notes. */
	private JTextArea notes;
	
	/** The cod warehouse. */
	private JComboBox<String> codWarehouse;
	
	/** The dtm. */
	private DefaultTableModel dtm;
	
	/** The db controller. */
	private DBMSController dbController;
	
	/** The purchase. */
	private Purchase purchase;
	
	/** The stock. */
	private Stock stock;

	/**
	 * Instantiates a new spare part logics impl.
	 *
	 * @param dbController the db controller
	 */
	public SparePartControllerImpl(DBMSController dbController) {
		this.dbController = dbController;
		this.purchase = new Purchase();
		this.stock = new Stock();
	}

	/**
	 * Builds the purchase.
	 *
	 * @param supplier the supplier
	 * @param billNumber the bill number
	 * @param purchaseDate the purchase date
	 * @param dtm the dtm
	 */
	@Override
	public void buildPurchase(JComboBox<String> supplier, JTextField billNumber, JTextField purchaseDate, DefaultTableModel dtm) {
		this.supplier = supplier;
		this.billNumber = billNumber;
		this.purchaseDate = purchaseDate;
		this.dtm = dtm;
		CommonQueries.updateTable(this.dbController, this.dtm, "SELECT * FROM PURCHASES");
	}

	/**
	 * Builds the stock.
	 *
	 * @param sparePart the spare part
	 * @param amount the amount
	 * @param unitCost the unit cost
	 * @param notes the notes
	 * @param codWarehouse the cod warehouse
	 */
	@Override
	public void buildStock(JComboBox<String> sparePart, JTextField amount, JTextField unitCost,
			JTextArea notes, JComboBox<String> codWarehouse) {
		this.sparePart = sparePart;
		this.amount = amount;
		this.unitCost = unitCost;
		this.notes = notes;
		this.codWarehouse = codWarehouse;
	}

	/**
	 * Save purchase.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean savePurchase() {
		this.purchase.setCodSupplier(CommonQueries.getSupplierKey(this.dbController, this.supplier.getSelectedItem()));
		this.purchase.setBillNumber(this.billNumber.getText());
		this.purchase.setPurchaseDate(this.purchaseDate.getText());
		if(this.dbController.newPurchase(this.purchase)){
			System.out.println("Acquisto inserito correttamente");
			CommonQueries.updateTable(this.dbController, this.dtm, "SELECT * FROM PURCHASES");
			return true;
		}
		return false;
	}

	/**
	 * Save stock.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean saveStock() {
		Pair<String, String> sparePartKey = CommonQueries.getSparePartKey(this.dbController, this.sparePart.getSelectedItem());
		this.stock.setItemSerialNumber(sparePartKey.getX());
		this.stock.setNumSparePart(sparePartKey.getY());
		this.stock.setCodSupplier(CommonQueries.getSupplierKey(this.dbController, this.supplier.getSelectedItem()));
		this.stock.setBillNumber(this.billNumber.getText());
		this.stock.setAmount(this.amount.getText());
		this.stock.setUnitCost(this.unitCost.getText());
		this.stock.setNotes(this.notes.getText());
		this.stock.setCodWarehouse(this.codWarehouse.getSelectedItem().toString());
		if(this.dbController.newStock(this.stock)) {
			System.out.println("Stock inserito correttamente");
			this.clearStock();
			return true;
		}
		return false;
	}

	/**
	 * Clear all.
	 */
	@Override
	public void clearAll() {
		this.clearStock();
		this.billNumber.setText("");
		this.purchaseDate.setText("");
	}

	/**
	 * Clear stock.
	 */
	@Override
	public void clearStock() {
		this.amount.setText("");
		this.unitCost.setText("");
		this.notes.setText("");
	}

}
