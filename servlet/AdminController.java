package com.poc.servlet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In do Service of AdminController method");
		
		String gi =request.getParameter("hdnbt");
		String localPath="/home/hduser/subro/";
		
		if (gi.equals("Remove"))
		{
			System.out.println("In Remove word");
			String S_word = request.getParameter("word_name");
			System.out.println("word = "+S_word);
			
			if(!(S_word.equals("")))
			{
				System.out.println("word = "+S_word);
				try
				{      	 
					String search = "";
					String value = "";
				
					String path[]= new String[1];
						path[0]=localPath+"taxonomy.txt";
												
					
					for(int i=0;i<path.length;i++){

						File file = new File(path[i]);
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line = "", oldtext = "";
						while((line = reader.readLine()) != null)
						{
							if(line.equals(S_word)){
								System.out.println("Match Found");
								continue;
							}
							else{
							oldtext += line + "\r\n";
							
						}}
						reader.close();
					
						FileWriter writer = new FileWriter(path[i]);
						writer.write(oldtext);
						//System.out.println("String ::"+newtext);
						writer.close();
						System.out.println("DONE");
					}
					RequestDispatcher rd = request.getRequestDispatcher("taxonomy.jsp");				
					rd.forward(request, response);
					//response.sendRedirect("/TextAnalytics/taxonomy.jsp");
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}   

			}
			else
			{
				System.out.println("Error.........!");
				response.sendRedirect("/TextAnalytics/ReportController");
			}
					
		}else if (gi.equals("Add"))
		{
			System.out.println("In Add word");
			String S_word = request.getParameter("word_name");
			System.out.println("word = "+S_word);
			
			if(!(S_word.equals("")))
			{

				System.out.println("word = "+S_word);
							
				try
				{      	 
					String search = "";
					String value = "";
				
					String path[]= new String[1];
						path[0]=localPath+"taxonomy.txt";
												
					
					for(int i=0;i<path.length;i++){

						File file = new File(path[i]);
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line = "", oldtext = "";
						while((line = reader.readLine()) != null)
						{
							oldtext += line + "\r\n";
						}
						oldtext +=S_word+"\r\n";

						reader.close();
						String newtext = oldtext.replaceAll(search,value);
						FileWriter writer = new FileWriter(path[i]);
						writer.write(newtext);
						//System.out.println("String ::"+newtext);
						writer.close();
						System.out.println("DONE");
					}
					RequestDispatcher rd = request.getRequestDispatcher("taxonomy.jsp");				
					rd.forward(request, response);
				
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
					 
				}   

			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("taxonomy.jsp");				
				rd.forward(request, response);
				
			}
		}
		else if(gi.equals("RUN R")){
			
			try {
				RequestDispatcher rd = request.getRequestDispatcher("taxonomy.jsp");				
				rd.forward(request, response);
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
}
