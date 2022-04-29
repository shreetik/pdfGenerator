package com.generator.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.model.Ec2Data;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;



@Service
public class PdfGenerator {

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		
		Ec2Service ec2Service = new Ec2Service();
		
		Ec2Data ec2data =  ec2Service.getEc2Data();
		Document document = new Document(PageSize.A4);
		PdfWriter pdf = PdfWriter.getInstance(document, response.getOutputStream());
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		String currentDateTime = dateFormatter.format(new Date());
		
		document.open();
		Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
		fontParagraph.setSize(15);
		
		Paragraph line1 = new Paragraph("Swayam - Consolidated Account Discovery Report",fontParagraph);
		Paragraph line2 = new Paragraph("Report Date : "+currentDateTime, fontParagraph);
		Paragraph line3 = new Paragraph(" ",fontParagraph);
		Paragraph line4 = new Paragraph("Number of Accounts : ",fontParagraph);
		Paragraph line5 = new Paragraph("Total EC2 : "+ec2data.getTotal_instances(),fontParagraph);
		Paragraph line6 = new Paragraph("Total EBS : "+ec2data.getVolume_count(),fontParagraph);
		Paragraph line7 = new Paragraph("Total Snapshots : "+ec2data.getSnapshot_count(),fontParagraph);
		Paragraph line8 = new Paragraph("  ",fontParagraph);
		Paragraph line9 = new Paragraph("Account wise Operations Report : ",fontParagraph);
		Paragraph line10 = new Paragraph(" ",fontParagraph);
		Paragraph line11 = new Paragraph("Account Number : ",fontParagraph);
		Paragraph line12 = new Paragraph(" ",fontParagraph);
		Paragraph line13 = new Paragraph("Region : Mumbai ",fontParagraph);
		Paragraph line14 = new Paragraph(" ",fontParagraph);
		Paragraph line15 = new Paragraph("EC2 in Start State : "+ec2data.getStart_state(),fontParagraph);
		Paragraph line16 = new Paragraph("EC2 in Stopped State : "+ec2data.getStop_state(),fontParagraph);
		Paragraph line17 = new Paragraph("RDS",fontParagraph);
		Paragraph line18 = new Paragraph("EBS",fontParagraph);
		
		document.add(line1);
		document.add(line2);
		document.add(line3);
		document.add(line4);
		document.add(line5);
		document.add(line6);
		document.add(line7);
		document.add(line8);
		document.add(line9);
		document.add(line10);
		document.add(line11);
		document.add(line12);
		document.add(line13);
		document.add(line14);
		document.add(line15);
		document.add(line16);
		document.add(line17);
		document.add(line18);
		
		document.close();
	}
	
}
