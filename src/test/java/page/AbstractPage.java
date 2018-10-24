package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import util.PropertyManager;

public class AbstractPage {

	protected WebDriver driver;
	PropertyManager property;
	
	
	public AbstractPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getdriver() {
		return driver;
	}
	
	public LoginPage navigateToLogin() {
		
		property = new PropertyManager();
		property.generateProperty();
		
		driver.navigate().to(property.SiteURL);
		
		// return a LoginPage object
		return PageFactory.initElements(driver, LoginPage.class);
	}
}
