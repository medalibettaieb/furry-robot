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

import tn.star.it.domain.Tier;
import tn.star.it.senariot1.TestAddTier;
import tn.star.it.senariot1.TestAddTierV2;

public class RunnerAddTiersFromExcel {
	public static final String SAMPLE_XLSX_FILE_PATH = "DocTest1.xlsx";
	private static List<Tier> tiers;
	private static TestAddTier testAddTier = new TestAddTier();
	private static TestAddTierV2 testAddTierV2 = new TestAddTierV2();

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
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
		Sheet sheet = workbook.getSheetAt(0);

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// 2. Or you can use a for-each loop to iterate over the rows and columns
		System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
		List<Tier> tiers = new ArrayList<>();
		for (Row row : sheet) {
			Tier tier = new Tier();
			if (row.getRowNum() != 0) {
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setNoeud(cellValue);
						;
					}
					if (cell.getColumnIndex() == 1) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setTypePersonne(cellValue);
						;
					}
					if (cell.getColumnIndex() == 2) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setProfession(cellValue);
						;
					}
					if (cell.getColumnIndex() == 3) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setNom(cellValue);
					}
					if (cell.getColumnIndex() == 4) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setPrenom(cellValue);
					}
					if (cell.getColumnIndex() == 5) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setNomArabe(cellValue);
					}
					if (cell.getColumnIndex() == 6) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setPrenomArabe(cellValue);
					}
					if (cell.getColumnIndex() == 7) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setSexe(cellValue);
					}
					if (cell.getColumnIndex() == 8) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setDateNaissance(cellValue);
					}
					if (cell.getColumnIndex() == 9) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setPaysNaissance(cellValue);
					}
					if (cell.getColumnIndex() == 10) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setLocaliteNaissance(cellValue);
					}
					if (cell.getColumnIndex() == 11) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setPaysDeDeliviance(cellValue);
					}
					if (cell.getColumnIndex() == 12) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setTypeDocument(cellValue);
					}
					if (cell.getColumnIndex() == 13) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setNumDocument(cellValue);
					}
					if (cell.getColumnIndex() == 14) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setNumAdresse(cellValue);
					}
					if (cell.getColumnIndex() == 15) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setTypeVoie(cellValue);
					}
					if (cell.getColumnIndex() == 16) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setAdresse(cellValue);
					}
					if (cell.getColumnIndex() == 17) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setLocalite(cellValue);
					}
					if (cell.getColumnIndex() == 18) {
						String cellValue = dataFormatter.formatCellValue(cell);
						tier.setNumTel(cellValue);
					}

				}
				tiers.add(tier);
			}
		}
// adding tiers
		for (Tier t : tiers) {
			try {
				testAddTierV2.ajouterTier(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Closing the workbook
		workbook.close();
	}
}
