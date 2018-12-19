package runner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import tn.star.it.senariot1.TestCloture;
import tn.star.it.senariot1.TestResiliation;

public class RunnerResiliationFromExcel {
	public static final String SAMPLE_XLSX_FILE_PATH = "DocTest1.xlsx";

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {

		TestResiliation testResiliation = new TestResiliation();

		TestCloture cloture = new TestCloture();

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

		// Retrieving the number of sheets in the Workbook
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
		/*
		 * ================================================================== Iterating
		 * over all the rows and columns in a Sheet (Multiple ways)
		 * ==================================================================
		 */

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(2);

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// 2. Or you can use a for-each loop to iterate over the rows and columns
		List<String> list = new ArrayList<String>();
		for (Row row : sheet) {
			if (row.getRowNum() != 0) {
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						String cellValue = dataFormatter.formatCellValue(cell);
						list.add(cellValue);
					}

				}
			}
		}

		// Closing the workbook
		workbook.close();
		System.out.println(list.size());
		// resilier police
		try {
			for (String s : list) {
				testResiliation.resilierContrat(s);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
