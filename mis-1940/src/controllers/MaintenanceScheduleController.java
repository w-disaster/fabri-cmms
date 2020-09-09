package controllers;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Interface MaintenanceScheduleLogics.
 */
public interface MaintenanceScheduleController {
	
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
	void build(JTextField description, JTextField numMaintenanceSchedule, JTextField itemSerialNumber,
			JComboBox<String> workType, JTextArea workTypeArea, JTextArea tasks, JTextField wDDescription, DefaultTableModel dtm);

	/**
	 * Save maintenance schedule.
	 *
	 * @return true, if successful
	 */
	boolean saveMaintenanceSchedule();
	
	/**
	 * Save plan.
	 *
	 * @return true, if successful
	 */
	boolean savePlan();
	
	/**
	 * Clear all.
	 */
	void clearAll();
}
