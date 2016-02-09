package com.poc.db;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import com.poc.bean.Complaint;
import com.poc.bean.Customer;
import com.poc.bean.Prediction;
import com.poc.common.ValueConverter;
import com.poc.mr.ChurnProbability;
import com.poc.mr.CustomerComplaint;
import com.poc.mr.CustomerRAG;
import com.poc.mr.CustomerRecord;
import com.poc.mr.GetCustomer;
import com.poc.mr.Util;

public class DBOperation {
	static Statement stmt = null;
	static Connection conn = null;

	public DBOperation() {

//		try {
//			conn = DBConnection.connect();
//			stmt = conn.createStatement();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public ArrayList<Customer> getCountRAG() throws SQLException {
		ArrayList<Customer> arrayList = new ArrayList<Customer>();
		String sql = " select rag , count(rag)  from CUSTOMER_MASTER_NBA group by rag ";
		System.out.println("Running: " + sql);
		ResultSet re = stmt.executeQuery(sql);
		while (re.next()) {
			Customer customer = new Customer();
			customer.setRag(re.getString(1));
			customer.setCount(Integer.parseInt(re.getString(2)));
			if (re.getString(1).equalsIgnoreCase("A")) {
				customer.setColour("FFA07A");
			} else if (re.getString(1).equalsIgnoreCase("G")) {
				customer.setColour("008000");
			} else if (re.getString(1).equalsIgnoreCase("R")) {
				customer.setColour("DC143C");
			}
			arrayList.add(customer);
		}
		return arrayList;

	}

	public Customer getCustomerRecord(Integer integer) throws SQLException {
		System.out.println("sdddfsdfsd");
		Integer customer_id = integer;
		Customer customer = new Customer();
		try {
			CustomerRecord cr = new CustomerRecord();
			customer = cr.getCustomerRecord(customer_id);

		} catch (Exception e) {

		}

		// String str =
		// " SELECT customer_name ,customer_id ,gender ,income_change ,transaction_number_decreased "
		// +
		// " ,transaction_amount_decreased ,credit_limit_utilised_cat ,no_complaints_cc ,"
		// + " avg_mnth_purch_amt_catg ,avg_mnth_bal_amt_catg ,account_cond , "
		// +
		// "  current_account_balance_catg ,no_complaints_ca ,complaint_ca ,sentiment_score_ca "
		// +
		// "  ,sent_score_catg_ca ,complaints_cc , sentiment_score_cc ,sent_score_cat_cc "
		// + " FROM FINAL_TABLE_NBA where customer_name = '"
		// + customername + "' ";
		/*
		 * String str =
		 * " SELECT  customer_name ,customer_id ,gender ,customer_age , marital_status , "
		 * +
		 * "   product1 , product2 , last_tran_amt , curr_tran_amt , transaction_amount_decreased , last_tran_no ,  "
		 * +
		 * "   curr_tran_no , transaction_number_decreased , credit_limit , credit_limit_utilised ,avg_mnth_bal_amt , "
		 * +
		 * "  avg_mnth_purch_amt , no_complaints_cc , no_complaints_ca , total_no_complaints , sentiment_score_ca , "
		 * +
		 * "    sentiment_score_cc , Total_Sent_Score, account_cond_catg ,own_rent_res ,cr_class ,"
		 * + " cb_cr_score ,cb_score_catg ,annual_income ,annual_income_catg , "
		 * + " occupation ,occupation_catg ,est_prob ,product_propensity , " +
		 * " churn_probability ,churn_likelihood ,decision  FROM CUSTOMER_MASTER_NBA where customer_id "
		 * + "  = '" + customer_id + "' "; // String str = //
		 * " SELECT  *  FROM CUSTOMER_MASTER_NBA where customer_id " // +
		 * "  = '" + customer_id + "' ";
		 * 
		 * ResultSet rs; try { rs = stmt.executeQuery(str); //
		 * System.out.println("value of rs "+rs);
		 * 
		 * while (rs.next()) { customer.setCustomername(rs.getString(1));
		 * customer.setCustomerid(rs.getString(2));
		 * customer.setGender(rs.getString(3));
		 * customer.setCustomer_age(rs.getString(4));
		 * customer.setMarital_status(rs.getString(5));
		 * customer.setProduct1(rs.getString(6));
		 * customer.setProduct2(rs.getString(7));
		 * customer.setLast_tran_amt(rs.getString(8));
		 * customer.setCurr_tran_amt(rs.getString(9));
		 * customer.setTransaction_amount_decreased(Integer.parseInt(rs
		 * .getString(10))); customer.setLast_tran_no(rs.getString(11));
		 * customer.setCurr_tran_no(rs.getString(12));
		 * customer.setTransaction_number_decreased(Integer.parseInt(rs
		 * .getString(13)));
		 * customer.setCredit_limit(Integer.parseInt(rs.getString(14)));
		 * customer.setCredit_limit_utilised(rs.getString(15));
		 * customer.setAvg_mnth_bal_amt(rs.getString(16));
		 * customer.setAvg_mnth_purch_amt(Integer.parseInt(rs .getString(17)));
		 * customer.setNo_complaints_cc(Integer.parseInt(rs.getString(18)));
		 * customer.setNo_complaints_ca(Integer.parseInt(rs.getString(19)));
		 * customer.setTotal_no_complaints(rs.getString(20));
		 * customer.setSentiment_score_ca(rs.getString(21));
		 * customer.setSentiment_score_cc(rs.getString(22));
		 * customer.setTotal_Sent_Score(rs.getString(23));
		 * 
		 * customer.setAccount_cond_catg(rs.getString(24));
		 * customer.setOwn_rent_res(rs.getString(25));
		 * customer.setCr_class(Integer.parseInt(rs.getString(26)));
		 * customer.setCb_cr_score(Integer.parseInt(rs.getString(27)));
		 * customer.setCb_score_catg(Integer.parseInt(rs.getString(28)));
		 * customer.setAnnual_income(Integer.parseInt(rs.getString(29)));
		 * customer.setAnnual_income_catg(Integer.parseInt(rs .getString(30)));
		 * customer.setOccupation(rs.getString(31));
		 * customer.setOccupation_catg(rs.getString(32));
		 * customer.setEst_prob(rs.getString(33));
		 * customer.setProduct_propensity(rs.getString(34));
		 * customer.setChurn_probability(rs.getString(35));
		 * customer.setChurn_likelihood(rs.getString(36));
		 * customer.setDecision(rs.getString(37));
		 * 
		 * } } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } finally { // /conn.close(); }
		 */

		return customer;

	}

	public ArrayList getCustomerAllRecord() {
		ArrayList arrayList = new ArrayList();
		// String sql
		// ="select customer_name, customer_id, initial_product, sentiment, " +
		// " credit_card_responce, mortgage_responce, churn_probability, cltv_value, decision "
		// +
		// " from NBA_DATA where Customer_ID = '"+ customername + "'";
		// System.out.println("Running: " + sql);
		// ResultSet res = stmt.executeQuery(sql);
		// while (res.next()) {
		// nb.setCustomer_Name(res.getString(1));
		// nb.setCustomer_ID(res.getString(2));
		// nb.setInitial_Product(res.getString(3));
		// nb.setSentiment_Category(res.getString(4));
		// nb.setResponse_to_Credit_Card(res.getString(5));
		// nb.setResponse_to_Mortgage(res.getString(6));
		// nb.setChurn_Probability(res.getString(7));
		// nb.setCLTV_Value(res.getString(8));
		// nb.setDecision(res.getString(9));
		// System.out.println(res.getString(1));
		// }

		return arrayList;

	}

	public ArrayList<Customer> getCustomer(String rag) throws SQLException {
		ArrayList<Customer> arrayList = new ArrayList<Customer>();
		/*
		 * String str =
		 * " SELECT customer_name ,customer_id ,product1 , product2 , " +
		 * "   churn_probability ,decision , rag FROM CUSTOMER_MASTER_NBA where rag= '"
		 * + rag + "'"; // String str = " SELECT * FROM CUSTOMER_MASTER_NBA  ";
		 * ResultSet rs; try { rs = stmt.executeQuery(str); while (rs.next()) {
		 * Customer customer = new Customer();
		 * customer.setCustomername(rs.getString(1));
		 * customer.setCustomerid(rs.getString(2));
		 * customer.setProduct1(rs.getString(3));
		 * customer.setProduct2(rs.getString(4)); Double value =
		 * Double.parseDouble(rs.getString(5)); Double Churn_probability =
		 * ValueConverter.roundDouble(value);
		 * customer.setChurn_probability(Churn_probability.toString());
		 * customer.setDecision(rs.getString(6));
		 * customer.setRag(rs.getString(7)); arrayList.add(customer); } } catch
		 * (SQLException e) { e.printStackTrace(); } finally { // conn.close();
		 * }
		 */
		try {
			CustomerRAG cg = new CustomerRAG();
			arrayList = cg.getCustomerRAG(rag);
		} catch (Exception e) {

		}
		return arrayList;
	}

	public ArrayList<Customer> getCustomer() throws SQLException {
		ArrayList<Customer> arrayList = new ArrayList<Customer>();
		/*
		 * String str =
		 * " SELECT customer_name ,customer_id ,product1 , product2 , " +
		 * "   churn_probability ,decision FROM CUSTOMER_MASTER_NBA "; ResultSet
		 * rs; try { rs = stmt.executeQuery(str); while (rs.next()) { Customer
		 * customer = new Customer(); customer.setCustomername(rs.getString(1));
		 * customer.setCustomerid(rs.getString(2));
		 * customer.setProduct1(rs.getString(3));
		 * customer.setProduct2(rs.getString(4));
		 * customer.setChurn_probability(rs.getString(5));
		 * customer.setDecision(rs.getString(6)); arrayList.add(customer); } }
		 * catch (SQLException e) { e.printStackTrace(); } finally { //
		 * conn.close(); }
		 */
		try {
			GetCustomer cg = new GetCustomer();
			arrayList = cg.getCustomerRAG();
		} catch (Exception e) {

		}
		return arrayList;

	}

	public List<Complaint> getCustomerComplaint(Integer integer)
			throws SQLException {
		/*
		 * System.out.println("sdddfsdfsd");
		 * 
		 * String str = " SELECT  created ,customer_id ," +
		 * " product_name ,complaint ,score ,no_of_complaints " +
		 * " FROM COMPLAINT_MASTER where customer_id " + "  = '" + customer_id +
		 * "' ";
		 * 
		 * ResultSet rs; try { rs = stmt.executeQuery(str); while (rs.next()) {
		 * complaint.setCreated(rs.getString(1));
		 * complaint.setCustomer_id(rs.getString(2));
		 * complaint.setProduct_name(rs.getString(3));
		 * complaint.setComplaint(rs.getString(4));
		 * complaint.setScore(rs.getString(5));
		 * complaint.setNo_of_complaints(rs.getString(6));
		 * 
		 * } } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } finally { // /conn.close(); }
		 */
		Integer customer_id = integer;
		List<Complaint> complaint = new ArrayList<Complaint>();
		try {
			CustomerComplaint cc = new CustomerComplaint();
			complaint = cc.getCustomerComplaint(customer_id);
		} catch (Exception e) {

		}
		return complaint;
	}

	public ArrayList<Prediction> getChurnProbability(Integer integer)
			throws SQLException {
		Integer customer_id = integer;
		ArrayList<Prediction> arrayList = new ArrayList<Prediction>();
		System.out.println("customer_id " + customer_id);
		/*
		 * String str = " SELECT customer_name ,customer_id ," +
		 * " month ,churn_prediction_percentage from PREDICTION where  customer_id = "
		 * + customer_id + " "; ResultSet rs; try { rs = stmt.executeQuery(str);
		 * System.out.println("rs " + rs); while (rs.next()) {
		 * System.out.println("rs.getString(1) " + rs.getString(1));
		 * System.out.println("rs.getString(2) " + rs.getString(2));
		 * System.out.println("rs.getString(3) " + rs.getString(3));
		 * System.out.println("rs.getString(4) " + rs.getString(4)); Prediction
		 * prediction = new Prediction();
		 * prediction.setCustomer_name(rs.getString(1));
		 * prediction.setCustomer_id(rs.getString(2)); try { String month =
		 * rs.getString(3); String m[] = month.split("-");
		 * prediction.setMonth(m[1]); } catch (Exception e) { // TODO: handle
		 * exception prediction.setMonth(rs.getString(3)); }
		 * prediction.setChurn_prediction_percentage(rs.getString(4));
		 * System.out.println("rs.getString(1) 1" +
		 * prediction.getCustomer_name());
		 * System.out.println("rs.getString(2)2 " +
		 * prediction.getCustomer_id()); System.out.println("rs.getString(3) 3"
		 * + prediction.getMonth()); System.out.println("rs.getString(4) 4" +
		 * prediction.getChurn_prediction_percentage());
		 * arrayList.add(prediction); } } catch (SQLException e) {
		 * e.printStackTrace(); } finally {
		 * System.out.println("size of array list :" + arrayList.size()); //
		 * conn.close(); }
		 */
		try {
			ChurnProbability cp = new ChurnProbability();
			arrayList = cp.getChurnProbability(customer_id);
		} catch (Exception e) {

		}

		return arrayList;
	}


 
}
