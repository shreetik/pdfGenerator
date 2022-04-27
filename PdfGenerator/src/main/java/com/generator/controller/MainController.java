package com.generator.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generator.service.PdfGenerator;
import com.lowagie.text.DocumentException;

@RestController
public class MainController {

	@Autowired
	private PdfGenerator pdfGenerator;
	
	@GetMapping("/pdf/download")
	public void generatePdf( HttpServletResponse response) throws DocumentException, IOException {
		
		response.setContentType("application/pdf");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename = pdf_"+currentDateTime +".pdf";
		response.setHeader(headerKey, headervalue);
		
		this.pdfGenerator.export(response);
	}
}
