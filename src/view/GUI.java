package view;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;

import javax.swing.border.BevelBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import controllers.DBController;
import controllers.DBControllerImpl;
import controllers.ItemLogics;
import controllers.ItemLogicsImpl;
import controllers.MaintenanceScheduleLogics;
import controllers.MaintenanceScheduleLogicsImpl;
import controllers.ProjectLogics;
import controllers.ProjectLogicsImpl;
import controllers.SparePartLogics;
import controllers.SparePartLogicsImpl;
import controllers.WorkDescriptionLogics;
import controllers.WorkDescriptionLogicsImpl;
import controllers.WorkOrderLogics;
import controllers.WorkOrderLogicsImpl;

import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JCheckBox;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTable itemsTable;
	private JTextField txtItemSerialNO;
	private JTextField txtItemName;
	private String itemFile;
	private String itemImageFile;
	private JTextField txtItemADR;
	private JTextField txtItemModel;
	private JTextField txtItemMaker;
	private JTextField txtItemDimension;
	private JTextField txtItemYear;
	private JLabel lblItemImage;
	private JTextField txtMCDateConclusion;
	private JTextField txtMCExpiration;
	private JTextField txtNumWarranty;
	private JTextField txtWarrantyDate;
	private JTextField txtWarrantyRecord;
	private JTextField txtWarrantyNotes;
	private JTable table;
	private JTextField txtSPBillNumber;
	private JTextField txtSPPurchaseDate;
	private JTextField txtStockAmount;
	private JTextField txtSPUnitCost;
	private JTextField txtMSDescription;
	private JTextField txtNumMS;
	private JTextField txtMSSerialNO;
	private JTextField txtMSWorkOrderDescription;
	private JTable MSTable;
	private JTextField txtProjectDescription;
	private JTextField txtCodProject;
	private JTable registeredProjectsTable;
	private JTextField txtWOWDDescription;
	private JTextField txtNumWO;
	private JTextField txtWOSerialNO;
	private JTextField txtWODescription;
	private JTextField txtPlannedTimeMaintenance;
	private JTextField txtPlannedDate;
	private JTextField txtPlannedEnd;
	private JTextField txtActualStart;
	private JTextField txtActualEnd;
	private JTextField textWONumMS;
	private JTextField txtWOCodProject;
	private JTable workOrdersTable;
	private JTextField txtWDSerialNumber;
	private JTextField txtWDDescription;
	private JTextField txtWDBillNumber;
	private JTextField txtWDStockAmount;
	private JTextField txtMHHours;
	private JTextField txtMHCostHour;
	private JTable table_3;
	private DBController dbController;
	private ItemLogics itemLogics;
	private SparePartLogics sparePartLogics;
	private WorkDescriptionLogics workDescriptionLogics;
	private MaintenanceScheduleLogics msLogics;
	private ProjectLogics projectLogics;
	private WorkOrderLogics workOrderLogics;
	
	private JTable projectWOTable;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1131, 891);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// CONTROLLERS
		this.dbController = new DBControllerImpl();
		this.itemLogics = new ItemLogicsImpl(this.dbController);
		this.sparePartLogics = new SparePartLogicsImpl(this.dbController);
		this.workDescriptionLogics = new WorkDescriptionLogicsImpl(this.dbController);
		this.msLogics = new MaintenanceScheduleLogicsImpl(this.dbController);
		this.projectLogics = new ProjectLogicsImpl(this.dbController);
		this.workOrderLogics = new WorkOrderLogicsImpl(this.dbController);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1119, 848);
		contentPane.add(tabbedPane);
		
		// BEGIN ITEMS TAB
		JPanel itemPanel = new JPanel();
		tabbedPane.addTab("Items", null, itemPanel, null);
		itemPanel.setLayout(null);
		
		JPanel itemRegistrationPanel = new JPanel();
		itemRegistrationPanel.setLayout(null);
		itemRegistrationPanel.setBorder(new TitledBorder(null, "Item registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		itemRegistrationPanel.setBounds(10, 12, 1089, 423);
		itemPanel.add(itemRegistrationPanel);
		
		JLabel lblSerialNO = new JLabel("Serial Number");
		lblSerialNO.setBounds(31, 36, 116, 15);
		itemRegistrationPanel.add(lblSerialNO);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(90, 65, 66, 15);
		itemRegistrationPanel.add(lblName);
		
		JLabel lblADR = new JLabel("Avarage Daily Running (ADR)");
		lblADR.setBounds(31, 357, 204, 15);
		itemRegistrationPanel.add(lblADR);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setBounds(90, 94, 66, 15);
		itemRegistrationPanel.add(lblModel);
		
		JLabel lblMaker = new JLabel("Maker");
		lblMaker.setBounds(90, 123, 66, 15);
		itemRegistrationPanel.add(lblMaker);
		
		JLabel lblDimension = new JLabel("Dimension");
		lblDimension.setBounds(60, 152, 83, 15);
		itemRegistrationPanel.add(lblDimension);
		
		JLabel lblFamily = new JLabel("Family");
		lblFamily.setBounds(12, 239, 66, 15);
		itemRegistrationPanel.add(lblFamily);
		
		JLabel lblSubfamily = new JLabel("Subfamily");
		lblSubfamily.setBounds(63, 268, 77, 15);
		itemRegistrationPanel.add(lblSubfamily);
		
		txtItemSerialNO = new JTextField();
		txtItemSerialNO.setColumns(10);
		txtItemSerialNO.setBounds(146, 34, 124, 19);
		itemRegistrationPanel.add(txtItemSerialNO);
		
		txtItemName = new JTextField();
		txtItemName.setColumns(10);
		txtItemName.setBounds(146, 63, 124, 19);
		itemRegistrationPanel.add(txtItemName);
		
		txtItemADR = new JTextField();
		txtItemADR.setColumns(10);
		txtItemADR.setBounds(246, 355, 124, 19);
		itemRegistrationPanel.add(txtItemADR);
		
		txtItemModel = new JTextField();
		txtItemModel.setColumns(10);
		txtItemModel.setBounds(146, 92, 124, 19);
		itemRegistrationPanel.add(txtItemModel);
		
		txtItemMaker = new JTextField();
		txtItemMaker.setColumns(10);
		txtItemMaker.setBounds(146, 121, 124, 19);
		itemRegistrationPanel.add(txtItemMaker);
		
		txtItemDimension = new JTextField();
		txtItemDimension.setColumns(10);
		txtItemDimension.setBounds(146, 150, 124, 19);
		itemRegistrationPanel.add(txtItemDimension);
		
		JLabel lblItemYear = new JLabel("Year");
		lblItemYear.setBounds(105, 181, 66, 15);
		itemRegistrationPanel.add(lblItemYear);
		
		JLabel lblUnit = new JLabel("Unit");
		lblUnit.setBounds(105, 208, 77, 15);
		itemRegistrationPanel.add(lblUnit);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(77, 328, 83, 15);
		itemRegistrationPanel.add(lblAddress);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(105, 297, 66, 15);
		itemRegistrationPanel.add(lblCity);
		
		String[] units = {"KM", "KWH", "H", "L", "M3", "CY"};
		JComboBox cmbItemUnit = new JComboBox(units);
		cmbItemUnit.setBounds(146, 206, 124, 19);
		itemRegistrationPanel.add(cmbItemUnit);
		
		txtItemYear = new JTextField();
		txtItemYear.setColumns(10);
		txtItemYear.setBounds(146, 179, 124, 19);
		itemRegistrationPanel.add(txtItemYear);
		
		//LOGICS BUILD ATTRIBUTES
		this.itemLogics.buildAttributes(txtItemSerialNO, txtItemName, txtItemModel, txtItemMaker, txtItemDimension, txtItemYear,
				cmbItemUnit, txtItemADR);
		
		JComboBox cmbItemFamily = new JComboBox(new String[] {"Plastic product manufacturing", "Automotive manufacturing", "Refineries",
				"Food production", "Steel mills", "Apparel production"});
		cmbItemFamily.setBounds(66, 237, 204, 19);
		itemRegistrationPanel.add(cmbItemFamily);
		
		JComboBox cmbItemSubfamily = new JComboBox();
		cmbItemSubfamily.setBounds(146, 266, 124, 19);
		itemRegistrationPanel.add(cmbItemSubfamily);
		
		
		JComboBox cmbItemCity = new JComboBox(new String[] {"Roma", "Rimini", "Milano", "Bologna", "Torino",
				"Ancona", "Senigallia"});
		cmbItemCity.setBounds(146, 295, 124, 19);
		itemRegistrationPanel.add(cmbItemCity);
		
		JComboBox cmbItemAddress = new JComboBox();
		cmbItemAddress.setBounds(146, 326, 124, 19);
		itemRegistrationPanel.add(cmbItemAddress);
		
		JLabel lblItemSupplier = new JLabel("Supplier");
		lblItemSupplier.setBounds(77, 386, 101, 15);
		itemRegistrationPanel.add(lblItemSupplier);
		
		JComboBox cmbItemCodSupplier = new JComboBox(new String[] {"ULIVIERI ricambi srl", "DMG MORI"});
		cmbItemCodSupplier.setBounds(146, 384, 124, 19);
		itemRegistrationPanel.add(cmbItemCodSupplier);
		
		//LOGICS: BUILD FOREIGN KEYS
		this.itemLogics.buildForeignKeys(cmbItemFamily, cmbItemSubfamily, cmbItemCity, cmbItemAddress, cmbItemCodSupplier);
		
		
		JPanel maintenanceContractPanel = new JPanel();
		maintenanceContractPanel.setBorder(new TitledBorder(null, "Maintenance Contract", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		maintenanceContractPanel.setBounds(319, 48, 349, 104);
		itemRegistrationPanel.add(maintenanceContractPanel);
		maintenanceContractPanel.setLayout(null);
		
		txtMCExpiration = new JTextField();
		txtMCExpiration.setBounds(102, 62, 124, 19);
		maintenanceContractPanel.add(txtMCExpiration);
		txtMCExpiration.setColumns(10);
		
		txtMCDateConclusion = new JTextField();
		txtMCDateConclusion.setBounds(142, 31, 124, 19);
		maintenanceContractPanel.add(txtMCDateConclusion);
		txtMCDateConclusion.setColumns(10);
		
		JLabel lblMCDateConclusion = new JLabel("Date conclusion");
		lblMCDateConclusion.setBounds(12, 33, 133, 15);
		maintenanceContractPanel.add(lblMCDateConclusion);
		
		JLabel lblMCExpiration = new JLabel("Expiration");
		lblMCExpiration.setBounds(12, 64, 91, 15);
		maintenanceContractPanel.add(lblMCExpiration);
		
		JPanel warrantyPanel = new JPanel();
		warrantyPanel.setLayout(null);
		warrantyPanel.setBorder(new TitledBorder(null, "Warranty", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		warrantyPanel.setBounds(319, 167, 349, 156);
		itemRegistrationPanel.add(warrantyPanel);
		
		JLabel lblNWarranty = new JLabel("Number Warranty");
		lblNWarranty.setBounds(12, 31, 124, 15);
		warrantyPanel.add(lblNWarranty);
		
		txtNumWarranty = new JTextField();
		txtNumWarranty.setColumns(10);
		txtNumWarranty.setBounds(148, 29, 124, 19);
		warrantyPanel.add(txtNumWarranty);
		
		JLabel lblWDate = new JLabel("Date");
		lblWDate.setBounds(12, 60, 66, 15);
		warrantyPanel.add(lblWDate);
		
		txtWarrantyDate = new JTextField();
		txtWarrantyDate.setColumns(10);
		txtWarrantyDate.setBounds(68, 58, 124, 19);
		warrantyPanel.add(txtWarrantyDate);
		
		JLabel lblWRecord = new JLabel("Record");
		lblWRecord.setBounds(12, 89, 66, 15);
		warrantyPanel.add(lblWRecord);
		
		txtWarrantyRecord = new JTextField();
		txtWarrantyRecord.setColumns(10);
		txtWarrantyRecord.setBounds(68, 87, 124, 19);
		warrantyPanel.add(txtWarrantyRecord);
		
		JLabel lblWNotes = new JLabel("Notes");
		lblWNotes.setBounds(12, 118, 66, 15);
		warrantyPanel.add(lblWNotes);
		
		txtWarrantyNotes = new JTextField();
		txtWarrantyNotes.setColumns(10);
		txtWarrantyNotes.setBounds(68, 116, 124, 19);
		warrantyPanel.add(txtWarrantyNotes);
		
		JLabel lblWDateFormat = new JLabel("format: yyyy-mm-dd");
		lblWDateFormat.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblWDateFormat.setBounds(198, 60, 124, 15);
		warrantyPanel.add(lblWDateFormat);
		
		//LOGICS: BUILD OPTIONALS
		this.itemLogics.buildOptionals(txtMCDateConclusion, txtMCExpiration, txtNumWarranty, 
				txtWarrantyDate, txtWarrantyRecord, txtWarrantyNotes);
		
		JPanel itemImagePanel_1 = new JPanel();
		itemImagePanel_1.setLayout(null);
		itemImagePanel_1.setBorder(new TitledBorder(null, "Item Image", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		itemImagePanel_1.setBounds(695, 48, 330, 220);
		itemRegistrationPanel.add(itemImagePanel_1);
		
		this.lblItemImage = new JLabel();
		this.lblItemImage.setBounds(5, 17, 320, 198);
		itemImagePanel_1.add(this.lblItemImage);
		
		JButton btnChooseImage = new JButton("Choose an image ...");
		this.itemImageFile = "";
		btnChooseImage.setBounds(856, 279, 169, 19);
		btnChooseImage.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Open an image");
				jfc.setFileFilter(new FileTypeFilter(".png", "PNG image"));
				jfc.setFileFilter(new FileTypeFilter(".jpeg", "JPEG image"));
				jfc.setFileFilter(new FileTypeFilter(".jpg", "JPG image"));

				int input = jfc.showOpenDialog(null);
				if(input == JFileChooser.APPROVE_OPTION) {
					File f = jfc.getSelectedFile();
					itemLogics.uploadImage(f.getAbsolutePath());
					setItemImage(f);
				}
				super.mouseClicked(e);
			}
		});
		
		itemRegistrationPanel.add(btnChooseImage);
		
		JButton btnChooseFile = new JButton("Choose a file ...");
		btnChooseFile.setBounds(856, 310, 169, 19);
		this.itemFile = "";
		btnChooseFile.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Open a file");
				jfc.setFileFilter(new FileTypeFilter(".txt", "TXT file"));
				jfc.setFileFilter(new FileTypeFilter(".odt", "ODT file"));
				jfc.setFileFilter(new FileTypeFilter(".pdf", "PDF file"));
				jfc.setFileFilter(new FileTypeFilter(".doc", "Word file"));
				jfc.setFileFilter(new FileTypeFilter(".docx", "Word file"));

				int input = jfc.showOpenDialog(null);
				
				if(input == JFileChooser.APPROVE_OPTION) {
					itemLogics.uploadFile(jfc.getSelectedFile().getAbsolutePath());
				}
				super.mouseClicked(e);
			}
			
		});
		itemRegistrationPanel.add(btnChooseFile);

		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setLayout(null);
		descriptionPanel.setBorder(new TitledBorder(null, "Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		descriptionPanel.setBounds(12, 447, 276, 135);
		itemPanel.add(descriptionPanel);
		
		JTextArea txtAreaDescriptions = new JTextArea();
		txtAreaDescriptions.setBounds(5, 17, 259, 110);
		descriptionPanel.add(txtAreaDescriptions);
		
		JPanel specificationsPanel = new JPanel();
		specificationsPanel.setLayout(null);
		specificationsPanel.setBorder(new TitledBorder(null, "Specifications", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		specificationsPanel.setBounds(344, 447, 758, 135);
		itemPanel.add(specificationsPanel);
		
		JTextArea txtAreaSpecifications = new JTextArea();
		txtAreaSpecifications.setBounds(5, 17, 741, 115);
		specificationsPanel.add(txtAreaSpecifications);
		
		JButton btnSaveItem = new JButton("Save");
		btnSaveItem.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				itemLogics.save();
			}
			
		});
		btnSaveItem.setBounds(488, 604, 114, 25);
		itemPanel.add(btnSaveItem);
		
		DefaultTableModel dtmItem = new DefaultTableModel();
		dtmItem.addColumn("Serial Number");
		dtmItem.addColumn("Name");
		dtmItem.addColumn("Picture");
		dtmItem.addColumn("File");
		dtmItem.addColumn("Description");
		dtmItem.addColumn("Specifications");
		dtmItem.addColumn("Adr");
		dtmItem.addColumn("Model");
		dtmItem.addColumn("Maker");
		dtmItem.addColumn("Dimension");
		dtmItem.addColumn("Year");
		dtmItem.addColumn("CodFamily"); 
		dtmItem.addColumn("CodSubfamily");
		dtmItem.addColumn("Unit");		
		dtmItem.addColumn("ZipCode");
		dtmItem.addColumn("CodAddress");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 658, 1089, 151);
		itemPanel.add(scrollPane_1);
		itemsTable = new JTable(dtmItem);
		scrollPane_1.setViewportView(itemsTable);
		itemsTable.setEnabled(false);
		
		//LOGICS: BUILD IMAGES, TEXT AREAS AND TABLE
		this.itemLogics.buildImTxtTable(txtAreaDescriptions, txtAreaSpecifications,  dtmItem);
		
		// END ITEMS TAB
		
		JPanel sparePartsPanel = new JPanel();
		tabbedPane.addTab("Spare parts", null, sparePartsPanel, null);
		sparePartsPanel.setLayout(null);
		
		DefaultTableModel dtmSparePart = new DefaultTableModel();
		dtmSparePart.addColumn("Cod Supplier");
		dtmSparePart.addColumn("Bill number");
		dtmSparePart.addColumn("Purchase date");
		
		JPanel purchasePanel = new JPanel();
		purchasePanel.setBorder(new TitledBorder(null, "Purchase", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		purchasePanel.setBounds(12, 38, 1090, 134);
		sparePartsPanel.add(purchasePanel);
		purchasePanel.setLayout(null);
		
		txtSPBillNumber = new JTextField();
		txtSPBillNumber.setColumns(10);
		txtSPBillNumber.setBounds(148, 49, 124, 19);
		purchasePanel.add(txtSPBillNumber);
		
		JLabel lblBillNumber = new JLabel("Bill Number");
		lblBillNumber.setBounds(33, 51, 116, 15);
		purchasePanel.add(lblBillNumber);
		
		JLabel lblPurchaseDate = new JLabel("Purchase Date");
		lblPurchaseDate.setBounds(364, 51, 105, 15);
		purchasePanel.add(lblPurchaseDate);
		
		txtSPPurchaseDate = new JTextField();
		txtSPPurchaseDate.setColumns(10);
		txtSPPurchaseDate.setBounds(486, 49, 124, 19);
		purchasePanel.add(txtSPPurchaseDate);
		
		JLabel lblPDateFormat = new JLabel("format: yyyy-mm-dd");
		lblPDateFormat.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblPDateFormat.setBounds(486, 69, 124, 15);
		purchasePanel.add(lblPDateFormat);
		
		JLabel lblSupplier = new JLabel("Supplier");
		lblSupplier.setBounds(784, 51, 101, 15);
		purchasePanel.add(lblSupplier);
		
		JComboBox cmbSPCodSupplier = new JComboBox(new String[]{"ULIVIERI ricambi srl", "DMG MORI"});
		cmbSPCodSupplier.setBounds(867, 49, 160, 19);
		purchasePanel.add(cmbSPCodSupplier);
		
		JPanel stockPanel = new JPanel();
		stockPanel.setBorder(new TitledBorder(null, "Stock", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		stockPanel.setBounds(12, 249, 1090, 270);
		sparePartsPanel.add(stockPanel);
		stockPanel.setLayout(null);
		
		JButton btnAddStock = new JButton("Add Stock");
		btnAddStock.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				sparePartLogics.saveStock();
				super.mouseClicked(e);
			}
			
		});
		btnAddStock.setBounds(485, 221, 114, 25);
		stockPanel.add(btnAddStock);
		
		JLabel lblNSparePart = new JLabel("Spare part name");
		lblNSparePart.setBounds(203, 48, 139, 15);
		stockPanel.add(lblNSparePart);
		
		JLabel lblStockAmount = new JLabel("Amount");
		lblStockAmount.setBounds(240, 116, 116, 15);
		stockPanel.add(lblStockAmount);
		
		txtStockAmount = new JTextField();
		txtStockAmount.setColumns(10);
		txtStockAmount.setBounds(355, 114, 124, 19);
		stockPanel.add(txtStockAmount);
		
		JLabel lblStockUnitCost = new JLabel("Unit Cost");
		lblStockUnitCost.setBounds(240, 175, 116, 15);
		stockPanel.add(lblStockUnitCost);
		
		txtSPUnitCost = new JTextField();
		txtSPUnitCost.setColumns(10);
		txtSPUnitCost.setBounds(355, 173, 124, 19);
		stockPanel.add(txtSPUnitCost);
		
		JLabel lblStockWarehouse = new JLabel("Warehouse");
		lblStockWarehouse.setBounds(591, 44, 116, 15);
		stockPanel.add(lblStockWarehouse);
		
		JPanel stockNotesPanel = new JPanel();
		stockNotesPanel.setBorder(new TitledBorder(null, "Notes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		stockNotesPanel.setBounds(591, 71, 241, 121);
		stockPanel.add(stockNotesPanel);
		stockNotesPanel.setLayout(null);
		
		JTextArea txtStockNotesArea = new JTextArea();
		txtStockNotesArea.setBounds(5, 17, 231, 92);
		stockNotesPanel.add(txtStockNotesArea);
		
		JComboBox cmbCodWarehouse = new JComboBox(new String[]{"1", "2", "3"});
		cmbCodWarehouse.setBounds(708, 44, 124, 19);
		stockPanel.add(cmbCodWarehouse);
		
		JButton btnRegisterPurchase = new JButton("Register purchase");
		btnRegisterPurchase.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				sparePartLogics.savePurchase();
				super.mouseClicked(e);
			}
			
		});
		btnRegisterPurchase.setBounds(465, 199, 158, 25);
		sparePartsPanel.add(btnRegisterPurchase);
		
		JComboBox cmbSparePart = new JComboBox(new Object[]{"ricambio 1", "ricambio 2"});
		cmbSparePart.setBounds(355, 43, 124, 19);
		stockPanel.add(cmbSparePart);
		
		this.sparePartLogics.buildStock(cmbSparePart, txtStockAmount, txtSPUnitCost, txtStockNotesArea, cmbCodWarehouse);
		
		JButton btnClearAll = new JButton("Clear all");
		btnClearAll.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				sparePartLogics.clearAll();
				super.mouseClicked(e);
			}
			
		});
		btnClearAll.setBounds(496, 542, 114, 25);
		sparePartsPanel.add(btnClearAll);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 581, 1090, 228);
		sparePartsPanel.add(scrollPane);
		table = new JTable(dtmSparePart);
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		
		this.sparePartLogics.buildPurchase(cmbSPCodSupplier, txtSPBillNumber, txtSPPurchaseDate, dtmSparePart);
		
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Serial Number");
		dtm.addColumn("Description");
		dtm.addColumn("Tasks");
		dtm.addColumn("Notes");
		
		//WORK DESCRIPTION TAB
		
		JPanel workDescriptionsPanel = new JPanel();
		tabbedPane.addTab("Work descriptions", null, workDescriptionsPanel, null);
		workDescriptionsPanel.setLayout(null);
		
		JLabel lblWDSerialNO = new JLabel("Item Serial Number");
		lblWDSerialNO.setBounds(57, 76, 144, 15);
		workDescriptionsPanel.add(lblWDSerialNO);
		
		txtWDSerialNumber = new JTextField();
		txtWDSerialNumber.setBounds(207, 75, 135, 17);
		workDescriptionsPanel.add(txtWDSerialNumber);
		txtWDSerialNumber.setColumns(10);
		
		JLabel lblWDDescription = new JLabel("Description");
		lblWDDescription.setBounds(31, 141, 98, 15);
		workDescriptionsPanel.add(lblWDDescription);
		
		txtWDDescription = new JTextField();
		txtWDDescription.setColumns(10);
		txtWDDescription.setBounds(125, 140, 217, 17);
		workDescriptionsPanel.add(txtWDDescription);
		
		JPanel WDtasksPanel = new JPanel();
		WDtasksPanel.setLayout(null);
		WDtasksPanel.setBorder(new TitledBorder(null, "Tasks", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		WDtasksPanel.setBounds(390, 35, 418, 156);
		workDescriptionsPanel.add(WDtasksPanel);
		
		JTextArea WDTasksTxtArea = new JTextArea();
		WDTasksTxtArea.setBounds(5, 17, 408, 127);
		WDtasksPanel.add(WDTasksTxtArea);
		
		JPanel WDNotesPanel = new JPanel();
		WDNotesPanel.setLayout(null);
		WDNotesPanel.setBorder(new TitledBorder(null, "Notes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		WDNotesPanel.setBounds(868, 35, 212, 157);
		workDescriptionsPanel.add(WDNotesPanel);
		
		JTextArea WDNotesTxtArea = new JTextArea();
		WDNotesTxtArea.setBounds(5, 17, 202, 128);
		WDNotesPanel.add(WDNotesTxtArea);
		
		this.workDescriptionLogics.buildWorkDescription(txtWDSerialNumber, txtWDDescription, WDTasksTxtArea, WDNotesTxtArea, dtm);
		
		JPanel WDStockPanel = new JPanel();
		WDStockPanel.setBorder(new TitledBorder(null, "Stock", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		WDStockPanel.setBounds(12, 270, 582, 215);
		workDescriptionsPanel.add(WDStockPanel);
		WDStockPanel.setLayout(null);
		
		JLabel lblWDBillNumber = new JLabel("Bill Number");
		lblWDBillNumber.setBounds(43, 138, 107, 15);
		WDStockPanel.add(lblWDBillNumber);
		
		txtWDBillNumber = new JTextField();
		txtWDBillNumber.setColumns(10);
		txtWDBillNumber.setBounds(147, 137, 135, 17);
		WDStockPanel.add(txtWDBillNumber);
		
		JLabel lblWDSupplier = new JLabel("Supplier");
		lblWDSupplier.setBounds(69, 91, 96, 15);
		WDStockPanel.add(lblWDSupplier);
		
		JLabel lblWDNumSparePart = new JLabel("Spare part");
		lblWDNumSparePart.setBounds(58, 38, 119, 15);
		WDStockPanel.add(lblWDNumSparePart);
		
		JLabel lblWDStockAmount = new JLabel("Amount");
		lblWDStockAmount.setBounds(333, 86, 64, 15);
		WDStockPanel.add(lblWDStockAmount);
		
		txtWDStockAmount = new JTextField();
		txtWDStockAmount.setColumns(10);
		txtWDStockAmount.setBounds(403, 85, 135, 17);
		WDStockPanel.add(txtWDStockAmount);	
		
		JButton btnWDAddStock = new JButton("Add Stock");
		btnWDAddStock.addActionListener(l -> {
			workDescriptionLogics.saveUses();
		});
		btnWDAddStock.setBounds(223, 178, 114, 25);
		WDStockPanel.add(btnWDAddStock);
		
		JComboBox cmbWDSupplier = new JComboBox(new Object[]{"ULIVIERI ricambi srl", "DMG MORI"});
		cmbWDSupplier.setBounds(147, 86, 135, 19);
		WDStockPanel.add(cmbWDSupplier);
		
		JComboBox cmbWDSparePart = new JComboBox(new Object[]{"ricambio fresatrice"});
		cmbWDSparePart.setBounds(147, 36, 135, 19);
		WDStockPanel.add(cmbWDSparePart);
		
		this.workDescriptionLogics.buildUses(cmbWDSupplier, txtWDBillNumber, cmbWDSparePart, txtWDStockAmount);
		
		JPanel manHoursPanel = new JPanel();
		manHoursPanel.setBorder(new TitledBorder(null, "Man Hours", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		manHoursPanel.setBounds(606, 270, 496, 215);
		workDescriptionsPanel.add(manHoursPanel);
		manHoursPanel.setLayout(null);
		
		JLabel lblWDCF = new JLabel("CF");
		lblWDCF.setBounds(151, 36, 107, 15);
		manHoursPanel.add(lblWDCF);
		
		JLabel lblMHHours = new JLabel("Hours");
		lblMHHours.setBounds(145, 81, 96, 15);
		manHoursPanel.add(lblMHHours);
		
		txtMHHours = new JTextField();
		txtMHHours.setColumns(10);
		txtMHHours.setBounds(240, 80, 135, 17);
		manHoursPanel.add(txtMHHours);
		
		JLabel lblMHCostHour = new JLabel("Cost per hour");
		lblMHCostHour.setBounds(122, 130, 119, 15);
		manHoursPanel.add(lblMHCostHour);
		
		txtMHCostHour = new JTextField();
		txtMHCostHour.setColumns(10);
		txtMHCostHour.setBounds(240, 129, 135, 17);
		manHoursPanel.add(txtMHCostHour);
		
		JButton btnAddManHours = new JButton("Add man hours");
		btnAddManHours.addActionListener(l -> {
			workDescriptionLogics.saveManHours();
		});
		btnAddManHours.setBounds(192, 178, 151, 25);
		manHoursPanel.add(btnAddManHours);
		
		JComboBox cmbWDCF = new JComboBox(new Object[]{"FBRLCU99R19I608E"});
		cmbWDCF.setBounds(240, 34, 135, 19);
		manHoursPanel.add(cmbWDCF);
		
		JButton btnAddWorkDescription = new JButton("Add work description");
		btnAddWorkDescription.addActionListener(l -> {
			workDescriptionLogics.saveWorkDescription();
		});
		btnAddWorkDescription.setBounds(502, 220, 187, 25);
		workDescriptionsPanel.add(btnAddWorkDescription);
		
		JButton btnClearAll_1 = new JButton("Clear all");
		btnClearAll_1.addActionListener(l -> {
			this.workDescriptionLogics.clearAll();
		});
		btnClearAll_1.setBounds(532, 517, 114, 25);
		workDescriptionsPanel.add(btnClearAll_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 570, 1090, 239);
		workDescriptionsPanel.add(scrollPane_2);
		table_3 = new JTable(dtm);
		table_3.setEnabled(false);
		scrollPane_2.setViewportView(table_3);
		
		this.workDescriptionLogics.buildManHours(cmbWDCF, txtMHHours, txtMHCostHour);
		//SCHEDULE TAB
		
		JPanel schedulePanel = new JPanel();
		tabbedPane.addTab("Schedule", null, schedulePanel, null);
		schedulePanel.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(12, 12, 1090, 797);
		schedulePanel.add(tabbedPane_1);
		
		JPanel maintenancePanel = new JPanel();
		tabbedPane_1.addTab("Maintenance", null, maintenancePanel, null);
		maintenancePanel.setLayout(null);
		
		JPanel maintenanceSchedulePanel = new JPanel();
		maintenanceSchedulePanel.setBorder(new TitledBorder(null, "Maintenance Schedule", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		maintenanceSchedulePanel.setBounds(12, 12, 1061, 296);
		maintenancePanel.add(maintenanceSchedulePanel);
		maintenanceSchedulePanel.setLayout(null);
		
		JLabel lblMSDescription = new JLabel("Description");
		lblMSDescription.setBounds(22, 36, 116, 15);
		maintenanceSchedulePanel.add(lblMSDescription);
		
		txtMSDescription = new JTextField();
		txtMSDescription.setColumns(10);
		txtMSDescription.setBounds(137, 34, 347, 19);
		maintenanceSchedulePanel.add(txtMSDescription);
		
		JLabel lblNumMS = new JLabel("Number Maintenance Schedule");
		lblNumMS.setBounds(22, 65, 236, 15);
		maintenanceSchedulePanel.add(lblNumMS);
		
		txtNumMS = new JTextField();
		txtNumMS.setColumns(10);
		txtNumMS.setBounds(257, 63, 227, 19);
		maintenanceSchedulePanel.add(txtNumMS);
		
		JLabel lblMSSerialNO = new JLabel("Item Serial Number");
		lblMSSerialNO.setBounds(22, 94, 141, 15);
		maintenanceSchedulePanel.add(lblMSSerialNO);
		
		txtMSSerialNO = new JTextField();
		txtMSSerialNO.setColumns(10);
		txtMSSerialNO.setBounds(169, 92, 315, 19);
		maintenanceSchedulePanel.add(txtMSSerialNO);
		
		JPanel MStasksPanel = new JPanel();
		MStasksPanel.setBorder(new TitledBorder(null, "Tasks", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		MStasksPanel.setBounds(618, 11, 418, 263);
		maintenanceSchedulePanel.add(MStasksPanel);
		MStasksPanel.setLayout(null);
		
		JTextArea MSTasksTxtArea = new JTextArea();
		MSTasksTxtArea.setBounds(5, 17, 408, 241);
		MStasksPanel.add(MSTasksTxtArea);
		
		JPanel workTypePanel = new JPanel();
		workTypePanel.setBorder(new TitledBorder(null, "Type of Maintenance Schedule", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		workTypePanel.setBounds(22, 132, 462, 142);
		maintenanceSchedulePanel.add(workTypePanel);
		workTypePanel.setLayout(null);
		
		JLabel lblWorkType = new JLabel("Work Type");
		lblWorkType.setBounds(12, 26, 83, 15);
		workTypePanel.add(lblWorkType);
		
		String[] workTypes = {"Preventive (by time)", "Preventive (by Usage)", "Predictive", "Condition based", "Run to fail"};
		JComboBox cmbWorkType = new JComboBox(workTypes);
		cmbWorkType.setBounds(93, 23, 179, 20);
		workTypePanel.add(cmbWorkType);
		
		JTextArea workTypeTxtArea = new JTextArea();
		workTypeTxtArea.setBounds(12, 55, 438, 75);
		workTypePanel.add(workTypeTxtArea);
		
		JPanel selectWDPanel = new JPanel();
		selectWDPanel.setBorder(new TitledBorder(null, "Work Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		selectWDPanel.setBounds(12, 381, 1061, 112);
		maintenancePanel.add(selectWDPanel);
		selectWDPanel.setLayout(null);
		
		JLabel lblMSWorkOrderDescription = new JLabel("Description");
		lblMSWorkOrderDescription.setBounds(387, 38, 86, 15);
		selectWDPanel.add(lblMSWorkOrderDescription);
		
		txtMSWorkOrderDescription = new JTextField();
		txtMSWorkOrderDescription.setText("");
		txtMSWorkOrderDescription.setBounds(471, 36, 209, 17);
		selectWDPanel.add(txtMSWorkOrderDescription);
		txtMSWorkOrderDescription.setColumns(10);
		
		JButton btnAddWD = new JButton("Add Work Description");
		btnAddWD.addActionListener(l -> {
			msLogics.savePlan();
		});
		btnAddWD.setBounds(438, 65, 182, 25);
		selectWDPanel.add(btnAddWD);
		
		DefaultTableModel dtmMS = new DefaultTableModel();
		dtmMS.addColumn("Serial Number");
		dtmMS.addColumn("Num maintenance schedule");
		dtmMS.addColumn("Description");
		dtmMS.addColumn("Tasks");
		dtmMS.addColumn("By time");
		dtmMS.addColumn("By usage");
		dtmMS.addColumn("By prediction");
		dtmMS.addColumn("By condition");
		dtmMS.addColumn("By run to fail");
		
		JButton btnRegisterMS = new JButton("Register Maintenance Schedule");
		btnRegisterMS.addActionListener(l -> {
			msLogics.saveMaintenanceSchedule();
		});
		btnRegisterMS.setBounds(414, 335, 250, 25);
		maintenancePanel.add(btnRegisterMS);
		
		this.msLogics.build(txtMSDescription, txtNumMS, txtMSSerialNO, cmbWorkType, workTypeTxtArea, 
				MSTasksTxtArea, txtMSWorkOrderDescription, dtmMS);
		
		JButton btnMSClearAll = new JButton("Clear all");
		btnMSClearAll.addActionListener(l -> {
			msLogics.clearAll();
		});
		btnMSClearAll.setBounds(476, 520, 136, 25);
		maintenancePanel.add(btnMSClearAll);
		
		//PROJECT TAB
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 576, 1061, 182);
		maintenancePanel.add(scrollPane_3);
		MSTable = new JTable(dtmMS);
		MSTable.setEnabled(false);
		scrollPane_3.setViewportView(MSTable);
		
		JPanel projectPanel = new JPanel();
		tabbedPane_1.addTab("Project", null, projectPanel, null);
		projectPanel.setLayout(null);
		
		JPanel projectSchedulePanel = new JPanel();
		projectSchedulePanel.setLayout(null);
		projectSchedulePanel.setBorder(new TitledBorder(null, "Project Schedule", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		projectSchedulePanel.setBounds(12, 24, 1061, 336);
		projectPanel.add(projectSchedulePanel);
		
		JLabel lblProjectDescription = new JLabel("Description");
		lblProjectDescription.setBounds(36, 36, 116, 15);
		projectSchedulePanel.add(lblProjectDescription);
		
		txtProjectDescription = new JTextField();
		txtProjectDescription.setColumns(10);
		txtProjectDescription.setBounds(137, 34, 347, 19);
		projectSchedulePanel.add(txtProjectDescription);
		
		JLabel lblCodProject = new JLabel("Cod Project");
		lblCodProject.setBounds(32, 70, 87, 15);
		projectSchedulePanel.add(lblCodProject);
		
		txtCodProject = new JTextField();
		txtCodProject.setColumns(10);
		txtCodProject.setBounds(137, 68, 347, 19);
		projectSchedulePanel.add(txtCodProject);
		
		JPanel projectTasksPanel = new JPanel();
		projectTasksPanel.setLayout(null);
		projectTasksPanel.setBorder(new TitledBorder(null, "Tasks/Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		projectTasksPanel.setBounds(618, 11, 418, 263);
		projectSchedulePanel.add(projectTasksPanel);
		
		JTextArea ProjectTasksTxtArea = new JTextArea();
		ProjectTasksTxtArea.setBounds(5, 17, 408, 241);
		projectTasksPanel.add(ProjectTasksTxtArea);
		
		JPanel projectWorkTypePanel = new JPanel();
		projectWorkTypePanel.setLayout(null);
		projectWorkTypePanel.setBorder(new TitledBorder(null, "Type of Maintenance Schedule", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		projectWorkTypePanel.setBounds(22, 110, 462, 164);
		projectSchedulePanel.add(projectWorkTypePanel);
		
		JLabel lblProjectWorkType = new JLabel("Work Type");
		lblProjectWorkType.setBounds(12, 33, 83, 15);
		projectWorkTypePanel.add(lblProjectWorkType);
		
		JComboBox cmbProjectWorkType = new JComboBox(workTypes);
		cmbProjectWorkType.setBounds(93, 30, 179, 20);
		projectWorkTypePanel.add(cmbProjectWorkType);
		
		JTextArea projectWorkTypeTxtArea = new JTextArea();
		projectWorkTypeTxtArea.setBounds(12, 62, 428, 90);
		projectWorkTypePanel.add(projectWorkTypeTxtArea);
		
		JButton btnRegisterProject = new JButton("Register Project");
		btnRegisterProject.addActionListener(l -> {
			projectLogics.saveProject();
		});
		btnRegisterProject.setBounds(468, 299, 182, 25);
		projectSchedulePanel.add(btnRegisterProject);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Work orders", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(7, 416, 1071, 138);
		projectPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(5, 17, 1061, 116);
		panel_1.add(scrollPane_4);
		
		DefaultTableModel dtmProjectWO = new DefaultTableModel();
		projectWOTable = new JTable(dtmProjectWO);
		projectWOTable.setEnabled(false);
		dtmProjectWO.addColumn("item serial number");
		dtmProjectWO.addColumn("Num work order");
		dtmProjectWO.addColumn("Description");
		dtmProjectWO.addColumn("Planned time maintenance");
		dtmProjectWO.addColumn("Planned start");
		dtmProjectWO.addColumn("Planned end");
		dtmProjectWO.addColumn("Actual start");
		dtmProjectWO.addColumn("Actual end");
		dtmProjectWO.addColumn("Type");
		dtmProjectWO.addColumn("Breakdown picture");
		dtmProjectWO.addColumn("Corrective");
		dtmProjectWO.addColumn("Cod urgency");
		dtmProjectWO.addColumn("Cod project");
		dtmProjectWO.addColumn("Item serial number");
		dtmProjectWO.addColumn("Num maintenance schedule");
		
		scrollPane_4.setViewportView(projectWOTable);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Projects", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(7, 566, 1071, 197);
		projectPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(5, 17, 1061, 197);
		panel_2.add(scrollPane_5);
		
		DefaultTableModel dtmProjects = new DefaultTableModel();
		registeredProjectsTable = new JTable(dtmProjects);
		registeredProjectsTable.setEnabled(false);
		dtmProjects.addColumn("Cod project");
		dtmProjects.addColumn("Description");
		dtmProjects.addColumn("Tasks");
		dtmProjects.addColumn("By time");
		dtmProjects.addColumn("By usage");
		dtmProjects.addColumn("By prediction");
		dtmProjects.addColumn("By condition");
		dtmProjects.addColumn("By run to fail");
		
		scrollPane_5.setViewportView(registeredProjectsTable);
		registeredProjectsTable.setEnabled(false);
		
		JButton btnFilterWorkOrders = new JButton("Filter project work orders");
		btnFilterWorkOrders.addActionListener(l -> {
			projectLogics.filterWorkOrders();
		});
		btnFilterWorkOrders.setFont(new Font("Dialog", Font.BOLD, 12));
		btnFilterWorkOrders.setBounds(466, 383, 207, 25);
		projectPanel.add(btnFilterWorkOrders);
		
		JLabel lblNewLabel = new JLabel("* compile Cod Project field");
		lblNewLabel.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblNewLabel.setBounds(691, 388, 172, 15);
		projectPanel.add(lblNewLabel);
		
		this.projectLogics.build(txtProjectDescription, txtCodProject, cmbProjectWorkType, projectWorkTypeTxtArea, 
				ProjectTasksTxtArea , dtmProjects, dtmProjectWO);
		
		//WORK ORDERS TAB
		
		JPanel workOrderPanel = new JPanel();
		tabbedPane.addTab("Work order", null, workOrderPanel, null);
		workOrderPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Work Order", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 1090, 191);
		workOrderPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNumWO = new JLabel("Number Work Order");
		lblNumWO.setBounds(12, 31, 151, 15);
		panel.add(lblNumWO);
		
		txtNumWO = new JTextField();
		txtNumWO.setBounds(165, 29, 139, 19);
		panel.add(txtNumWO);
		txtNumWO.setColumns(10);
		
		JLabel lblWOSerialNO = new JLabel("Item Serial Number");
		lblWOSerialNO.setBounds(12, 79, 151, 15);
		panel.add(lblWOSerialNO);
		
		txtWOSerialNO = new JTextField();
		txtWOSerialNO.setColumns(10);
		txtWOSerialNO.setBounds(165, 77, 139, 19);
		panel.add(txtWOSerialNO);
		
		JLabel lblWODescription = new JLabel("Description");
		lblWODescription.setBounds(23, 121, 104, 17);
		panel.add(lblWODescription);
		
		txtWODescription = new JTextField();
		txtWODescription.setColumns(10);
		txtWODescription.setBounds(123, 119, 182, 19);
		panel.add(txtWODescription);
		
		JLabel lblPlannedTimeMaintenance = new JLabel("Planned Time Maintenance");
		lblPlannedTimeMaintenance.setBounds(363, 77, 191, 15);
		panel.add(lblPlannedTimeMaintenance);
		
		txtPlannedTimeMaintenance = new JTextField();
		txtPlannedTimeMaintenance.setColumns(10);
		txtPlannedTimeMaintenance.setBounds(569, 75, 104, 19);
		panel.add(txtPlannedTimeMaintenance);
		
		JLabel lblPlannedStart = new JLabel("Planned Start Date");
		lblPlannedStart.setBounds(414, 31, 151, 15);
		panel.add(lblPlannedStart);
		
		txtPlannedDate = new JTextField();
		txtPlannedDate.setColumns(10);
		txtPlannedDate.setBounds(569, 29, 104, 19);
		panel.add(txtPlannedDate);
		
		JLabel lblPlannedEnd = new JLabel("Planned End Date");
		lblPlannedEnd.setBounds(779, 33, 151, 15);
		panel.add(lblPlannedEnd);
		
		txtPlannedEnd = new JTextField();
		txtPlannedEnd.setColumns(10);
		txtPlannedEnd.setBounds(932, 31, 104, 19);
		panel.add(txtPlannedEnd);
		
		JLabel lblActualStart = new JLabel("Actual Start Date");
		lblActualStart.setBounds(779, 75, 151, 15);
		panel.add(lblActualStart);
		
		txtActualStart = new JTextField();
		txtActualStart.setColumns(10);
		txtActualStart.setBounds(932, 73, 104, 19);
		panel.add(txtActualStart);
		
		JLabel lblActualEnd = new JLabel("Actual End Date");
		lblActualEnd.setBounds(779, 118, 151, 15);
		panel.add(lblActualEnd);
		
		txtActualEnd = new JTextField();
		txtActualEnd.setColumns(10);
		txtActualEnd.setBounds(932, 116, 104, 19);
		panel.add(txtActualEnd);
		
		JLabel lblPDateFormat_1 = new JLabel("dates format: yyyy-mm-dd");
		lblPDateFormat_1.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblPDateFormat_1.setBounds(868, 145, 168, 15);
		panel.add(lblPDateFormat_1);
		
		JLabel lblUrgencyLevel = new JLabel("Urgency Level");
		lblUrgencyLevel.setBounds(434, 120, 111, 15);
		panel.add(lblUrgencyLevel);
		
		String[] urgencyLevels = { "Normal", "Urgent", "Priority", "Critical" };
		JComboBox cmbUrgencyLevel = new JComboBox(urgencyLevels);
		cmbUrgencyLevel.setBounds(549, 118, 124, 19);
		panel.add(cmbUrgencyLevel);
		
		JPanel selectWDPanel_1 = new JPanel();
		selectWDPanel_1.setLayout(null);
		selectWDPanel_1.setBorder(new TitledBorder(null, "Work Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		selectWDPanel_1.setBounds(12, 402, 1090, 112);
		workOrderPanel.add(selectWDPanel_1);
		
		JLabel lblWDDescription_1 = new JLabel("Description");
		lblWDDescription_1.setBounds(375, 38, 86, 15);
		selectWDPanel_1.add(lblWDDescription_1);
		
		txtWOWDDescription = new JTextField();
		txtWOWDDescription.setText("");
		txtWOWDDescription.setColumns(10);
		txtWOWDDescription.setBounds(459, 36, 209, 17);
		selectWDPanel_1.add(txtWOWDDescription);
		
		this.workOrderLogics.buildWorkOrder(txtNumWO, txtWOSerialNO, txtWODescription, txtPlannedTimeMaintenance,
				txtPlannedDate, txtPlannedEnd, cmbUrgencyLevel, txtActualStart, txtActualEnd, txtWOWDDescription);
		
		
		JButton btnAddWOWD = new JButton("Add Work Description");
		btnAddWOWD.addActionListener(l -> {
			workOrderLogics.saveExecute();
		});
		btnAddWOWD.setBounds(441, 65, 182, 25);
		selectWDPanel_1.add(btnAddWOWD);
		
		JPanel extraWOPanel = new JPanel();
		
		extraWOPanel.setBounds(560, 215, 542, 122);
		workOrderPanel.add(extraWOPanel);
		extraWOPanel.setLayout(null);
		extraWOPanel.setBorder(new TitledBorder(null, "Extraordinary Work Order", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblTrigger = new JLabel("Trigger");
		lblTrigger.setBounds(14, 46, 83, 15);
		extraWOPanel.add(lblTrigger);
		
		String[] extraWOTriggers = {"Reactive", "Corrective"};
		JComboBox cmbWOTrigger = new JComboBox(extraWOTriggers);
		cmbWOTrigger.setBounds(95, 43, 151, 20);
		extraWOPanel.add(cmbWOTrigger);
		
		JTextArea WOTriggertxtArea = new JTextArea();
		WOTriggertxtArea.setBounds(264, 12, 266, 98);
		extraWOPanel.add(WOTriggertxtArea);
		
		JPanel ordinaryWOPanel = new JPanel();
		ordinaryWOPanel.setBorder(new TitledBorder(null, "Ordinary Work Order", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ordinaryWOPanel.setBounds(146, 215, 387, 122);
		workOrderPanel.add(ordinaryWOPanel);
		ordinaryWOPanel.setLayout(null);
		
		JLabel lblNumMS_1 = new JLabel("Number Maintenance Schedule");
		lblNumMS_1.setBounds(12, 40, 236, 15);
		ordinaryWOPanel.add(lblNumMS_1);
		
		textWONumMS = new JTextField();
		textWONumMS.setColumns(10);
		textWONumMS.setBounds(247, 38, 96, 19);
		ordinaryWOPanel.add(textWONumMS);
		
		JLabel lblCodProject_1 = new JLabel("Cod Project");
		lblCodProject_1.setBounds(146, 69, 87, 15);
		ordinaryWOPanel.add(lblCodProject_1);
		
		txtWOCodProject = new JTextField();
		txtWOCodProject.setColumns(10);
		txtWOCodProject.setBounds(247, 67, 96, 19);
		ordinaryWOPanel.add(txtWOCodProject);
		
		JButton btnAddWorkOrder = new JButton("Add work order");
		btnAddWorkOrder.addActionListener(l -> {
			workOrderLogics.saveWorkOrder();
		});
		btnAddWorkOrder.setBounds(469, 365, 139, 25);
		workOrderPanel.add(btnAddWorkOrder);
		
		JComboBox cmbWOType = new JComboBox(new Object[]{"Ordinary", "Extraordinary"});
		cmbWOType.setBounds(12, 265, 125, 20);
		workOrderPanel.add(cmbWOType);
		
		JButton btnClearAll_2 = new JButton("Clear all");
		btnClearAll_2.addActionListener(l -> {
			workOrderLogics.clearAll();
		});
		btnClearAll_2.setBounds(452, 541, 182, 25);
		workOrderPanel.add(btnClearAll_2);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(12, 595, 1090, 214);
		workOrderPanel.add(scrollPane_6);
		
		DefaultTableModel dtmWorkOrders = new DefaultTableModel();
		workOrdersTable = new JTable(dtmWorkOrders);
		workOrdersTable.setEnabled(false);
		projectWOTable.setEnabled(false);
		dtmWorkOrders.addColumn("Item serial number");
		dtmWorkOrders.addColumn("Num work order");
		dtmWorkOrders.addColumn("Description");
		dtmWorkOrders.addColumn("Planned time maintenance");
		dtmWorkOrders.addColumn("Planned start");
		dtmWorkOrders.addColumn("Planned end");
		dtmWorkOrders.addColumn("Actual start");
		dtmWorkOrders.addColumn("Actual end");
		dtmWorkOrders.addColumn("Type");
		dtmWorkOrders.addColumn("Breakdown picture");
		dtmWorkOrders.addColumn("Corrective");
		dtmWorkOrders.addColumn("Cod urgency");
		dtmWorkOrders.addColumn("Cod project");
		dtmWorkOrders.addColumn("Item serial number");
		dtmWorkOrders.addColumn("Num maintenance schedule");

		scrollPane_6.setViewportView(workOrdersTable);
				
		this.workOrderLogics.buildWorkOrderType(cmbWOType, textWONumMS, txtWOCodProject , cmbWOTrigger, 
				WOTriggertxtArea, dtmWorkOrders);
		
	}
	
	private void setItemImage(File imageFile) {
		
		BufferedImage itemImage = null;
		try {
		    itemImage = ImageIO.read(imageFile);
		} catch (IOException e) {
		    e.printStackTrace();
		}		
		Image dimg = itemImage.getScaledInstance(this.lblItemImage.getWidth(), this.lblItemImage.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		this.lblItemImage.setIcon(imageIcon);
	}
}
