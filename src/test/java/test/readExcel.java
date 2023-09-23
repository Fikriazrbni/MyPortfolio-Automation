package test;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static definitions.stepDefinitionApi.hashPassword;

public class readExcel {

    public static final String pthTestData = "testData.xlsx";
    public void writeData(String title ,String value) throws IOException {

        String excelFilePath = pthTestData;
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
    public void test() throws IOException, NoSuchAlgorithmException {
        writeData("ID","new id");
        writeData("New password",hashPassword("Password"));
    }
}
