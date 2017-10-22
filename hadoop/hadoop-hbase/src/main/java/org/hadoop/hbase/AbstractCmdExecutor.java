package org.hadoop.hbase;

public abstract class AbstractCmdExecutor<T> implements CmdExecutor<T> {
	
	private OpType opType;
	 
	 public AbstractCmdExecutor(OpType opTyp) {
		this.opType = opTyp;
		CmdService.register(this);
	}

	public OpType getOpType() {
		return opType;
	}
	 
	

}
