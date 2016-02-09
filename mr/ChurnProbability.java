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
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


import com.poc.bean.Prediction;


public class ChurnProbability {
	static class ChurnProbabilityMapper extends
			Mapper<LongWritable, Text, Text, Text> {
		String cid = null;

		@Override
		protected void setup(Context context) throws IOException,
				InterruptedException {
			Configuration conf = context.getConfiguration();
			cid = conf.get("cid");
		}

		@Override
		public void map(LongWritable key, Text values, Context context)
				throws IOException, InterruptedException {
			Prediction prediction = new Prediction();
			prediction.parsePrediction(values);
			if (prediction.getCustomer_id().equalsIgnoreCase(cid)) {

				context.write(
						new Text(prediction.getCustomer_id()),
						new Text(prediction.getCustomer_name() + ","
								+ prediction.getCustomer_id() + ","
								+ prediction.getMonth()+ ","
								+ prediction.getChurn_prediction_percentage()));
			}

		}
	}

	static class ChurnProbabilityReduce extends Reducer<Text, Text, Text, Text> {

		@Override
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			Iterator<Text> iterable = values.iterator();

			Text text = new Text(iterable.next());

			context.write(key, text);
		}
	}

	// public ArrayList<Prediction> getChurnProbability(Integer integer) throws
	// IOException,
	// InterruptedException, ClassNotFoundException {
	// ArrayList<Prediction> list = new ArrayList<Prediction>();
	// Util util=new Util();
	// Properties properties=new Properties();
	// properties=util.getPropertiesFromClasspath();
	// Configuration conf = new Configuration();
	// conf.set("cid", integer.toString());
	// Job job = new Job(conf);
	//
	// job.setJarByClass(ChurnProbability.class);
	// job.setJobName("Count RAG");
	// String outputPath = properties.getProperty("ChurnProbability");
	// Util.deleteFile(new File(outputPath));
	//
	// FileInputFormat.addInputPath(job,new
	// Path(properties.getProperty("PREDICTION")));
	// FileOutputFormat.setOutputPath(job, new Path(outputPath));
	//
	// job.setMapperClass(ChurnProbabilityMapper.class);
	// //job.setReducerClass(ChurnProbabilityReduce.class);
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
	// String array1[] = null;
	// while ((line = bufferedReader.readLine()) != null) {
	// System.out.println(line);
	// array = line.split("\t");
	// array1 = array[1].split(",");
	// Prediction prediction = new Prediction();
	// prediction.setCustomer_name(array1[0]);
	// prediction.setCustomer_id(array1[1]);
	// try {
	// String month = array1[2];
	// String m[] = month.split("-");
	// prediction.setMonth(m[1]);
	// } catch (Exception e) {
	// // TODO: handle exception
	// prediction.setMonth(array1[2]);
	// }
	//
	// prediction.setChurn_prediction_percentage(array1[3]);
	//
	// list.add(prediction);
	// }
	//
	// }
	//
	// return list;
	// }

	public ArrayList<Prediction> getChurnProbability(Integer integer) throws IOException,
	InterruptedException, ClassNotFoundException {
		ArrayList<Prediction> list = new ArrayList<Prediction>();
		Util util=new Util();
		Properties properties=new Properties();
		properties=util.getPropertiesFromClasspath();
		File file = new File(properties.getProperty("PREDICTION"));
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				file));
		String line = null;
		String array[] = null;
		String array1[] = null;
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
			array = line.split("\t");
			array1 = array[1].split(",");
			Prediction prediction = new Prediction();
			prediction.setCustomer_name(array1[0]);
			prediction.setCustomer_id(array1[1]);
			try {
				String month = array1[2];
				String m[] = month.split("-");
				prediction.setMonth(m[1]);
			} catch (Exception e) {
				// TODO: handle exception
				prediction.setMonth(array1[2]);
			}			
			
			prediction.setChurn_prediction_percentage(array1[3]);
			
			list.add(prediction);
		}
		return list;
	}

	
		
	
	public static void main(String args[]) {
		try {
			System.out.println("1");
			ChurnProbability churnProbability = new ChurnProbability();
			System.out.println("2");
			ArrayList<Prediction> alst = churnProbability.getChurnProbability(5025);
			System.out.println("alst : " + alst.size());
		} catch (Exception e) {

		}

	}
}
