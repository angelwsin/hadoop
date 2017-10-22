package org.hadoop.hbase.delete;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.hadoop.hbase.AbstractCmdExecutor;
import org.hadoop.hbase.CmdContext;
import org.hadoop.hbase.CmdInvoker;
import org.hadoop.hbase.OpType;

public  class DeleteCmd extends AbstractCmdExecutor<Delete>{

	public DeleteCmd(OpType opTyp) {
		super(opTyp);
	}

	@Override
	public void executor(CmdContext<Delete> context) {
		new CmdInvoker().doInvoke((Connection conn)->{
			try(Table table = conn.getTable(context.getTableName());) {
				table.delete(parseArgs(context.getData()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}

	@Override
	public Delete parseArgs(String[] args) {
		if(args.length<3){
			throw   new IllegalArgumentException("参数不正确");
		}
		//args2 rowKey
		Delete del = new Delete(Bytes.toBytes(args[2]));
		return del;
	}

}
