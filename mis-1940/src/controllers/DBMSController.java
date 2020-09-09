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

// TODO: Auto-generated Javadoc
/**
 * The Interface DBController.
 */
public interface DBMSController {

	/**
	 * New item.
	 *
	 * @param item the item
	 * @return true, if successful
	 */
	boolean newItem(Item item);
	
	/**
	 * New sale.
	 *
	 * @param sale the sale
	 * @return true, if successful
	 */
	boolean newSale(Sale sale);
	
	/**
	 * New purchase.
	 *
	 * @param purchase the purchase
	 * @return true, if successful
	 */
	boolean newPurchase(Purchase purchase);
	
	/**
	 * New stock.
	 *
	 * @param stock the stock
	 * @return true, if successful
	 */
	boolean newStock(Stock stock);
	
	/**
	 * New work description.
	 *
	 * @param workDescription the work description
	 * @return true, if successful
	 */
	boolean newWorkDescription(WorkDescription workDescription);
	
	/**
	 * New uses.
	 *
	 * @param uses the uses
	 * @return true, if successful
	 */
	boolean newUses(Uses uses);
	
	/**
	 * New man hours.
	 *
	 * @param manHours the man hours
	 * @return true, if successful
	 */
	boolean newManHours(ManHours manHours);
	
	/**
	 * New maintenance schedule.
	 *
	 * @param maintenanceSchedule the maintenance schedule
	 * @return true, if successful
	 */
	boolean newMaintenanceSchedule(MaintenanceSchedule maintenanceSchedule);
	
	/**
	 * New plan.
	 *
	 * @param plan the plan
	 * @return true, if successful
	 */
	boolean newPlan(Plan plan);
	
	/**
	 * New project.
	 *
	 * @param project the project
	 * @return true, if successful
	 */
	boolean newProject(Project project);
	
	/**
	 * New work order.
	 *
	 * @param workOrder the work order
	 * @return true, if successful
	 */
	boolean newWorkOrder(WorkOrder workOrder);
	
	/**
	 * New execute.
	 *
	 * @param execute the execute
	 * @return true, if successful
	 */
	boolean newExecute(Execute execute);
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	Connection getConnection();

	
}
