package com.poc.mr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.poc.bean.Customer;

public class CountRAG {
	static class RAGMapper extends
			Mapper<LongWritable, Text, Text, IntWritable> {

		@Override
		public void map(LongWritable key, Text values, Context context)
				throws IOException, InterruptedException {
			String arr[] = values.toString().split(",", -1);
			String cid = arr[1];
			String rag = arr[37];
			context.write(new Text(rag), new IntWritable(1));
		}
	}

	static class RAGReduce extends
			Reducer<Text, IntWritable, Text, IntWritable> {

		@Override
		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable value : values) {
				sum += ((IntWritable) value).get();
			}
			context.write(key, new IntWritable(sum));
		}
	}

	// public ArrayList<Customer> getCountRAG() throws IOException,
	// InterruptedException, ClassNotFoundException {
	// ArrayList<Customer> list = new ArrayList<Customer>();
	//
	// Util util = new Util();
	// Properties properties = new Properties();
	// properties = util.getPropertiesFromClasspath();
	// Configuration conf = new Configuration();
	// Job job = new Job(conf);
	//
	// job.setJarByClass(CountRAG.class);
	// job.setJobName("Count RAG");
	// String outputPath = properties.getProperty("CountRAG");
	// Util.deleteFile(new File(outputPath));
	//
	// FileInputFormat.addInputPath(job,
	// new Path(properties.getProperty("CUSTOMER_MASTER_NBA")));
	// FileOutputFormat.setOutputPath(job, new Path(outputPath));
	//
	// job.setMapperClass(RAGMapper.class);
	// job.setReducerClass(RAGReduce.class);
	// job.setMapOutputKeyClass(Text.class);
	// job.setMapOutputValueClass(IntWritable.class);
	//
	// if (job.waitForCompletion(true)) {
	//
	// File file = new File(outputPath + "/part-r-00000");
	// BufferedReader bufferedReader = new BufferedReader(new FileReader(
	// file));
	// String line = null;
	// String array[] = null;
	// while ((line = bufferedReader.readLine()) != null) {
	// Customer customer = new Customer();
	// array = line.split("\t");
	// customer.setRag(array[0]);
	// customer.setCount(Integer.parseInt(array[1]));
	// if (array[0].equalsIgnoreCase("A")) {
	// customer.setColour("FFA07A");
	// } else if (array[0].equalsIgnoreCase("G")) {
	// customer.setColour("008000");
	// } else if (array[0].equalsIgnoreCase("R")) {
	// customer.setColour("DC143C");
	// }
	// list.add(customer);
	// }
	//
	// }
	//
	// return list;
	// }

	public ArrayList<Customer> getCountRAG() throws IOException,
			InterruptedException, ClassNotFoundException {
		ArrayList<Customer> list = new ArrayList<Customer>();

		Util util = new Util();
		Properties properties = new Properties();
		properties = util.getPropertiesFromClasspath();

		File file = new File(properties.getProperty("CUSTOMER_MASTER_NBA"));
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = null;
		String array[] = null;
		int red = 0;
		int amber = 0;
		int green = 0;
		while ((line = bufferedReader.readLine()) != null) {

			array = line.split(",");
			

			if (array[37].equalsIgnoreCase("A")) {
				amber++;
			} else if (array[37].equalsIgnoreCase("G")) {
				green++;
			} else if (array[37].equalsIgnoreCase("R")) {
				red++;
			}
		}
		System.out.println("red : " + red + "amber " + amber + "green" + green);
		
		File file1 = new File(properties.getProperty("CUSTOMER_MASTER_NBA"));
		BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file1));
		String line1 = null;
		String array1[] = null;
		while ((line1 = bufferedReader1.readLine()) != null) {
			System.out.println(line);
			Customer customer = new Customer();
			array1 = line1.split(",");
			System.out.println("eered : "+red +"amber "+amber+"green"+green);
			if (array1[37].equalsIgnoreCase("A")) {
				
				customer.setRag(array1[37]);
				customer.setCount(amber);
				customer.setColour("FFA07A");
			} else if (array1[37].equalsIgnoreCase("G")) {
				
				customer.setRag(array1[37]);
				customer.setCount(green);
				customer.setColour("008000");
			} else if (array1[37].equalsIgnoreCase("R")) {
				
				customer.setRag(array1[37]);
				customer.setCount(red);
				customer.setColour("DC143C");
			}
			list.add(customer);
		}

		return list;
	}

	public static void main(String args[]) {
		try {
			System.out.println("1");
			CountRAG countRAG = new CountRAG();
			System.out.println("2");
			ArrayList<Customer> alst = countRAG.getCountRAG();
			System.out.println("alst : " + alst.size());
		} catch (Exception e) {

		}

	}

}
