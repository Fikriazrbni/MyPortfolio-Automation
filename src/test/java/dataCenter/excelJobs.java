package dataCenter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class excelJobs {

    public static String getCellValueForString(String filePath, String targetString) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cellA = row.getCell(0); // Column A
                if (cellA != null && cellA.getCellType() == CellType.STRING && cellA.getStringCellValue().equals(targetString)) {
                    // Found the target string in column A, retrieve value from column B
                    Cell cellB = row.getCell(1); // Column B
                    if (cellB != null && cellB.getCellType() == CellType.STRING) {
                        return cellB.getStringCellValue();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;  // Return null if the target string is not found or there is an error
    }

    public static void main(String[] args) {
        String filePath = "testData.xlsx";
        String targetString = "New Password";

        String valueInColumnB = getCellValueForString(filePath, targetString);

        if (valueInColumnB != null) {
            System.out.println("Value in column B for the target string in column A: " + valueInColumnB);
        } else {
            System.out.println("Target string not found or an error occurred.");
        }
    }
}
