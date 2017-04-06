package com.info;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

public class Extracting_Data_From_Website{
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException
	{

	WebClient webClient=new WebClient();
	webClient.getOptions().setJavaScriptEnabled(false);
	HtmlPage page=webClient.getPage("http://www.nasdaq.com/symbol/AXON/real-time");
	HtmlSpan ticker=(HtmlSpan) page.getElementById("quotes_content_left__LastSale");
	System.out.println(ticker.getTextContent());
	HtmlSpan close =(HtmlSpan) page.getElementById("quotes_content_left__PreviousClose");
	System.out.println(close.getTextContent());
	HtmlSpan volume=(HtmlSpan) page.getElementById("quotes_content_left__Volume");
	System.out.println(volume.getTextContent());
	
}
}
