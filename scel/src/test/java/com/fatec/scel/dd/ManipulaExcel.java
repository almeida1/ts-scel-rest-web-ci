package com.fatec.scel.dd;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ManipulaExcel {

	private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell cell;
	private static XSSFRow row;
	private static String path;
	
	/**
	 * este metodo configura o caminho para abrir o arquivo Excel 
	 * @param Path
	 * @param SheetName - nome da pasta
	 * @throws Exception
	 */
	public static void setExcelFile(String path, String sheetName) throws Exception {
		try {
			// Abre o arquivo Excel
			System.out.println("manipula excel abre arquivo path = " + path);
			System.out.println("manipula excel abre sheet= " + sheetName);

			FileInputStream ExcelFile = new FileInputStream(path);

			// Acessa a planilha de dados de teste
			excelWBook = new XSSFWorkbook(ExcelFile);
			excelWSheet = excelWBook.getSheet(sheetName);
         
		} catch (Exception e) {
			System.out.println("erro manipula excel = " + e.getMessage());
			throw (e);
		}
	}

	/**
	 * Este metodo le os dados de teste de uma celula do Excel passando como parametro
	 * linha x coluna, retorna o conteudo da celula como um String
	 * @param RowNum - linha
	 * @param ColNum - coluna
	 * @return - conteudo da celula
	 * @throws Exception
	 */

	public static String getCellData(int rowNum, int colNum) throws Exception {

		try {
			cell = excelWSheet.getRow(rowNum).getCell(colNum);
			String cellData = cell.getStringCellValue();
			return cellData;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Este metodo escreve na celula do Excel passando linha X coluna
	 * @param Result
	 * @param RowNum
	 * @param ColNum
	 * @throws Exception
	 */
	public static void setCellData(String result, int rowNum, int colNum) throws Exception {
		try {
			row = excelWSheet.getRow(rowNum);
			cell = row.getCell(colNum);
			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(result);
			} else {
				cell.setCellValue(result);
			}
			// Constante que define o caminho dos dados e o arquivo de dados
			FileOutputStream fileOut = new FileOutputStream(path);
			excelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			System.out.println("erro no set excel data = " + e.getMessage());
			throw (e);
		}
	}
}