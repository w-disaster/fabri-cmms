package model;

import java.sql.Date;

public class Sale {
	
	private String codSupplier;
	private String itemSerialNumber;
	private String dateConclusion;
	private String expiration;
	private String numWarranty;
	private String date;
	private String record;
	private String warrantyFile = "";
	private String notes;
	private String image;
	
	public String getItemSerialNumber() {
		return itemSerialNumber;
	}
	public void setItemSerialNumber(String itemSerialNumber) {
		this.itemSerialNumber = itemSerialNumber;
	}
	public String getCodSupplier() {
		return codSupplier;
	}
	public void setCodSupplier(String codSupplier) {
		this.codSupplier = codSupplier;
	}
	public String getDateConclusion() {
		return dateConclusion;
	}
	public void setDateConclusion(String dateConclusion) {
		this.dateConclusion = dateConclusion;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public String getNumWarranty() {
		return numWarranty;
	}
	public void setNumWarranty(String numWarranty) {
		this.numWarranty = numWarranty;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getWarrantyFile() {
		return warrantyFile;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
