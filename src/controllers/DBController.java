package controllers;

import java.sql.Connection;

import model.Execute;
import model.Item;
import model.MaintenanceSchedule;
import model.ManHours;
import model.Plan;
import model.Project;
import model.Purchase;
import model.Sale;
import model.Stock;
import model.Uses;
import model.WorkDescription;
import model.WorkOrder;

public interface DBController {

	boolean newItem(Item item);
	
	boolean newSale(Sale sale);
	
	boolean newPurchase(Purchase purchase);
	
	boolean newStock(Stock stock);
	
	boolean newWorkDescription(WorkDescription workDescription);
	
	boolean newUses(Uses uses);
	
	boolean newManHours(ManHours manHours);
	
	boolean newMaintenanceSchedule(MaintenanceSchedule maintenanceSchedule);
	
	boolean newPlan(Plan plan);
	
	boolean newProject(Project project);
	
	boolean newWorkOrder(WorkOrder workOrder);
	
	boolean newExecute(Execute execute);
	
	Connection getConnection();

	
}
