package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * ExcelUtility class provides methods to read and write Excel files.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class ExcelUtility {

    private Workbook workbook;
    private Sheet sheet;
    private String excelFilePath;

    /**
     * Constructor to load Excel file.
     */
    public ExcelUtility(String filePath, String sheetName) {
        try {
            this.excelFilePath = filePath;
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            LoggerManager.info("Excel file loaded: " + filePath);
        } catch (Exception e) {
            LoggerManager.error("Failed to load Excel file: " + e.getMessage(), e);
            throw new RuntimeException("Excel file loading failed", e);
        }
    }

    /**
     * Gets data from Excel cell.
     */
    public String getCellData(int rowNum, int colNum) {
        try {
            Row row = sheet.getRow(rowNum);
            if (row == null) return "";
            Cell cell = row.getCell(colNum);
            if (cell == null) return "";
            return cell.toString();
        } catch (Exception e) {
            LoggerManager.error("Failed to get cell data: " + e.getMessage(), e);
            return "";
        }
    }

    /**
     * Sets data to Excel cell.
     */
    public void setCellData(int rowNum, int colNum, String data) {
        try {
            Row row = sheet.getRow(rowNum);
            if (row == null) row = sheet.createRow(rowNum);
            Cell cell = row.createCell(colNum);
            cell.setCellValue(data);
            LoggerManager.info("Cell data set at row: " + rowNum + ", col: " + colNum);
        } catch (Exception e) {
            LoggerManager.error("Failed to set cell data: " + e.getMessage(), e);
        }
    }

    /**
     * Gets total row count.
     */
    public int getRowCount() {
        return sheet.getLastRowNum() + 1;
    }

    /**
     * Gets column count for a specific row.
     */
    public int getColumnCount(int rowNum) {
        return sheet.getRow(rowNum).getLastCellNum();
    }

    /**
     * Saves the Excel file.
     */
    public void saveExcelFile() {
        try {
            FileOutputStream fos = new FileOutputStream(excelFilePath);
            workbook.write(fos);
            fos.close();
            LoggerManager.info("Excel file saved.");
        } catch (Exception e) {
            LoggerManager.error("Failed to save Excel file: " + e.getMessage(), e);
        }
    }

    /**
     * Closes the Excel file.
     */
    public void closeExcelFile() {
        try {
            workbook.close();
            LoggerManager.info("Excel file closed.");
        } catch (Exception e) {
            LoggerManager.error("Failed to close Excel file: " + e.getMessage(), e);
        }
    }
}
