package stepDefinitions;

import actions.PeopleAPI;
import dto.response.FilmResponseDTO;
import dto.response.PeopleResponseDTO;
import hooks.Hook;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class PeopleApiSteps extends BaseSteps {

    private final PeopleAPI peopleAPI;

    public PeopleApiSteps() {
        super(Hook.getContext());
        peopleAPI = new PeopleAPI();
    }

    @When("User searches person with name {string} among the characters for response {string}")
    public void searchesPersonWithNameAmongTheCharactersForResponse(String expectedName, String responseReference) {
        Response filmResponse = (Response) context.getContext(responseReference);
        FilmResponseDTO filmResponseData = filmResponse.as(FilmResponseDTO.class);
        for (String characterUrl : filmResponseData.getResults().get(0).getCharacters()) {
            Response personResponse = peopleAPI.sendGet(characterUrl);
            PeopleResponseDTO people = personResponse.as(PeopleResponseDTO.class);
            if (people.getName().equals(expectedName)) {
                context.setContext(expectedName, people);
                break;
            }
        }
    }

}
