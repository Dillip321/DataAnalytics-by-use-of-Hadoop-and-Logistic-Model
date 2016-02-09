package com.poc.db;



import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PDFWriter {
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
			Font.NORMAL);
	private static Font smallFonth = new Font(Font.FontFamily.TIMES_ROMAN, 10,
			Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL, BaseColor.GRAY);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);
	private static Font smallBold1 = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL);
	private static Font catFontPay = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBoldPay = new Font(Font.FontFamily.TIMES_ROMAN,
			12, Font.BOLD);

	public static void convertpdf(String FILE) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addMetaData(document);
			addTitlePage(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void addMetaData(Document document) {
		document.addTitle("Salary Slip");
		document.addSubject("Using PDF");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("Nirmal Kumar");

	}

	private static void addTitlePage(Document document	)
			throws DocumentException, BadElementException,
			MalformedURLException, IOException {
		System.out.println("this main part of title");
		Paragraph preface = new Paragraph();

		Image image = Image
				.getInstance("http://portal.ahduni.edu.in/IL2AUPROD/static/images/aulogoname.png");
		image.scaleAbsolute(400, 55);
		image.setAlignment(1);
		// We add one empty line
		preface.add(image);
		preface.setAlignment(1);

		// Lets write a big header

//		preface.add(new Paragraph(universityMasterDetailMaster.getUniversity(),
//				catFont));
//
//		// addEmptyLine(preface, 1);
//		// Will create: Report generated by: _name, _date
//		preface.add(new Paragraph(universityMasterDetailMaster.getAddress()
//				+ "," + universityMasterDetailMaster.getCity() + "("
//				+ universityMasterDetailMaster.getState_name() + "-"
//				+ universityMasterDetailMaster.getCountry_name() + ")",
//				smallBold));
//		// addEmptyLine(preface, 1);
//		preface.add(new Paragraph("Pay Slip for the Month of "
//				+ paySalaryMaster.getCurrent_month() + "  "
//				+ paySalaryMaster.getCurrent_year(), smallBold1));
//		PdfPTable table = new PdfPTable(4);
//		table.setWidthPercentage(100);
//		PdfPCell c1 = new PdfPCell(new Phrase("Employee ID ", smallFonth));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase(paySalaryMaster.getEmp_id(), smallFont));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase("Name ", smallFonth));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase(paySalaryMaster.getName(), smallFont));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase("Department ", smallFonth));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase(paySalaryMaster.getDepartment_name(),
//				smallFont));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase("Designation ", smallFonth));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase(paySalaryMaster.getDesignation_name(),
//				smallFont));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase("Date of Joining  ", smallFonth));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		SimpleDateFormat sdfSource = new SimpleDateFormat("dd-MMM-yyyy");
//		c1 = new PdfPCell(new Phrase(
//				sdfSource.format(paySalaryMaster.getDoj()), smallFont));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase("PF Account Number  ", smallFonth));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase(paySalaryMaster.getPf_no(), smallFont));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase("Days Worked  ", smallFonth));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase(paySalaryMaster.getWorking_days()
//				.toString(), smallFont));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase("PAN Number  ", smallFonth));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase(paySalaryMaster.getPan_no(), smallFont));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase("Bank Acct/Cheque Number ", smallFonth));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(
//				new Phrase(paySalaryMaster.getAccount_no(), smallFont));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase("Father's/Husband's Name  ", smallFonth));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase(paySalaryMaster.getFather_name(),
//				smallFont));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase("Earned Leave ", smallFonth));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase("--", smallFont));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase("LWP Leave  ", smallFonth));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		c1 = new PdfPCell(new Phrase(paySalaryMaster.getAbsent_days()
//				.toString(), smallFonth));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBorder(0);
//		table.addCell(c1);
//		preface.add(table);
//		preface.add(new Paragraph());
//		float[] colsWidth = { 2f, 1f, 2f, 1f };
//		PdfPTable table1 = new PdfPTable(colsWidth);
//		table1.setWidthPercentage(100);
//		c1 = new PdfPCell(new Phrase("Earnings", smallBold));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//		table1.addCell(c1);
//		c1 = new PdfPCell(new Phrase("Amount", smallBold));
//		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//		table1.addCell(c1);
//		c1 = new PdfPCell(new Phrase("Deductions", smallBold));
//		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//		table1.addCell(c1);
//		c1 = new PdfPCell(new Phrase("Amount", smallBold));
//		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//		table1.addCell(c1);
//		table1.setHeaderRows(1);
//		double total_salary = 0.00;
//		double total_deduvtion_salary = 0.00;
//		java.util.List list1 = new ArrayList();
//		java.util.List list2 = new ArrayList();
//		for (int i = 0; i < list.size(); i++) {
//			if (list.get(i).getTable_name().equals("pay_earning_head_process")
//					|| list.get(i).getTable_name()
//							.equals("pay_earning_head_process_onetime")
//					|| list.get(i).getTable_name()
//							.equals("pay_additional_process")
//					|| list.get(i).getTable_name()
//							.equals("pay_extra_allowance_process")) {
//				list1.add(list.get(i));
//			}
//			if (list.get(i).getTable_name().equals("pay_deduction_process")
//					|| list.get(i).getTable_name()
//							.equals("pay_deduction_head_process_onetime")) {
//				list2.add(list.get(i));
//			}
//		}
//		DecimalFormat twoDForm = new DecimalFormat();
//		twoDForm.setMinimumFractionDigits(2);
//		twoDForm.setMaximumFractionDigits(2);
//		int x = 0;
//		for (int i = 0; i < Math.max(list1.size(), list2.size()); i++) {
//			if (list1.size() > i) {
//				table1.addCell(new Phrase(list1.get(i).getHead_name(),
//						smallFont));
//
//				PdfPCell c2 = new PdfPCell(new Phrase(twoDForm.format(list1
//						.get(i).getAmount()), smallFont));
//				// table1.addCell(new
//				// Phrase(list1.get(i).getAmount().toString(), smallFont));
//				c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
//				table1.addCell(c2);
//				total_salary += list1.get(i).getAmount();
//			} else {
//				table1.addCell(new Phrase("", smallFont));
//				table1.addCell(new Phrase("", smallFont));
//			}
//			if (list2.size() > i) {
//				table1.addCell(new Phrase(list2.get(i).getHead_name(),
//						smallFont));
//				PdfPCell c2 = new PdfPCell(new Phrase(twoDForm.format(list2
//						.get(i).getAmount()), smallFont));
//				// table1.addCell(new
//				// Phrase(list1.get(i).getAmount().toString(), smallFont));
//				c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
//				table1.addCell(c2);
//				total_deduvtion_salary += list2.get(i).getAmount();
//			} else {
//				if (x == 0) {
//					table1.addCell(new Phrase("TDS", smallFont));
//					PdfPCell c2 = new PdfPCell(new Phrase(
//							twoDForm.format(paySalaryMaster.getTds()),
//							smallFont));
//					// table1.addCell(new
//					// Phrase(list1.get(i).getAmount().toString(), smallFont));
//					c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
//					table1.addCell(c2);
//					total_deduvtion_salary += paySalaryMaster.getTds();
//					x = 1;
//				} else {
//					table1.addCell(new Phrase("", smallFont));
//					table1.addCell(new Phrase("", smallFont));
//				}
//			}
//			if ((x == 0) && (list2.size() == i + 1)
//					&& (list1.size() <= list2.size())) {
//				table1.addCell(new Phrase("", smallFont));
//				table1.addCell(new Phrase("", smallFont));
//				PdfPCell c2 = new PdfPCell(new Phrase(
//						twoDForm.format(paySalaryMaster.getTds()), smallFont));
//				// table1.addCell(new
//				// Phrase(list1.get(i).getAmount().toString(), smallFont));
//				c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
//				table1.addCell(c2);
//				total_deduvtion_salary += paySalaryMaster.getTds();
//				x = 1;
//			}
//		}
//		PdfPCell c2 = new PdfPCell(new Phrase("Total Earning ", smallBold));
//		c2.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(c2);
//		c2 = new PdfPCell(new Phrase(twoDForm.format(total_salary), smallBold));
//		c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		table1.addCell(c2);
//		c2 = new PdfPCell(new Phrase("Total Deductions", smallBold));
//		c2.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(c2);
//		c2 = new PdfPCell(new Phrase(twoDForm.format(total_deduvtion_salary),
//				smallBold));
//		c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		table1.addCell(c2);
//		PdfPCell c3 = new PdfPCell(new Phrase("Previous Balance ", smallBold));
//		c3.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(c3);
//		c3 = new PdfPCell(new Phrase("0.00", smallBold));
//		c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		table1.addCell(c3);
//		c3 = new PdfPCell(new Phrase("Net Pay ", smallBold));
//		c3.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(c3);
//		c3 = new PdfPCell(new Phrase(twoDForm.format(total_salary
//				- total_deduvtion_salary), smallBold));
//		c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		table1.addCell(c3);
//		// table1.setFooterRows();
//		preface.add(table1);
//		preface.add(new Paragraph());
//		preface.add(new Paragraph(
//				"Note: This is a computer generated salary slip, signatures or stamps are not required. ",
//				redFont));
//		document.add(preface);
//		document.newPage();
	}
}