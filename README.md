# Teste Técnico AgiBank — Automação de Testes da Dog API

Este projeto foi desenvolvido como parte de um teste técnico para a AgiBank, com o objetivo de automatizar testes para a [Dog API](https://dog.ceo/dog-api/), utilizando Java, JUnit 5 e Rest Assured.

## Contexto do Desafio

O desafio solicitava a automação de testes para os seguintes endpoints da API:

- **GET /breeds/list/all** — Listar todas as raças de cães.
- **GET /breed/{breed}/images** — Listar todas as imagens de uma raça específica.
- **GET /breeds/image/random** — Obter uma imagem aleatória de qualquer raça.

Além dos endpoints obrigatórios, foram implementados testes adicionais para garantir maior cobertura e robustez da API, incluindo cenários de erro, validação de headers, sub-raças e performance.

## Pré-requisitos

- Java 17
- Gradle (ou utilize o wrapper incluso)
- Git

## Configuração do Projeto

1. **Clone o repositório:**
   ```sh
   git clone https://github.com/LauroFelipeQA/qa-automation-api-agi.git
   cd qa-automation-api-agi


## Detalhamento dos Testes

- **shouldListAllBreeds**  
  Verifica se a API retorna todas as raças disponíveis, validando o status da resposta e a presença da lista de raças.

- **shouldReturnImagesByBreed**  
  Testa se a API retorna imagens para uma raça específica (exemplo: "hound"), garantindo que a lista de imagens não esteja vazia.

- **shouldReturnRandomImage**  
  Valida se a API retorna uma imagem aleatória de qualquer raça, conferindo se a URL da imagem está presente e correta.

- **shouldListSubBreeds**  
  Verifica se a API retorna corretamente as sub-raças de uma raça específica (exemplo: "bulldog").

- **shouldReturnRandomImageBySubBreed**  
  Testa se a API retorna uma imagem aleatória de uma sub-raça específica (exemplo: "bulldog/french").

- **shouldReturnImagesBySubBreed**  
  Valida se a API retorna todas as imagens de uma sub-raça específica, garantindo que a lista não esteja vazia.

- **shouldReturnErrorForInvalidBreed**  
  Garante que a API retorna erro (status 404) ao buscar imagens de uma raça inexistente.

- **shouldValidateContentType**  
  Verifica se o cabeçalho `Content-Type` da resposta está correto (deve conter `application/json`).

- **shouldRespondWithinTimeLimit**  
  Testa se a API responde dentro do tempo limite de 3 segundos, falhando caso ultrapasse esse tempo.


## Dependências

As dependências são gerenciadas pelo Gradle e estão listadas no arquivo build.gradle:
JUnit Jupiter (JUnit 5)
Rest Assured

## Como Executar os Testes

Execute todos os testes com o Gradle:
    No Linux/Mac: ./gradlew test
    No Windows: gradlew.bat test

Os relatórios de execução estarão disponíveis em build/reports/tests/test/index.html.

## Estrutura do Projeto

src/test/java/tests/DogApiTest.java — Classe principal de testes.
src/test/java/api/ — Classes utilitárias para requisições à API.