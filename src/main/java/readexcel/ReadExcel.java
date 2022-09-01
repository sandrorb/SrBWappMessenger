package readexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
		
	public static void execute() {

		FileInputStream file;
		Workbook workbook = null;

		try {
			file = new FileInputStream(new File(".\\teste.xlsx"));
			workbook = new XSSFWorkbook(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		Sheet sheet = workbook.getSheet("Dados");
		
		for (int i=0; i<sheet.getPhysicalNumberOfRows();i++) {
			final Row row = sheet.getRow(i);
			System.out.format("%4s  %-35s  %-20s\n", row.getCell(0), row.getCell(1), row.getCell(2));
		}	
	
	}

}
