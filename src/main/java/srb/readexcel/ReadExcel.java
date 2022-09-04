package srb.readexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import srb.webservice.ClienteContato;

public class ReadExcel {
	
	private String fileName = ".\\teste.xlsx";
	
	private static ReadExcel instance = null;
	
	public ReadExcel() {

	}
	
	public static ReadExcel getInstance() {
		if(instance == null) {
			instance = new ReadExcel();
		}
		return instance;
	}
	
///////////////////////////////////////////////////////////////////////	
	
	public List<ClienteContato> getClientes(InputStream is){
		List<ClienteContato> clientes = new ArrayList<ClienteContato>();
		Workbook wb = getWorkbook(is);
		Sheet sheet = wb.getSheet("Dados");
		for (int i=0; i<sheet.getPhysicalNumberOfRows();i++) {
			final Row row = sheet.getRow(i);
			//System.out.format("Local: %4s  %-35s  %-20s\n", row.getCell(0), row.getCell(1), row.getCell(2));
			ClienteContato cliente = new ClienteContato();
			cliente.setNome(row.getCell(1).toString());
			cliente.setTelefone(row.getCell(2).toString());
			clientes.add(cliente);
		}
		return clientes;
	}
	
	public String toString(Workbook wb) {
		
		StringBuilder sb = new StringBuilder();
		Sheet sheet = wb.getSheet("Dados");
		
		for (int i=0; i<sheet.getPhysicalNumberOfRows();i++) {
			final Row row = sheet.getRow(i);
			//System.out.format("Local: %4s  %-35s  %-20s\n", row.getCell(0), row.getCell(1), row.getCell(2));
			sb.append(String.format("%4s  %-35s  %-20s\n", row.getCell(0), row.getCell(1), row.getCell(2)));
		}	
		return sb.toString();
	}
	
	public void print() {
		toString(getWorkbook(getInputStream(fileName)));
	}
	
	public String toString(InputStream is) {
		return toString(getWorkbook(is));
	}
	
	public Workbook getWorkbook(InputStream is) {
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook;
	}

	public InputStream getInputStream(String fileName) {
		InputStream file = null;
		try {
			file = new FileInputStream(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return file;
	}
}
