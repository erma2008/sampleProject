package Runner;

import Utils.ExcelUtils;

import java.io.IOException;

public class ExcelRunner {
    public static void main(String[] args) throws IOException {

        ExcelUtils.openExcelFile("Employee","Page 1");
        String cellValue=ExcelUtils.getCellValue(3,4);
        System.out.println(cellValue);

        ExcelUtils.setCellValue("Employee","USA",4,5);
        cellValue=ExcelUtils.getCellValue(4,5);
        System.out.println(cellValue);


        ExcelUtils.closeWorkbook();

        ExcelUtils.openExcelFile("Book2","Sheet1");
       // ExcelUtils.getAllExcelValue();
        System.out.println(ExcelUtils.getAllValues());
    }
}
