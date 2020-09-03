package controllers;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Project;

public class ProjectLogicsImpl implements ProjectLogics {
	
	private JTextField description;
	private JTextField codProject;
	private JComboBox workType;
	private JTextArea workTypeArea;
	private JTextArea tasksArea;
	private DefaultTableModel dtmProjects;
	private DefaultTableModel workOrders;
	private DBController dbController;
	private Project project;
	
	public ProjectLogicsImpl(DBController dbController) {
		super();
		this.dbController = dbController;
		this.project = new Project();
	}

	@Override
	public void build(JTextField description, JTextField codProject, JComboBox workType, JTextArea workTypeArea,
			JTextArea tasksArea, DefaultTableModel dtmProjects, DefaultTableModel workOrders) {
		this.description = description;
		this.codProject = codProject;
		this.workType = workType;
		this.workTypeArea = workTypeArea;
		this.tasksArea = tasksArea;
		this.dtmProjects = dtmProjects;
		this.workOrders = workOrders;
		CommonQueries.updateTable(this.dbController, this.dtmProjects, "SELECT * FROM PROJECT_SCHEDULES");
	}

	@Override
	public boolean saveProject() {
		this.project.setDescription(this.description.getText());
		this.project.setCodProject(this.codProject.getText());
		switch(this.workType.getSelectedItem().toString()) {
			case "Preventive (by time)":
				this.project.setByTime(this.workTypeArea.getText());
				break;
			case "Preventive (by Usage)":
				this.project.setByUsage(this.workTypeArea.getText());
				break;
			case "Predictive":
				this.project.setByPrediction(this.workTypeArea.getText());
				break;
			case "Condition based":
				this.project.setByPrediction(this.workTypeArea.getText());
				break;
			case "Run to fail":
				this.project.setByPrediction(this.workTypeArea.getText());
				break;
		}
		this.project.setTasks(this.tasksArea.getText());
		if(this.dbController.newProject(this.project)) {
			System.out.println("Project inserito correttamente");
			CommonQueries.updateTable(this.dbController, this.dtmProjects, "SELECT * FROM PROJECT_SCHEDULES");
			this.clearAll();
			return true;
		}
		return false;
	}
	
	private void clearAll() {
		this.description.setText("");
		this.codProject.setText("");
		this.workTypeArea.setText("");
	}

	@Override
	public void filterWorkOrders() {
		CommonQueries.updateTable(this.dbController, this.workOrders, 
				"SELECT * "
				+ "FROM WORK_ORDERS "
				+ "WHERE CodProject = " + this.codProject.getText() + "");
	}

}
