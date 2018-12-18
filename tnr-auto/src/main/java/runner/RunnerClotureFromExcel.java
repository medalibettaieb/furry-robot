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

public class RunnerClotureFromExcel {
	public static final String SAMPLE_XLSX_FILE_PATH = "CahierDeRecettePilote.xlsx";

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
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

		String numSinistre = null;
		String cdl = null;
		for (Row row : sheet) {
			if (row.getRowNum() != 0) {
				for (Cell cell : row) {
					System.out.println(cell.getStringCellValue());
					String cellValue = dataFormatter.formatCellValue(cell);
					if (cell.getRowIndex() != 0) {
						if (numSinistre == null) {
							numSinistre = cellValue;
						} else {
							cdl = cellValue;
						}
					}
				}
				//System.out.println(numSinistre + "----" + cdl);
				cloture.clotureSinistre(numSinistre, cdl);
				numSinistre = null;
				cdl = null;
			} else {
				System.out.println("first line");
			}
		}

		// Closing the workbook
		workbook.close();

	}

}
