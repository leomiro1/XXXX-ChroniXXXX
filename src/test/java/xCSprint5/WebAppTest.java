package xCSprint5;

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
	
	
	/*
	@Test
	public void TC001XCheckComponentExistence() throws IOException {

		util.StartLoggin("TC001_Check component existence");
		
		util.logInfo("Login - Entering the application", driver);
		
		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		util.logInfo("Login - Begin", driver);
		
		// login
		HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		util.logInfo("Login - End", driver);
		
		// go to the specific report section
		homePage.GoToSection("Reports","ALL REPORTS");
		
		
		
		// INICIO DE FUNCION: TRES PARAMETROS DE ENTRADA -> reportToRevise, componentToRevise, webElementToRevise
		
		// check that a certain component is displayed in a list of reports and are able to use it
		//String reportsToRevise = "911 Calls,Abandoned Calls,Account Code Summary,Agent Call Cost,Agent Call Cost Summary,Agent Call Summary,Agent Call Volume,Agent Calls,Agent Event Summary,Agent Inbound Calls,Agent Inbound Summary,Agent Outbound Calls,Agent Outbound Summary,Agent Performance Summary,Agent Realtime Feature Trace,Agent Reason Code Trace,Agent Scorecard Summary - {Campaign},Agent Summary by Group,Agent Talking Summary,Agent Time Card,Agent Transfer Summary,Basic System Totals,Call Details,Call Details (Basic),Call Direction Summary,Calls by Account Code,Calls by Call Direction,Calls by Call Direction (New),Calls by Caller ID,Calls by Campaign - {Campaign},Calls by External Party,Campaign Summary,Conference Calls,Event Sequence Call List,Event Sequence Calls by Agent,Excessive {Event Type} by Agent,Excessive {Event Type} by Group,External Number Summary,Group Abandoned Calls,Group Event Summary,Group Presented Calls,Group Scorecard Summary - {Campaign},Group Summary,Group Summary by Agent,Inbound Call Performance,Inbound Call Performance(imported),Inbound Call Service Level,Inbound Call Summary,Inbound Caller ID Summary,Inbound Calls by Local Number,Inbound Group Summary,Local Number Inbound Summary,Lost Call Summary,Outbound Call Summary,Outbound Calls by External Party,QCB Port Utilization,Queue Summary by Group,Queued Call Volume,Queued Calls by Group,Roles Call Cost,Roles Call Cost Summary,Scores by Agent - {Campaign},Scores by Group - {Campaign},Tag Summary,Trunk Usage By Time,Trunk Usage Summary";		
		String reportsToRevise = "Group Event Summary,Group Scorecard Summary - {Campaign},Group Summary,Group Summary by Agent,Inbound Group Summary,QCB Port Utilization,Queue Summary by Group";
		String componentToRevise = "Report Timeframe"; //"Rows (Group)";
		//WebElement webElementToRevise = driver.findElement(By.xpath(property.xpathGroupAllIndicator));
		
		List<String>arrayReportsToRevise = Arrays.asList(reportsToRevise.split(","));
		CustomizeReportPage customizeReport;
		List<String> filtersAvailable;
		int reportCounter = 0;
		int hitCounter = 0;
		
		for (String report : arrayReportsToRevise) {
			
			// click on specific report on report list
			customizeReport = homePage.PickReportToExecute(report);
			util.logInfo("report customization of '" + report + "' is executed", driver);
			
			util.wait(2);
			
			// If the report was previously executed then the user needs to click configure button
			if (!(util.isElementDisplayed(driver.findElement(By.xpath("/html/body/app-root/app-home-container/app-home/mat-sidenav-container/mat-sidenav-content/div/app-reports-container/app-reports/app-run-report-container/app-run-report/mat-sidenav-container/mat-sidenav/app-configure-report-container/app-configure-report/div/mat-toolbar/span[2]"))))) {
				// click on "Configure" button
				WebElement configureButton = driver.findElement(By.xpath(property.xpathConfigureButton));
				configureButton.click();	
			}
			
			// return the available filters of the displayed report
			filtersAvailable = customizeReport.FiltersAvailable();
			
			// if the component is not available in report customization I log it and stop the execution
			if (!(filtersAvailable.contains(componentToRevise))) {
			    util.logError("TC001_Check component existence - FAILED - The component '" + componentToRevise + "' is not present in '" + report +"'. Please check the report");
			    Assert.fail("TC001_Check component existence - FAILED - The component '" + componentToRevise + "' is not present in '" + report +"'. Please check the report");
			}
			
			util.wait(2);
			
			// take the order of component
			int orden =  filtersAvailable.indexOf(componentToRevise);
			
			// Filter sections
			List <WebElement> filterSections = driver.findElements(By.xpath("//*[contains(@id, '" + "mat-tab-label" + "')]"));
				
			// if the component is "Report Timeframe" I need to choose the "Timeframe" section
			if (componentToRevise.equals("Report Timeframe")) { 
				// go to "Timeframe" section
				filterSections.get(0).click();
			
				// manipulate the component
				util.wait(2);
			
				//TimeFrame parameter webelement
				WebElement reportStartTime = driver.findElement(By.xpath("//*[@id=\"mat-tab-content-" + reportCounter + "-0\"]/div/div/app-timeframe-configure-report-parameter/app-report-timeline/form/div[2]/div[1]/span"));
				WebElement webElementToRevise = reportStartTime;	
			
				util.wait(2);
				
				// check that the webelement of the parameter exists inside the parameter component
				if (util.isElementDisplayed(webElementToRevise)) {
					util.logInfo("component of filter '" + componentToRevise + "' detected on report '" + report + "'", driver);
					hitCounter = hitCounter + 1;
				}
				else {
					util.logInfo("component of filter '" + componentToRevise + "' not detected on report '" + report + "'", driver);
				}
			
			}
			else {
				
				// go to "Others" section
				filterSections.get(1).click();
				
				// manipulate the component
				util.wait(2);
			
		        String componentDevice ="";
		        switch (componentToRevise) {
		            case "Agent":
		            case "Rows (Agent)":
		            case "Call Includes Agent":  componentDevice = "app-agents-roles-configure-report-preview-parameter";
		                     break;
		            case "Group":
		            case "Rows (Group)":
		            case "Call Includes Group":  componentDevice = "app-groups-configure-report-preview-parameter";
		                     break;
		            case "Account Code":
		            case "Rows (Account Code)":  componentDevice = "app-account-codes-configure-report-preview-parameter";
		                     break;
		            case "Reason Code":
		            case "Caller ID":
		            case "External Party":
		            case "Local Number":
		            case "Dialed Party Number":  componentDevice = "app-criteria-configure-report-preview-parameter";
		                     break;
		            case "Charts": 				 componentDevice = "app-charts-configure-report-preview-parameter";
		            		 break;
		        }
		        
				// click on the specific filter indicator
				driver.findElement(By.xpath("//*[@id=\"mat-tab-content-" + reportCounter + "-1\"]/div/div/div[" + orden + "]/app-configure-report-preview-parameter/" + componentDevice + "/div/div/div[2]/span[2]/mat-icon")).click();
								
				// manipulate the component
				util.wait(2);
				
				// header of the component
				WebElement webElementToRevise = driver.findElement(By.xpath("//*[@id=\"cdk-overlay-" + reportCounter + "\"]/mat-bottom-sheet-container/app-configure-report-parameter-container/app-configure-report-parameter/div/mat-toolbar/span[1]"));
							
				util.wait(2);
			
				// check that the header of the component exists inside the parameter component
				if (util.isElementDisplayed(webElementToRevise)) {
					util.logInfo("component of filter '" + componentToRevise + "' detected on report '" + report + "'", driver);
					hitCounter = hitCounter + 1;
					//close the component
					driver.findElement(By.xpath("//*[@id=\"cdk-overlay-" + reportCounter + "\"]/mat-bottom-sheet-container/app-configure-report-parameter-container/app-configure-report-parameter/div/mat-toolbar/button/span/mat-icon")).click();
				}
				else {
					util.logInfo("component of filter '" + componentToRevise + "' not detected on report '" + report + "'", driver);
				}
	
			}
				
			
			// if the filter is not included in the report parameters the report is logged for identification
			if (!filtersAvailable.contains(componentToRevise)) {
				util.logInfo("FILTER '" + componentToRevise + "' NOT DETECTED ON REPORT '" + report + "'", driver);
			}
			
			util.wait(2);
			
			// cancel the report
			driver.findElement(By.xpath("/html/body/app-root/app-home-container/app-home/mat-sidenav-container/mat-sidenav-content/div/app-reports-container/app-reports/app-run-report-container/app-run-report/mat-sidenav-container/mat-sidenav/app-configure-report-container/app-configure-report/div/div[2]/div[2]/button[1]/span")).click();

			util.wait(2);
			
			// return to report list
			driver.findElement(By.xpath("/html/body/app-root/app-home-container/app-home/mat-sidenav-container/mat-sidenav-content/div/app-reports-container/app-reports/app-run-report-container/app-run-report/mat-sidenav-container/mat-sidenav-content/app-run-report-toolbar/mat-toolbar/mat-toolbar-row/div[1]/div/div[1]/button/span/mat-icon")).click();
						
			util.wait(2);
			
			// increment report count
			reportCounter = reportCounter + 1;
			
			util.wait(2);
		}
				
		// in case there are reports that do not show the searched parameter
		int numberReportsToRevise = arrayReportsToRevise.size();
		int noMatch = numberReportsToRevise-hitCounter;
		
		
		// assert if the amount of hits is equal to the amounts of revised reports
		try {
				  Assert.assertTrue(hitCounter == arrayReportsToRevise.size());
			  	  util.logInfo("TC001_Check component existence - The list of reports specified contain the filter '" + componentToRevise  +"'", driver);
				  util.EndLoggin("TC001_Check component existence - PASSED");
			    } catch(NoSuchElementException nsee) {
			      util.logError("TC001_Check component existence - FAILED - There are " + noMatch + " reports that do not contain the filter '" + componentToRevise  + "'. Please check the log file");
			      Assert.fail("TC001_Check component existence - FAILED - There are " + noMatch + " reports that do not contain the filter '" + componentToRevise  + "'. Please check the log file");
			    } catch(AssertionError ae) {
			      util.logWarn("TC001_Check component existence - FAILED - There are " + noMatch + " reports that do not contain the filter '" + componentToRevise  + "'. Please check the log file");
			      Assert.fail("TC001_Check component existence - FAILED - There are " + noMatch + " reports that do not contain the filter '" + componentToRevise  + "'. Please check the log file");
			    }
		
	}
	*/
	
	
	@Test
	public void TC001XCCustomizeReport() throws IOException {

		String componentDevice = "";
		
		util.StartLoggin("TC001XCCustomizeReport");
		
		util.logInfo("Login - Entering the application", driver);
		
		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		util.logInfo("Login - Begin", driver);
		
		// login
		HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		util.logInfo("Login - End", driver);
		
		// go to the specific report section
		homePage.GoToSection("Reports","ALL REPORTS");
		
		// click on specific report on report list
		CustomizeReportPage customizeReport = homePage.PickReportToExecute("Outbound Calls by External Party"); //("Agent Reason Code Trace"); //("Agent Event Summary"); //("Excessive {Event Type} by Group"); //("Agent Summary by Group"); //("Account Code Summary"); //("Agent Reason Code Trace");//("Agent Call Volume"); //("Call Direction Summary"); //("Calls by Call Direction"); //("Agent Calls");
		
		util.wait(2);
		
		// If the report was previously executed then the user needs to click configure button
		if (!(util.isElementDisplayed(driver.findElement(By.xpath("/html/body/app-root/app-home-container/app-home/mat-sidenav-container/mat-sidenav-content/div/app-reports-container/app-reports/app-run-report-container/app-run-report/mat-sidenav-container/mat-sidenav/app-configure-report-container/app-configure-report/div/mat-toolbar/span[2]"))))) {
			// click on "Configure" button
			WebElement configureButton = driver.findElement(By.xpath(property.xpathConfigureButton));
			configureButton.click();	
		}
		
		util.wait(2);
		
		// Filter sections
		List <WebElement> filterSections = driver.findElements(By.xpath("//*[contains(@id, '" + "mat-tab-label" + "')]"));
		
		// return the available filters of the displayed report
		List<String> filtersAvailable = customizeReport.FiltersAvailable();
		
		util.logInfo("Customize Report - Filters available on report to execute: " + filtersAvailable, driver);
		
		// for every detected filter of the report there should be a method to fill it
		for (String filter : filtersAvailable) {
			
			util.wait(2);
			
			// click on the specific filter indicator
			int orden =  filtersAvailable.indexOf(filter);
			
			// customize timeframe component
			if (filter.equals("Report Timeframe")) {
				// go to "Timeframe" section
				filterSections.get(0).click();
				customizeReport.FillTimeframe("4/01/2018", "4/07/2018");
				//customizeReport.FillTimeframe("3/21/2018", "9/19/2018", "12:05:33", "19:31:22", "14:33","19:55", "Night","Mo,We,Su");
			}
			else {
				
				// go to "Others" section
				filterSections.get(1).click();
			
				util.wait(2);
				
				// customize agent component
				if (filter.equals("Agent") || filter.equals("Rows (Agent)") || filter.equals("Call Includes Agent")) {
				
					componentDevice = "app-agents-roles-configure-report-preview-parameter";
					// click on the specific filter indicator
					driver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-1\"]/div/div/div[" + orden + "]/app-configure-report-preview-parameter/" + componentDevice + "/div/div/div[2]/span[2]/mat-icon")).click();
				
					//String agentsAndRolesList = "Agents:All";
					//String agentsAndRolesList = "Roles:All";
					String agentsAndRolesList = "Agents:Adriana Taylor,Alexandra Burns,Alicia Ford";
					//String agentsAndRolesList = "Roles:Billing,Provider";
					customizeReport.FillAgentAndRoles(agentsAndRolesList);
				
					customizeReport.applyFilter();
				}
			
				// customize rate label component
				if (filter.equals("Rate Label")) {
					//customizeReport.FillRateLabel(); <- METHOD TO BUILD
				}
			
				// customize call cost profile component
				if (filter.equals("Call Cost Profile")) {
					//customizeReport.FillCallCostProfile(); <- METHOD TO BUILD
				}
			
				// customize group component
				if (filter.equals("Group") || filter.equals("Rows (Group)") || filter.equals("Call Includes Group")) {
				
					componentDevice = "app-groups-configure-report-preview-parameter";
					// click on the specific filter indicator
					driver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-1\"]/div/div/div[" + orden + "]/app-configure-report-preview-parameter/" + componentDevice + "/div/div/div[2]/span[2]/mat-icon")).click();
				
					//customizeReport.FillGroup("All");
					//customizeReport.FillGroup("Provider");
					customizeReport.FillGroup("Appointments,Billing,Customer Service");
				
					customizeReport.applyFilter();
				}
			
				// customize account code component
				if (filter.equals("Account Code") || filter.equals("Rows (Account Code)")) {
				
					componentDevice = "app-account-codes-configure-report-preview-parameter";
					// click on the specific filter indicator
					driver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-1\"]/div/div/div[" + orden + "]/app-configure-report-preview-parameter/" + componentDevice + "/div/div/div[2]/span[2]/mat-icon")).click();
				
					customizeReport.FillAccountCodes("Pull From Database");
					//String accountCodeList = "Company A, Company D, Company F";
					//customizeReport.FillAccountCodes("Pre-Configured Account Codes", accountCodeList);
				
					customizeReport.applyFilter();		
				}
			
				// customize rows (time) component
				if (filter.equals("Rows (Time)")) {
					//customizeReport.FillRowsTime(); <- METHOD TO BUILD
				}
			
				// customize reason code component
				if (filter.equals("Reason Code") || filter.equals("Caller ID") || filter.equals("External Party") || filter.equals("Local Number") || filter.equals("Dialed Party Number")) {
				
					componentDevice = "app-criteria-configure-report-preview-parameter";
					// click on the specific filter indicator
					driver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-1\"]/div/div/div[" + orden + "]/app-configure-report-preview-parameter/" + componentDevice + "/div/div/div[2]/span[2]/mat-icon")).click();
				
					String reasonCodeList= "Include ANY:Starts With-911,Contains-123";
					//String reasonCodeList= "Include ANY:Contains-123";
					customizeReport.FillReasonCode(reasonCodeList);
				
					customizeReport.applyFilter();
				}
			
				// customize report skin component
				if (filter.equals("Report Skin")) {
				
					String skin = "Xima Default"; // "Xima Classic";
					customizeReport.FillReportSkin(skin);
				}
			}
		}
		/*		
		// run the report
		//customizeReport.RunReport(filtersAvailable);
		ReportResultsPage reportResultsPage = customizeReport.RunReport();
		
		util.wait(10);
		
		reportResultsPage.ShowResultsInConsole();
		
		
  	   	// assert if the report was executed checking if the "Last time run" section is shown in screen
		try {
				  WebElement lastTimeRunSection=driver.findElement(By.xpath(property.xpathLastTimeRunSection));
				  Assert.assertTrue(lastTimeRunSection.isDisplayed());
			  	  util.logInfo("TC001XCCustomizeReport - The report customization was successfull and the report was executed", driver);
				  util.EndLoggin("TC001XCCustomizeReport - PASSED");
			    } catch(NoSuchElementException nsee) {
			      util.logError("TC001XCCustomizeReport - FAILED - There has been an error in the report customization. Please check the input data");
			      Assert.fail("TC001XCCustomizeReport - FAILED - There has been an error in the report customization. Please check the input data");
			    } catch(AssertionError ae) {
			      util.logWarn("TC001XCCustomizeReport - FAILED - There has been an error in the report customization. Please check the input data");
			      Assert.fail("TC001XCCustomizeReport - FAILED - There has been an error in the report customization. Please check the input data");
			    }
		*/
	}
	
		 	
}
