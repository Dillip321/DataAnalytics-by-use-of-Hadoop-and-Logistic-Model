package com.poc.mr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.poc.bean.Complaint;

public class CustomerComplaint {

	static class CustomerComplaintMapper extends
			Mapper<LongWritable, Text, Text, NullWritable> {
		String custid = null;

		@Override
		protected void setup(Context context) throws IOException,
				InterruptedException {
			Configuration conf = context.getConfiguration();
			custid = conf.get("custid");
		}

		@Override
		public void map(LongWritable key, Text values, Context context)
				throws IOException, InterruptedException {
			Complaint complaint = new Complaint();
			complaint.parseComplaint(values);
			if (complaint.getCustomer_id().equalsIgnoreCase(custid)) {

				context.write(
						new Text(complaint.getCreated() + ","
								+ complaint.getCustomer_id() + ","
								+ complaint.getProduct_name() + ","
								+ complaint.getComplaint() + ","
								+ complaint.getScore() + ","
								+ complaint.getNo_of_complaints()),
						NullWritable.get());
			}

		}
	}

	static class CustomerComplaintReduce extends
			Reducer<Text, NullWritable, Text, NullWritable> {

		@Override
		public void reduce(Text key, Iterable<NullWritable> values,
				Context context) throws IOException, InterruptedException {
			// Iterator<Text> iterable = values.iterator();

			// Text text = new Text(iterable.next());

			context.write(key, NullWritable.get());
		}
	}

//	public List<Complaint> getCustomerComplaint(Integer integer)
//			throws IOException, InterruptedException, ClassNotFoundException {
//		Util util = new Util();
//		Properties properties = new Properties();
//		properties = util.getPropertiesFromClasspath();
//		List<Complaint> cList = new ArrayList<Complaint>();
//
//		Configuration conf = new Configuration();
//		conf.set("custid", integer.toString());
//		Job job = new Job(conf);
//
//		job.setJarByClass(CustomerComplaint.class);
//		job.setJobName("Count RAG");
//		String outputPath = properties.getProperty("CustomerComplaint");
//		Util.deleteFile(new File(outputPath));
//
//		FileInputFormat.addInputPath(job,
//				new Path(properties.getProperty("COMPLAINT_MASTER")));
//		FileOutputFormat.setOutputPath(job, new Path(outputPath));
//
//		job.setMapperClass(CustomerComplaintMapper.class);
//		job.setReducerClass(CustomerComplaintReduce.class);
//		job.setMapOutputKeyClass(Text.class);
//		job.setMapOutputValueClass(NullWritable.class);
//
//		if (job.waitForCompletion(true)) {
//
//			File file = new File(outputPath + "/part-r-00000");
//			BufferedReader bufferedReader = new BufferedReader(new FileReader(
//					file));
//			String line = null;
//			String array[] = null;
//			String array1[] = null;
//
//			while ((line = bufferedReader.readLine()) != null) {
//				// System.out.println(line);
//				array1 = line.split(",");
//				Complaint complaint = new Complaint();
//				complaint.setCreated(array1[0]);
//				complaint.setCustomer_id(array1[1]);
//				complaint.setProduct_name(array1[2]);
//				complaint.setComplaint(array1[3]);
//				complaint.setScore(array1[4]);
//				complaint.setNo_of_complaints(array1[5]);
//				String productname = array1[2];
//				String product_name1 = productname.replaceAll("\\s", "");
//				complaint.setComplaints_flag(product_name1);
//
//				cList.add(complaint);
//			}
//
//		}
//
//		return cList;
//	}

	public List<Complaint> getCustomerComplaint(Integer integer)
			throws IOException, InterruptedException, ClassNotFoundException {
		Util util = new Util();
		Properties properties = new Properties();
		properties = util.getPropertiesFromClasspath();
		List<Complaint> cList = new ArrayList<Complaint>();

		File file = new File(properties.getProperty("COMPLAINT_MASTER"));
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = null;
		String array[] = null;
		String array1[] = null;

		while ((line = bufferedReader.readLine()) != null) {
			// System.out.println(line);
			array1 = line.split(",");
			Complaint complaint = new Complaint();
			complaint.setCreated(array1[0]);
			complaint.setCustomer_id(array1[1]);
			complaint.setProduct_name(array1[2]);
			complaint.setComplaint(array1[3]);
			complaint.setScore(array1[4]);
			complaint.setNo_of_complaints(array1[5]);
			String productname = array1[2];
			String product_name1 = productname.replaceAll("\\s", "");
			complaint.setComplaints_flag(product_name1);

			cList.add(complaint);
		}

		return cList;
	}

	public static void main(String args[]) {
		try {
			// System.out.println("1");
			CustomerComplaint customerComplaint = new CustomerComplaint();
			// System.out.println("2");
			List<Complaint> alst = customerComplaint.getCustomerComplaint(5008);
			// System.out.println("alst : " + alst.size());

			for (int i = 0; i < alst.size(); i++) {
				String created = alst.get(i).getCreated();
				String customer_id = alst.get(i).getCustomer_id();
				String product_name = alst.get(i).getProduct_name();
				String complaintname = alst.get(i).getComplaint();
				String score = alst.get(i).getScore();
				String no_of_complaints = alst.get(i).getNo_of_complaints();
				System.out.println("product_name : " + product_name);
				String product_name1 = product_name.replaceAll("\\s", "");
				System.out.println("product_name1 : " + product_name1);
				System.out.println("created" + created);
				System.out.println("customer_id" + customer_id);
				System.out.println("product_name" + product_name);
				System.out.println("complaintname" + complaintname);
				System.out.println("score" + score);
				System.out.println("no_of_complaints" + no_of_complaints);

			}
		} catch (Exception e) {

		}

	}
}
