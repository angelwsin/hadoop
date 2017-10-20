package org.hadoop.hdfs.namenode;

import org.apache.hadoop.hdfs.server.namenode.NameNode;

public class NameNodeCmd {
	
	
	
	public static void main(String[] args) {
		
		
		//commdline    start-dfs.sh
		  try {
			NameNode.main(args);
			
			//rpc     NameNodeRpcServer
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  
		  
		  //rpc  栈
		  //应用层
		  //   协议层                序列化    protobuf
		  //  传输层                 netty
		  
		  
		  /*
		   *      rpcServer org.apache.hadoop.ipc.Server.Server(String, int, Class<? extends Writable>, int, int, int, Configuration, String, SecretManager<? extends TokenIdentifier>, String)
		   *      
		   *      accept           Listener线程
		   *      
		   *      reader            Reader线程
		   *      
		   *      writer           Responder线程
		   *      
		   *      
		   *      Server的组件处理方式
		   *      
		   *      Listener线程 监听 accept    得到客户端socket 注册到connectionManager中
		   *        使用 求余的到一个 Reader 线程注册到 线程的处理队列中  在 Reader线程中注册reader事件 进行读处理
		   *         Handler 线程  处理提交到callQueue的请求    writer响应客户端
		   * 
		   * 
		   */
		  
		  
		  //start datanode
		  //org.apache.hadoop.hdfs.server.datanode.DataNode
	}

}
