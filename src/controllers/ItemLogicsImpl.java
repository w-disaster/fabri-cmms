package controllers;

import java.awt.TextComponent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ibm.icu.util.Calendar;

import model.Item;
import model.Pair;
import model.Sale;

public class ItemLogicsImpl implements ItemLogics {

	private JTextField serialNO;
	private JTextField name;
	private JTextField model;
	private JTextField maker;
	private JTextField dimension;
	private JTextField year;
	private JComboBox unit;
	private JComboBox family; 
	private JComboBox subfamily;
	private JComboBox city;
	private JComboBox address;
	private JTextField adr;
	private JComboBox supplier;
	private JTextField dateConclusion;
	private JTextField expiration;
	private JTextField numWarranty;
	private JTextField date;
	private JTextField record;
	private JTextField notes;
	private JTextArea description;
	private JTextArea specifications;
	private Item item;
	private Sale sale;
	private DefaultTableModel dtm;
	private DBController dbController;
	
	public ItemLogicsImpl(DBController dbController) {
		super();
		this.dbController = dbController;
		this.item = new Item();
		this.sale = new Sale();
	}

	@Override
	public void buildAttributes(JTextField serialNO, JTextField name, JTextField model, JTextField maker,
			JTextField dimension, JTextField year, JComboBox unit, JTextField adr) {
		this.serialNO = serialNO;
		this.name = name;
		this.model = model;
		this.maker = maker;
		this.dimension = dimension;
		this.year = year;
		this.unit = unit;
		this.adr = adr;
	}
	
	@Override
	public void buildForeignKeys(JComboBox family, JComboBox subfamily, JComboBox city, JComboBox address,
			JComboBox supplier) {
		this.family = family;
		this.subfamily = subfamily;
		this.city = city;
		this.address = address;
		this.supplier = supplier;
		
		for(String s : filterSubfamily(this.family)) {
			this.subfamily.addItem(s);
		}
		this.family.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				subfamily.removeAllItems();
				for(String s : filterSubfamily(family)) {
					subfamily.addItem(s);
				}
			}
			
		});
		
		for(String s : filterAddress(this.city)) {
			this.address.addItem(s);
		}
		this.city.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				address.removeAllItems();
				for(String s : filterAddress(city)) {
					address.addItem(s);
				}
			}
			
		});
	}
	
	@Override
	public void buildOptionals(JTextField dateConclusion, JTextField expiration, JTextField numWarranty,
			JTextField date, JTextField record, JTextField notes) {
		this.dateConclusion = dateConclusion;
		this.expiration = expiration;
		this.numWarranty = numWarranty;
		this.date = date;
		this.record = record;
		this.notes = notes;	
	}
	
	@Override
	public void buildImTxtTable(JTextArea description, JTextArea specifications, DefaultTableModel dtm) {
		this.description = description;
		this.specifications = specifications;
		this.dtm = dtm;
		CommonQueries.updateTable(this.dbController, this.dtm, "SELECT * FROM ITEMS");
	}
	
	@Override
	public boolean save() {
		this.item.setSerialNO(this.serialNO.getText());
		this.item.setName(this.name.getText());
		this.item.setModel(this.model.getText());
		this.item.setMaker(this.maker.getText());
		this.item.setDimension(this.dimension.getText());
		this.item.setYear(this.year.getText());
		this.item.setUnit(this.getUnitKey());
		Pair<String, String> famSubfamKeys = this.getFamSubfam();
		this.item.setCodFamily(famSubfamKeys.getX());
		this.item.setCodSubfamily(famSubfamKeys.getY());
		Pair<String, String> cityAddrKeys = this.getCityAddrKeys();
		this.item.setZipCode(cityAddrKeys.getX());
		this.item.setCodAddress(cityAddrKeys.getY());
		this.item.setAdr(this.adr.getText());
		this.sale.setCodSupplier(CommonQueries.getSupplierKey(this.dbController, this.supplier.getSelectedItem()));	
		this.sale.setItemSerialNumber(this.serialNO.getText());
		String dc = this.dateConclusion.getText();
		this.sale.setDateConclusion(dc.equals("") ? "NULL" : dc);
		String exp = this.expiration.getText();
		this.sale.setExpiration(exp.equals("") ? "NULL" : exp);
		String war = this.numWarranty.getText();
		this.sale.setNumWarranty(war.equals("") ? "NULL" : war);
		String date = this.date.getText();
		this.sale.setDate(date.equals("") ? "NULL" : date);
		String record = this.record.getText();
		this.sale.setRecord(record.equals("") ? "NULL" : record);
		String notes = this.notes.getText();
		this.sale.setNotes(notes.equals("") ? "NULL" : notes);
		this.item.setDescription(this.description.getText());
		this.item.setSpecifications(this.specifications.getText());

		if(this.dbController.newItem(this.item) && this.dbController.newSale(this.sale)) {
			System.out.println("Macchinario inserito correttamente");
			this.clear();
			return true;
		}
		return false;
	}
	
	
	
	private Pair<String, String> getCityAddrKeys() {
		Pair<String, String> result = new Pair<>("", "");

		Connection c = this.dbController.getConnection();
		Statement statement = null;
		try {
			statement = c.createStatement();
			String query = "SELECT ZipCode, CodAddress "
					+ "FROM ADDRESSES "
					+ "WHERE Name = '"
					+ this.address.getSelectedItem() + "'";
	
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()) {
				result = new Pair<>(rs.getString(1), rs.getString(2));
			}
			
		} catch(SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	
	private Pair<String, String> getFamSubfam() {
		Pair<String, String> result = new Pair<>("", "");
		Connection c = this.dbController.getConnection();
		Statement statement = null;
		try {
			statement = c.createStatement();
			String query = "SELECT CodFamily, CodSubfamily "
					+ "FROM SUBFAMILIES "
					+ "WHERE Description = '"
					+ this.subfamily.getSelectedItem() + "'";
	
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()) {
				result = new Pair<>(rs.getString(1), rs.getString(2));
			}
			
		} catch(SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	
	private String getUnitKey() {
		String codUnit = "";
		Connection c = this.dbController.getConnection();
		Statement statement = null;
		try {
			statement = c.createStatement();
			String filterUnit = "SELECT CodUnit "
					+ "FROM UNITS "
					+ "WHERE Description = '"
					+ this.unit.getSelectedItem() + "'";
			System.out.println(filterUnit);
			ResultSet rs = statement.executeQuery(filterUnit);
			if(rs.next()) {
				codUnit = rs.getString(1);
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
		return codUnit;
			
	}
		
	private void clear() {
		this.serialNO.setText("");
		this.name.setText("");
		this.model.setText("");
		this.maker.setText("");
		this.dimension.setText("");
		this.year.setText("");
		this.adr.setText("");
		this.dateConclusion.setText("");
		this.expiration.setText("");
		this.numWarranty.setText("");
		this.date.setText("");
		this.record.setText("");
		this.notes.setText("");
		this.item.setImage("");
		this.item.setFile("");
		this.description.setText("");
		this.specifications.setText("");
		CommonQueries.updateTable(this.dbController, this.dtm, "SELECT * FROM ITEMS");
	}
	
	@Override
	public List<String> filterAddress(JComboBox city) {
		List<String> result = new ArrayList<>();
		Connection c = this.dbController.getConnection();
		Statement statement = null;
		try {
			statement = c.createStatement();
			String cityQuery = "SELECT ZipCode "
					+ "FROM CITIES "
					+ "WHERE Name = '"
					+ city.getSelectedItem() + "'";
	
			ResultSet rsCity = statement.executeQuery(cityQuery);
			if(rsCity.next()) {
				String addressQuery = "SELECT Name "
						+ "FROM ADDRESSES "
						+ "WHERE ZipCode = '"
						+ rsCity.getString(1) + "'";
				ResultSet rsAddress = statement.executeQuery(addressQuery);
				while(rsAddress.next()) {
					result.add(rsAddress.getString(1));
				}
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
		return result;
	}

	@Override
	public List<String> filterSubfamily(JComboBox family) {
		List<String> result = new ArrayList<>();
		Connection c = this.dbController.getConnection();
		Statement statement = null;
		try {
			statement = c.createStatement();
			String familyQuery = "SELECT CodFamily "
					+ "FROM FAMILIES "
					+ "WHERE Description = '"
					+ family.getSelectedItem() + "'";
	
			ResultSet rsFamily = statement.executeQuery(familyQuery);
			if(rsFamily.next()) {
				String subfamilyQuery = "SELECT Description "
						+ "FROM SUBFAMILIES "
						+ "WHERE CodFamily = "
						+ rsFamily.getString(1);
				ResultSet rsSubfamily = statement.executeQuery(subfamilyQuery);
				while(rsSubfamily.next()) {
					result.add(rsSubfamily.getString(1));
				}
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	


	@Override
	public void uploadImage(String imagePath) {
		this.item.setImage(imagePath);
	}

	@Override
	public void uploadFile(String filePath) {
		this.item.setFile(filePath);
	}

	
}
