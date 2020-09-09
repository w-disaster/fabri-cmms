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

// TODO: Auto-generated Javadoc
/**
 * The Class WorkOrderLogicsImpl.
 */
public class WorkOrderControllerImpl implements WorkOrderController {
	
	/** The num work order. */
	private JTextField numWorkOrder;
	
	/** The item serial number. */
	private JTextField itemSerialNumber;
	
	/** The description. */
	private JTextField description;
	
	/** The planned time. */
	private JTextField plannedTime;
	
	/** The planned start. */
	private JTextField plannedStart;
	
	/** The planned end. */
	private JTextField plannedEnd;
	
	/** The urgency level. */
	private JComboBox<String> urgencyLevel;
	
	/** The actual start. */
	private JTextField actualStart;
	
	/** The actual end. */
	private JTextField actualEnd;
	
	/** The w ddescription. */
	private JTextField wDdescription;
	
	/** The work type. */
	private JComboBox<String> workType;
	
	/** The num maintenance schedule. */
	private JTextField numMaintenanceSchedule;
	
	/** The cod project. */
	private JTextField codProject;
	
	/** The cmb trigger. */
	private JComboBox<String> cmbTrigger;
	
	/** The trigger text area. */
	private JTextArea triggerTextArea;
	
	/** The dtm. */
	private DefaultTableModel dtm;
	
	/** The db controller. */
	private DBMSController dbController;
	
	/** The work order. */
	private WorkOrder workOrder;
	
	/** The execute. */
	private Execute execute;

	/**
	 * Instantiates a new work order logics impl.
	 *
	 * @param dbController the db controller
	 */
	public WorkOrderControllerImpl(DBMSController dbController) {
		super();
		this.dbController = dbController;
		this.workOrder = new WorkOrder();
		this.execute = new Execute();
	}

	/**
	 * Builds the work order.
	 *
	 * @param numWorkOrder the num work order
	 * @param itemSerialNumber the item serial number
	 * @param description the description
	 * @param plannedTime the planned time
	 * @param plannedStart the planned start
	 * @param plannedEnd the planned end
	 * @param urgencyLevel the urgency level
	 * @param actualStart the actual start
	 * @param actualEnd the actual end
	 * @param wDdescription the w ddescription
	 */
	@Override
	public void buildWorkOrder(JTextField numWorkOrder, JTextField itemSerialNumber, JTextField description,
			JTextField plannedTime, JTextField plannedStart, JTextField plannedEnd, JComboBox<String> urgencyLevel,
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

	/**
	 * Builds the work order type.
	 *
	 * @param workType the work type
	 * @param numMaintenanceSchedule the num maintenance schedule
	 * @param codProject the cod project
	 * @param cmbTrigger the cmb trigger
	 * @param triggerTextArea the trigger text area
	 * @param dtm the dtm
	 */
	@Override
	public void buildWorkOrderType(JComboBox<String> workType, JTextField numMaintenanceSchedule, JTextField codProject,
			JComboBox<String> cmbTrigger, JTextArea triggerTextArea, DefaultTableModel dtm) {
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
	
	/**
	 * Sets the types disabled.
	 */
	private void setTypesDisabled() {
		this.numMaintenanceSchedule.setEnabled(false);
		this.codProject.setEnabled(false);
		this.cmbTrigger.setEnabled(false);
		this.triggerTextArea.setEnabled(false);
	}

	/**
	 * Gets the urgency key.
	 *
	 * @param urgency the urgency
	 * @return the urgency key
	 */
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
	
	/**
	 * Save work order.
	 *
	 * @return true, if successful
	 */
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
	
	/**
	 * Clear work description.
	 */
	private void clearWorkDescription() {
		this.wDdescription.setText("");
	}
	
	/**
	 * Clear all.
	 */
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

	/**
	 * Save execute.
	 *
	 * @return true, if successful
	 */
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
