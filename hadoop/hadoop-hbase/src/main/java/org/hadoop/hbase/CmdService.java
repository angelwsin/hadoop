package org.hadoop.hbase;

import java.util.HashMap;
import java.util.Map;

public class CmdService {
	
	public static Map<OpType,CmdExecutor<?>> cmds = new HashMap<>();
	
	public static void register(CmdExecutor<?> cmd){
		cmds.put(cmd.getOpType(), cmd);
	}

	@SuppressWarnings("unchecked")
	public static <T> CmdExecutor<T> getCmdExecutor(OpType optype){
		  return (CmdExecutor<T>) cmds.get(optype);
	}
}
