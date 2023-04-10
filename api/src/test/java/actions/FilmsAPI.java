package actions;

import io.restassured.response.Response;

import java.util.Map;

public class FilmsAPI extends BaseAPI {

    private static final String FILMS = "/films";

    public Response searchFilm(Map<String, String> parameters) {
        return client.sendGet(FILMS, parameters);
    }

}
