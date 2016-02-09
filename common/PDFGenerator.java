package com.poc.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.poc.bean.Complaint;
import com.poc.bean.Customer;
import com.poc.bean.Prediction;
import com.poc.db.DBOperation;

public class PDFGenerator {
	private static final long serialVersionUID = 1L;
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font fontH = new Font(Font.FontFamily.TIMES_ROMAN, 20,
			Font.BOLDITALIC);
	public static void pdfGenerator(Integer cId) throws  IOException {
		
		Document document = new Document();
		try {
			
				Integer customerId = cId;
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
				}
				System.out.println("Value of cc " + cc);
				ArrayList<Customer> arrayList = new ArrayList<Customer>();
				try {
					arrayList = db.getCustomer();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try{
					 
		    		File file = new File("/home/hduser/sharefolder/SimpleImages.pdf");
		 
		    		if(file.delete()){
		    			System.out.println(file.getName() + " is deleted!");
		    		}else{
		    			System.out.println("Delete operation is failed.");
		    		}
		 
		    	}catch(Exception e){
		 
		    		e.printStackTrace();
		 
		    	}

				PdfWriter.getInstance(document, new FileOutputStream("/home/hduser/sharefolder/SimpleImages.pdf"));
				//PdfWriter.getInstance(document, response.getOutputStream()); // Code
																				// 2
				
				document.open();
				// Code 3
				document.addTitle("Churn Analytics");
				document.addSubject("Churn Analytics");
				document.addKeywords("Java, PDF, iText");
				document.addAuthor("Narender Paul");
					
				//**********************************************************
				PdfPTable headTable = new PdfPTable(3);

				headTable.setWidths(new int[]{15, 60, 25}); 
				headTable.setWidthPercentage(150); 
				headTable.getDefaultCell().setPadding(2); 
				headTable.getDefaultCell().setBorderWidth(1);
				headTable.getDefaultCell().setBorderWidth(0); 
				headTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

				headTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

				headTable.addCell("Churn Analytics");
				//headTable.addCell("Someth″);
				headTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT); 
				headTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_UNDEFINED); 
				headTable.addCell("Big Data Platform ");

				headTable.getDefaultCell().setBorderWidth(0); 
				headTable.getDefaultCell().setColspan(3); 
				headTable.addCell("Big Data in Power");
				//headTable.addCell("hhhhhhhhhhh");
				document.add(headTable);
				//***********************************************************				
				
				document.add(new Paragraph("Customer Details",catFont));
				document.add(new Paragraph(" " ,catFont));
				PdfPTable table = new PdfPTable(2);
			
				table.addCell("Customer Name");
				table.addCell(cc.getCustomername());
				table.addCell("Customer Id");
				table.addCell(cc.getCustomerid());
				table.addCell("Gender");
				table.addCell(cc.getGender());
				table.addCell("Age");
				table.addCell(cc.getCustomer_age());
				table.addCell("Marital Status");
				table.addCell(cc.getMarital_status());
				table.addCell("Product List");
				table.addCell(cc.getProduct1()+"/"+cc.getProduct2());			 
				// Credit Card 				
				document.add(table);		
				
				document.add(new Paragraph("Complaints Details",catFont));				
				document.add(new Paragraph(" " ,catFont));
				PdfPTable table1 = new PdfPTable(2);			
				for(int i=0;i<complaint.size();i++){
					table1.addCell("Product Name");
					table1.addCell(complaint.get(i).getProduct_name());
					table1.addCell("No of Complaints ");
					table1.addCell(complaint.get(i).getNo_of_complaints());
					table1.addCell("Sentiment Score");
					table1.addCell(complaint.get(i).getScore());
					table1.addCell("Complaints Detials");
					table1.addCell(complaint.get(i).getComplaint());				
					document.add(table1);
					}
				
				document.add(new Paragraph("Transaction Details" ,catFont));				
				document.add(new Paragraph(" " ,catFont));
				PdfPTable table2 = new PdfPTable(2);			
				table2.addCell("Last Transaction");
				table2.addCell(cc.getLast_tran_amt());
				table2.addCell("Current Transaction  ");
				table2.addCell(cc.getCurr_tran_amt());
				table2.addCell("Transaction Amount Decrease");
				table2.addCell(cc.getTransaction_amount_decreased().toString());
				table2.addCell("Credit Limit");
				table2.addCell(cc.getCredit_limit().toString());	
				table2.addCell("Average Transaction Amount");
				table2.addCell(cc.getAvg_mnth_purch_amt().toString());
				table2.addCell("Average Balance");
				table2.addCell(cc.getAvg_mnth_bal_amt());
				table2.addCell("Credit Limit Utilized");
				table2.addCell(cc.getSentiment_score_cc());
				document.add(table2);
				
				document.add(new Paragraph("Customer Churn Probability" ,catFont));				
				document.add(new Paragraph(" " ,catFont));
				PdfPTable table3 = new PdfPTable(2);			
				table3.addCell("Transaction Number Decrease");
				table3.addCell(cc.getTransaction_number_decreased().toString());
				table3.addCell("Transaction Amount Decrease");
				table3.addCell(cc.getTransaction_amount_decreased().toString());
				table3.addCell("No of Complaints");
				table3.addCell(cc.getTotal_no_complaints().toString());
				table3.addCell("Sentiment score");
				table3.addCell(cc.getTotal_Sent_Score().toString());				
				document.add(table3);
				
				document.add(new Paragraph("Contribution Factor" ,catFont));				
				document.add(new Paragraph(" " ,catFont));
				PdfPTable table4 = new PdfPTable(2);			
				table4.addCell("Account Condition");
				table4.addCell(cc.getAccount_cond_catg().toString());
				table4.addCell("CB Score");
				table4.addCell(cc.getCb_cr_score().toString());
				table4.addCell("Annual Income");
				table4.addCell(cc.getAnnual_income().toString());
				table4.addCell("Occupation");
				table4.addCell(cc.getOccupation().toString());				
				document.add(table4);
				
				document.add(new Paragraph("Product Offering" ,catFont));				
				document.add(new Paragraph(" " ,catFont));
				PdfPTable table5 = new PdfPTable(2);			
				table5.addCell("Product Propensity");
				table5.addCell(cc.getEst_prob().toString()+"("+cc.getProduct_propensity()+")");
				table5.addCell("Churn Analytic Value");
				table5.addCell(cc.getChurn_probability().toString()+"("+cc.getChurn_likelihood()+")");
				table5.addCell("Product Offering");
				table5.addCell(cc.getDecision());							
				document.add(table5);
				
				document.close();
		
		} catch (DocumentException e) {
			e.printStackTrace();
			// out.println("Exception: "+e);
		}
	}
}
