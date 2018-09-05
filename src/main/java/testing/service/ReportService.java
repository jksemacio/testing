package testing.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testing.model.entity.Contact;
import testing.model.entity.User;

public class ReportService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private XSSFWorkbook workbook;
	
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
		File file = new File("Users.xlsx");
		FileOutputStream out = new FileOutputStream(file);
		workbook.write(out);
		out.close();
		
		File usersFile = new File("Users.xlsx");
		FileInputStream in = new FileInputStream(usersFile);
		return in;
	}
}
