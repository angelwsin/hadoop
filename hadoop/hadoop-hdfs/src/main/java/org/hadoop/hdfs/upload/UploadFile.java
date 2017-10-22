package org.hadoop.hdfs.upload;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.EnumSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.AbstractFileSystem;
import org.apache.hadoop.fs.CreateFlag;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileContext;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Hdfs;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.Options.CreateOpts;
import org.apache.hadoop.fs.permission.FsPermission;

public class UploadFile {
    
    
    static String  PATH = System.getProperty("user.dir")+File.separator+"file";
    
    public static void main(String[] args) throws Exception{
        
        Configuration conf = new Configuration(false);
        //conf.addResource(name);
        conf.set("fs.AbstractFileSystem.hdfs.impl", "org.apache.hadoop.fs.Hdfs");
            Hdfs fs =   (Hdfs) AbstractFileSystem.createFileSystem(new URI("hdfs://n1:9000"), conf);
            FileContext fileContext = FileContext.getFileContext(fs, conf);
            fileContext.mkdir(new Path("hdfs://n1:9000/logs"), new FsPermission((short)777), false);
            Path f=  new Path("hdfs://n1:9000/logs/weather.txt");
            //fileContext.delete(f, false);
            File file = new File(PATH+"/weather.txt");
            
            try(FSDataOutputStream out =  fs.create(f, EnumSet.of(CreateFlag.CREATE), CreateOpts.blockSize(64).perms(new FsPermission((short)777)));
                FileInputStream is=  new FileInputStream(file);) {
                byte[] b=  new byte[is.available()];
                is.read(b);
                out.write(b);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
    }

}
