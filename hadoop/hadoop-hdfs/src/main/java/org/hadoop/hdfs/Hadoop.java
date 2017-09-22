package org.hadoop.hdfs;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Hadoop {
	
	
	public static void main(String[] args) throws Exception{
		
		//System.setProperty("hadoop.home.dir", "D:\\hadoop\\hadoop-common-2.2.0-bin-master");
		Configuration conf = new Configuration(false);
    	//conf.addResource(name);
    	//conf.set("fs.AbstractFileSystem.hdfs.impl", "org.apache.hadoop.fs.Hdfs");
    	  FileSystem fs=   FileSystem.get(new URI("hdfs://192.168.1.127:9000"), conf);
    	//	Hdfs fs = 	(Hdfs) AbstractFileSystem.createFileSystem(new URI("hdfs://192.168.1.127:9000"), conf);
    	//	FileContext fileContext = FileContext.getFileContext(fs, conf);
    		//fileContext.mkdir(new Path("hdfs://192.168.1.127:9000/"), new FsPermission((short)777), false);
    		try (FSDataInputStream input = fs.open(new Path("hdfs://192.168.1.127:9000/logs/weat.txt"));){
    			System.out.println(input.available());
    			byte[] b = new byte[input.available()];
    			input.read(b);
    			System.out.println(new String(b));
				
			} catch (Exception e) {
				// TODO: handle exception
			    e.printStackTrace();
			}
    		
    		
    		
	}
	
	
	

}
