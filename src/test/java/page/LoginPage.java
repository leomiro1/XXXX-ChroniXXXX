package page;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import util.WebDriverUtils;
import util.PropertyManager;

public class LoginPage extends AbstractPage {

	WebDriverUtils util = new WebDriverUtils(driver);
	PropertyManager property;
	
	
	
	public LoginPage (WebDriver driver) {
		super(driver);
	}


	
	public HomePage LoginToHomePage(String dataUserName, String dataPassword) throws IOException {
		
		property = new PropertyManager();
		property.generateProperty();
		
		// elements of the page
		WebElement username =    driver.findElement(By.id(property.idUsernameFieldLogin));
		WebElement password =    driver.findElement(By.id(property.idPasswordFieldLogin));
		WebElement loginButton = driver.findElement(By.xpath(property.xpathLoginButton));
		
		// login procedure
		util.wait(1);
		username.clear();
		username.sendKeys(dataUserName);
		util.logInfo("Login - username inserted " + "\'" +dataUserName + "\'", driver);
		util.wait(1);
		password.clear();
		password.sendKeys(dataPassword);
		util.logInfo("Login - password inserted", driver);
		loginButton.click();
		util.logInfo("Login - login requested", driver);
		
		// return a HomePage object
		return PageFactory.initElements(driver, HomePage.class);
		
	}
}