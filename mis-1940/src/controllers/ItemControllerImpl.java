package controllers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Item;
import model.Pair;
import model.Sale;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemLogicsImpl.
 */
public class ItemControllerImpl implements ItemController {

	/** The serial NO. */
	private JTextField serialNO;
	
	/** The name. */
	private JTextField name;
	
	/** The model. */
	private JTextField model;
	
	/** The maker. */
	private JTextField maker;
	
	/** The dimension. */
	private JTextField dimension;
	
	/** The year. */
	private JTextField year;
	
	/** The unit. */
	private JComboBox<String>unit;
	
	/** The family. */
	private JComboBox<String>family; 
	
	/** The subfamily. */
	private JComboBox<String>subfamily;
	
	/** The city. */
	private JComboBox<String>city;
	
	/** The address. */
	private JComboBox<String>address;
	
	/** The adr. */
	private JTextField adr;
	
	/** The supplier. */
	private JComboBox<String>supplier;
	
	/** The date conclusion. */
	private JTextField dateConclusion;
	
	/** The expiration. */
	private JTextField expiration;
	
	/** The num warranty. */
	private JTextField numWarranty;
	
	/** The date. */
	private JTextField date;
	
	/** The record. */
	private JTextField record;
	
	/** The notes. */
	private JTextField notes;
	
	/** The description. */
	private JTextArea description;
	
	/** The specifications. */
	private JTextArea specifications;
	
	/** The item. */
	private Item item;
	
	/** The sale. */
	private Sale sale;
	
	/** The dtm. */
	private DefaultTableModel dtm;
	
	/** The db controller. */
	private DBMSController dbController;
	
	/**
	 * Instantiates a new item logics impl.
	 *
	 * @param dbController the db controller
	 */
	public ItemControllerImpl(DBMSController dbController) {
		super();
		this.dbController = dbController;
		this.item = new Item();
		this.sale = new Sale();
	}

	/**
	 * Builds the attributes.
	 *
	 * @param serialNO the serial NO
	 * @param name the name
	 * @param model the model
	 * @param maker the maker
	 * @param dimension the dimension
	 * @param year the year
	 * @param unit the unit
	 * @param adr the adr
	 */
	@Override
	public void buildAttributes(JTextField serialNO, JTextField name, JTextField model, JTextField maker,
			JTextField dimension, JTextField year, JComboBox<String>unit, JTextField adr) {
		this.serialNO = serialNO;
		this.name = name;
		this.model = model;
		this.maker = maker;
		this.dimension = dimension;
		this.year = year;
		this.unit = unit;
		this.adr = adr;
	}
	
	/**
	 * Builds the foreign keys.
	 *
	 * @param family the family
	 * @param subfamily the subfamily
	 * @param city the city
	 * @param address the address
	 * @param supplier the supplier
	 */
	@Override
	public void buildForeignKeys(JComboBox<String>family, JComboBox<String>subfamily, JComboBox<String>city, JComboBox<String>address,
			JComboBox<String>supplier) {
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
	
	/**
	 * Builds the optionals.
	 *
	 * @param dateConclusion the date conclusion
	 * @param expiration the expiration
	 * @param numWarranty the num warranty
	 * @param date the date
	 * @param record the record
	 * @param notes the notes
	 */
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
	
	/**
	 * Builds the im txt table.
	 *
	 * @param description the description
	 * @param specifications the specifications
	 * @param dtm the dtm
	 */
	@Override
	public void buildImTxtTable(JTextArea description, JTextArea specifications, DefaultTableModel dtm) {
		this.description = description;
		this.specifications = specifications;
		this.dtm = dtm;
		CommonQueries.updateTable(this.dbController, this.dtm, "SELECT * FROM ITEMS");
	}
	
	/**
	 * Save.
	 *
	 * @return true, if successful
	 */
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
	
	
	
	/**
	 * Gets the city addr keys.
	 *
	 * @return the city addr keys
	 */
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
	
	/**
	 * Gets the fam subfam.
	 *
	 * @return the fam subfam
	 */
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
	
	/**
	 * Gets the unit key.
	 *
	 * @return the unit key
	 */
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
		
	/**
	 * Clear.
	 */
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
	
	/**
	 * Filter address.
	 *
	 * @param city the city
	 * @return the list
	 */
	@Override
	public List<String> filterAddress(JComboBox<String>city) {
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

	/**
	 * Filter subfamily.
	 *
	 * @param family the family
	 * @return the list
	 */
	@Override
	public List<String> filterSubfamily(JComboBox<String>family) {
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
	


	/**
	 * Upload image.
	 *
	 * @param imagePath the image path
	 */
	@Override
	public void uploadImage(String imagePath) {
		this.item.setImage(imagePath);
	}

	/**
	 * Upload file.
	 *
	 * @param filePath the file path
	 */
	@Override
	public void uploadFile(String filePath) {
		this.item.setFile(filePath);
	}

	
}
