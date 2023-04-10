package stepDefinitions;

import utils.ScenarioContext;

public class BaseSteps {

    protected static ScenarioContext context;

    public BaseSteps(ScenarioContext context) {
        BaseSteps.context = context;
    }

}
