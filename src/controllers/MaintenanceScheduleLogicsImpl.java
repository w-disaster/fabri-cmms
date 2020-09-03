package controllers;

import java.util.Optional;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.MaintenanceSchedule;
import model.Plan;

public class MaintenanceScheduleLogicsImpl implements MaintenanceScheduleLogics {
	
	private JTextField description;
	private JTextField numMaintenanceSchedule;
	private JTextField itemSerialNumber;
	private JComboBox workType;
	private JTextArea workTypeArea;
	private JTextArea tasks;
	private JTextField wDDescription;
	private DefaultTableModel dtm;
	private DBController dbController;
	private MaintenanceSchedule maintenanceSchedule;
	private Plan plan;
	
	public MaintenanceScheduleLogicsImpl(DBController dbController) {
		super();
		this.dbController = dbController;
		this.maintenanceSchedule = new MaintenanceSchedule();
		this.plan = new Plan();
	}

	@Override
	public void build(JTextField description, JTextField numMaintenanceSchedule, JTextField itemSerialNumber,
			JComboBox workType, JTextArea workTypeArea, JTextArea tasks, JTextField wDDescription,
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

	private void clearWorkDescription() {
		this.wDDescription.setText("");
	}
	
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
