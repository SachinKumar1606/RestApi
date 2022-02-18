package RestAssureFramework.BaseMethods;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

public class Base {

    public static RequestSpecification httpRequest;
    public static Response response;
    public static Logger logger;

    String code = "sk_test_51KTT9cSEOCWRCO875SUsrU1AqhUPHbcd7fB2P6O9el645bCuxvYj" +
            "YWhxqmJgKf293ZEHYOCqdA3V52VH1sKbEWFw002FaiKyPi";

    @BeforeSuite
    public void initiate() {
        logger = Logger.getLogger("RestAssuredAPITesting_StripeProject");
        PropertyConfigurator.configure("src/test/java/RestAssureFramework/Resorces/log4j.properties");
        logger.setLevel(Level.DEBUG);
    }

    private Response setup(String extend) {
        logger.info("**************Started TC001_Get_All_Customers*************");

        RestAssured.baseURI = "https://api.stripe.com";
        // Basic Authentication
        PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
        authscheme.setUserName(code);
        authscheme.setPassword("");
        RestAssured.authentication = authscheme;
        // Request Object
        httpRequest = RestAssured.given();
        // Response Object
        response = httpRequest.request(Method.GET, extend);
        return response;
    }

    public void getResponseBody(String ext) {
        logger.info("********** Checking the response Body **********");
        response = setup(ext);
        String responseBody = response.getBody().asString();
        logger.info("Response Body=>" + responseBody);
        Assert.assertTrue(responseBody != null);
    }

    public void checkStatusCode(String ext){
        logger.info("********** Checking the response Code **********");
        response = setup(ext);
        int responseStatusCode = response.getStatusCode();
        logger.info("Status Code is => " + responseStatusCode);
        if(responseStatusCode == 200){
            logger.info("Query processed sucessfully......");
        }
        else{
            logger.error("Query do not process....");
        }
    }

    public void checkingResponseTime(String ext){
        response = setup(ext);
        logger.info("********** Checking the response time **********");
        long responseTime=response.getTime();
        logger.info("Response Time is:"+responseTime);

        if(responseTime>2000)
            logger.warn("Response time is greater then 2000");
        Assert.assertTrue(responseTime>2000);
    }
}
