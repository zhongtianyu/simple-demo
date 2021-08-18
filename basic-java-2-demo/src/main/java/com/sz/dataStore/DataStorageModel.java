package com.sz.dataStore;

/**
 * 数据存储model
 * @author wangBo
 * @date 2018年9月27日 上午9:36:20
 */

public class DataStorageModel {
	
	
	public DataStorageModel() {

	}

	/**
	 * id
	 */
	private String id;

	/**
	 * 业务线
	 */
	private String busiLine;

	/**
	 * 账期日
	 */
	private String settleDate;

	/**
	 * 省
	 */
	private String province;
	
	/**
	 * json集
	 */
	private String json;
	
	/**
	 * 页码
	 */
	private int pageNo =1;
	
	/**
	 * 条数
	 */
	private int pageSize =2;
	
	
	/**
	 * 流程类型
	 */
	private String flowType;
	
	/**
	 * 表别名 用于前端传递表名
	 */
	private String tableAlias;

	/**
	 * 需要重跑的表，以竖线分割
	 */
	private String tableNames = "";

	
	/**
	 * 时间类型
	 */
	private String period;
	
	public String getPeriod() {
		return period;
	}
	
	public void setPeriod(String period) {
		this.period = period;
	}

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusiLine() {
		return busiLine;
	}

	public void setBusiLine(String busiLine) {
		this.busiLine = busiLine;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}


	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public DataStorageModel(String busiLine, String settleDate, String province, String flowType) {
		super();
		this.busiLine = busiLine;
		this.settleDate = settleDate;
		this.province = province;
		this.flowType= flowType;
	}

	public DataStorageModel(String busiLine, String settleDate, String province, String flowType, String period) {
		super();
		this.busiLine = busiLine;
		this.settleDate = settleDate;
		this.province = province;
		this.flowType= flowType;
		this.period = period;
	}

	@Override
	public String toString() {
		return "DataStorageModel{" +
				"id='" + id + '\'' +
				", busiLine='" + busiLine + '\'' +
				", settleDate='" + settleDate + '\'' +
				", province='" + province + '\'' +
				", json='" + json + '\'' +
				", pageNo=" + pageNo +
				", pageSize=" + pageSize +
				", flowType='" + flowType + '\'' +
				", tableAlias='" + tableAlias + '\'' +
				", tableNames='" + tableNames + '\'' +
				", period='" + period + '\'' +
				'}';
	}

	public String getTableNames() {
		return tableNames;
	}

	public void setTableNames(String tableNames) {
		this.tableNames = tableNames;
	}
}
