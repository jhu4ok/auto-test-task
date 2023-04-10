package actions;

import client.ApiClient;
import io.restassured.response.Response;

public class BaseAPI {

    protected final ApiClient client = new ApiClient();

    public Response sendGet(String characterUrl) {
        return client.sendGet(characterUrl);
    }

}
