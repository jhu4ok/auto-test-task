package hooks;

import io.cucumber.java.Before;
import utils.ScenarioContext;

public class Hook {

    private static ScenarioContext context;

    @Before
    public void setUp() {
        context = new ScenarioContext();
    }

    public static ScenarioContext getContext() {
        return context;
    }

}
