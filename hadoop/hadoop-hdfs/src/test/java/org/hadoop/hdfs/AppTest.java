package org.hadoop.hdfs;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	
	public static void main(String[] args) {
		// System.setProperty("java.library.path", "D:\\hadoop\\hadoop-common-2.2.0-bin-master\\bin"); 
		System.out.println(System.getProperty("user.dir"));
		System.loadLibrary("hadoop");
		System.out.println(System.getProperty("java.library.path"));
	}
    
}
