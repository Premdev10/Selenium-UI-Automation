package com.framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    public static List<Map<String, String>> getData(String excelFilePath, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
             
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet " + sheetName + " does not exist.");
            }
            
            Row headerRow = sheet.getRow(0);
            int totalColumns = headerRow.getLastCellNum();
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                if (currentRow == null) continue;
                
                Map<String, String> rowMap = new LinkedHashMap<>();
                for (int j = 0; j < totalColumns; j++) {
                    String header = headerRow.getCell(j).getStringCellValue();
                    Cell cell = currentRow.getCell(j);
                    String value = "";
                    if (cell != null) {
                        DataFormatter formatter = new DataFormatter();
                        value = formatter.formatCellValue(cell);
                    }
                    rowMap.put(header, value);
                }
                data.add(rowMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
