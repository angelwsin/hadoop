package org.hadoop.hbase.scan;

import java.util.NavigableMap;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.hadoop.hbase.AbstractCmdExecutor;
import org.hadoop.hbase.CmdContext;
import org.hadoop.hbase.CmdInvoker;
import org.hadoop.hbase.OpType;

public class ScanCmd extends AbstractCmdExecutor<Scan>{

	public ScanCmd(OpType opTyp) {
		super(opTyp);
	}

	@Override
	public void executor(CmdContext<Scan> context) {
		new CmdInvoker().doInvoke((Connection conn)->{
			try(Table table = conn.getTable(context.getTableName());) {
				ResultScanner rsScan = table.getScanner(parseArgs(context.getData()));
				rsScan.forEach((Result result)->{
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
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		
	}

	@Override
	public Scan parseArgs(String[] args) {
		// args2  colum
		// args3  rowkey
		if(args.length<5){
			throw   new IllegalArgumentException("参数不正确");
		}
		Scan scan = new Scan(Bytes.toBytes(args[3]), new QualifierFilter(CompareOp.EQUAL, new SubstringComparator(args[4])));
		scan.addFamily(Bytes.toBytes(args[2]));
		return scan;
	}

}
