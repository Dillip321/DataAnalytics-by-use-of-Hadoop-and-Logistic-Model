package com.poc.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Testapp {

	public static double roundDouble(double x) {
		return (double) (Math.ceil(x * 2) / 2);
	}

	public static void main(String[] args) {
		System.out.println("float value : " + Testapp.roundDouble(12.8888888));

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		double num = 2.954165f;
		double round = round(num,2);
		  System.out.println("Rounded data: " + round);
	}

	public static double round(double Rval, int Rpl) {
		double p = (double) Math.pow(10, Rpl);
		Rval = Rval * p;
		double tmp = Math.round(Rval);
		return (double) tmp / p;
	}
}
