package controllers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.ManHours;
import model.Pair;
import model.Uses;
import model.WorkDescription;

public class WorkDescriptionLogicsImpl implements WorkDescriptionLogics {
	
	private JTextField itemSerialNumber;
	private JTextField description;
	private JTextArea tasks;
	private JTextArea notes;
	private DefaultTableModel dtm;
	private JComboBox supplier;
	private JTextField billNumber;
	private JComboBox sparePart;
	private JTextField amount;
	private JComboBox fiscalCode;
	private JTextField hours;
	private JTextField costPerHour;
	private DBController dbController;
	private WorkDescription workDescription;
	private ManHours manHours;
	private Uses uses;
	
	
	public WorkDescriptionLogicsImpl(DBController dbController) {
		super();
		this.dbController = dbController;
		this.workDescription = new WorkDescription();
		this.manHours = new ManHours();
		this.uses = new Uses();
	}

	@Override
	public void buildWorkDescription(JTextField itemSerialNumber, JTextField description, JTextArea tasks,
			JTextArea notes, DefaultTableModel dtm) {
		this.itemSerialNumber = itemSerialNumber;
		this.description = description;
		this.tasks = tasks;
		this.notes = notes;
		this.dtm = dtm;
		CommonQueries.updateTable(this.dbController, this.dtm, "SELECT * FROM WORK_DESCRIPTIONS");
	}

	@Override
	public void buildUses(JComboBox supplier, JTextField billNumber, JComboBox sparePart, JTextField amount) {
		this.supplier = supplier;
		this.billNumber = billNumber;
		this.sparePart = sparePart;
		this.amount = amount;
	}
	
	private void updateSpareParts() {
		this.sparePart.removeAllItems();
		Connection c = dbController.getConnection();
		Statement statement = null;
		try {
			statement = c.createStatement();
			String query = "SELECT Name "
					+ "FROM SPARE_PARTS "
					+ "WHERE SerialNO = '"
					+ this.itemSerialNumber.getText() + "'";
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				System.out.println(rs.getString(1));
				this.sparePart.addItem(rs.getString(1));
			}
			
		} catch(SQLException exception) {
			System.out.println(exception);
		}			
	}

	@Override
	public void buildManHours(JComboBox fiscalCode, JTextField hours, JTextField costPerHour) {
		this.fiscalCode = fiscalCode;
		this.hours = hours;
		this.costPerHour = costPerHour;
	}

	@Override
	public boolean saveWorkDescription() {
		this.workDescription.setSerialNumber(this.itemSerialNumber.getText());
		this.workDescription.setDescription(this.description.getText());
		this.workDescription.setTasks(this.tasks.getText());
		this.workDescription.setNotes(this.notes.getText());

		this.updateSpareParts();
		if(this.dbController.newWorkDescription(this.workDescription)) {
			System.out.println("Work description inserito correttamente");
			CommonQueries.updateTable(this.dbController, this.dtm, "SELECT * FROM WORK_DESCRIPTIONS");
			return true;
		}
		
		return false;
	}

	@Override
	public boolean saveUses() {
		this.uses.setCodSupplier(CommonQueries.getSupplierKey(this.dbController, this.supplier.getSelectedItem()));
		this.uses.setBillNumber(this.billNumber.getText());
		this.uses.setItemSerialNumber(this.itemSerialNumber.getText());
		this.uses.setNumSparePart(CommonQueries.getSparePartKey(this.dbController, this.sparePart.getSelectedItem()).getY());
		this.uses.setDescription(this.description.getText());
		this.uses.setAmount(this.amount.getText());
		if(this.dbController.newUses(this.uses)) {
			this.clearUses();
			System.out.println("Uses inserito correttamente");
			return true;
		}
		
		return false;
	}
	
	private void clearUses() {
		this.billNumber.setText("");
		this.amount.setText("");
	}
	
	private void clearManHours() {
		this.hours.setText("");
		this.costPerHour.setText("");
	}

	@Override
	public boolean saveManHours() {
		this.manHours.setSerialNumber(this.itemSerialNumber.getText());
		this.manHours.setDescription(this.description.getText());
		this.manHours.setFiscalCode(this.fiscalCode.getSelectedItem().toString());
		this.manHours.setHours(this.hours.getText());
		this.manHours.setCostPerHour(this.costPerHour.getText());
		if(this.dbController.newManHours(this.manHours)) {
			this.clearManHours();
			System.out.println("Man Hours inserito correttamente");
			return true;
		}
		
		
		return false;
	}

	@Override
	public void clearAll() {
		this.itemSerialNumber.setText("");
		this.description.setText("");
		this.notes.setText("");
		this.tasks.setText("");
		this.clearUses();
		this.clearManHours();
	}
	
}
