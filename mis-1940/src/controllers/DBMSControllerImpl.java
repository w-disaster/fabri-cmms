package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
 * The Class DBControllerImpl.
 */
public class DBMSControllerImpl implements DBMSController {
	
	/** The connection. */
	private Connection connection;
	
	/** The db name. */
	String dbName = "MIS";
	
	/**
	 * Instantiates a new DB controller impl.
	 */
	public DBMSControllerImpl() {
    	//MySQL locale
    	
       String driver = "com.mysql.cj.jdbc.Driver";
       String dbUri = "jdbc:mysql://localhost:3306/"+ this.dbName + 
    		   "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   	   String userName = "root";
   	   String password = "password";
        
       Connection connection = null;
        try {
            System.out.println("DataSource.getConnection() driver = " + driver);
            Class.forName(driver);
            System.out.println("DataSource.getConnection() dbUri = " + dbUri);
            connection = DriverManager.getConnection(dbUri, userName, password);
        }
        catch (ClassNotFoundException e) {
            new Exception(e.getMessage());
            System.out.println("Errore "+ e.getMessage());
        }
        catch(SQLException e) {
            new Exception(e.getMessage());
            System.out.println("Errore"+ e.getMessage());
        }
        this.connection = connection;
    }
	
	/**
	 * Execute insert.
	 *
	 * @param query the query
	 * @return true, if successful
	 */
	private boolean executeInsert(String query) {
		int result = 0;
		System.out.println(query);
		try {
			Statement statement = this.connection.createStatement();
			result = statement.executeUpdate(query);
		} catch (SQLException e) {
        	new Exception(e.getMessage());
            System.out.println("Errore"+ e.getMessage());
        }	
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * New item.
	 *
	 * @param item the item
	 * @return true, if successful
	 */
	@Override
	public boolean newItem(Item item) {
		String query = "INSERT INTO ITEMS VALUES ("
				+ "'" + item.getSerialNO() + "', " 
				+ "'" + item.getName() + "', "
				+ "'" + item.getImage() + "', "
				+ "'" + item.getFile() + "', "
				+ "'" + item.getDescription() + "', "
				+ "'" + item.getSpecifications() + "', "
				+ item.getAdr() + ", " 
				+ "'" + item.getModel() + "', "
				+ "'" + item.getMaker() + "', "
				+ "'" + item.getDimension() + "', "
				+ "'" + item.getYear() + "', "
				+ item.getCodFamily() + ", "
				+ item.getCodSubfamily() + ", "
				+ "" + item.getUnit() + ", "
				+ "'" + item.getZipCode() + "', "
				+ item.getCodAddress() + ");";
		return this.executeInsert(query);
	}
	
	/**
	 * New sale.
	 *
	 * @param sale the sale
	 * @return true, if successful
	 */
	@Override
	public boolean newSale(Sale sale) {
		String numWarrantyInsert =  sale.getNumWarranty().equals("NULL") ? "NULL, " : "'" + sale.getNumWarranty() + "', ";
		String warrantyDate =  sale.getDate().equals("NULL") ? "NULL, " : "'" + sale.getDate() + "', ";
		String warrantyNotes =  sale.getNotes().equals("NULL") ? "NULL, " : "'" + sale.getNotes() + "', ";
		String warrantyRecord =  sale.getRecord().equals("NULL") ? "NULL, " : sale.getRecord() + ", ";
		String contractConclusion =  sale.getDateConclusion().equals("NULL") ? "NULL, " : "'" + sale.getDateConclusion()+ "', ";
		String contractExpiration =  sale.getExpiration().equals("NULL") ? "NULL, " : "'" + sale.getExpiration() + "', ";


		String query = "INSERT INTO SALES VALUES ("
				+ "'" + sale.getItemSerialNumber() + "', "
				+ numWarrantyInsert
				+ warrantyDate
				+ warrantyRecord
				+ warrantyNotes
				+ "'" + sale.getWarrantyFile() + "', "
				+ contractConclusion
				+ contractExpiration
				+ sale.getCodSupplier() + ");";
		return this.executeInsert(query);
	}
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	@Override
	public Connection getConnection() {
		return this.connection;
	}

	/**
	 * New purchase.
	 *
	 * @param purchase the purchase
	 * @return true, if successful
	 */
	@Override
	public boolean newPurchase(Purchase purchase) {
		String query = "INSERT INTO PURCHASES VALUES ("
				+ purchase.getCodSupplier() + ", "
				+ purchase.getBillNumber() + ", "
				+ "'" + purchase.getPurchaseDate() + "');";
		return this.executeInsert(query);
	}

	/**
	 * New stock.
	 *
	 * @param stock the stock
	 * @return true, if successful
	 */
	@Override
	public boolean newStock(Stock stock) {
		String query = "INSERT INTO STOCKS VALUES ("
				+ "'" + stock.getItemSerialNumber() + "', "
				+ stock.getNumSparePart() + ", "
				+ stock.getCodSupplier() + ", "
				+ stock.getBillNumber() + ", "
				+ stock.getAmount() + ", "
				+ stock.getUnitCost() + ", "
				+ "'" + stock.getPicture() + "', "
				+ "'" + stock.getNotes() + "', "
				+ stock.getCodWarehouse() + ");";
		return this.executeInsert(query);
	}

	/**
	 * New work description.
	 *
	 * @param workDescription the work description
	 * @return true, if successful
	 */
	@Override
	public boolean newWorkDescription(WorkDescription workDescription) {
		String query = "INSERT INTO WORK_DESCRIPTIONS VALUES ("
				+ "'" + workDescription.getSerialNumber() + "', "
				+ "'" + workDescription.getDescription() + "', "
				+ "'" + workDescription.getTasks()+ "', "
				+ "'" + workDescription.getNotes() + "');";
		return this.executeInsert(query);
	}

	/**
	 * New uses.
	 *
	 * @param uses the uses
	 * @return true, if successful
	 */
	@Override
	public boolean newUses(Uses uses) {
		String query = "INSERT INTO USES VALUES ("
				+ uses.getCodSupplier() + ", "
				+ uses.getBillNumber() + ", "
				+ "'" + uses.getItemSerialNumber() + "', "
				+ uses.getNumSparePart() + ", "
				+ "'" + uses.getItemSerialNumber() + "', "
				+ "'" + uses.getDescription() + "', "
				+ uses.getAmount() + ");";
		return this.executeInsert(query);
	}

	/**
	 * New man hours.
	 *
	 * @param manHours the man hours
	 * @return true, if successful
	 */
	@Override
	public boolean newManHours(ManHours manHours) {
		String query = "INSERT INTO MAN_HOURS VALUES ("
				+ "'" + manHours.getSerialNumber() + "', "
				+ "'" + manHours.getDescription() + "', "
				+ "'" + manHours.getFiscalCode() + "', "
				+ manHours.getHours()  + ", "
				+ manHours.getCostPerHour() + ");";	
		return this.executeInsert(query);
	}

	/**
	 * New maintenance schedule.
	 *
	 * @param maintenanceSchedule the maintenance schedule
	 * @return true, if successful
	 */
	@Override
	public boolean newMaintenanceSchedule(MaintenanceSchedule maintenanceSchedule) {
		String query = "INSERT INTO MAINTENANCE_SCHEDULES VALUES ("
				+ "'" + maintenanceSchedule.getItemSerialNumber() + "', "
				+ maintenanceSchedule.getNumMaintenanceSchedule() + ", "
				+ "'" + maintenanceSchedule.getDescription() + "', "
				+ "'" + maintenanceSchedule.getTasks() + "', "
				+ (maintenanceSchedule.getByTime() == null ? "NULL, " : "'" + maintenanceSchedule.getByTime() + "', ")
				+ (maintenanceSchedule.getByUsage() == null ? "NULL, " : "'" + maintenanceSchedule.getByUsage() + "', ")
				+ (maintenanceSchedule.getByPrediction() == null ? "NULL, " : "'" + maintenanceSchedule.getByPrediction() + "', ")
				+ (maintenanceSchedule.getByCondition() == null ? "NULL, " : "'" + maintenanceSchedule.getByCondition() + "', ")
				+ (maintenanceSchedule.getByRunToFail() == null ? "NULL" : "'" + maintenanceSchedule.getByRunToFail() + "'")
				+ ");";
		return this.executeInsert(query);
	}

	/**
	 * New plan.
	 *
	 * @param plan the plan
	 * @return true, if successful
	 */
	@Override
	public boolean newPlan(Plan plan) {
		String query = "INSERT INTO PLANS VALUES ("
				+ "'" + plan.getItemSerialNumber() + "', "
				+ plan.getNumMaintenanceSchedule() + ", "
				+ "'" + plan.getItemSerialNumber() + "', "
				+ "'" + plan.getDescription() + "');";
		return this.executeInsert(query);
	}

	/**
	 * New project.
	 *
	 * @param project the project
	 * @return true, if successful
	 */
	@Override
	public boolean newProject(Project project) {
		String query = "INSERT INTO PROJECT_SCHEDULES VALUES ("
				+ project.getCodProject() + ", "
				+ "'" + project.getDescription() + "', "
				+ "'" + project.getTasks() + "', "
				+ (project.getByTime() == null ? "NULL, " : "'" + project.getByTime() + "', ")
				+ (project.getByUsage() == null ? "NULL, " : "'" + project.getByUsage() + "', ")
				+ (project.getByPrediction() == null ? "NULL, " : "'" + project.getByPrediction() + "', ")
				+ (project.getByCondition() == null ? "NULL, " : "'" + project.getByCondition() + "', ")
				+ (project.getByRunToFail() == null ? "NULL" : "'" + project.getByRunToFail() + "'")
				+ ");";
		return this.executeInsert(query);
	}

	/**
	 * New work order.
	 *
	 * @param workOrder the work order
	 * @return true, if successful
	 */
	@Override
	public boolean newWorkOrder(WorkOrder workOrder) {
		String query = "INSERT INTO WORK_ORDERS VALUES ("
				+ "'" + workOrder.getItemSerialNumber() + "', "
				+ workOrder.getNumWorkOrder() + ", "
				+ "'" +  workOrder.getDescription() + "', "
				+ (workOrder.getPlannedTime().equals("") ? "NULL, " : "'" + workOrder.getPlannedTime() + "', ")
				+ (workOrder.getPlannedStart().equals("") ? "NULL, " : "'" + workOrder.getPlannedStart() + "', ")
				+ (workOrder.getPlannedEnd().equals("") ? "NULL, " : "'" + workOrder.getPlannedEnd() + "', ")
				+ (workOrder.getActualStart().equals("") ? "NULL, " : "'" + workOrder.getActualStart() + "', ")
				+(workOrder.getActualEnd().equals("") ? "NULL, " : "'" + workOrder.getActualEnd() + "', ")
				+ "'" +  workOrder.getType() + "', "
				+ "NULL, "				
				+ (workOrder.getCorrective() == null ? "NULL, " : "'" + workOrder.getCorrective() + "', ")
				+ workOrder.getCodUrgency() + ", "
				+ (workOrder.getCodProject() == null ? "NULL, " : workOrder.getCodProject() + ", ")
				+ "'" + workOrder.getItemSerialNumber() + "', "
				+ (workOrder.getNumMaintenanceSchedule() == null ? "NULL" :  workOrder.getNumMaintenanceSchedule())
				+ ");";
		return this.executeInsert(query);
	}

	/**
	 * New execute.
	 *
	 * @param execute the execute
	 * @return true, if successful
	 */
	@Override
	public boolean newExecute(Execute execute) {
		String query = "INSERT INTO EXECUTIONS VALUES ("
				+ "'" + execute.getItemSerialNumber() + "', "
				+ "" + execute.getNumWorkOrder() + ", "
				+ "'" + execute.getItemSerialNumber() + "', "
				+ "'" + execute.getDescription() + "');";
		return this.executeInsert(query);
	}

}
