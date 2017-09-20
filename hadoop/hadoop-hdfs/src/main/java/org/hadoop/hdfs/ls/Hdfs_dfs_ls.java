package org.hadoop.hdfs.ls;

import java.io.File;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.AbstractFileSystem;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Hdfs;
import org.apache.hadoop.fs.Path;

public class Hdfs_dfs_ls {
    
    
    
    static{
        
        
    }
    
    static String  PATH = System.getProperty("user.dir")+File.pathSeparator+"hdfs";
    
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration(false);
        //conf.addResource(name);
        conf.set("fs.AbstractFileSystem.hdfs.impl", "org.apache.hadoop.fs.Hdfs");
            Hdfs fs =   (Hdfs) AbstractFileSystem.createFileSystem(new URI("hdfs://192.168.1.127:9000"), conf);
            ls(fs,new Path("hdfs://192.168.1.127:9000/"),"\t");
    }
    
    
    public static void ls(Hdfs fs,Path path,String t)throws Exception{
        FileStatus[] files = fs.listStatus(path);
        if(files==null||files.length==0||files[0].getPath()==path)return;
        for(FileStatus fst : files){
            Path pt = fst.getPath();
            System.out.println(String.format(t+"-%s", pt.toString()));
            ls(fs,pt,t+t);
        }
    }

}
