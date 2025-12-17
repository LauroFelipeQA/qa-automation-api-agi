package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BreedsApi extends BaseApi {
    /**
     * Retorna todas as raças.
     */
    public Response listAllBreeds() {
        response = RestAssured
                .given()
                .when()
                .get("/breeds/list/all");
        return response;
    }

    /**
     * Retorna as sub-raças de uma raça específica.
     */
    public Response listSubBreeds(String breed) {
        response = RestAssured
                .given()
                .when()
                .get("/breed/{breed}/list", breed);
        return response;
    }
}
