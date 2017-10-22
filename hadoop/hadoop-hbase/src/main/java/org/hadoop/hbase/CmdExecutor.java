package org.hadoop.hbase;

public interface CmdExecutor<T> {
	
	
   public void executor(CmdContext<T> context);
   
   public OpType getOpType();
   
   public T parseArgs(String[] args);

}
