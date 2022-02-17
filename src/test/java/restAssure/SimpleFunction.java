package restAssure;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SimpleFunction {

    String url = "https://api.stripe.com/v1/customers";
    String code= "sk_test_51KTT9cSEOCWRCO875SUsrU1AqhUPHbcd7fB2P6O9el645bCuxvYj"+
            "YWhxqmJgKf293ZEHYOCqdA3V52VH1sKbEWFw002FaiKyPi";
    String path = System.getProperty("user.dir") + "\\Resorces\\Data.xlsx";
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;

    @Test
    public void enterData() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("Resorces\\help.properties"));
        Response resp = given().auth().basic(code, "").formParam("email", prop.getProperty("email")).
                formParam("name",prop.getProperty("name")).when().post(url);
        int respcode = resp.getStatusCode();
        System.out.println("Responce code is: "+respcode);
        String responsebody=resp.getBody().asString();
        System.out.println(responsebody);
    }
        @DataProvider (name = "data")
        public Object[][] getdata() throws IOException {
//            FileReader file = new FileReader(path);
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            int row = sheet.getLastRowNum();
            Object[][] arr = new Object[row][5];
            int a = 0;
            for (int i = 1; i <= row; i++) {
                arr[a][0] = sheet.getRow(i).getCell(0).getStringCellValue();
                arr[a][1] = sheet.getRow(i).getCell(1).getStringCellValue();
                arr[a][2] = sheet.getRow(i).getCell(2).getStringCellValue();
                arr[a][3] = i;
                arr[a][4] = 3;
                a++;
            }
        return arr;
        }

        @Test(dataProvider = "data")
        public void enterDataExcel (String email, String name, String desc, int i, int j) throws IOException {
            System.out.println(email+ " " + name + " " + desc + " " + i + " " + j);
            Response resp = given().auth().basic(code, "").formParam("email", email).
                formParam("name", name).formParam("description",desc).when().post(url);
        int respcode = resp.getStatusCode();
        System.out.println("Responce code is: " + respcode);
        String responsebody = resp.getBody().asString();
        System.out.println(responsebody);
        fileOut = new FileOutputStream(path);
        sheet.getRow(i).createCell(j).setCellValue(respcode);
        workbook.write(fileOut);
    }

    @Test (dependsOnMethods = "enterDataExcel")
    void simpleData() throws IOException {
        workbook.close();
    }
}
