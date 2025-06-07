package com.drugstore.DTO;

public class LowStockReportDTO {
    private String drugName;
    public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public long getCurrentQuantity() {
		return currentQuantity;
	}
	public void setCurrentQuantity(long currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
	public long getReorderThreshold() {
		return reorderThreshold;
	}
	public void setReorderThreshold(long reorderThreshold) {
		this.reorderThreshold = reorderThreshold;
	}
	private long currentQuantity;
    private long reorderThreshold;
}