package xCSprint1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import util.PropertyManager;

public class AbstractTestDriver {

	protected WebDriver driver;
	PropertyManager property;
	private static boolean setUpLogIsDone = false;
	
	@Before
	public void setUpLog() {
	    if (setUpLogIsDone == true) {
	        return;
	    }
	    
		// settings log name
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
        Date date = new Date();
		System.setProperty("log_name", "XCSprint1_" + dateFormat.format(date) + ".log");
	    
	    setUpLogIsDone = true;
	}
	
	@Before
	public void testStartUp() {
		
		property = new PropertyManager();
		property.generateProperty();
				
		// create an instance of Webdriver
		//System.setProperty("webdriver.gecko.driver", property.FirefoxdriverPath);
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver",property.ChromedriverPath);
		ChromeOptions chromeOptions = new ChromeOptions();
    	chromeOptions.addArguments("--start-maximized");
		driver = new ChromeDriver(chromeOptions);
	}
	
	
	@After
	public void testShutDown() {
		// close the web page
		driver.quit();
	}
	
}
