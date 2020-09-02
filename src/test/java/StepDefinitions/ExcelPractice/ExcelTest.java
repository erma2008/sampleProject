package StepDefinitions.ExcelPractice;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class ExcelTest {

    public static void main(String[] args) throws Exception {
        //System.getProperty("user.dir"); --> it will give current project directory
        //D:\java class videos\Project\TechtorialCucumber\TechtorialCucumber\src\test\resources\com.cucumber.features.ExcelData\Book2.xlsx
        String filePath="D:\\java class videos\\Project\\TechtorialCucumber\\TechtorialCucumber\\src\\test\\resources\\com.cucumber.features.ExcelData\\Book2.xlsx";
        //Inside file class we need to give the file path
        File file=new File(filePath);
        //FileInputStream will open the file which we provided.
        FileInputStream inputStream=new FileInputStream(file);
        // XSSFWorkbook requires location of your excel file
        Workbook excelWorkbook=new XSSFWorkbook(inputStream);
        Sheet excelSheet=excelWorkbook.getSheet("Sheet1");
        Row row=excelSheet.getRow(3);
        Cell cell=row.getCell(1);
        System.out.println(cell);

        //to change the value

        //first we create the row
        row=excelSheet.createRow(5);
        //after we create the cell
        cell=row.createCell(0);
        //cell type
        cell.setCellType(CellType.STRING);
        //cell value
        cell.setCellValue("Test16");

        //If you want to store values in your excel files,
        //We need to use fileOutput class
        //We need to give the file path

        FileOutputStream outputStream=new FileOutputStream(filePath);
        excelWorkbook.write(outputStream);

            String expected="Test16";
            row=excelSheet.getRow(5);
            cell=row.getCell(0);
            String actual=cell.toString();

        Assert.assertEquals(expected,actual);


        row=excelSheet.getRow(2);
        cell=row.getCell(1);
        cell.setCellValue("Techtorial");
        outputStream=new FileOutputStream(filePath);
        excelWorkbook.write(outputStream);
    }

}
