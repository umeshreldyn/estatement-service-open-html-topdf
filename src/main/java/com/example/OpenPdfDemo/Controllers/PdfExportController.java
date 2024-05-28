package com.example.OpenPdfDemo.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.text.StringSubstitutor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

@RestController
public class PdfExportController {
	
	@GetMapping()
	public ResponseEntity<String> pdfExport(){
		try {
            File htmlFile = ResourceUtils.getFile("classpath:BicReminderCL01.html");
            String htmlString = new String(new FileInputStream(htmlFile).readAllBytes());
          Map<String, String> tempParams = Map.of(
            "card_no", "123345",
            "ac_no", "5078900546",
            "amount", "5000.00",
            "date", "27/08/2023"
        );
            
           StringSubstitutor stringSubstitutor = new StringSubstitutor(Optional.of(tempParams).orElse(Collections.emptyMap()));
            String replaced = stringSubstitutor.replace(htmlString);
            
            File pdf = new File("Demo.pdf");
            OutputStream os = new FileOutputStream(pdf);
          long beforeTime = System.currentTimeMillis();
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(replaced, replaced);
//          builder.withW3cDocument(PdfExportService.html5ParseDocument("src/main/resources/BicReminderCL01.html", 0), htmlString);
            builder.toStream(os);
            builder.run();
            os.close();
            long afterTime = System.currentTimeMillis();
            System.out.println("**********Time is required for pdf generation -> " + (afterTime - beforeTime));
            return ResponseEntity.ok().body("Pdf successfully genrated");
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
	
}
