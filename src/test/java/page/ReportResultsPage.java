package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import util.PropertyManager;
import util.WebDriverUtils;

public class ReportResultsPage extends CustomizeReportPage {

	WebDriverUtils util = new WebDriverUtils(driver);
	PropertyManager property;
	
		
	public ReportResultsPage (WebDriver driver) {
		super(driver);
	}

	public void ShowResultsInConsole () {
		
		String contentReport = "";
		
		List <WebElement> content = driver.findElements(By.tagName("mat-table"));
		
		for (WebElement table : content) {
			//System.out.println(table.getText());
			contentReport = contentReport.concat(table.getText());
		}
		
		System.out.println(contentReport);
	}
	
}
