package tests;

import api.BaseApi;
import api.BreedsApi;
import api.ImagesApi;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DogApiTest {

    // Instâncias das classes de API para uso nos testes
    BreedsApi breedsApi = new BreedsApi();
    ImagesApi imagesApi = new ImagesApi();

    /**
     * Testa se a API retorna todas as raças disponíveis.
     */
    @Test
    void shouldListAllBreeds() {
        breedsApi.listAllBreeds()
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", notNullValue());

        var breeds = BaseApi.response.jsonPath().getMap("message").keySet();
        System.out.println("Raças disponíveis: " + breeds);
    }

    /**
     * Testa se a API retorna imagens para uma raça específica.
     */
    @Test
    void shouldReturnImagesByBreed() {
        imagesApi.getImagesByBreed("hound")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", not(empty()));

        var images = BaseApi.response.jsonPath().getList("message");
        System.out.println("Imagens da raça: " + images);
    }

    /**
     * Testa se a API retorna uma imagem aleatória.
     */
    @Test
    void shouldReturnRandomImage() {
        imagesApi.getRandomImage()
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", containsString("https://images.dog.ceo/"));

        // Obtém e exibe a URL da imagem aleatória
        String imageUrl = BaseApi.response.jsonPath().getString("message");
        System.out.println("Imagem aleatória: " + imageUrl);
    }

    /**
     * Testa se a API retorna as sub-raças de uma raça específica.
     */
    @Test
    void shouldListSubBreeds() {
        breedsApi.listSubBreeds("bulldog")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", not(empty()));

        // Obtém e exibe as sub-raças
        var subBreeds = BaseApi.response.jsonPath().getList("message");
        System.out.println("Sub-raças de bulldog: " + subBreeds);
    }

    /**
     * Testa se a API retorna uma imagem aleatória de uma sub-raça.
     */
    @Test
    void shouldReturnRandomImageBySubBreed() {
        imagesApi.getRandomImageBySubBreed("bulldog", "french")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", containsString("https://images.dog.ceo/"));

        String imageUrl = BaseApi.response.jsonPath().getString("message");
        System.out.println("Imagem aleatória da sub-raça: " + imageUrl);
    }

    /**
     * Testa se a API retorna todas as imagens de uma sub-raça.
     */
    @Test
    void shouldReturnImagesBySubBreed() {
        imagesApi.getImagesBySubBreed("bulldog", "french")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", not(empty()));

        // Obtém e exibe as imagens da sub-raça
        var images = BaseApi.response.jsonPath().getList("message");
        System.out.println("Imagens da sub-raça: " + images);
    }

    /**
     * Testa se a API retorna erro ao buscar imagens de uma raça inexistente.
     */
    @Test
    void shouldReturnErrorForInvalidBreed() {
        imagesApi.getImagesByBreed("invalidBreed")
                .then()
                .statusCode(404)
                .body("status", equalTo("error"));
        System.out.println("Resposta para raça inválida: " + BaseApi.response.asString());

    }

    /**
     * Testa se o cabeçalho Content-Type da resposta está correto.
     */
    @Test
    void shouldValidateContentType() {
        imagesApi.getRandomImage()
                .then()
                .header("Content-Type", containsString("application/json"));
        System.out.println("Resposta para validação do Content-Type: " + BaseApi.response.asString());

    }

    /**
     * Testa se a API responde dentro do tempo limite de 2 segundos.
     * O teste falha se a resposta demorar mais que 3000 ms.
     */
    @Test
    void shouldRespondWithinTimeLimit() {
        long startTime = System.currentTimeMillis();

        imagesApi.getRandomImage()
                .then()
                .statusCode(200);

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Tempo de resposta: " + elapsedTime + " ms");
        assertTrue(elapsedTime < 3000, "A resposta demorou mais que 2 segundos");
    }
}
