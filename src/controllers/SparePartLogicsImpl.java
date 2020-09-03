package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Pair;
import model.Purchase;
import model.Stock;

public class SparePartLogicsImpl implements SparePartLogics {
	
	private JComboBox supplier;
	private JTextField billNumber;
	private JTextField purchaseDate;
	private JComboBox sparePart;
	private JTextField amount;
	private JTextField unitCost;
	private JTextArea notes;
	private JComboBox codWarehouse;
	private DefaultTableModel dtm;
	private DBController dbController;
	private Purchase purchase;
	private Stock stock;

	public SparePartLogicsImpl(DBController dbController) {
		this.dbController = dbController;
		this.purchase = new Purchase();
		this.stock = new Stock();
	}

	@Override
	public void buildPurchase(JComboBox supplier, JTextField billNumber, JTextField purchaseDate, DefaultTableModel dtm) {
		this.supplier = supplier;
		this.billNumber = billNumber;
		this.purchaseDate = purchaseDate;
		this.dtm = dtm;
		CommonQueries.updateTable(this.dbController, this.dtm, "SELECT * FROM PURCHASES");
	}

	@Override
	public void buildStock(JComboBox sparePart, JTextField amount, JTextField unitCost,
			JTextArea notes, JComboBox codWarehouse) {
		this.sparePart = sparePart;
		this.amount = amount;
		this.unitCost = unitCost;
		this.notes = notes;
		this.codWarehouse = codWarehouse;
	}

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

	@Override
	public void clearAll() {
		this.clearStock();
		this.billNumber.setText("");
		this.purchaseDate.setText("");
	}

	@Override
	public void clearStock() {
		this.amount.setText("");
		this.unitCost.setText("");
		this.notes.setText("");
	}

}
