package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Execute;
import model.WorkOrder;

public class WorkOrderLogicsImpl implements WorkOrderLogics {
	
	private JTextField numWorkOrder;
	private JTextField itemSerialNumber;
	private JTextField description;
	private JTextField plannedTime;
	private JTextField plannedStart;
	private JTextField plannedEnd;
	private JComboBox urgencyLevel;
	private JTextField actualStart;
	private JTextField actualEnd;
	private JTextField wDdescription;
	private JComboBox workType;
	private JTextField numMaintenanceSchedule;
	private JTextField codProject;
	private JComboBox cmbTrigger;
	private JTextArea triggerTextArea;
	private DefaultTableModel dtm;
	private DBController dbController;
	private WorkOrder workOrder;
	private Execute execute;

	public WorkOrderLogicsImpl(DBController dbController) {
		super();
		this.dbController = dbController;
		this.workOrder = new WorkOrder();
		this.execute = new Execute();
	}

	@Override
	public void buildWorkOrder(JTextField numWorkOrder, JTextField itemSerialNumber, JTextField description,
			JTextField plannedTime, JTextField plannedStart, JTextField plannedEnd, JComboBox urgencyLevel,
			JTextField actualStart, JTextField actualEnd, JTextField wDdescription) {
		this.numWorkOrder = numWorkOrder;
		this.itemSerialNumber = itemSerialNumber;
		this.description = description;
		this.plannedTime = plannedTime;
		this.plannedStart = plannedStart;
		this.plannedEnd = plannedEnd;
		this.urgencyLevel = urgencyLevel;
		this.actualStart = actualStart;
		this.actualEnd = actualEnd;
		this.wDdescription = wDdescription;
	}

	@Override
	public void buildWorkOrderType(JComboBox workType, JTextField numMaintenanceSchedule, JTextField codProject,
			JComboBox cmbTrigger, JTextArea triggerTextArea, DefaultTableModel dtm) {
		this.workType = workType;
		this.numMaintenanceSchedule = numMaintenanceSchedule;
		this.codProject = codProject;
		this.cmbTrigger = cmbTrigger;
		this.triggerTextArea = triggerTextArea;
		this.dtm = dtm;
		this.setTypesDisabled();
		this.workType.addActionListener(l -> {
			if(this.workType.getSelectedItem().toString().equals("Ordinary")){
				this.numMaintenanceSchedule.setEnabled(true);
				this.codProject.setEnabled(true);
				this.cmbTrigger.setEnabled(false);
				this.triggerTextArea.setEnabled(false);
			} else {
				this.cmbTrigger.setEnabled(true);
				this.triggerTextArea.setEnabled(true);
				this.numMaintenanceSchedule.setEnabled(false);
				this.codProject.setEnabled(false);
			}
		});
		CommonQueries.updateTable(this.dbController, dtm, "SELECT * FROM WORK_ORDERS");
	}
	
	private void setTypesDisabled() {
		this.numMaintenanceSchedule.setEnabled(false);
		this.codProject.setEnabled(false);
		this.cmbTrigger.setEnabled(false);
		this.triggerTextArea.setEnabled(false);
	}

	private String getUrgencyKey(String urgency) {
		String result = "";
		Connection c = dbController.getConnection();
		Statement statement = null;
		try {
			statement = c.createStatement();
			String query = "SELECT CodUrgency "
					+ "FROM URGENCY_LEVELS "
					+ "WHERE Description = '"
					+ urgency + "'";
	
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()) {
				result = rs.getString(1);
			}
			
		} catch(SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	
	@Override
	public boolean saveWorkOrder() {
		this.workOrder.setNumWorkOrder(this.numWorkOrder.getText());
		this.workOrder.setItemSerialNumber(this.itemSerialNumber.getText());
		this.workOrder.setDescription(this.description.getText());
		this.workOrder.setPlannedStart(this.plannedStart.getText());
		this.workOrder.setPlannedTime(this.plannedTime.getText());
		this.workOrder.setCodUrgency(this.getUrgencyKey(this.urgencyLevel.getSelectedItem().toString()));
		this.workOrder.setPlannedEnd(this.plannedEnd.getText());
		this.workOrder.setActualStart(this.actualStart.getText());
		this.workOrder.setActualEnd(this.actualEnd.getText());
		this.workOrder.setType(this.workType.getSelectedItem().toString());
		if(this.workType.getSelectedItem().toString().equals("Ordinary")) {
			System.out.println(this.numMaintenanceSchedule.getText());
			this.workOrder.setNumMaintenanceSchedule(this.numMaintenanceSchedule.getText().equals("") ? null : this.numMaintenanceSchedule.getText());
			this.workOrder.setCodProject(this.codProject.getText().equals("") ? null : this.codProject.getText());
			this.workOrder.setCorrective(null);
		} else {
			if(this.cmbTrigger.getSelectedItem().toString().equals("Reactive")) {
				this.workOrder.setCorrective(this.triggerTextArea.getText());
			}
			this.workOrder.setNumMaintenanceSchedule(null);
			this.workOrder.setCodProject(null);
		}
		if(this.dbController.newWorkOrder(this.workOrder)) {
			System.out.println("Work Order inserito correttamente");
			CommonQueries.updateTable(this.dbController, this.dtm, "SELECT * FROM WORK_ORDERS");
			return true;
		}
		return false;
		
	}
	
	private void clearWorkDescription() {
		this.wDdescription.setText("");
	}
	
	@Override
	public void clearAll() {
		this.numWorkOrder.setText("");
		this.itemSerialNumber.setText("");
		this.description.setText("");
		this.plannedTime.setText("");
		this.plannedStart.setText("");
		this.plannedEnd.setText("");
		this.actualStart.setText("");
		this.actualEnd.setText("");
		this.numMaintenanceSchedule.setText("");
		this.codProject.setText("");
		this.triggerTextArea.setText("");
		this.clearWorkDescription();
	}

	@Override
	public boolean saveExecute() {
		this.execute.setItemSerialNumber(this.itemSerialNumber.getText());
		this.execute.setNumWorkOrder(this.numWorkOrder.getText());
		this.execute.setDescription(this.wDdescription.getText());
		if(this.dbController.newExecute(this.execute)) {
			System.out.println("Execute inserito correttamente");
			this.clearWorkDescription();
			return true;
		}
		return false;
	}

}
