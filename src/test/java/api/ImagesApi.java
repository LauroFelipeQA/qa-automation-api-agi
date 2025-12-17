package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ImagesApi extends BaseApi {
    /**
     * Retorna todas as imagens de uma raça.
     */
    public Response getImagesByBreed(String breed) {
        response = RestAssured
                .given()
                .when()
                .get("/breed/{breed}/images", breed);
        return response;
    }

    /**
     * Retorna uma imagem aleatória.
     */
    public Response getRandomImage() {
        response = RestAssured
                .given()
                .when()
                .get("/breeds/image/random");
        return response;
    }

    /**
     * Retorna uma imagem aleatória de uma sub-raça.
     */
    public Response getRandomImageBySubBreed(String breed, String subBreed) {
        response = RestAssured
                .given()
                .when()
                .get("/breed/{breed}/{subBreed}/images/random", breed, subBreed);
        return response;
    }

    /**
     * Retorna todas as imagens de uma sub-raça.
     */
    public Response getImagesBySubBreed(String breed, String subBreed) {
        response = RestAssured
                .given()
                .when()
                .get("/breed/{breed}/{subBreed}/images", breed, subBreed);
        return response;
    }
}
