package com.poc.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.poc.bean.Customer;
import com.poc.mr.CountRAG;
import com.poc.mr.Util;

public class TestCurrent {

	public static void main(String[] args) throws IOException,
			InterruptedException, ClassNotFoundException {
		Util util = new Util();

		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "9.121.56.189");
		conf.set("adaptivemr.zookeeper.hosts", "9.121.56.189:2181");
		conf.set("hbase.zookeeper.quorum", "9.121.56.189");
		conf.set("hbase.zookeeper.quorum", "9.121.56.189");
		Job job = new Job(conf);

		job.setJarByClass(CountRAG.class);
		job.setJobName("Count RAG");

		FileInputFormat.addInputPath(job, new Path(
				"hdfs://9.121.56.189:8020/dillip/COMPLAINT_MASTER.txt"));
		FileOutputFormat.setOutputPath(job, new Path(
				"hdfs://9.121.56.189:8020/dillip/COMPLAINT_MASTER.txt"));

		// job.setMapperClass(RAGMapper.class);
		// job.setReducerClass(RAGReduce.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		if (job.waitForCompletion(true)) {

			File file = new File("hdfs://9.121.56.189:8020/dillip"
					+ "/part-r-00000");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					file));
			String line = null;
			String array[] = null;

		}
	}

}
