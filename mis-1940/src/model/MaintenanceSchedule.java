package model;

// TODO: Auto-generated Javadoc
/**
 * The Class MaintenanceSchedule.
 */
public class MaintenanceSchedule {

	/** The item serial number. */
	private String itemSerialNumber;
	
	/** The num maintenance schedule. */
	private String numMaintenanceSchedule;
	
	/** The description. */
	private String description;
	
	/** The tasks. */
	private String tasks;
	
	/** The by time. */
	private String byTime;
	
	/** The by usage. */
	private String byUsage;
	
	/** The by prediction. */
	private String byPrediction;
	
	/** The by condition. */
	private String byCondition;
	
	/** The by run to fail. */
	private String byRunToFail;
	
	/**
	 * Gets the item serial number.
	 *
	 * @return the item serial number
	 */
	public String getItemSerialNumber() {
		return itemSerialNumber;
	}
	
	/**
	 * Sets the item serial number.
	 *
	 * @param itemSerialNumber the new item serial number
	 */
	public void setItemSerialNumber(String itemSerialNumber) {
		this.itemSerialNumber = itemSerialNumber;
	}
	
	/**
	 * Gets the num maintenance schedule.
	 *
	 * @return the num maintenance schedule
	 */
	public String getNumMaintenanceSchedule() {
		return numMaintenanceSchedule;
	}
	
	/**
	 * Sets the num maintenance schedule.
	 *
	 * @param numMaintenanceSchedule the new num maintenance schedule
	 */
	public void setNumMaintenanceSchedule(String numMaintenanceSchedule) {
		this.numMaintenanceSchedule = numMaintenanceSchedule;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the tasks.
	 *
	 * @return the tasks
	 */
	public String getTasks() {
		return tasks;
	}
	
	/**
	 * Sets the tasks.
	 *
	 * @param tasks the new tasks
	 */
	public void setTasks(String tasks) {
		this.tasks = tasks;
	}
	
	/**
	 * Gets the by time.
	 *
	 * @return the by time
	 */
	public String getByTime() {
		return byTime;
	}
	
	/**
	 * Sets the by time.
	 *
	 * @param byTime the new by time
	 */
	public void setByTime(String byTime) {
		this.byTime = byTime;
		this.byUsage = null;
		this.byPrediction = null;
		this.byCondition = null;
		this.byRunToFail = null;
	}
	
	/**
	 * Gets the by usage.
	 *
	 * @return the by usage
	 */
	public String getByUsage() {
		return byUsage;
	}
	
	/**
	 * Sets the by usage.
	 *
	 * @param byUsage the new by usage
	 */
	public void setByUsage(String byUsage) {
		this.byUsage = byUsage;
		this.byTime = null;
		this.byPrediction = null;
		this.byCondition = null;
		this.byRunToFail = null;
	}
	
	/**
	 * Gets the by prediction.
	 *
	 * @return the by prediction
	 */
	public String getByPrediction() {
		return byPrediction;
	}
	
	/**
	 * Sets the by prediction.
	 *
	 * @param byPrediction the new by prediction
	 */
	public void setByPrediction(String byPrediction) {
		this.byPrediction = byPrediction;
		this.byTime = null;
		this.byUsage = null;
		this.byCondition = null;
		this.byRunToFail = null;
	}
	
	/**
	 * Gets the by condition.
	 *
	 * @return the by condition
	 */
	public String getByCondition() {
		return byCondition;
	}
	
	/**
	 * Sets the by condition.
	 *
	 * @param byCondition the new by condition
	 */
	public void setByCondition(String byCondition) {
		this.byCondition = byCondition;
		this.byTime = null;
		this.byUsage = null;
		this.byPrediction = null;
		this.byRunToFail = null;
	}
	
	/**
	 * Gets the by run to fail.
	 *
	 * @return the by run to fail
	 */
	public String getByRunToFail() {
		return byRunToFail;
	}
	
	/**
	 * Sets the by run to fail.
	 *
	 * @param byRunToFail the new by run to fail
	 */
	public void setByRunToFail(String byRunToFail) {
		this.byRunToFail = byRunToFail;
		this.byTime = null;
		this.byUsage = null;
		this.byPrediction = null;
		this.byCondition = null;
	}
	
}
