package com.poc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.poc.bean.Complaint;
import com.poc.bean.Customer;
import com.poc.bean.Prediction;
import com.poc.db.DBOperation;


public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SearchServlet.class);
	InetAddress ip;
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		
		try {
 
			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());
 
		} catch (UnknownHostException e) {
 
			e.printStackTrace();
 
		}


	}
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info( ip.getHostAddress());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		System.out.println("******************************");
		DBOperation db = new DBOperation();
		Customer cc = new Customer();
		java.util.List<Complaint> complaint = new ArrayList<Complaint>();
		ArrayList<Prediction> predictionlist = new ArrayList<Prediction>();
		Integer customerId=0;
		String customername="";		
		String searchType=(String) request.getParameter("txtSearchType");
		
			 customerId = Integer.parseInt((String) request.getParameter("dropType"));
			 logger.info( ip.getHostAddress()+"Churn Analysis Search customer "+customerId);
			 try {
					cc = db.getCustomerRecord(customerId);
					complaint = db.getCustomerComplaint(customerId);
					predictionlist = db.getChurnProbability(customerId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.toString());
				}
		

		
		System.out.println("Value of cc " + cc);
		ArrayList<Customer> arrayList = new ArrayList<Customer>();
		try {
			arrayList = db.getCustomer();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
		}
		request.setAttribute("customer", cc);
		request.setAttribute("arrayList", arrayList);
		request.setAttribute("complaint", complaint);
		request.setAttribute("predictionlist", predictionlist);
		RequestDispatcher rd = request.getRequestDispatcher("customer.jsp");
		System.out.println("welcome to servlet.");
		rd.forward(request, response);
	}

}