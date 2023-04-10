package stepDefinitions;

import actions.StarshipsAPI;
import dto.response.PeopleResponseDTO;
import dto.response.StarshipResponseDTO;
import hooks.Hook;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StarshipsApiSteps extends BaseSteps {

    private final StarshipsAPI starshipsAPI;

    public StarshipsApiSteps() {
        super(Hook.getContext());
        starshipsAPI = new StarshipsAPI();
    }

    @When("User searches starship for character {string} and save data in context with reference {string}")
    public void searchStarshipForCharacterString(String characterReference, String starshipDataReference) {
        PeopleResponseDTO people = (PeopleResponseDTO) context.getContext(characterReference);
        context.setContext(starshipDataReference, starshipsAPI.sendGet(people.getStarships().get(0))
                .as(StarshipResponseDTO.class));
    }

    @Then("Starship {string} class is {string}")
    public void starshipClassIs(String starshipDataReference, String expectedResult) {
        StarshipResponseDTO starshipResponseData = (StarshipResponseDTO) context.getContext(starshipDataReference);
        assertThat(starshipResponseData.getStarshipClass())
                .as("Starship class is unexpected")
                .isEqualTo(expectedResult);
    }

    @Then("Starship {string} pilots list contains")
    public void starshipPilotsListContains(String starshipDataReference, List<String> expectedResult) {
        StarshipResponseDTO starshipResponseData = (StarshipResponseDTO) context.getContext(starshipDataReference);
        List<String> actualResult = starshipResponseData.getPilots().stream()
                .map(pilotUrl -> starshipsAPI.sendGet(pilotUrl).as(PeopleResponseDTO.class).getName()).toList();
        assertThat(actualResult)
                .as("Starship pilots list is unexpected")
                .containsAll(expectedResult);
    }

}
