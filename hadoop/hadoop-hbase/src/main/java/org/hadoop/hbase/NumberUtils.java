package org.hadoop.hbase;

public class NumberUtils {
	
	
		  public static byte[] int2byte(int number){  
		        byte[] byt = new byte[4];  
		        byt[0] = (byte)(number&0xff);  
		        byt[1] = (byte)(number>>8&0xff);  
		        byt[2] = (byte)(number>>16&0xff);  
		        byt[3] = (byte)(number>>24&0xff);  
		        return byt;  
		    }  

}
