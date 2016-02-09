package com.poc.mr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.poc.bean.Customer;
import com.poc.common.ValueConverter;
import com.poc.mr.CountRAG.RAGMapper;
import com.poc.mr.CountRAG.RAGReduce;

public class CustomerRAG {
	static class CustomerRAGMapper extends
			Mapper<LongWritable, Text, Text, Text> {
		String colour = null;

		@Override
		protected void setup(Context context) throws IOException,
				InterruptedException {
			Configuration conf = context.getConfiguration();
			colour = conf.get("colour");
		}

		@Override
		public void map(LongWritable key, Text values, Context context)
				throws IOException, InterruptedException {
			Customer customer = new Customer();
			customer.parseCustomer(values);
			if (customer.getRag().equalsIgnoreCase(colour)) {

				context.write(
						new Text(customer.getCustomerid()),
						new Text(customer.getCustomername() + ","
								+ customer.getCustomerid() + ","
								+ customer.getProduct1() + ","
								+ customer.getProduct2() + ","
								+ customer.getChurn_probability() + ","
								+ customer.getDecision() + ","
								+ customer.getRag()));
			}

		}
	}

	static class CustomerRAGReduce extends Reducer<Text, Text, Text, Text> {

		@Override
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			Iterator<Text> iterable = values.iterator();

			Text text = new Text(iterable.next());

			context.write(key, text);
		}
	}

	// public ArrayList<Customer> getCustomerRAG(String rag) throws IOException,
	// InterruptedException, ClassNotFoundException {
	// ArrayList<Customer> list = new ArrayList<Customer>();
	//
	// Util util=new Util();
	// Properties properties=new Properties();
	// properties=util.getPropertiesFromClasspath();
	// Configuration conf = new Configuration();
	// conf.set("colour", rag);
	// Job job = new Job(conf);
	//
	// job.setJarByClass(CustomerRAG.class);
	// job.setJobName("Count RAG");
	// String outputPath = properties.getProperty("CustomerRAG");
	// Util.deleteFile(new File(outputPath));
	//
	// FileInputFormat
	// .addInputPath(
	// job,
	// new Path(
	// properties.getProperty("CUSTOMER_MASTER_NBA")));
	// FileOutputFormat.setOutputPath(job, new Path(outputPath));
	//
	// job.setMapperClass(CustomerRAGMapper.class);
	// job.setReducerClass(CustomerRAGReduce.class);
	// job.setMapOutputKeyClass(Text.class);
	// job.setMapOutputValueClass(Text.class);
	//
	// if (job.waitForCompletion(true)) {
	//
	// File file = new File(outputPath+"/part-r-00000");
	// BufferedReader bufferedReader = new BufferedReader(new FileReader(
	// file));
	// String line = null;
	// String array[] = null;
	// String array1[]=null;
	// while ((line = bufferedReader.readLine()) != null) {
	// array = line.split("\t");
	// array1=array[1].split(",");
	// Customer customer = new Customer();
	// customer.setCustomername(array1[0]);
	// customer.setCustomerid(array1[1]);
	// customer.setProduct1(array1[2]);
	// customer.setProduct2(array1[3]);
	// Double value = Double.parseDouble(array1[4]);
	// Double Churn_probability = ValueConverter.roundDouble(value);
	// customer.setChurn_probability(Churn_probability.toString());
	// customer.setDecision(array1[5]);
	// customer.setRag(array1[6]);
	// list.add(customer);
	// }
	//
	// }
	//
	// return list;
	// }

	public ArrayList<Customer> getCustomerRAG(String rag) throws IOException,
			InterruptedException, ClassNotFoundException {
		ArrayList<Customer> list = new ArrayList<Customer>();

		Util util = new Util();
		Properties properties = new Properties();
		properties = util.getPropertiesFromClasspath();

		File file = new File(properties.getProperty("CUSTOMER_MASTER_NBA"));
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = null;
		String array[] = null;
		String array1[] = null;
		while ((line = bufferedReader.readLine()) != null) {
			array = line.split("\t");
			array1 = array[1].split(",");
			Customer customer = new Customer();
			customer.setCustomername(array1[0]);
			customer.setCustomerid(array1[1]);
			customer.setProduct1(array1[2]);
			customer.setProduct2(array1[3]);
			Double value = Double.parseDouble(array1[4]);
			Double Churn_probability = ValueConverter.roundDouble(value);
			customer.setChurn_probability(Churn_probability.toString());
			customer.setDecision(array1[5]);
			customer.setRag(array1[6]);
			list.add(customer);
		}

		return list;
	}

	public static void main(String args[]) {
		try {
			System.out.println("1");
			CustomerRAG customerRAG = new CustomerRAG();
			System.out.println("2");
			ArrayList<Customer> alst = customerRAG.getCustomerRAG("R");
			System.out.println("alst : " + alst.size());
		} catch (Exception e) {

		}

	}
}
