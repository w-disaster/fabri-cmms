package controllers;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Interface ProjectLogics.
 */
public interface ProjectController {
	
	/**
	 * Builds the.
	 *
	 * @param description the description
	 * @param codProject the cod project
	 * @param workType the work type
	 * @param workTypeArea the work type area
	 * @param tasksArea the tasks area
	 * @param dtmProjects the dtm projects
	 * @param workOrders the work orders
	 */
	void build(JTextField description, JTextField codProject, JComboBox<String> workType, JTextArea workTypeArea,
			JTextArea tasksArea, DefaultTableModel dtmProjects, DefaultTableModel workOrders);

	/**
	 * Save project.
	 *
	 * @return true, if successful
	 */
	boolean saveProject();
	
	/**
	 * Filter work orders.
	 */
	void filterWorkOrders();
}
