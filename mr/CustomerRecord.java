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
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.jruby.RubyProcess.Sys;

import com.itextpdf.text.log.SysoLogger;
import com.poc.bean.Customer;
import com.poc.common.ValueConverter;
import com.poc.mr.CustomerRAG.CustomerRAGMapper;
import com.poc.mr.CustomerRAG.CustomerRAGReduce;

public class CustomerRecord {

	static class CustomerRecordMapper extends
			Mapper<LongWritable, Text, Text, NullWritable> {
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
			Customer customer = new Customer();
			customer.parseCustomerRecord(values);
			if (customer.getCustomerid().equalsIgnoreCase(cid)) {

				context.write(

						new Text(customer.getCustomername() + ","
								+ customer.getCustomerid() + ","
								+ customer.getGender() + ","
								+ customer.getCustomer_age() + ","
								+ customer.getMarital_status() + ","
								+ customer.getProduct1() + ","
								+ customer.getProduct2() + ","
								+ customer.getLast_tran_amt() + ","
								+ customer.getCurr_tran_amt() + ","
								+ customer.getTransaction_amount_decreased()
								+ "," + customer.getLast_tran_no() + ","
								+ customer.getCurr_tran_no() + ","
								+ customer.getTransaction_number_decreased()
								+ "," + customer.getCredit_limit() + ","
								+ customer.getCredit_limit_utilised_cat() + ","
								+ customer.getAvg_mnth_bal_amt_catg() + ","
								+ customer.getAvg_mnth_purch_amt_catg() + ","
								+ customer.getNo_complaints_cc() + ","
								+ customer.getNo_complaints_ca() + ","
								+ customer.getTotal_no_complaints() + ","
								+ customer.getSentiment_score_ca() + ","
								+ customer.getSentiment_score_cc() + ","
								+ customer.getTotal_Sent_Score() + ","
								+ customer.getAccount_cond_catg() + ","
								+ customer.getOwn_rent_res() + ","
								+ customer.getCr_class() + ","
								+ customer.getCb_cr_score() + ","
								+ customer.getCb_score_catg() + ","
								+ customer.getAnnual_income() + ","
								+ customer.getAnnual_income_catg() + ","
								+ customer.getOccupation() + ","
								+ customer.getOccupation_catg() + ","
								+ customer.getEst_prob() + ","
								+ customer.getProduct_propensity() + ","
								+ customer.getChurn_probability() + ","
								+ customer.getChurn_likelihood() + ","
								+ customer.getDecision() + ","
								+ customer.getRag()), NullWritable.get());
			}

		}
	}

	static class CustomerRecordReduce extends
			Reducer<Text, NullWritable, Text, NullWritable> {

		@Override
		public void reduce(Text key, Iterable<NullWritable> values,
				Context context) throws IOException, InterruptedException {

			context.write(key, NullWritable.get());
		}
	}

	// public Customer getCustomerRecord(Integer integer) throws IOException,
	// InterruptedException, ClassNotFoundException {
	// Util util=new Util();
	// Properties properties=new Properties();
	// properties=util.getPropertiesFromClasspath();
	// Customer customer = new Customer();
	// Configuration conf = new Configuration();
	// conf.set("cid", integer.toString());
	// Job job = new Job(conf);
	//
	// job.setJarByClass(CustomerRecord.class);
	// job.setJobName("Count RAG");
	// String outputPath =properties.getProperty("CustomerRecord") ;
	// Util.deleteFile(new File(outputPath));
	//
	// FileInputFormat
	// .addInputPath(
	// job,
	// new Path(
	// properties.getProperty("CUSTOMER_MASTER_NBA")));
	// FileOutputFormat.setOutputPath(job, new Path(outputPath));
	//
	// job.setMapperClass(CustomerRecordMapper.class);
	// job.setReducerClass(CustomerRecordReduce.class);
	// job.setMapOutputKeyClass(Text.class);
	// job.setMapOutputValueClass(NullWritable.class);
	//
	// if (job.waitForCompletion(true)) {
	//
	// File file = new File(outputPath+"/part-r-00000");
	// BufferedReader bufferedReader = new BufferedReader(new FileReader(
	// file));
	// String line = null;
	// String array[] = null;
	//
	// while ((line = bufferedReader.readLine()) != null) {
	// System.out.println(line);
	// array = line.split(",");
	//
	//
	// customer.setCustomername(array[0]);
	// customer.setCustomerid(array[1]);
	// customer.setGender(array[2]);
	// customer.setCustomer_age(array[3]);
	// customer.setMarital_status(array[4]);
	// customer.setProduct1(array[5]);
	// customer.setProduct2(array[6]);
	// customer.setLast_tran_amt(array[7]);
	// customer.setCurr_tran_amt(array[8]);
	// System.out.println("Transaction_amount_decreased : "+array[9]);
	// customer.setTransaction_amount_decreased(Integer.parseInt(array[9]));
	// customer.setLast_tran_no(array[10]);
	// customer.setCurr_tran_no(array[11]);
	//
	// customer.setTransaction_number_decreased(Integer.parseInt(array[12]));
	// customer.setCredit_limit(Integer.parseInt(array[13]));
	// customer.setCredit_limit_utilised(array[14]);
	// customer.setAvg_mnth_bal_amt(array[15]);
	// System.out.println("Transaction_amount_decreased : "+array[15]);
	// customer.setAvg_mnth_purch_amt(Integer.parseInt(array[16]));
	// customer.setNo_complaints_cc(Integer.parseInt(array[17]));
	// customer.setNo_complaints_ca(Integer.parseInt(array[18]));
	// customer.setTotal_no_complaints(array[19]);
	// customer.setSentiment_score_ca(array[20]);
	// customer.setSentiment_score_cc(array[21]);
	// customer.setTotal_Sent_Score(array[22]);
	// customer.setAccount_cond_catg(array[23]);
	// customer.setOwn_rent_res(array[24]);
	// customer.setCr_class(Integer.parseInt(array[25]));
	// customer.setCb_cr_score(Integer.parseInt(array[26]));
	// customer.setCb_score_catg(Integer.parseInt(array[27]));
	// customer.setAnnual_income(Integer.parseInt(array[28]));
	// customer.setAnnual_income_catg(Integer.parseInt(array[29]));
	// customer.setOccupation(array[30]);
	// customer.setOccupation_catg(array[31]);
	// System.out.println("array[32] "+array[32]);
	//
	// Double value = Double.parseDouble(array[32]);
	//
	// Double Est_prob = ValueConverter.roundDouble(value);
	// System.out.println("Est_prob "+Est_prob);
	// customer.setEst_prob(Est_prob.toString());
	// customer.setProduct_propensity(array[33]);
	// System.out.println("array[34] "+array[34]);
	// Double value1 = Double.parseDouble(array[34]);
	//
	// Double Churn_probability = ValueConverter.roundDouble(value1);
	// System.out.println("Churn_probability "+Churn_probability);
	// customer.setChurn_probability(Churn_probability.toString());
	// customer.setChurn_likelihood(array[35]);
	// customer.setDecision(array[36]);
	// customer.setRag(array[37]);
	//
	// }
	//
	// }
	//
	// return customer;
	// }

	public Customer getCustomerRecord(Integer integer) throws IOException,
			InterruptedException, ClassNotFoundException {
		Util util = new Util();
		Properties properties = new Properties();
		properties = util.getPropertiesFromClasspath();
		Customer customer = new Customer();

		File file = new File(properties.getProperty("CUSTOMER_MASTER_NBA"));
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = null;
		String array[] = null;

		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
			array = line.split(",");

			customer.setCustomername(array[0]);
			customer.setCustomerid(array[1]);
			customer.setGender(array[2]);
			customer.setCustomer_age(array[3]);
			customer.setMarital_status(array[4]);
			customer.setProduct1(array[5]);
			customer.setProduct2(array[6]);
			customer.setLast_tran_amt(array[7]);
			customer.setCurr_tran_amt(array[8]);
			System.out.println("Transaction_amount_decreased : " + array[9]);
			customer.setTransaction_amount_decreased(Integer.parseInt(array[9]));
			customer.setLast_tran_no(array[10]);
			customer.setCurr_tran_no(array[11]);

			customer.setTransaction_number_decreased(Integer
					.parseInt(array[12]));
			customer.setCredit_limit(Integer.parseInt(array[13]));
			customer.setCredit_limit_utilised(array[14]);
			customer.setAvg_mnth_bal_amt(array[15]);
			System.out.println("Transaction_amount_decreased : " + array[15]);
			customer.setAvg_mnth_purch_amt(Integer.parseInt(array[16]));
			customer.setNo_complaints_cc(Integer.parseInt(array[17]));
			customer.setNo_complaints_ca(Integer.parseInt(array[18]));
			customer.setTotal_no_complaints(array[19]);
			customer.setSentiment_score_ca(array[20]);
			customer.setSentiment_score_cc(array[21]);
			customer.setTotal_Sent_Score(array[22]);
			customer.setAccount_cond_catg(array[23]);
			customer.setOwn_rent_res(array[24]);
			customer.setCr_class(Integer.parseInt(array[25]));
			customer.setCb_cr_score(Integer.parseInt(array[26]));
			customer.setCb_score_catg(Integer.parseInt(array[27]));
			customer.setAnnual_income(Integer.parseInt(array[28]));
			customer.setAnnual_income_catg(Integer.parseInt(array[29]));
			customer.setOccupation(array[30]);
			customer.setOccupation_catg(array[31]);
			System.out.println("array[32] " + array[32]);

			Double value = Double.parseDouble(array[32]);

			Double Est_prob = ValueConverter.roundDouble(value);
			System.out.println("Est_prob " + Est_prob);
			customer.setEst_prob(Est_prob.toString());
			customer.setProduct_propensity(array[33]);
			System.out.println("array[34] " + array[34]);
			Double value1 = Double.parseDouble(array[34]);

			Double Churn_probability = ValueConverter.roundDouble(value1);
			System.out.println("Churn_probability " + Churn_probability);
			customer.setChurn_probability(Churn_probability.toString());
			customer.setChurn_likelihood(array[35]);
			customer.setDecision(array[36]);
			customer.setRag(array[37]);

		}

		return customer;
	}

	public static void main(String args[]) {
		try {
			System.out.println("1");
			CustomerRecord customerRAG = new CustomerRecord();
			System.out.println("2");
			Customer alst = customerRAG.getCustomerRecord(new Integer(5045));
			System.out.println("alst : " + alst);
		} catch (Exception e) {

		}

	}
}
