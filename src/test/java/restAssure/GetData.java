package restAssure;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetData {

    @Test
    public void GetRequest(){
        String code= "sk_test_51KTT9cSEOCWRCO875SUsrU1AqhUPHbcd7fB2P6O9el645bCuxvYj"+
                "YWhxqmJgKf293ZEHYOCqdA3V52VH1sKbEWFw002FaiKyPi";
        RestAssured.baseURI = "https://api.stripe.com/";
        RequestSpecification httprequest = RestAssured.given().auth().basic(code, "");
        Response response = httprequest.request(Method.GET,"v1/customers");
        String responsebody=response.getBody().asString();

        //Print Response
        System.out.println("Response Body is:"+responsebody);

        //Validate Status code
        int statusCode=response.getStatusCode();
        System.out.println("Status Code is:"+statusCode);
        Assert.assertEquals(statusCode, 200);

        String statusline=response.getStatusLine();
        System.out.println("Status line is:"+statusline);
        Assert.assertEquals(statusline, "HTTP/1.1 200 OK");

    }
}
