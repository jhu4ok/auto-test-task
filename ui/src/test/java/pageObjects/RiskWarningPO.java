package pageObjects;

import lombok.Getter;

@Getter
public class RiskWarningPO {

    public final String textBlockLink = "//h5[contains(text(), '%s')]/following-sibling::p//a[text()='%s']";

}
