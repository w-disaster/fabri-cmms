package model;

public class Purchase {
	
	private String codSupplier;
	private String billNumber;
	private String purchaseDate;
	
	public String getCodSupplier() {
		return codSupplier;
	}
	public void setCodSupplier(String codSupplier) {
		this.codSupplier = codSupplier;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}
