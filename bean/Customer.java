package com.poc.bean;

import org.apache.hadoop.io.Text;

public class Customer {

	private String customername;
	private String customerid;
	private String gender;
	private String customer_age;
	private String marital_status;
	private String product1;
	private String product2;
	private String last_tran_amt;
	private String curr_tran_amt;
	private Integer transaction_amount_decreased;
	private String last_tran_no;
	private String curr_tran_no;
	private Integer transaction_number_decreased;
	private Integer credit_limit;
	private String credit_limit_utilised_cat;
	private String avg_mnth_bal_amt_catg;
	private String avg_mnth_purch_amt_catg;
	private Integer no_complaints_cc;
	private Integer no_complaints_ca;
	private String total_no_complaints;
	private String sentiment_score_ca;
	private String sentiment_score_cc;
	private String Total_Sent_Score;
	private String account_cond_catg;
	private String own_rent_res;
	private Integer cr_class;
	private Integer cb_cr_score;
	private Integer cb_score_catg;
	private Integer annual_income;
	private Integer annual_income_catg;
	private String occupation;
	private String occupation_catg;
	private String est_prob;
	private String product_propensity;
	private String churn_probability;
	private String churn_likelihood;
	private String decision;
	public String rag;

	private String income_change;

	private String account_cond;
	private String current_account_balance_catg;

	private String complaint_ca;

	private String sent_score_catg_ca;
	private String complaints_cc;

	private String sent_score_cat_cc;

	private String credit_limit_utilised;
	private String avg_mnth_bal_amt;
	private Integer avg_mnth_purch_amt;

	public Integer count;

	public String colour;

	public void parseCustomer(String values) {
		String[] cusDtl = values.split(",", -1);
		customername = cusDtl[0];
		customerid = cusDtl[1];
		product1 = cusDtl[5];
		product2 = cusDtl[6];
		churn_probability = cusDtl[34];
		decision = cusDtl[36];
		rag = cusDtl[37];
	}

	public void parseCustomer(Text values) {
		parseCustomer(values.toString());
	}

	public void parseCustomerRecord(String values) {
		String[] cusDtl = values.split(",", -1);
		customername = cusDtl[0];
		customerid = cusDtl[1];
		gender = cusDtl[2];
		customer_age = cusDtl[3];
		marital_status = cusDtl[4];
		product1 = cusDtl[5];
		product2 = cusDtl[6];
		last_tran_amt = cusDtl[7];
		curr_tran_amt = cusDtl[8];
		transaction_amount_decreased = Integer.parseInt(cusDtl[9]);
		last_tran_no = cusDtl[10];
		curr_tran_no = cusDtl[11];
		transaction_number_decreased = Integer.parseInt(cusDtl[12]);
		credit_limit = Integer.parseInt(cusDtl[13]);
		credit_limit_utilised_cat = cusDtl[14];//
		avg_mnth_bal_amt_catg = cusDtl[15];
		avg_mnth_purch_amt_catg = cusDtl[16];
		no_complaints_cc = Integer.parseInt(cusDtl[17]);
		no_complaints_ca = Integer.parseInt(cusDtl[18]);
		total_no_complaints = cusDtl[19];
		sentiment_score_ca = cusDtl[20];
		sentiment_score_cc = cusDtl[21];
		Total_Sent_Score = cusDtl[22];
		account_cond_catg = cusDtl[23];
		own_rent_res = cusDtl[24];
		cr_class = Integer.parseInt(cusDtl[25]);
		cb_cr_score = Integer.parseInt(cusDtl[26]);
		cb_score_catg = Integer.parseInt(cusDtl[27]);
		annual_income = Integer.parseInt(cusDtl[28]);
		annual_income_catg = Integer.parseInt(cusDtl[29]);
		occupation = cusDtl[30];
		occupation_catg = cusDtl[31];
		est_prob = cusDtl[32];
		product_propensity = cusDtl[33];
		churn_probability = cusDtl[34];
		churn_likelihood = cusDtl[35];
		decision = cusDtl[36];
		rag = cusDtl[37];
	}

	public void parseCustomerRecord(Text values) {
		parseCustomerRecord(values.toString());
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getRag() {
		return rag;
	}

	public void setRag(String rag) {
		this.rag = rag;
	}

	public String getAccount_cond_catg() {
		return account_cond_catg;
	}

	public void setAccount_cond_catg(String account_cond_catg) {
		this.account_cond_catg = account_cond_catg;
	}

	public String getOwn_rent_res() {
		return own_rent_res;
	}

	public void setOwn_rent_res(String own_rent_res) {
		this.own_rent_res = own_rent_res;
	}

	public Integer getCr_class() {
		return cr_class;
	}

	public void setCr_class(Integer cr_class) {
		this.cr_class = cr_class;
	}

	public Integer getCb_cr_score() {
		return cb_cr_score;
	}

	public void setCb_cr_score(Integer cb_cr_score) {
		this.cb_cr_score = cb_cr_score;
	}

	public Integer getCb_score_catg() {
		return cb_score_catg;
	}

	public void setCb_score_catg(Integer cb_score_catg) {
		this.cb_score_catg = cb_score_catg;
	}

	public Integer getAnnual_income() {
		return annual_income;
	}

	public void setAnnual_income(Integer annual_income) {
		this.annual_income = annual_income;
	}

	public Integer getAnnual_income_catg() {
		return annual_income_catg;
	}

	public void setAnnual_income_catg(Integer annual_income_catg) {
		this.annual_income_catg = annual_income_catg;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getOccupation_catg() {
		return occupation_catg;
	}

	public void setOccupation_catg(String occupation_catg) {
		this.occupation_catg = occupation_catg;
	}

	public String getEst_prob() {
		return est_prob;
	}

	public void setEst_prob(String est_prob) {
		this.est_prob = est_prob;
	}

	public String getProduct_propensity() {
		return product_propensity;
	}

	public void setProduct_propensity(String product_propensity) {
		this.product_propensity = product_propensity;
	}

	public String getChurn_probability() {
		return churn_probability;
	}

	public void setChurn_probability(String churn_probability) {
		this.churn_probability = churn_probability;
	}

	public String getChurn_likelihood() {
		return churn_likelihood;
	}

	public void setChurn_likelihood(String churn_likelihood) {
		this.churn_likelihood = churn_likelihood;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public Integer getNo_complaints_cc() {
		return no_complaints_cc;
	}

	public void setNo_complaints_cc(Integer no_complaints_cc) {
		this.no_complaints_cc = no_complaints_cc;
	}

	public Integer getNo_complaints_ca() {
		return no_complaints_ca;
	}

	public void setNo_complaints_ca(Integer no_complaints_ca) {
		this.no_complaints_ca = no_complaints_ca;
	}

	public String getCustomer_age() {
		return customer_age;
	}

	public void setCustomer_age(String customer_age) {
		this.customer_age = customer_age;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getProduct1() {
		return product1;
	}

	public void setProduct1(String product1) {
		this.product1 = product1;
	}

	public String getProduct2() {
		return product2;
	}

	public void setProduct2(String product2) {
		this.product2 = product2;
	}

	public String getLast_tran_amt() {
		return last_tran_amt;
	}

	public void setLast_tran_amt(String last_tran_amt) {
		this.last_tran_amt = last_tran_amt;
	}

	public String getCurr_tran_amt() {
		return curr_tran_amt;
	}

	public void setCurr_tran_amt(String curr_tran_amt) {
		this.curr_tran_amt = curr_tran_amt;
	}

	public String getLast_tran_no() {
		return last_tran_no;
	}

	public void setLast_tran_no(String last_tran_no) {
		this.last_tran_no = last_tran_no;
	}

	public String getCurr_tran_no() {
		return curr_tran_no;
	}

	public void setCurr_tran_no(String curr_tran_no) {
		this.curr_tran_no = curr_tran_no;
	}

	public String getCredit_limit_utilised() {
		return credit_limit_utilised;
	}

	public void setCredit_limit_utilised(String credit_limit_utilised) {
		this.credit_limit_utilised = credit_limit_utilised;
	}

	public String getAvg_mnth_bal_amt() {
		return avg_mnth_bal_amt;
	}

	public void setAvg_mnth_bal_amt(String avg_mnth_bal_amt) {
		this.avg_mnth_bal_amt = avg_mnth_bal_amt;
	}

	public String getTotal_no_complaints() {
		return total_no_complaints;
	}

	public void setTotal_no_complaints(String total_no_complaints) {
		this.total_no_complaints = total_no_complaints;
	}

	public String getTotal_Sent_Score() {
		return Total_Sent_Score;
	}

	public void setTotal_Sent_Score(String total_Sent_Score) {
		Total_Sent_Score = total_Sent_Score;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIncome_change() {
		return income_change;
	}

	public void setIncome_change(String income_change) {
		this.income_change = income_change;
	}

	public Integer getTransaction_amount_decreased() {
		return transaction_amount_decreased;
	}

	public void setTransaction_amount_decreased(
			Integer transaction_amount_decreased) {
		this.transaction_amount_decreased = transaction_amount_decreased;
	}

	public String getCredit_limit_utilised_cat() {
		return credit_limit_utilised_cat;
	}

	public void setCredit_limit_utilised_cat(String credit_limit_utilised_cat) {
		this.credit_limit_utilised_cat = credit_limit_utilised_cat;
	}

	public String getAvg_mnth_purch_amt_catg() {
		return avg_mnth_purch_amt_catg;
	}

	public void setAvg_mnth_purch_amt_catg(String avg_mnth_purch_amt_catg) {
		this.avg_mnth_purch_amt_catg = avg_mnth_purch_amt_catg;
	}

	public String getAvg_mnth_bal_amt_catg() {
		return avg_mnth_bal_amt_catg;
	}

	public void setAvg_mnth_bal_amt_catg(String avg_mnth_bal_amt_catg) {
		this.avg_mnth_bal_amt_catg = avg_mnth_bal_amt_catg;
	}

	public String getAccount_cond() {
		return account_cond;
	}

	public void setAccount_cond(String account_cond) {
		this.account_cond = account_cond;
	}

	public String getCurrent_account_balance_catg() {
		return current_account_balance_catg;
	}

	public void setCurrent_account_balance_catg(
			String current_account_balance_catg) {
		this.current_account_balance_catg = current_account_balance_catg;
	}

	public String getComplaint_ca() {
		return complaint_ca;
	}

	public void setComplaint_ca(String complaint_ca) {
		this.complaint_ca = complaint_ca;
	}

	public String getSentiment_score_ca() {
		return sentiment_score_ca;
	}

	public void setSentiment_score_ca(String sentiment_score_ca) {
		this.sentiment_score_ca = sentiment_score_ca;
	}

	public String getSent_score_catg_ca() {
		return sent_score_catg_ca;
	}

	public void setSent_score_catg_ca(String sent_score_catg_ca) {
		this.sent_score_catg_ca = sent_score_catg_ca;
	}

	public String getComplaints_cc() {
		return complaints_cc;
	}

	public void setComplaints_cc(String complaints_cc) {
		this.complaints_cc = complaints_cc;
	}

	public String getSentiment_score_cc() {
		return sentiment_score_cc;
	}

	public void setSentiment_score_cc(String sentiment_score_cc) {
		this.sentiment_score_cc = sentiment_score_cc;
	}

	public String getSent_score_cat_cc() {
		return sent_score_cat_cc;
	}

	public void setSent_score_cat_cc(String sent_score_cat_cc) {
		this.sent_score_cat_cc = sent_score_cat_cc;
	}

	public Integer getTransaction_number_decreased() {
		return transaction_number_decreased;
	}

	public void setTransaction_number_decreased(
			Integer transaction_number_decreased) {
		this.transaction_number_decreased = transaction_number_decreased;
	}

	public Integer getCredit_limit() {
		return credit_limit;
	}

	public void setCredit_limit(Integer credit_limit) {
		this.credit_limit = credit_limit;
	}

	public Integer getAvg_mnth_purch_amt() {
		return avg_mnth_purch_amt;
	}

	public void setAvg_mnth_purch_amt(Integer avg_mnth_purch_amt) {
		this.avg_mnth_purch_amt = avg_mnth_purch_amt;
	}

}
