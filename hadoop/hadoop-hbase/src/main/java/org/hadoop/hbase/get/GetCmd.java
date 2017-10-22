package org.hadoop.hbase.get;

import java.util.NavigableMap;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.hadoop.hbase.AbstractCmdExecutor;
import org.hadoop.hbase.CmdContext;
import org.hadoop.hbase.CmdInvoker;
import org.hadoop.hbase.OpType;

public class GetCmd extends AbstractCmdExecutor<Get>{

	public GetCmd(OpType opTyp) {
		super(opTyp);
	}

	@Override
	public void executor(CmdContext<Get> context) {
		new CmdInvoker().doInvoke((Connection conn)->{
			try(Table table = conn.getTable(context.getTableName());) {
				Result result =  table.get(parseArgs(context.getData()));
				result.getMap().forEach((byte[] familyName, NavigableMap<byte[], NavigableMap<Long, byte[]>> family)->{
					StringBuilder builder  = new StringBuilder("\n");
					family.forEach((byte[] qualifier, NavigableMap<Long, byte[]> values)->{
						builder.append(Bytes.toString(familyName))
						.append(":")
						.append(Bytes.toString(qualifier));
						builder.append("\t");
						 values.forEach((Long version, byte[] value)->{
							 builder.append(Bytes.toString(value));
							 builder.append("\t");
							 builder.append(version);
						 });
					 System.out.println(builder.toString());
					});
					
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}

	@Override
	public Get parseArgs(String[] args) {
		// args2 rowkey 
		if(args.length<3){
			throw   new IllegalArgumentException("参数不正确");
		}
		Get get  = new Get(Bytes.toBytes(args[2]));
		return get;
	}

}
