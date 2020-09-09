package controllers;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.MaintenanceSchedule;
import model.Plan;

// TODO: Auto-generated Javadoc
/**
 * The Class MaintenanceScheduleLogicsImpl.
 */
public class MaintenanceScheduleControllerImpl implements MaintenanceScheduleController {
	
	/** The description. */
	private JTextField description;
	
	/** The num maintenance schedule. */
	private JTextField numMaintenanceSchedule;
	
	/** The item serial number. */
	private JTextField itemSerialNumber;
	
	/** The work type. */
	private JComboBox<String>workType;
	
	/** The work type area. */
	private JTextArea workTypeArea;
	
	/** The tasks. */
	private JTextArea tasks;
	
	/** The w D description. */
	private JTextField wDDescription;
	
	/** The dtm. */
	private DefaultTableModel dtm;
	
	/** The db controller. */
	private DBMSController dbController;
	
	/** The maintenance schedule. */
	private MaintenanceSchedule maintenanceSchedule;
	
	/** The plan. */
	private Plan plan;
	
	/**
	 * Instantiates a new maintenance schedule logics impl.
	 *
	 * @param dbController the db controller
	 */
	public MaintenanceScheduleControllerImpl(DBMSController dbController) {
		super();
		this.dbController = dbController;
		this.maintenanceSchedule = new MaintenanceSchedule();
		this.plan = new Plan();
	}

	/**
	 * Builds the.
	 *
	 * @param description the description
	 * @param numMaintenanceSchedule the num maintenance schedule
	 * @param itemSerialNumber the item serial number
	 * @param workType the work type
	 * @param workTypeArea the work type area
	 * @param tasks the tasks
	 * @param wDDescription the w D description
	 * @param dtm the dtm
	 */
	@Override
	public void build(JTextField description, JTextField numMaintenanceSchedule, JTextField itemSerialNumber,
			JComboBox<String>workType, JTextArea workTypeArea, JTextArea tasks, JTextField wDDescription,
			DefaultTableModel dtm) {
		this.description = description;
		this.numMaintenanceSchedule = numMaintenanceSchedule;
		this.itemSerialNumber = itemSerialNumber;
		this.workType = workType;
		this.workTypeArea = workTypeArea;
		this.tasks = tasks;
		this.wDDescription = wDDescription;
		this.dtm = dtm;
		CommonQueries.updateTable(this.dbController, this.dtm, "SELECT * FROM MAINTENANCE_SCHEDULES");
	}

	/**
	 * Save maintenance schedule.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean saveMaintenanceSchedule() {
		this.maintenanceSchedule.setItemSerialNumber(this.itemSerialNumber.getText());
		this.maintenanceSchedule.setNumMaintenanceSchedule(this.numMaintenanceSchedule.getText());
		this.maintenanceSchedule.setDescription(this.description.getText());
		this.maintenanceSchedule.setTasks(this.tasks.getText());
		switch(this.workType.getSelectedItem().toString()) {
			case "Preventive (by time)":
				this.maintenanceSchedule.setByTime(this.workTypeArea.getText());
				break;
			case "Preventive (by Usage)":
				this.maintenanceSchedule.setByUsage(this.workTypeArea.getText());
				break;
			case "Predictive":
				this.maintenanceSchedule.setByPrediction(this.workTypeArea.getText());
				break;
			case "Condition based":
				this.maintenanceSchedule.setByPrediction(this.workTypeArea.getText());
				break;
			case "Run to fail":
				this.maintenanceSchedule.setByPrediction(this.workTypeArea.getText());
				break;
		}
		if(this.dbController.newMaintenanceSchedule(this.maintenanceSchedule)) {
			System.out.println("Maintenance Schedule inserito correttamente");
			CommonQueries.updateTable(this.dbController, this.dtm, "SELECT * FROM MAINTENANCE_SCHEDULES");
			return true;
		}
		return false;
	}

	/**
	 * Save plan.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean savePlan() {
		this.plan.setItemSerialNumber(this.itemSerialNumber.getText());
		this.plan.setNumMaintenanceSchedule(this.numMaintenanceSchedule.getText());
		this.plan.setDescription(this.wDDescription.getText());
		if(this.dbController.newPlan(this.plan)) {
			System.out.println("Plan inserito correttamente");
			this.clearWorkDescription();
			return true;
		}
		return false;
	}

	/**
	 * Clear work description.
	 */
	private void clearWorkDescription() {
		this.wDDescription.setText("");
	}
	
	/**
	 * Clear all.
	 */
	@Override
	public void clearAll() {
		this.description.setText("");
		this.numMaintenanceSchedule.setText("");
		this.itemSerialNumber.setText("");
		this.workTypeArea.setText("");
		this.tasks.setText("");
		this.clearWorkDescription();
	}

}
