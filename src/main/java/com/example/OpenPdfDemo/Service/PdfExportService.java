package com.example.OpenPdfDemo.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

@Service
public class PdfExportService {
	public void ExportPdf(){
		
		 ByteArrayOutputStream actual = new ByteArrayOutputStream();
        
		 String html ="src/main/resources/BicReminderCL01.html";
		 PdfRendererBuilder builder = new PdfRendererBuilder();
//	        builder.withHtmlContent(html, VisualTester.class.getResource(this.resourcePath).toString());
		   builder.withProducer(html);
		  
	        builder.toStream(actual);
	        builder.useFastMode();
	        builder.testMode(true);
//	        config.configure(builder);
	}
	
	public static Document html5ParseDocument(String urlStr, int timeoutMs) throws IOException 
	{
		
		
//		System.out.println("****************"+urlStr);
//		URl url = new URL(urlStr);
//		org.jsoup.nodes.Document doc;
//		
//		if (url.getProtocol().equalsIgnoreCase("file")) {
//			doc = Jsoup.parse(new File(url.getPath()), "UTF-8");
//		}
//		else {
		return Jsoup.parse(urlStr);	
			
//		}
		// Should reuse W3CDom instance if converting multiple documents.
//		return new W3CDom().fromJsoup(doc);
	}

}
