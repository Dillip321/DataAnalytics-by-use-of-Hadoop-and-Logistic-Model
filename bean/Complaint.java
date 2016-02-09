package com.poc.bean;

import org.apache.hadoop.io.Text;

public class Complaint {
	private String created;
	private String customer_id;
	private String product_name;
	private String complaint;
	private String score;
	private String no_of_complaints;
	private String complaints_flag;

	public void parseComplaint(String values) {
		String[] cusDtl = values.split(",", -1);
		 created=cusDtl[0];
		 customer_id=cusDtl[1];
		 product_name=cusDtl[2];
		 complaint=cusDtl[3];
		 score=cusDtl[4];
		 no_of_complaints=cusDtl[5];
	}

	public void parseComplaint(Text values) {
		parseComplaint(values.toString());
	}
	
	public String getCreated() {
		return created;
	}

	
	public String getComplaints_flag() {
		return complaints_flag;
	}

	public void setComplaints_flag(String complaints_flag) {
		this.complaints_flag = complaints_flag;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getNo_of_complaints() {
		return no_of_complaints;
	}

	public void setNo_of_complaints(String no_of_complaints) {
		this.no_of_complaints = no_of_complaints;
	}

}
