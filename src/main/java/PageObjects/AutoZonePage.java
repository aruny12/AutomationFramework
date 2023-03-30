package PageObjects;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.autoZoneAutomationProject.Logger.LoggerHelper;
import com.autoZoneAutomationProject.TestBase.TestBase;
import com.autoZoneAutomationProject.TestUtils.TestUtils;

public class AutoZonePage extends TestBase {

	private final Logger log = LoggerHelper.getLogger(AutoZonePage.class);
	@FindBy(xpath = "//div[@id='menu_nav']/following::div[1]//button[@class='az_dob']")
	public WebElement autAddVeh;
	public TestUtils utils =null;
	
	public AutoZonePage(WebDriver driver) {
		WebDriver baseDriver = getDriver();
		driver = baseDriver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickautAddVehBtn() {
		log.info("Logging info: Waiting for the element to be clickable");
		waitForElement(driver, 10, autAddVeh);
		log.info("Logigng info: Clicking on the webElement");
		utils.clickElement(autAddVeh);
	}
	
	public void navigateToURL() throws IOException {
		log.info("Logging info: Waiting for the element to be clickable");
		gettingURL();
	}
	
}
