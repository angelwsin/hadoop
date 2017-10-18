package org.hadoop.yarn.Scheduler;

//http://hadoop.apache.org/docs/stable/hadoop-yarn/hadoop-yarn-site/ResourceManagerRestart.html
public class ResourceManger {
	
	
	//ResourceManger 存在单点问题 
	
	//ResourceManager Restart,一个功能可以增强ResourceManager，使其在重新启动时保持运行状态，并且使ResourceManager的停机时间对最终用户不可见
	
	//ResourceManager Restart feature is divided into two phases:
	 //1)ResourceManager Restart Phase 1 (Non-work-preserving RM restart):增强RM以在可插拔状态存储中持久应用/尝试状态和其他凭证信息。  
	      //RM将在重新启动时从状态重新加载此信息，并重新启动以前运行的应用程序。 用户不需要重新提交申请。
	 //2)ResourceManager Restart Phase 2 (Work-preserving RM restart):专注于重新构建ResourceManager的运行状态，方法是在重新启动之后，
	          //通过组合来自NodeManagers的容器状态和来自ApplicationMasters的容器请求。 与第1阶段的主要区别在于，以前运行的应用程序在RM重新启动后不会被杀死，因此由于RM中断，应用程序不会失去其工作。
	
	
	

}
