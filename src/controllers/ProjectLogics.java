package controllers;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public interface ProjectLogics {
	
	void build(JTextField description, JTextField codProject, JComboBox workType, JTextArea workTypeArea,
			JTextArea tasksArea, DefaultTableModel dtmProjects, DefaultTableModel workOrders);

	boolean saveProject();
	
	void filterWorkOrders();
}
