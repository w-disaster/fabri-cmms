package controllers;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public interface WorkOrderLogics {

	void buildWorkOrder(JTextField numWorkOrder, JTextField itemSerialNumber, JTextField description, JTextField plannedTime,
			JTextField plannedStart, JTextField plannedEnd, JComboBox urgencyLevel,
			JTextField actualStart, JTextField actualEnd, JTextField wDdescription);
	
	void buildWorkOrderType(JComboBox workType, JTextField numMaintenanceSchedule, JTextField codProject,
			JComboBox cmbTrigger, JTextArea triggerTextArea, DefaultTableModel dtm);
	
	boolean saveWorkOrder();
	
	boolean saveExecute();
	
	void clearAll();
	
}
