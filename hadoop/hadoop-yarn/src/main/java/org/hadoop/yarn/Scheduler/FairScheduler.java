package org.hadoop.yarn.Scheduler;

public class FairScheduler {
	
	//公平的共享资源
    //默认情况下，公平调度器仅在内存上调度公平性决定
	//可以 使用  Dominant Resource Fairness 将其配置为使用内存和CPU进行调度
	//允许加优先级实现优先调用
	//此外，公平调度程序允许为每个队列设置不同的自定义策略，以允许以任何方式共享队列的资源
	//org.apache.hadoop.yarn.server.resourcemanager.scheduler.fair.SchedulingPolicy来构建自定义策略
	//FifoPolicy，FairSharePolicy（默认）和DominantResourceFairnessPolicy都是内置的

}
