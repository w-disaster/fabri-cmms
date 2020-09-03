package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

public class DBControllerImpl implements DBController {
	
	private Connection connection;
	String dbName = "MIS";
	
	public DBControllerImpl() {
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
	
	@Override
	public Connection getConnection() {
		return this.connection;
	}

	@Override
	public boolean newPurchase(Purchase purchase) {
		String query = "INSERT INTO PURCHASES VALUES ("
				+ purchase.getCodSupplier() + ", "
				+ purchase.getBillNumber() + ", "
				+ "'" + purchase.getPurchaseDate() + "');";
		return this.executeInsert(query);
	}

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

	@Override
	public boolean newWorkDescription(WorkDescription workDescription) {
		String query = "INSERT INTO WORK_DESCRIPTIONS VALUES ("
				+ "'" + workDescription.getSerialNumber() + "', "
				+ "'" + workDescription.getDescription() + "', "
				+ "'" + workDescription.getTasks()+ "', "
				+ "'" + workDescription.getNotes() + "');";
		return this.executeInsert(query);
	}

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

	@Override
	public boolean newPlan(Plan plan) {
		String query = "INSERT INTO PLANS VALUES ("
				+ "'" + plan.getItemSerialNumber() + "', "
				+ plan.getNumMaintenanceSchedule() + ", "
				+ "'" + plan.getItemSerialNumber() + "', "
				+ "'" + plan.getDescription() + "');";
		return this.executeInsert(query);
	}

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
