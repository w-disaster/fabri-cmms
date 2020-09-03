package model;
;

public class WorkOrder {
	
	private String itemSerialNumber;
	private String numWorkOrder;
	private String description;
	private String plannedTime;
	private String plannedStart;
	private String plannedEnd;
	private String actualStart;
	private String actualEnd;
	private String type;
	private String breakdownPicture;
	private String corrective;
	private String codUrgency;
	private String codProject;
	private String numMaintenanceSchedule;
	
	public String getItemSerialNumber() {
		return itemSerialNumber;
	}
	public void setItemSerialNumber(String itemSerialNumber) {
		this.itemSerialNumber = itemSerialNumber;
	}
	public String getNumWorkOrder() {
		return numWorkOrder;
	}
	public void setNumWorkOrder(String numWorkOrder) {
		this.numWorkOrder = numWorkOrder;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlannedTime() {
		return plannedTime;
	}
	public void setPlannedTime(String plannedTime) {
		this.plannedTime = plannedTime;
	}
	public String getPlannedStart() {
		return plannedStart;
	}
	public void setPlannedStart(String plannedStart) {
		this.plannedStart = plannedStart;
	}
	public String getPlannedEnd() {
		return plannedEnd;
	}
	public void setPlannedEnd(String plannedEnd) {
		this.plannedEnd = plannedEnd;
	}
	public String getActualStart() {
		return actualStart;
	}
	public void setActualStart(String actualStart) {
		this.actualStart = actualStart;
	}
	public String getActualEnd() {
		return actualEnd;
	}
	public void setActualEnd(String actualEnd) {
		this.actualEnd = actualEnd;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBreakdownPicture() {
		return this.breakdownPicture;
	}
	public String getCorrective() {
		return corrective;
	}
	public void setCorrective(String corrective) {
		this.corrective = corrective;
	}
	public String getCodUrgency() {
		return codUrgency;
	}
	public void setCodUrgency(String codUrgency) {
		this.codUrgency = codUrgency;
	}
	public String getCodProject() {
		return codProject;
	}
	public void setCodProject(String codProject) {
		this.codProject = codProject;
	}
	public String getNumMaintenanceSchedule() {
		return numMaintenanceSchedule;
	}
	public void setNumMaintenanceSchedule(String numMaintenanceSchedule) {
		this.numMaintenanceSchedule = numMaintenanceSchedule;
	}
	
}
