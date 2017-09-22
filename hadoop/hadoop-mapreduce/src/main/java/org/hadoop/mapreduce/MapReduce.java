package org.hadoop.mapreduce;

import java.io.IOException;
import java.util.Objects;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapReduce {
	
    
	
	public static void main(String[] args)throws Exception {
		// Create a new Job
		     Job job = Job.getInstance();
		     job.setJarByClass(MapReduce.class);
		     
		     // Specify various job-specific parameters     
		     job.setJobName("myjob");
		     //设置输入和输出路径
		     FileInputFormat.addInputPath(job, new Path("/logs/weather.txt"));
		     FileOutputFormat.setOutputPath(job, new Path("/out/"));
		     job.setMapperClass(LogMapper.class);
		     job.setReducerClass(Reducex.class);
		     job.setOutputKeyClass(Text.class);
		     job.setMapOutputValueClass(IntWritable.class);
		
		     // Submit the job, then poll for progress until the job is complete
		     job.waitForCompletion(true);
		 
	}

}



// 2.x 引入了 Context
class LogMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

    static Logger logger =  LoggerFactory.getLogger(App.class);
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//StringTokenizer token = new StringTokenizer(arg1.toString()," ");
        String[] str  =  StringUtils.split(value.toString(), ' ');
        logger.info(str.toString());
        if(!(Objects.isNull(str)||str.length<3)){
            
        	context.write(new Text(str[0]), new IntWritable(Integer.valueOf(str[2].replace("°C", ""))));
        }
	}
    
    
    
    
}


class Reducex extends  Reducer<Text, IntWritable, Text, IntWritable>{

    
	@Override
	protected void reduce(Text arg0, Iterable<IntWritable> arg1,
			Reducer<Text, IntWritable, Text, IntWritable>.Context arg2) throws IOException, InterruptedException {
		
		arg2.write(arg0, arg1.iterator().next());
	}
    
}
