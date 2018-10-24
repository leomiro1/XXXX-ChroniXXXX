package util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import util.PropertyManager;

public class WebDriverUtils {

	WebDriver driver;
	PropertyManager property;
	
	// constructor
	public WebDriverUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	// take the screenshot
	// and leave the photo in a folder
	public void takeScreenShot(String screenShotName, WebDriver driverBrowser) throws IOException {
		
		property = new PropertyManager();
		property.generateProperty();
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa_");
		String dateFile = dateFormat.format(new Date());
		
		File screenShotFile = ((TakesScreenshot)driverBrowser).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenShotFile, new File(property.ScreenshotsPath + dateFile + screenShotName + ".png"));
	}
	
	// wait for a set amount of time
	// and I should be able to set the time I want
	public void wait (int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// So I can know if a webelement
	// is displayed in the application	
	public boolean isElementDisplayed(WebElement element) {
	    try {
	         return element.isDisplayed();
	        } catch(NoSuchElementException e) {
	         return false;
	    }
	}

	// initiate the configuration settings
	// for loggin of specific test cases
	public void StartLoggin(String TestCase) {
		// Provide Log4j configuration settings
		DOMConfigurator.configure("log4j.xml");
		logStartTestCase(TestCase);
	}
	
	// end the configuration settings
	// for loggin of specific test cases
	public void EndLoggin(String TestCase) {
		// Provide Log4j configuration settings
		logEndTestCase(TestCase);
	}
	

	////////////////////////// LOG FUNCTIONS /////////////////////////////////////
	
	// Initialize Log4j logs
	private static Logger Log = Logger.getLogger(WebDriverUtils.class.getName());

    
    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
    public static void logStartTestCase(String sTestCaseName){
 
 		 Log.info("****************************************************************************************");
 		 Log.info("****************************************************************************************");
 		 Log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
 		 Log.info("****************************************************************************************");
 		 Log.info("****************************************************************************************");
	}
 
	//This is to print log for the ending of the test case
 	public static void logEndTestCase(String sTestCaseName){
 		Log.info("XXXXXXXXXXXXXXXXXXXXXXX "+"-E---N---D-"+" "+sTestCaseName+ " XXXXXXXXXXXXXXXXXXXXXX");
 		Log.info("X");
 		Log.info("X");
 		Log.info("X");
 		Log.info("X");
 	}
 
	// Need to create these methods, so that they can be called  
 	 public void logInfo(String message, WebDriver driver) throws IOException {
 		WebDriverUtils util = new WebDriverUtils(driver);
 		 Log.info(message);
 		 util.takeScreenShot(message, driver);
 	}
 
 	 public void logWarn(String message) {
 	    Log.warn(message);
 		Log.info("X");
 		Log.info("X");
 	}
 
 	 public void logError(String message) {
 	    Log.error(message);
 		Log.info("X");
 		Log.info("X");
 		Log.info("X");
 		Log.info("X");
 	}
 
 	 public static void fatal(String message) {
 	    Log.fatal(message);
 	}
 
 	 public static void debug(String message) {
	 	Log.debug(message);
 	}

	
	
	
	
	
	
}
