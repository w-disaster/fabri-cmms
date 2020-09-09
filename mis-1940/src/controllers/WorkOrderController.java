package controllers;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Interface WorkOrderLogics.
 */
public interface WorkOrderController {

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
	void buildWorkOrder(JTextField numWorkOrder, JTextField itemSerialNumber, JTextField description, JTextField plannedTime,
			JTextField plannedStart, JTextField plannedEnd, JComboBox<String> urgencyLevel,
			JTextField actualStart, JTextField actualEnd, JTextField wDdescription);
	
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
	void buildWorkOrderType(JComboBox<String> workType, JTextField numMaintenanceSchedule, JTextField codProject,
			JComboBox<String> cmbTrigger, JTextArea triggerTextArea, DefaultTableModel dtm);
	
	/**
	 * Save work order.
	 *
	 * @return true, if successful
	 */
	boolean saveWorkOrder();
	
	/**
	 * Save execute.
	 *
	 * @return true, if successful
	 */
	boolean saveExecute();
	
	/**
	 * Clear all.
	 */
	void clearAll();
	
}
