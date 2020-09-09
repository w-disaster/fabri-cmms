package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import model.Pair;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonQueries.
 */
public class CommonQueries {
	
	/**
	 * Gets the supplier key.
	 *
	 * @param dbController the db controller
	 * @param supplier the supplier
	 * @return the supplier key
	 */
	public static String getSupplierKey(DBMSController dbController, Object supplier) {
		String result = "";
		Connection c = dbController.getConnection();
		Statement statement = null;
		try {
			statement = c.createStatement();
			String query = "SELECT CodSupplier "
					+ "FROM SUPPLIERS "
					+ "WHERE Name = '"
					+ supplier.toString() + "'";
	
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()) {
				result = rs.getString(1);
			}
			
		} catch(SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	
	/**
	 * Gets the spare part key.
	 *
	 * @param dbController the db controller
	 * @param sparePart the spare part
	 * @return the spare part key
	 */
	public static Pair<String, String> getSparePartKey(DBMSController dbController, Object sparePart) {
		Pair<String, String> result = new Pair<>("", "");
		Connection c = dbController.getConnection();
		Statement statement = null;
		try {
			statement = c.createStatement();
			String query = "SELECT SerialNO, NumSparePart "
					+ "FROM SPARE_PARTS "
					+ "WHERE Name = '"
					+ sparePart.toString() + "'";
	
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()) {
				result = new Pair<>(rs.getString(1), rs.getString(2));
			}
			
		} catch(SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	
	/**
	 * Update table.
	 *
	 * @param dbController the db controller
	 * @param dtm the dtm
	 * @param query the query
	 */
	public static void updateTable(DBMSController dbController, DefaultTableModel dtm, String query) {
		Connection c = dbController.getConnection();
		System.out.println(query);
		Statement statement = null;
		for(int i = dtm.getRowCount() - 1; i >= 0; i--) {
			dtm.removeRow(i);
		}
		try {
			statement = c.createStatement();
			ResultSet rs = statement.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i = 1; rs.next(); ++i) {
				String s[] = new String[rsmd.getColumnCount()];
				for(int k = 1; k <= rsmd.getColumnCount(); ++k) {
					s[k-1] = rs.getString(k);
				}
				dtm.insertRow(i-1, s);				
			}
			
		} catch(SQLException e) {
			System.out.println(e);
		}
	}

}
