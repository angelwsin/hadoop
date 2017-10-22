package org.hadoop.hbase;

import org.apache.hadoop.hbase.TableName;

public class CmdContext<T> {
	
	 private OpType opType;
	 private String[] data;
	 private  String tableName;
	 
	public OpType getOpType() {
		return opType;
	}
	public void setOpType(OpType opType) {
		this.opType = opType;
	}
	public TableName getTableName() {
		return TableName.valueOf(tableName);
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	
	
	 

}
