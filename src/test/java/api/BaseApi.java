package api;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public abstract class BaseApi {
    static {
        RestAssured.baseURI = "https://dog.ceo/api";
        RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
    }
    public static Response response;

}
