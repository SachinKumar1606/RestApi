package RestAssureFramework.Test;

import RestAssureFramework.BaseMethods.Base;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static RestAssureFramework.BaseMethods.Base.logger;
import static RestAssureFramework.BaseMethods.Base.response;

public class BasicTest extends Base {

        @Test
        public void checkAllUsers() {
                getResponseBody("/v1/customers");
        }
        @Test
        public void checkCode(){
                checkStatusCode("/v1/customers");
        }

}
