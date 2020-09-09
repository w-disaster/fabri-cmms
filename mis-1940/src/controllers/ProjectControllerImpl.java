package controllers;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Project;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectLogicsImpl.
 */
public class ProjectControllerImpl implements ProjectController {
	
	/** The description. */
	private JTextField description;
	
	/** The cod project. */
	private JTextField codProject;
	
	/** The work type. */
	private JComboBox<String> workType;
	
	/** The work type area. */
	private JTextArea workTypeArea;
	
	/** The tasks area. */
	private JTextArea tasksArea;
	
	/** The dtm projects. */
	private DefaultTableModel dtmProjects;
	
	/** The work orders. */
	private DefaultTableModel workOrders;
	
	/** The db controller. */
	private DBMSController dbController;
	
	/** The project. */
	private Project project;
	
	/**
	 * Instantiates a new project logics impl.
	 *
	 * @param dbController the db controller
	 */
	public ProjectControllerImpl(DBMSController dbController) {
		super();
		this.dbController = dbController;
		this.project = new Project();
	}

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
	@Override
	public void build(JTextField description, JTextField codProject, JComboBox<String> workType, JTextArea workTypeArea,
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

	/**
	 * Save project.
	 *
	 * @return true, if successful
	 */
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
	
	/**
	 * Clear all.
	 */
	private void clearAll() {
		this.description.setText("");
		this.codProject.setText("");
		this.workTypeArea.setText("");
	}

	/**
	 * Filter work orders.
	 */
	@Override
	public void filterWorkOrders() {
		CommonQueries.updateTable(this.dbController, this.workOrders, 
				"SELECT * "
				+ "FROM WORK_ORDERS "
				+ "WHERE CodProject = " + this.codProject.getText() + "");
	}

}
