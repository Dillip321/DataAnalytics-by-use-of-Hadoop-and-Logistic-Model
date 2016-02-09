package com.poc.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.poc.bean.TextAnalysis;
import com.poc.mr.Util;

public class TextAnalyisS extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(TextAnalyisS.class);
	
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
		Util util=new Util();
		Properties properties=new Properties();
		properties=util.getPropertiesFromClasspath();
		String outputPath = properties.getProperty("Final_OP");
		
		try{
			File file=new File(outputPath);
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			String line = null;
			String array[] = null;
			ArrayList<TextAnalysis> alst =new ArrayList<TextAnalysis>();
			while((line = bufferedReader.readLine()) != null){
				array = line.split(";");
				TextAnalysis textAnalysis=new TextAnalysis();
				textAnalysis.setName(array[0]);
				textAnalysis.setFlag(array[1]);
				textAnalysis.setCompany(array[2]);
				
				alst.add(textAnalysis);
			}
			request.setAttribute("alst", alst);
			RequestDispatcher rd = request.getRequestDispatcher("text_analysis.jsp");			
			rd.forward(request, response);
		}
		catch(Exception e){
			
		}
	}
	
	

}
