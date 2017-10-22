package org.hadoop.hbase;

import org.apache.hadoop.hbase.client.Connection;

public interface Invoker {
	
	public void invoke(Connection connection);

}
