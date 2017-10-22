package org.hadoop.hbase.create;

import java.util.ArrayList;
import java.util.List;

import javax.management.OperationsException;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.hadoop.hbase.AbstractCmdExecutor;
import org.hadoop.hbase.CmdContext;
import org.hadoop.hbase.CmdInvoker;
import org.hadoop.hbase.OpType;

public class CreateCmd extends AbstractCmdExecutor<List<HColumnDescriptor>> {
	
	 

	public CreateCmd(OpType opTyp) {
		super(opTyp);
	}

	@Override
	public void executor(CmdContext<List<HColumnDescriptor>> context) {
		 new CmdInvoker().doInvoke((Connection conn)->{
			 try (Admin admin = conn.getAdmin();){
				 TableName tableName = context.getTableName();
				 if(admin.tableExists(tableName)){
						throw new OperationsException("已经存在了该表:"+tableName);
				   }
				 
				 HTableDescriptor table = new HTableDescriptor(tableName);
				 parseArgs(context.getData()).forEach((HColumnDescriptor colum)->{
					 table.addFamily(colum);
					});
				 admin.createTable(table);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		 });
		
		
	}

	@Override
	public List<HColumnDescriptor> parseArgs(String[] args) {
		// args1 tabelname
		// args2 ...n column
		if(args.length<3){
			throw   new IllegalArgumentException("参数不正确");
		}
		List<HColumnDescriptor> colums = new ArrayList<>(args.length-2);
		for(int i=2;i<args.length;i++){
			HColumnDescriptor colum = new HColumnDescriptor(args[i]);
			colums.add(colum);
		}
		return colums;
	}

}
