package DataTools;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static DataTools.securityData.decrypt;
import static DataTools.securityData.encrypt;

public class excelJobs {
    private final static String path = "testData.xlsx";

    public static String readData(String targetString) {
        try (FileInputStream fis = new FileInputStream(path);
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

    public void writeNewCredential(String title , String value) throws IOException {

        String excelFilePath = path;
        File file = new File(excelFilePath);
        FileInputStream os = new FileInputStream(excelFilePath);
        XSSFWorkbook wb = new XSSFWorkbook(os);
        XSSFSheet sheet = wb.getSheet("credential");

        // Find the first empty column
        int iRow = 1;
        while (sheet.getRow(iRow)!= null) {
            iRow++;
        }

        // Insert new row data to excel
        XSSFRow row = sheet.createRow(iRow);
        row.createCell(1).setCellValue(value);
        row.createCell(0).setCellValue(title);

        // Auto-size the column width
        sheet.autoSizeColumn(1);

        // Write the updated workbook to the file
        FileOutputStream fout = new FileOutputStream(file);
        wb.write(fout);
        os.close();
        wb.close();
    }

    @Test
    public void test() throws IOException {
        writeNewCredential("usere2e", encrypt("password"));
        System.out.println(decrypt(readData("usere2e")));
    }
}
