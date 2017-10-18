package org.hadoop.yarn;


/*
 * 
 * YARN的基本思想是将资源管理和作业调度/监控的功能分为独立的守护进程。 想法是拥有一个全局ResourceManager（RM）和每个应用程序的ApplicationMaster（AM）。 应用程序是单个作业或作业的DAG。
 * 
 * ResourceManager和NodeManager构成数据计算框架。  ResourceManager是在系统中的所有应用程序之间仲裁资源的最终权力。  NodeManager是负责容器的每机器框架代理，监视其资源使用情况（cpu，内存，磁盘，网络）并将其与ResourceManager / Scheduler进行报告。

       每个应用程序ApplicationMaster实际上是一个框架特定的库，任务是从ResourceManager协商资源，并与NodeManager一起执行和监视任务。
 * 
 */
public class Yarn {

}
