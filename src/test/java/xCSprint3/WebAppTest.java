package xCSprint3;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import util.WebDriverUtils;
import page.LoginPage;
import page.HomePage;
import page.CustomizeReportPage;
import page.ReportResultsPage;

public class WebAppTest extends AbstractTestDriver {

	WebDriverUtils util = new WebDriverUtils(driver);
	
	
	@Test
	public void TC001XListReportsByComponent() throws IOException {

		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		// login
		HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		// go to the specific report section
		homePage.GoToSection("Reports","ALL REPORTS");
		
		
		
		// INICIO DE FUNCION: DOS PARAMETROS DE ENTRADA -> listofreports, componentToRevise
		
		// check that a certain component is displayed in a list of reports and are able to use it
		//String listofreports = "911 Calls,Abandoned Calls,Account Code Summary,Agent Call Cost,Agent Call Cost Summary,Agent Call Summary,Agent Call Volume,Agent Calls,Agent Event Summary,Agent Inbound Calls,Agent Inbound Summary,Agent Outbound Calls,Agent Outbound Summary,Agent Performance Summary,Agent Realtime Feature Trace,Agent Reason Code Trace,Agent Scorecard Summary - {Campaign},Agent Summary by Group,Agent Talking Summary,Agent Time Card,Agent Transfer Summary,Basic System Totals,Call Details,Call Details (Basic),Call Direction Summary,Calls by Account Code,Calls by Call Direction,Calls by Call Direction (New),Calls by Caller ID,Calls by Campaign - {Campaign},Calls by External Party,Campaign Summary,Conference Calls,Event Sequence Call List,Event Sequence Calls by Agent,Excessive {Event Type} by Agent,Excessive {Event Type} by Group,External Number Summary,Group Abandoned Calls,Group Event Summary,Group Presented Calls,Group Scorecard Summary - {Campaign},Group Summary,Group Summary by Agent,Inbound Call Performance,Inbound Call Performance(imported),Inbound Call Service Level,Inbound Call Summary,Inbound Caller ID Summary,Inbound Calls by Local Number,Inbound Group Summary,Local Number Inbound Summary,Lost Call Summary,Outbound Call Summary,Outbound Calls by External Party,QCB Port Utilization,Queue Summary by Group,Queued Call Volume,Queued Calls by Group,Roles Call Cost,Roles Call Cost Summary,Scores by Agent - {Campaign},Scores by Group - {Campaign},Tag Summary,Trunk Usage By Time,Trunk Usage Summary";	
		String listofreports = "911 Calls,Abandoned Calls,Account Code Summary";
		String componentToRevise = "Charts";
	
		List<String>arrayReportsToRevise = Arrays.asList(listofreports.split(","));
		CustomizeReportPage customizeReport;
		List<String> filtersAvailable;
		
		System.out.println("Reports with the component: " +componentToRevise);
		System.out.println("***************************");
		
		for (String report : arrayReportsToRevise) {
			
			// click on specific report on report list
			customizeReport = homePage.PickReportToExecute(report);
			
			util.wait(2);
			
			// If the report was previously executed then the user needs to click configure button
			if (!(util.isElementDisplayed(driver.findElement(By.xpath("/html/body/app-root/app-home-container/app-home/mat-sidenav-container/mat-sidenav-content/div/app-reports-container/app-reports/app-run-report-container/app-run-report/mat-sidenav-container/mat-sidenav/app-configure-report-container/app-configure-report/div/mat-toolbar/span[2]"))))) {
				// click on "Configure" button
				WebElement configureButton = driver.findElement(By.xpath(property.xpathConfigureButton));
				configureButton.click();	
			}
			
			// return the available filters of the displayed report
			filtersAvailable = customizeReport.FiltersAvailable();
			
			// if the filter is included in the report parameters then the report is printed in console
			if (filtersAvailable.contains(componentToRevise)) {
				System.out.println("," + report);
			}
				
			// cancel the report
			driver.findElement(By.xpath("/html/body/app-root/app-home-container/app-home/mat-sidenav-container/mat-sidenav-content/div/app-reports-container/app-reports/app-run-report-container/app-run-report/mat-sidenav-container/mat-sidenav/app-configure-report-container/app-configure-report/div/div[2]/div[2]/button[1]/span")).click();

			util.wait(2);
			
			// return to report list
			driver.findElement(By.xpath("/html/body/app-root/app-home-container/app-home/mat-sidenav-container/mat-sidenav-content/div/app-reports-container/app-reports/app-run-report-container/app-run-report/mat-sidenav-container/mat-sidenav-content/app-run-report-toolbar/mat-toolbar/mat-toolbar-row/div[1]/div/div[1]/button/span/mat-icon")).click();
			
			util.wait(2);
		}
		
	}
	 	
}
