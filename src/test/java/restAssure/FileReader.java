package restAssure;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileReader {

    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;

    public FileReader(String path) {

        this.path = path;
        System.out.println(path);
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int getRowCount(int sheetno) {
        sheet = workbook.getSheetAt(sheetno);
        int number = sheet.getLastRowNum() + 1;
        return number;
    }


    public String getData(int sheetNo, int row, int col){
        String data;
        sheet = workbook.getSheetAt(sheetNo);
        data = sheet.getRow(row).getCell(col).getStringCellValue();
        return data;
    }


}
