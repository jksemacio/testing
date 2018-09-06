package testing.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xalan.processor.TransformerFactoryImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import testing.model.entity.Contact;
import testing.model.entity.User;

public class ReportService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private XSSFWorkbook workbook;
	
	public FileInputStream getReportPDF(User user, List<Contact> contacts) throws ConfigurationException, SAXException, IOException, TransformerException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
        
		Element rootElement = doc.createElement("User");
		doc.appendChild(rootElement);
		
		Attr userAttr = doc.createAttribute("id");
	    userAttr.setValue(Integer.toString(user.getId()));
	    rootElement.setAttributeNode(userAttr);
		
		Element userElement = doc.createElement("Email");
		userElement.appendChild(doc.createTextNode(user.getEmail()));
		rootElement.appendChild(userElement);
        
        Element contactsElement = doc.createElement("Contacts");
        rootElement.appendChild(contactsElement);
        
        Iterator<Contact> contactsIterator = contacts.iterator();
        
        while(contactsIterator.hasNext()) {
        	Contact contact = contactsIterator.next();
        	Element contactElement = doc.createElement("Contact");
            contactsElement.appendChild(contactElement);
            
            Attr contactAttr = doc.createAttribute("id");
            contactAttr.setValue(Integer.toString(contact.getId()));
            contactElement.setAttributeNode(contactAttr);
            
            Element contactName = doc.createElement("Name");
    		contactName.appendChild(doc.createTextNode(contact.getName()));
            contactElement.appendChild(contactName);
            
            Element contactNumber = doc.createElement("Number");
            contactNumber.appendChild(doc.createTextNode(contact.getNumber()));
            contactElement.appendChild(contactNumber);
            
            Element contactCountry = doc.createElement("Country");
            contactCountry.appendChild(doc.createTextNode(contact.getCountry().getCountry()));
            contactElement.appendChild(contactCountry);    
        }
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("userContacts.xml"));
        transformer.transform(source, result);
       
		File xmlFile = new File("userContacts.xml");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File xsltfile = new File(classLoader.getResource("userContacts.xsl").getFile());
		StreamSource sourceStream = new StreamSource(xmlFile);
		StreamSource transformSource = new StreamSource(xsltfile);
		
		FopFactory fopFactory = FopFactory.newInstance(xmlFile);
		
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		Transformer xslfoTransformer;
		xslfoTransformer = getTransformer(transformSource);

		Fop fop;
		fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, outStream);
		
		Result res = new SAXResult(fop.getDefaultHandler());
		xslfoTransformer.transform(sourceStream, res);
		
		File pdffile = new File("userContacts.pdf");
		OutputStream out = new java.io.FileOutputStream(pdffile);
        out = new java.io.BufferedOutputStream(out);
	    FileOutputStream str = new FileOutputStream(pdffile);
	    str.write(outStream.toByteArray());
	    str.close();
	    out.close();

	    File userContactsFile = new File("userContacts.pdf");
		FileInputStream in = new FileInputStream(userContactsFile);
		return in;
	}
	
	private Transformer getTransformer(StreamSource streamSource)
	{
		TransformerFactoryImpl impl = new TransformerFactoryImpl();

		try {
			return impl.newTransformer(streamSource);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public FileInputStream getReportXML(User user, List<Contact> contacts) throws ParserConfigurationException, TransformerException, FileNotFoundException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
        
		Element rootElement = doc.createElement("User");
		doc.appendChild(rootElement);
		
		Attr userAttr = doc.createAttribute("id");
	    userAttr.setValue(Integer.toString(user.getId()));
	    rootElement.setAttributeNode(userAttr);
		
		Element userElement = doc.createElement("Email");
		userElement.appendChild(doc.createTextNode(user.getEmail()));
		rootElement.appendChild(userElement);
        
        Element contactsElement = doc.createElement("Contacts");
        rootElement.appendChild(contactsElement);
        
        Iterator<Contact> contactsIterator = contacts.iterator();
        
        while(contactsIterator.hasNext()) {
        	Contact contact = contactsIterator.next();
        	Element contactElement = doc.createElement("Contact");
            contactsElement.appendChild(contactElement);
            
            Attr contactAttr = doc.createAttribute("id");
            contactAttr.setValue(Integer.toString(contact.getId()));
            contactElement.setAttributeNode(contactAttr);
            
            Element contactName = doc.createElement("Name");
    		contactName.appendChild(doc.createTextNode(contact.getName()));
            contactElement.appendChild(contactName);
            
            Element contactNumber = doc.createElement("Number");
            contactNumber.appendChild(doc.createTextNode(contact.getNumber()));
            contactElement.appendChild(contactNumber);
            
            Element contactCountry = doc.createElement("Country");
            contactCountry.appendChild(doc.createTextNode(contact.getCountry().getCountry()));
            contactElement.appendChild(contactCountry);    
        }
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("userContacts.xml"));
        transformer.transform(source, result);
       
		File userContactsFile = new File("userContacts.xml");
		FileInputStream in = new FileInputStream(userContactsFile);
		return in;
	}
	
	public FileInputStream getReport(User user, List<Contact> contacts) throws IOException { 
		workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("Users");
		
		XSSFRow row1 = spreadsheet.createRow(0);
		
		row1.createCell(0).setCellValue("User:");
		row1.createCell(1).setCellValue(user.getId());
		row1.createCell(2).setCellValue(user.getEmail());
		
		XSSFRow row = spreadsheet.createRow(2);
		XSSFCell cell;
		cell = row.createCell(1); cell.setCellValue("Contacts:");
		cell = row.createCell(2); cell.setCellValue("Id");
		cell = row.createCell(3); cell.setCellValue("Name");
		cell = row.createCell(4); cell.setCellValue("Number");
		cell = row.createCell(5); cell.setCellValue("Country");
		
		Iterator<Contact> contactsIterator = contacts.iterator();
		int i = 3;
		while(contactsIterator.hasNext()) {
		   row = spreadsheet.createRow(i);
		   Contact contact = contactsIterator.next();
		   cell = row.createCell(2); cell.setCellValue(contact.getId());
		   cell = row.createCell(3); cell.setCellValue(contact.getName());
		   cell = row.createCell(4); cell.setCellValue(contact.getNumber());
		   cell = row.createCell(5); cell.setCellValue(contact.getCountry().getCountry());
		   i++;
		}
		File file = new File("userContacts.xlsx");
		FileOutputStream out = new FileOutputStream(file);
		workbook.write(out);
		out.close();
		
		File usersFile = new File("userContacts.xlsx");
		FileInputStream in = new FileInputStream(usersFile);
		return in;
	}
}
