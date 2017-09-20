package org.hadoop.mapreduce;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringTokenizer;

import org.apache.commons.math3.analysis.function.Max;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.StringUtils;

public class MapReduce {

}




// 2.8.1 引入了 Context
class LogMapper implements Mapper<IntWritable, Text, Text, IntWritable>{

    @Override
    public void configure(JobConf arg0) {
        
    }

    @Override
    public void close() throws IOException {
        
    }

    @Override
    public void map(IntWritable arg0, Text arg1, OutputCollector<Text, IntWritable> arg2, Reporter arg3) throws IOException {
              //StringTokenizer token = new StringTokenizer(arg1.toString()," ");
              String[] str  =  StringUtils.split(arg1.toString(), ' ');
              if(!(Objects.isNull(str)||str.length<3)){
              arg2.collect(new Text(str[0]), new IntWritable(Integer.valueOf(str[2].replace("°C", ""))));
              }
        
    }
    
    
    
    
}


class Reducex implements  Reducer<Text, IntWritable, Text, IntWritable>{

    @Override
    public void configure(JobConf job) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void close() throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
               
        output.collect(key, values.next());
    }

    
    
}
