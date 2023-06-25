package ks46team04.admin.dto;

public class InOutcomingForm {
	/* 입/출고 정보 */
	private String inOutcomingCode;
	private String goodsName;
	private String goodsLotNumber;
	private String goodsCompany;
	private String inOutcomingType;
	private int inOutcomingQuantity;
	private String inOutcomingDate;
	
	/* 입고 상세 정보 및 비정상재고 정보 */
	private String goodsStockCode;
	private String goodsExpiryDate;
	private Boolean stocktakingCheck;
	private String stocktakingDate;
	private Boolean unusualStockCheck;
	private int unusualStockAmount;
	private String unusualStockDate;
	private String unusualStockReason;
	
	/* 출고 상세 정보 */
	private String outcomingId;
	private String foundationName;
	public String getInOutcomingCode() {
		return inOutcomingCode;
	}
	public void setInOutcomingCode(String inOutcomingCode) {
		this.inOutcomingCode = inOutcomingCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsLotNumber() {
		return goodsLotNumber;
	}
	public void setGoodsLotNumber(String goodsLotNumber) {
		this.goodsLotNumber = goodsLotNumber;
	}
	public String getGoodsCompany() {
		return goodsCompany;
	}
	public void setGoodsCompany(String goodsCompany) {
		this.goodsCompany = goodsCompany;
	}
	public String getInOutcomingType() {
		return inOutcomingType;
	}
	public void setInOutcomingType(String inOutcomingType) {
		this.inOutcomingType = inOutcomingType;
	}
	public int getInOutcomingQuantity() {
		return inOutcomingQuantity;
	}
	public void setInOutcomingQuantity(int inOutcomingQuantity) {
		this.inOutcomingQuantity = inOutcomingQuantity;
	}
	public String getInOutcomingDate() {
		return inOutcomingDate;
	}
	public void setInOutcomingDate(String inOutcomingDate) {
		this.inOutcomingDate = inOutcomingDate;
	}
	public String getGoodsStockCode() {
		return goodsStockCode;
	}
	public void setGoodsStockCode(String goodsStockCode) {
		this.goodsStockCode = goodsStockCode;
	}
	public String getGoodsExpiryDate() {
		return goodsExpiryDate;
	}
	public void setGoodsExpiryDate(String goodsExpiryDate) {
		this.goodsExpiryDate = goodsExpiryDate;
	}
	public Boolean getStocktakingCheck() {
		return stocktakingCheck;
	}
	public void setStocktakingCheck(Boolean stocktakingCheck) {
		this.stocktakingCheck = stocktakingCheck;
	}
	public String getStocktakingDate() {
		return stocktakingDate;
	}
	public void setStocktakingDate(String stocktakingDate) {
		this.stocktakingDate = stocktakingDate;
	}
	public Boolean getUnusualStockCheck() {
		return unusualStockCheck;
	}
	public void setUnusualStockCheck(Boolean unusualStockCheck) {
		this.unusualStockCheck = unusualStockCheck;
	}
	public int getUnusualStockAmount() {
		return unusualStockAmount;
	}
	public void setUnusualStockAmount(int unusualStockAmount) {
		this.unusualStockAmount = unusualStockAmount;
	}
	public String getUnusualStockDate() {
		return unusualStockDate;
	}
	public void setUnusualStockDate(String unusualStockDate) {
		this.unusualStockDate = unusualStockDate;
	}
	public String getUnusualStockReason() {
		return unusualStockReason;
	}
	public void setUnusualStockReason(String unusualStockReason) {
		this.unusualStockReason = unusualStockReason;
	}
	public String getOutcomingId() {
		return outcomingId;
	}
	public void setOutcomingId(String outcomingId) {
		this.outcomingId = outcomingId;
	}
	public String getFoundationName() {
		return foundationName;
	}
	public void setFoundationName(String foundationName) {
		this.foundationName = foundationName;
	}
	@Override
	public String toString() {
		return "InOutcomingForm [inOutcomingCode=" + inOutcomingCode + ", goodsName=" + goodsName + ", goodsLotNumber="
				+ goodsLotNumber + ", goodsCompany=" + goodsCompany + ", inOutcomingType=" + inOutcomingType
				+ ", inOutcomingQuantity=" + inOutcomingQuantity + ", inOutcomingDate=" + inOutcomingDate
				+ ", goodsStockCode=" + goodsStockCode + ", goodsExpiryDate=" + goodsExpiryDate + ", stocktakingCheck="
				+ stocktakingCheck + ", stocktakingDate=" + stocktakingDate + ", unusualStockCheck=" + unusualStockCheck
				+ ", unusualStockAmount=" + unusualStockAmount + ", unusualStockDate=" + unusualStockDate
				+ ", unusualStockReason=" + unusualStockReason + ", outcomingId=" + outcomingId + ", foundationName="
				+ foundationName + "]";
	}

	
}
