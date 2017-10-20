package org.hadoop.hbase;

import java.util.Map;
import java.util.Objects;

import javax.management.OperationsException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;

public class HbaseCmd {
	
	
	public static void main(String[] args) {
		
		if(Objects.isNull(args)||args.length==0){
			throw   new IllegalArgumentException("参数不正确,不能为空");
		}
		 Configuration config = new Configuration(false);
	        config.set(HConstants.ZOOKEEPER_QUORUM, "n3,n4,n5,n6,n7");
		OpType type = Enum.valueOf(OpType.class, args[0]);
		
		try(Connection connection =ConnectionFactory.createConnection(config);
				Admin admin = connection.getAdmin();) {
			TableName talbeName = TableName.valueOf(args[1]);
			
			switch (type) {
			case create :{
				if(args.length%2==0){
					throw   new IllegalArgumentException("参数不正确,第一个参数是操作类型，第二个是表，第三个是列,其他 key value");
				}
				if(admin.tableExists(talbeName)){
					throw new OperationsException("已经存在了该表");
				}
				HTableDescriptor  desc = new HTableDescriptor(talbeName);
				HColumnDescriptor colum = new HColumnDescriptor(args[2]);
				desc.addFamily(colum);
				for(int i=3;i<args.length;){
					colum.setValue(args[i], args[++i]);
				}
				admin.createTable(desc);
			}break;
			
			case insert:{
				if(args.length%2==0){
					throw   new IllegalArgumentException("参数不正确,第一个参数是操作类型，第二个是表，第三个是列,其他 key value");
				}
				
				HTableDescriptor table = admin.getTableDescriptor(talbeName);
				for(int i=3;i<args.length;){
					table.setValue(args[i], args[++i]);
				}
			}break;
			case select:{
				if(args.length%2!=0){
					throw   new IllegalArgumentException("参数不正确,第一个参数是操作类型，第二个是表，第三个是列,其他 key value");
				}
				HTableDescriptor table =admin.getTableDescriptor(talbeName);
				table.getFamiliesKeys().forEach((byte[] column)->{
					HColumnDescriptor colum = table.getFamily(column);
					Map<ImmutableBytesWritable,ImmutableBytesWritable> values = colum.getValues();
					values.forEach((ImmutableBytesWritable key,ImmutableBytesWritable value)->{
						System.out.println(new String(key.get())+"="+new String(value.get())+",");
					});
				});
			}break;
			default:
				break;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
