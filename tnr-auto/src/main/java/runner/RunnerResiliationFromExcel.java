package runner;

import java.io.File;
import java.io.IOException;

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
	public static final String SAMPLE_XLSX_FILE_PATH = "CahierDeRecettePilote.xlsx";

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
		Sheet sheet = workbook.getSheetAt(1);

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// 2. Or you can use a for-each loop to iterate over the rows and columns

		for (Row row : sheet) {
			if (row.getRowNum() != 0) {
				for (Cell cell : row) {
					String cellValue = dataFormatter.formatCellValue(cell);
				}
			}
		}

		// Closing the workbook
		workbook.close();

		// resilier police
		try {
			testResiliation.resilierContrat("");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
