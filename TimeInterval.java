package com.info;

import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.io.*;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

public class TimeInterval extends Thread {
	volatile boolean flag = true;
	public static int c=0;

	public void run() {
		try {
			BufferedReader br=new BufferedReader(new FileReader("C:/Users/Dell/Desktop/Banwali/abc.txt"));
			while(br.readLine()!=null)
			{
				
				String s=br.readLine();
				WebClient webClient=new WebClient();
				webClient.getOptions().setJavaScriptEnabled(false);
				HtmlPage page=webClient.getPage("http://www.nasdaq.com/symbol/"+s+"/real-time");
				HtmlSpan ticker=(HtmlSpan) page.getElementById("quotes_content_left__LastSale");
				String ticker_database=(ticker.getTextContent());
				HtmlSpan close =(HtmlSpan) page.getElementById("quotes_content_left__PreviousClose");
				String close_database=(close.getTextContent());
				HtmlSpan volume=(HtmlSpan) page.getElementById("quotes_content_left__Volume");
		     	String volume_database=(volume.getTextContent());
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "oracle");

				CallableStatement stmt = con
						.prepareCall("{call insertuser(?,?,?,?)}");
				stmt.setString(1, s);
				stmt.setString(2,ticker_database);
				stmt.setString(3, close_database);
				stmt.setString(4, volume_database);
				stmt.execute();
				System.out.println("Success");
				System.out.println(++c);
				con.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		TimeInterval t = new TimeInterval();
		t.start();

	}

}