package org.hadoop.hbase.put;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.hadoop.hbase.AbstractCmdExecutor;
import org.hadoop.hbase.CmdContext;
import org.hadoop.hbase.CmdInvoker;
import org.hadoop.hbase.OpType;

public class PutCmd extends AbstractCmdExecutor<Put>{

	public PutCmd(OpType opTyp) {
		super(opTyp);
	}

	@Override
	public void executor(CmdContext<Put> context) {
		new CmdInvoker().doInvoke((Connection conn)->{
			try(Table table = conn.getTable(context.getTableName());) {
				table.put(parseArgs(context.getData()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}

	@Override
	public Put parseArgs(String[] args) {
		// args1 tablename
		// args2 column
		// args3 rowkey
		// args4 q  args5 value
		if(args.length!=6){
			throw   new IllegalArgumentException("参数不正确");
		}
		Put put = new Put(Bytes.toBytes(args[3]));
		put.addColumn(Bytes.toBytes(args[2]), Bytes.toBytes(args[4]), Bytes.toBytes(args[5]));
		return put;
	}

}
