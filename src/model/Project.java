package model;

public class Project {
	
	private String codProject;
	private String description;
	private String tasks;
	private String byTime;
	private String byUsage;
	private String byPrediction;
	private String byCondition;
	private String byRunToFail;
	
	public String getCodProject() {
		return codProject;
	}
	public void setCodProject(String codProject) {
		this.codProject = codProject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTasks() {
		return tasks;
	}
	public void setTasks(String tasks) {
		this.tasks = tasks;
	}
	public String getByTime() {
		return byTime;
	}
	public void setByTime(String byTime) {
		this.byTime = byTime;
		this.byUsage = null;
		this.byPrediction = null;
		this.byCondition = null;
		this.byRunToFail = null;
	}
	public String getByUsage() {
		return byUsage;
	}
	public void setByUsage(String byUsage) {
		this.byUsage = byUsage;
		this.byTime = null;
		this.byPrediction = null;
		this.byCondition = null;
		this.byRunToFail = null;
	}
	public String getByPrediction() {
		return byPrediction;
	}
	public void setByPrediction(String byPrediction) {
		this.byPrediction = byPrediction;
		this.byTime = null;
		this.byUsage = null;
		this.byCondition = null;
		this.byRunToFail = null;
	}
	public String getByCondition() {
		return byCondition;
	}
	public void setByCondition(String byCondition) {
		this.byCondition = byCondition;
		this.byTime = null;
		this.byUsage = null;
		this.byPrediction = null;
		this.byRunToFail = null;
	}
	public String getByRunToFail() {
		return byRunToFail;
	}
	public void setByRunToFail(String byRunToFail) {
		this.byRunToFail = byRunToFail;
		this.byTime = null;
		this.byUsage = null;
		this.byPrediction = null;
		this.byCondition = null;
	}

}