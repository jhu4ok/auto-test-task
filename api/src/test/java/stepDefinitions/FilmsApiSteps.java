package stepDefinitions;

import actions.FilmsAPI;
import hooks.Hook;
import io.cucumber.java.en.When;

import java.util.Map;

public class FilmsApiSteps extends BaseSteps {

    private final FilmsAPI filmsAPI;

    public FilmsApiSteps() {
        super(Hook.getContext());
        filmsAPI = new FilmsAPI();
    }

    @When("User sends {string} request to search film with a title")
    public void sendSearchFilmRequest(String requestReference, Map<String, String> parameters) {
        context.setContext(requestReference, filmsAPI.searchFilm(parameters));
    }

}
