package com.poc.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.print.DocFlavor.URL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger; 
import org.apache.log4j.PropertyConfigurator; 
import com.google.gson.Gson;
import com.poc.bean.Complaint;
import com.poc.bean.Customer;
import com.poc.bean.Prediction;
import com.poc.db.DBOperation;

import com.poc.mr.CountRAG;

public class CustomerContoller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CustomerContoller.class);
	
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		logger.info( ip.getHostAddress());
		logger.info("Test Log*"); 	
		
		//logger.info(out);

		if (request.getParameter("customeriid") != null) {
			//logger.info(request.getParameter("customeriid"));
			Integer customerId = Integer.parseInt((String) request
					.getParameter("customeriid"));
			System.out.println("value of customerId " + customerId);
			System.out.println("Welcome to servlet");
			DBOperation db = new DBOperation();
			
			Customer cc = new Customer();
			java.util.List<Complaint> complaint = new ArrayList<Complaint>();
			ArrayList<Prediction> predictionlist = new ArrayList<Prediction>();
			
			
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
			logger.info( ip.getHostAddress()+"Churn Analysis customer page");
			request.setAttribute("customer", cc);
			request.setAttribute("customerId", customerId);
			request.setAttribute("arrayList", arrayList);
			request.setAttribute("complaint", complaint);
			request.setAttribute("predictionlist", predictionlist);
			request.setAttribute("rag", request.getParameter("ragr"));
			RequestDispatcher rd = request.getRequestDispatcher("customer.jsp");
			System.out.println("welcome to servlet.");
			rd.forward(request, response);
		}
		else if (request.getParameter("rag") != null){
			System.out.println("welcome to servlet else.");
			logger.info( ip.getHostAddress()+"Churn Analysis All customer page");
			logger.info(request.getParameter("rag"));
			DBOperation db = new DBOperation();			
			ArrayList<Customer> arrayList = new ArrayList<Customer>();
			String rag=request.getParameter("rag");
			logger.info(" rag value "+request.getParameter("rag"));
			try {
				arrayList = db.getCustomer(rag);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.toString());
			}
			request.setAttribute("arrayList", arrayList);
			request.setAttribute("flag", 0);
			request.setAttribute("rag", rag);
			RequestDispatcher rd = request.getRequestDispatcher("allcustomer.jsp");
			System.out.println("welcome to servlet.");
			rd.forward(request, response);
		}
		else if (request.getParameter("search") != null){
			logger.info(request.getParameter("search"));
			logger.info( ip.getHostAddress()+"Churn Analysis search customer page");
			String name=null;
			name = "Hello "+request.getParameter("search");
			if(request.getParameter("search").toString().equals("")){
			name="Hello User";
			}
			DBOperation db = new DBOperation();			
			ArrayList<Customer> arrayList = new ArrayList<Customer>();
						try {
				arrayList = db.getCustomer();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.toString());
			}
			Gson gson = new Gson();
			String json = new Gson().toJson(arrayList);			
			response.setContentType("text/plain");  
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().write(json); 
			
		}
		else {
			System.out.println("welcome to rag else.");
			logger.info( ip.getHostAddress()+"Churn Analysis home page");
			DBOperation db = new DBOperation();			
			CountRAG countRAG=new CountRAG();
			ArrayList<Customer> arrayList = new ArrayList<Customer>();
			try {
				//arrayList = db.getCountRAG();
				arrayList=countRAG.getCountRAG();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.toString());
			}
			System.out.println("arrar list size "+arrayList.size());
			for(int i=0;i<arrayList.size();i++){
				System.out.println("rag"+arrayList.get(i).getRag());
				System.out.println("count"+arrayList.get(i).getCount());
				
			}
			request.setAttribute("arrayList1", arrayList);
			request.setAttribute("flag", 1);
			RequestDispatcher rd = request.getRequestDispatcher("viewrag.jsp");
			System.out.println("welcome to rag.");
			//logger.info(rd);
			rd.forward(request, response);
		}
	}
	
	
}
