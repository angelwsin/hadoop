package org.hadoop.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public  class CmdInvoker {
	
	static Configuration config = new Configuration(false);
	{
	 config.set(HConstants.ZOOKEEPER_QUORUM, "n3,n4,n5,n6,n7");
	}
    
	
	
	public void doInvoke(Invoker invoker){
		try (Connection connection =ConnectionFactory.createConnection(config);){
			invoker.invoke(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
