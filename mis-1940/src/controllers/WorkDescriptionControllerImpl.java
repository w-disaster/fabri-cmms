package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.ManHours;
import model.Uses;
import model.WorkDescription;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkDescriptionLogicsImpl.
 */
public class WorkDescriptionControllerImpl implements WorkDescriptionController {
	
	/** The item serial number. */
	private JTextField itemSerialNumber;
	
	/** The description. */
	private JTextField description;
	
	/** The tasks. */
	private JTextArea tasks;
	
	/** The notes. */
	private JTextArea notes;
	
	/** The dtm. */
	private DefaultTableModel dtm;
	
	/** The supplier. */
	private JComboBox<String> supplier;
	
	/** The bill number. */
	private JTextField billNumber;
	
	/** The spare part. */
	private JComboBox<String> sparePart;
	
	/** The amount. */
	private JTextField amount;
	
	/** The fiscal code. */
	private JComboBox<String> fiscalCode;
	
	/** The hours. */
	private JTextField hours;
	
	/** The cost per hour. */
	private JTextField costPerHour;
	
	/** The db controller. */
	private DBMSController dbController;
	
	/** The work description. */
	private WorkDescription workDescription;
	
	/** The man hours. */
	private ManHours manHours;
	
	/** The uses. */
	private Uses uses;
	
	
	/**
	 * Instantiates a new work description logics impl.
	 *
	 * @param dbController the db controller
	 */
	public WorkDescriptionControllerImpl(DBMSController dbController) {
		super();
		this.dbController = dbController;
		this.workDescription = new WorkDescription();
		this.manHours = new ManHours();
		this.uses = new Uses();
	}

	/**
	 * Builds the work description.
	 *
	 * @param itemSerialNumber the item serial number
	 * @param description the description
	 * @param tasks the tasks
	 * @param notes the notes
	 * @param dtm the dtm
	 */
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

	/**
	 * Builds the uses.
	 *
	 * @param supplier the supplier
	 * @param billNumber the bill number
	 * @param sparePart the spare part
	 * @param amount the amount
	 */
	@Override
	public void buildUses(JComboBox<String> supplier, JTextField billNumber, JComboBox<String> sparePart, JTextField amount) {
		this.supplier = supplier;
		this.billNumber = billNumber;
		this.sparePart = sparePart;
		this.amount = amount;
	}
	
	/**
	 * Update spare parts.
	 */
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

	/**
	 * Builds the man hours.
	 *
	 * @param fiscalCode the fiscal code
	 * @param hours the hours
	 * @param costPerHour the cost per hour
	 */
	@Override
	public void buildManHours(JComboBox<String> fiscalCode, JTextField hours, JTextField costPerHour) {
		this.fiscalCode = fiscalCode;
		this.hours = hours;
		this.costPerHour = costPerHour;
	}

	/**
	 * Save work description.
	 *
	 * @return true, if successful
	 */
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

	/**
	 * Save uses.
	 *
	 * @return true, if successful
	 */
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
	
	/**
	 * Clear uses.
	 */
	private void clearUses() {
		this.billNumber.setText("");
		this.amount.setText("");
	}
	
	/**
	 * Clear man hours.
	 */
	private void clearManHours() {
		this.hours.setText("");
		this.costPerHour.setText("");
	}

	/**
	 * Save man hours.
	 *
	 * @return true, if successful
	 */
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

	/**
	 * Clear all.
	 */
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
