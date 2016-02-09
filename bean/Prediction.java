package com.poc.bean;

import org.apache.hadoop.io.Text;

public class Prediction {
	private String customer_name;
	private String customer_id ;
	private String  month;
	private String churn_prediction_percentage;
	
	
	public void parsePrediction(String values) {
		String[] prediction = values.split(",", -1);
		customer_name = prediction[0];
		customer_id = prediction[1];
		month = prediction[2];
		churn_prediction_percentage = prediction[3];
	
	}

	public void parsePrediction(Text values) {
		parsePrediction(values.toString());
	}
	
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getChurn_prediction_percentage() {
		return churn_prediction_percentage;
	}
	public void setChurn_prediction_percentage(String churn_prediction_percentage) {
		this.churn_prediction_percentage = churn_prediction_percentage;
	}
	
}
