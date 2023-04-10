package client;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigsReader;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RedirectConfig.redirectConfig;

public class ApiClient {

    protected static final String URL = ConfigsReader.get().value("base.url");
    protected static final String API_PATH = "/api";

    public ApiClient() {
        RestAssured.config = new RestAssuredConfig()
                .encoderConfig(encoderConfig().defaultContentCharset(StandardCharsets.UTF_8))
                .redirect(redirectConfig().followRedirects(false))
                .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)
                                            .gsonObjectMapperFactory((cl, ch) -> new Gson()));
    }

    public Response sendGet(String endpoint) {
        return given()
                .when()
                .get(endpoint);
    }

    public Response sendGet(String endpoint, Map<String, String> params) {
        return given()
                .params(params)
                .when()
                .get(endpoint);
    }

    private RequestSpecification given() {
        return RestAssured.given().spec(new RequestSpecBuilder().setBaseUri(URL).setBasePath(API_PATH).build());
    }

}
