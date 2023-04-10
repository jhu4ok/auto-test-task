package pageActions;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import pageObjects.RiskWarningPO;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

@Getter
public class RiskWarningPage extends BasePage {

    public static final String RISK_WARNING_ENDPOINT = "risk_warning";
    private final RiskWarningPO riskWarningPO = new RiskWarningPO();

    public RiskWarningPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return getCurrentUrl().endsWith(RISK_WARNING_ENDPOINT);
    }

    public void clickTextLink(String textBlock, String linkText) {
        clickOnUsingJS(xpath(format(riskWarningPO.getTextBlockLink(), textBlock, linkText)));
    }

}
