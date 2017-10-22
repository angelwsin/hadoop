package org.hadoop.hbase;

import java.util.Objects;

import org.hadoop.hbase.create.CreateCmd;
import org.hadoop.hbase.delete.DeleteCmd;
import org.hadoop.hbase.get.GetCmd;
import org.hadoop.hbase.put.PutCmd;
import org.hadoop.hbase.scan.ScanCmd;

public class HbaseCmd {
	             
	static{
		CmdService.register(new CreateCmd(OpType.create));
		CmdService.register(new PutCmd(OpType.put));
		CmdService.register(new GetCmd(OpType.get));
		CmdService.register(new ScanCmd(OpType.scan));
		CmdService.register(new DeleteCmd(OpType.delete));
	}
	
	public static void main(String[] args) {
		if(Objects.isNull(args)||args.length<2){
			throw   new IllegalArgumentException("参数不正确,不能为空");
		}
		OpType type = Enum.valueOf(OpType.class, args[0]);
		CmdContext<Object> context = new CmdContext<>();
		context.setData(args);
		context.setOpType(type);
		context.setTableName(args[1]);
		CmdService.getCmdExecutor(type).executor(context);
		
		
		
	}

}
