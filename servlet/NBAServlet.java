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

import com.poc.bean.Complaint;
import com.poc.bean.Customer;
import com.poc.bean.Prediction;
import com.poc.db.DBOperation;

public class NBAServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(NBAServlet.class);
	InetAddress ip;
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		
		try {
 
			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());
 
		} catch (UnknownHostException e) {
 
			e.printStackTrace();
			logger.error(e.toString());
 
		}


	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info( ip.getHostAddress());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("*************************");
		System.out.println("value of customername"
				+ request.getParameter("customername"));

		String customername = request.getParameter("customername");
		String customerid = request.getParameter("customerid");
		String gender = request.getParameter("gender");
		String customer_age = request.getParameter("customer_age");
		String marital_status = request.getParameter("marital_status");
		String product1 = request.getParameter("product1");
		String product2 = request.getParameter("product2");

		String account_cond_catg = request.getParameter("account_cond_catg");
		String own_rent_res = request.getParameter("own_rent_res");
		Integer cr_class = Integer.parseInt(request.getParameter("cr_class"));
		Integer cb_cr_score = Integer.parseInt(request
				.getParameter("cb_cr_score"));
		Integer cb_score_catg = Integer.parseInt(request
				.getParameter("cb_score_catg"));
		Integer annual_income = Integer.parseInt(request
				.getParameter("annual_income"));
		Integer annual_income_catg = Integer.parseInt(request
				.getParameter("annual_income_catg"));
		String occupation = request.getParameter("occupation");
		String occupation_catg = request.getParameter("occupation_catg");
		String est_prob = request.getParameter("est_prob");
		String product_propensity = request.getParameter("product_propensity");
		String churn_probability = request.getParameter("churn_probability");
		String churn_likelihood = request.getParameter("churn_likelihood");
		String decision = request.getParameter("decision");
		String rag = request.getParameter("rag");
		Customer customer = new Customer();
		customer.setCustomername(customername);
		customer.setCustomerid(customerid);
		customer.setGender(gender);
		customer.setCustomer_age(customer_age);
		customer.setMarital_status(marital_status);
		customer.setProduct1(product1);
		customer.setProduct2(product2);

		customer.setAccount_cond_catg(account_cond_catg);
		customer.setOwn_rent_res(own_rent_res);
		customer.setCr_class(cr_class);
		customer.setCb_cr_score(cb_cr_score);
		customer.setCb_score_catg(cb_score_catg);
		customer.setAnnual_income(annual_income);
		customer.setAnnual_income_catg(annual_income_catg);
		customer.setOccupation(occupation);
		customer.setOccupation_catg(occupation_catg);
		customer.setEst_prob(est_prob);
		customer.setProduct_propensity(product_propensity);
		customer.setChurn_probability(churn_probability);
		customer.setChurn_likelihood(churn_likelihood);
		customer.setDecision(decision);

		request.setAttribute("customer", customer);
		request.setAttribute("rag", rag);
		RequestDispatcher rd = request.getRequestDispatcher("nbaaction.jsp");
		System.out.println("welcome to NBA servlet.");
		rd.forward(request, response);

	}

}
