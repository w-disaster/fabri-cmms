package controllers;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public interface MaintenanceScheduleLogics {
	
	void build(JTextField description, JTextField numMaintenanceSchedule, JTextField itemSerialNumber,
			JComboBox workType, JTextArea workTypeArea, JTextArea tasks, JTextField wDDescription, DefaultTableModel dtm);

	boolean saveMaintenanceSchedule();
	
	boolean savePlan();
	
	void clearAll();
}
