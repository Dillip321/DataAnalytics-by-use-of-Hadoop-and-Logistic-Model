package com.poc.test;
import java.io.*;


import com.itextpdf.text.pdf.PdfPTable;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfBorderDictionary;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PushbuttonField;
import com.lowagie.text.pdf.RadioCheckField;
import com.lowagie.text.Rectangle;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
public class FirstDoc {

	public void RadioCheckBoxForm() throws Exception{
		Document document = new Document();
		
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Documents and Settings\\napaul\\workspace\\poc\\WebContent\\resourse\\RadioCheckBoxForm.pdf"));
		document.open();
//		document.addHeader("gggggggggggg", ";;;;;;;;;;;;;;;;;");
//		document.addAuthor("gbgggggggggggg");
//		document.addCreationDate();
//		document.addTitle("jjjjjjjjjjjjjjjjjjjjjj");
		Table table =new Table(3);
		table.backgroundColor();
		table.absWidth();
		table.addCell("hhhhhhhhhhhhhhhhhh");
		table.addColumns(1);
		writer.getPdfTable(table);
		PdfContentByte cb = writer.getDirectContent();
		
		BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
		Rectangle rect;		
		
		// Code 1 create radio button
		String[] radios = { "Radio1", "Radio2", "Radio3" };
		PdfFormField radioField = PdfFormField.createRadioButton(writer, true);
		radioField.setFieldName("radioField");
		radioField.setValueAsName(radios[0]);
		for (int i = 0; i < radios.length; i++) {
			rect = new Rectangle(40, 815 - i * 30, 60, 797 - i * 30);
			addRadioButton(writer, rect, radioField, radios[i], i == 0);
			cb.beginText();
			cb.setFontAndSize(bf, 12);
			cb.showTextAligned(Element.ALIGN_LEFT, radios[i], 70, 802 - i * 30, 0);
			cb.endText();
		}
		writer.addAnnotation(radioField);		
		
		
		// Code 2 create checkbox button
		String[] options = {"Check1", "Check2", "Check3"};
		for (int i = 0; i < options.length; i++) {
			rect = new Rectangle(160, 815 - i * 30, 180, 797 - i * 30);
			addCheckbox(writer, rect, options[i]);
			cb.beginText();
			cb.setFontAndSize(bf, 12);
			cb.showTextAligned(Element.ALIGN_LEFT, options[i], 190,
					802 - i * 30, 0);
			cb.endText();
		}		
	
		
		// Code 3 add function and button for showing state 
		writer.addJavaScript("function showState(){" +
				"app.alert('Radio:'+ this.getField('radioField').value +'\\n\\n'+" +
				"'Check1:'+this.getField('Check1').value +'\\n'+" +
				"'Check2:'+this.getField('Check2').value +'\\n'+" +
				"'Check3:'+this.getField('Check3').value);" +
				"}");		
		PushbuttonField push = new PushbuttonField(writer, new Rectangle(
				80, 710, 150, 730), "pushAction");
		push.setBackgroundColor(Color.LIGHT_GRAY);
		push.setBorderColor(Color.GRAY);
		push.setText("Show State");
		push.setBorderStyle(PdfBorderDictionary.STYLE_BEVELED);
		push.setTextColor(Color.BLACK);
		PdfFormField pushbutton = push.getField();
		pushbutton.setAction(PdfAction.javaScript("showState()",
				writer));
		writer.addAnnotation(pushbutton);	
		
		writer.addJavaScript("function showState(){" +
				"app.alert('Radio:'+ this.getField('radioField').value +'\\n\\n'+" +
				"'Check1:'+this.getField('Check1').value +'\\n'+" +
				"'Check2:'+this.getField('Check2').value +'\\n'+" +
				"'Check3:'+this.getField('Check3').value);" +
				"}");
		
		String FusionCharts="<script language='JavaScript'	src='FusionChartsFree/Code/FusionCharts/FusionCharts.js'></script>";
		writer.addJavaScript(FusionCharts);
		//Class<PdfPTable> table=PdfPTable.class;
//		Table tablew = new Table(2);
//		tablew.addCell("ssssssssssssss");
//		tablew.addCell("11111111111111111111111111111111");
//		tablew.addCell("ssssssssssssdddddss");
		
		writer.addJavaScript(" function showGraph(){  " +
				"var strXML = '<graph animation='0'  showNames='1'><set name='CA' value='1' color='AFD8F8' link='JavaScript:onclickFF();' /><set name='CC' value='10' color='F6BD0F' link='JavaScript:onclickFF();' /></graph>"
							+" var chart1 = new FusionCharts('FusionChartsFree/Code/FusionCharts/FCF_Pie3D.swf',	'myChart', '430', '230', '0', '0') "
							+ " chart1.setDataXML(strXML) } ");
		PushbuttonField pushG = new PushbuttonField(writer, new Rectangle(
				180, 710, 150, 730), "pushAction");
		pushG.setBackgroundColor(Color.LIGHT_GRAY);
		pushG.setBorderColor(Color.GRAY);
		pushG.setText("Show Graph");
		pushG.setBorderStyle(PdfBorderDictionary.STYLE_BEVELED);
		pushG.setTextColor(Color.BLACK);
		PdfFormField pushbuttonG = push.getField();
		pushbuttonG.setAction(PdfAction.javaScript("showGraph()",
				writer));
		writer.addAnnotation(pushbutton);
		
		
		document.close();
	}
	
	private static void addRadioButton(PdfWriter writer, Rectangle rect,
			PdfFormField radio, String name, boolean on) throws IOException,
			DocumentException {
		RadioCheckField check = new RadioCheckField(writer, rect, null, name);
		check.setCheckType(RadioCheckField.TYPE_CHECK);
		check.setBorderColor(Color.BLACK);
		check.setChecked(on);
		radio.addKid(check.getRadioField());
	}	
	
	private static void addCheckbox(PdfWriter writer, Rectangle rect,
			String name) throws IOException, DocumentException {
		RadioCheckField check = new RadioCheckField(writer, rect, name, "On");
		check.setCheckType(RadioCheckField.TYPE_CROSS);
		check.setBorderColor(Color.BLACK);
		writer.addAnnotation(check.getCheckField());
	}	

	public static void main(String[] args){
		try{
			FirstDoc radioCheckForm = new FirstDoc();
			radioCheckForm.RadioCheckBoxForm();
		}catch(Exception e){
			System.out.println(e);
		}

	}
}
