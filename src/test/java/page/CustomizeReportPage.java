package page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import util.PropertyManager;
import util.WebDriverUtils;

public class CustomizeReportPage extends HomePage {

	WebDriverUtils util = new WebDriverUtils(driver);
	PropertyManager property;
	
	
	
	public CustomizeReportPage (WebDriver driver) {
		super(driver);
	}
	
	
	
	public List<String> FiltersAvailable() {
		
		property = new PropertyManager();
		property.generateProperty();
		
		List<String> filtersAvailable = new ArrayList<String>();
		List<String> filtersAvailable2 = new ArrayList<String>();
		List<String> filtersAvailableAux = new ArrayList<String>();
		String filter = "";
		
		// add TIMEFRAME filter as first filter because appears in every report
		filtersAvailable.add("Report Timeframe");

		util.wait(2);
		
		// go to "Others" section
		List <WebElement> filterSections = driver.findElements(By.xpath("//*[contains(@id, '" + "mat-tab-label" + "')]"));
		filterSections.get(1).click();
		
			
		try {
				//17/05/2018: I CANT UNDERSTAND WHY THE METHOD IS NOT TAKING THE PROPERTIES PROVOKING A NULL POINTER EXCEPTION
				//WebElement filterNameObject = driver.findElement(By.xpath(property.xpathReportFilterNameHead + "[" + cont + "]" + property.xpathReportFilterNameTail));
				
				//WebElement filterNameObject = driver.findElement(By.xpath("/html/body/app-root/app-home-container/app-home/mat-sidenav-container/mat-sidenav-content/app-configure-report-container/app-configure-report/div/div/div[1]/mat-list/mat-list-item[" + cont + "]/div/div[2]/span"));
				List <WebElement> filterNameObject = driver.findElements(By.xpath("//*[contains(@id, '" + "mat-tab-content" + "')]"));
			
				if (filterNameObject.get(1).isDisplayed()) {
					filter = filterNameObject.get(1).getText().replaceAll("\n", ",");
					filtersAvailable.add(filter);
				}
				else {
					//filtersAvailable.add("0");
				}
			} catch(NoSuchElementException e) {
			}
			
		
		// list of parameters
		String listString = String.join(", ", filtersAvailable);
		filtersAvailable2 = Arrays.asList(listString.split(","));
		
		
		// suppress all the elements that are not parameters
		for (String s : filtersAvailable2) {
			if(property.dataReportParametersAvailable.contains(s.trim().replace("*", ""))) {
				filtersAvailableAux.add(s.trim().replace("*", ""));
			}
		}
		
		// go to "Timeframe" section before exiting report customization
		filterSections.get(0).click();

		return filtersAvailableAux;
	}
	
	
	
	public void FillTimeframe(String startDate, String endDate) throws IOException {
		
		property = new PropertyManager();
		property.generateProperty();
		
		util.wait(2);
		
		// fill start date and end date fields
		WebElement startDateField = driver.findElement(By.cssSelector(property.cssStartDate));
		startDateField.clear();
		startDateField.sendKeys(startDate);
		
		WebElement endDateField = driver.findElement(By.cssSelector(property.cssEndDate));
		endDateField.clear();
		endDateField.sendKeys(endDate);
		
		util.logInfo("Customize Report - Timeframe set OK", driver);
		
	}
	
	
	
	public void FillTimeframe(String startDate, String endDate, String reportStartTime, String reportEndTime, String TimeOfDayFrom, String TimeOfDayTo, String shift, String daysOfWeek) throws IOException {
		
		property = new PropertyManager();
		property.generateProperty();
		
		util.wait(2);
		
		// fill start date and end date fields
		WebElement startDateField = driver.findElement(By.cssSelector(property.cssStartDate));
		startDateField.clear();
		startDateField.sendKeys(startDate);
		
		WebElement endDateField = driver.findElement(By.cssSelector(property.cssEndDate));
		endDateField.clear();
		endDateField.sendKeys(endDate);
		
		util.logInfo("Customize Report - Timeframe set OK", driver);
		
		//slide to enter advanced section
		//driver.findElement(By.xpath(property.xpathTimeframeSlideAdvanced)).click();
		
		//fill fields of advanced section - Report start and end time
		List<String>arrayReportStartTime = Arrays.asList(reportStartTime.split(":"));
		List<String>arrayReportEndTime = Arrays.asList(reportEndTime.split(":"));
		
		if ((arrayReportStartTime.size() != 1) && (arrayReportEndTime.size() != 1)) {
			WebElement ReportStartTimeHour= driver.findElement(By.cssSelector(property.cssReportStartTimeHour));
			WebElement ReportStartTimeMinute= driver.findElement(By.cssSelector(property.cssReportStartTimeMinute));
			WebElement ReportStartTimeSeconds= driver.findElement(By.cssSelector(property.cssReportStartTimeSeconds));
			WebElement ReportEndTimeHour= driver.findElement(By.cssSelector(property.cssReportEndTimeHour));
			WebElement ReportEndTimeMinute= driver.findElement(By.cssSelector(property.cssReportEndTimeMinute));
			WebElement ReportEndTimeSeconds= driver.findElement(By.cssSelector(property.cssReportEndTimeSeconds));
		
			ReportStartTimeHour.clear();
			ReportStartTimeHour.sendKeys(arrayReportStartTime.get(0));
			ReportStartTimeMinute.clear();
			ReportStartTimeMinute.sendKeys(arrayReportStartTime.get(1));
			ReportStartTimeSeconds.clear();
			ReportStartTimeSeconds.sendKeys(arrayReportStartTime.get(2));
			ReportEndTimeHour.clear();
			ReportEndTimeHour.sendKeys(arrayReportEndTime.get(0));
			ReportEndTimeMinute.clear();
			ReportEndTimeMinute.sendKeys(arrayReportEndTime.get(1));
			ReportEndTimeSeconds.clear();
			ReportEndTimeSeconds.sendKeys(arrayReportEndTime.get(2));
			
		}
		
		// fill fields of advanced section - Time of day
		List<String>arrayTimeOfDayFrom = Arrays.asList(TimeOfDayFrom.split(":"));
		List<String>arrayTimeOfDayTo = Arrays.asList(TimeOfDayTo.split(":"));
		
		if ((arrayTimeOfDayFrom.size() != 1) && (arrayTimeOfDayTo.size() != 1)) {
			WebElement TimeOfDayHourFrom= driver.findElement(By.cssSelector(property.cssTimeOfDayHourFrom));
			WebElement TimeOfDayMinuteFrom= driver.findElement(By.cssSelector(property.cssTimeOfDayMinuteFrom));
			WebElement TimeOfDayHourTo= driver.findElement(By.cssSelector(property.cssTimeOfDayHourTo));
			WebElement TimeOfDayMinuteTo= driver.findElement(By.cssSelector(property.cssTimeOfDayMinuteTo));
		
			TimeOfDayHourFrom.clear();
			TimeOfDayHourFrom.sendKeys(arrayTimeOfDayFrom.get(0));
			TimeOfDayMinuteFrom.clear();
			TimeOfDayMinuteFrom.sendKeys(arrayTimeOfDayFrom.get(1));
			TimeOfDayHourTo.clear();
			TimeOfDayHourTo.sendKeys(arrayTimeOfDayTo.get(0));
			TimeOfDayMinuteTo.clear();
			TimeOfDayMinuteTo.sendKeys(arrayTimeOfDayTo.get(1));
			
		}
		
		//fill fields of advanced section - Days of week
		List<String>arrayDaysOfWeek = Arrays.asList(daysOfWeek.split(","));
		
		for (String dayOfWeek : arrayDaysOfWeek) {
			driver.findElement(By.xpath(".//*[text()='" + dayOfWeek + "']")).click();
		}
		
		//fill fields of advanced section -shift
		driver.findElement(By.xpath(property.xpathComboShift)).click();
		driver.findElement(By.xpath(".//*[text()='" + shift + "']")).click();
				
		util.logInfo("Customize Report - Timeframe advanced section set OK", driver);
		
	}
	
	
	
	public void FillGroup (String group) throws IOException {
		
		util.wait(2);
		
		if (group.equals("All")) {
			//select component "All"
			WebElement checkboxTreeElement = driver.findElement(By.xpath("//span[contains(text(), '" + group + "')]"));
			checkboxTreeElement.click();
		}
		else {
			
			//select component "All" to reset the previous selection
			WebElement checkboxAllElement = driver.findElement(By.xpath("//span[contains(text(), '" + "All" + "')]"));
			checkboxAllElement.click();
			checkboxAllElement.click();

			//extent ALL
			
			List <WebElement> allIndicator = driver.findElements(By.xpath("//*[starts-with(@id, 'cdk-overlay-')]/mat-bottom-sheet-container/app-configure-report-parameter-container/app-configure-report-parameter/div/div[1]/app-groups-configure-report-parameter/app-groups/app-checkbox-tree/mat-tree/mat-nested-tree-node/div[1]/button/span/mat-icon"));
			allIndicator.get(0).click();
			
			//selected components List
			String componentList = group;
			List <String> checkboxTree =  Arrays.asList(componentList.split(","));
			
			for (String checkbox : checkboxTree) {
			
				//check selected component of the group tree
				WebElement checkboxTreeElement = driver.findElement(By.xpath("//span[contains(text(), '" + checkbox + "')]"));
				checkboxTreeElement.click();
			
			}
			
		}

		util.logInfo("Customize Report - Groups set OK", driver);
	}
	
	
	
	public void FillAgentAndRoles (String agentsAndRolesList) throws IOException {
		
		util.wait(2);
		
		List <String> componentList =  Arrays.asList(agentsAndRolesList.split(":"));
		
		//search of the tabs by position, if a recursive search is done then the index change
		List <WebElement> tabAgentsRoles = driver.findElements(By.xpath("//*[contains(@id, '" + "mat-tab-label" + "')]"));
		
		// first check in which section I have to click: agents or roles
		if (componentList.get(0).equals("Agents")) {
			//driver.findElement(By.xpath(property.xpathReportFilterAgentsSection)).click();
			tabAgentsRoles.get(2).click();
		}	
		else {
			if (componentList.get(0).equals("Roles")) {
				//driver.findElement(By.xpath(property.xpathReportFilterRolesSection)).click();
				tabAgentsRoles.get(3).click();
				util.wait(2);
			}
		}
		
		// then I have to tell if it is needed to click on "All" checkbox
		if (componentList.get(1).equals("All")) {
			//select component "All"
			WebElement checkboxTreeElement = driver.findElement(By.xpath("//span[contains(text(), '" + componentList.get(1) + "')]"));
			checkboxTreeElement.click();
		}
		else {
			
			//reset the previous agent selection
			WebElement checkboxAllElement = driver.findElement(By.xpath("//span[contains(text(), '" + "All" + "')]"));
			checkboxAllElement.click();
			checkboxAllElement.click();
			
			//for every component of the list I will click on the proper checkbox
			List <String> itemList =  Arrays.asList(componentList.get(1).split(","));
			
			for (String item : itemList) {
			
				//check selected component of the group tree
				WebElement checkboxTreeElement = driver.findElement(By.xpath("//span[contains(text(), '" + item + "')]"));
				checkboxTreeElement.click();
			
			}
			
		}

		util.logInfo("Customize Report - Agents and Roles set OK", driver);
	}
	
	
	
	public void FillReasonCode (String reasonCodeList) throws IOException {
		
		util.wait(2);
				
		// clear the previous criteria
		//WebElement clearCriteria = driver.findElement(By.xpath("//*[@id=\"cdk-overlay-1\"]/mat-bottom-sheet-container/app-configure-report-parameter-container/app-configure-report-parameter/div/div[1]/app-criteria-configure-report-parameter/app-criteria/div/div/mat-card-actions/button[3]/span"));
		WebElement clearCriteria = driver.findElement(By.xpath("//*[contains(@id, '" + "cdk-overlay" + "')]/mat-bottom-sheet-container/app-configure-report-parameter-container/app-configure-report-parameter/div/div[1]/app-criteria-configure-report-parameter/app-criteria/div/div/mat-card-actions/button[3]/span"));
		clearCriteria.click();
		
		//WebElement clearCriteriaOK = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-confirmation-dialog/mat-dialog-actions/button[1]/span"));
		WebElement clearCriteriaOK = driver.findElement(By.xpath("//*[contains(@id, '" + "mat-dialog" + "')]/app-confirmation-dialog/mat-dialog-actions/button[1]/span"));
		clearCriteriaOK.click();
		
		
		// set the new criteria
		List <String> componentList =  Arrays.asList(reasonCodeList.split(":"));
		
		// first check if it is needed to check "Include ANY" or "Include ALL"
		if (componentList.get(0).equals("Include ANY"))
			driver.findElement(By.xpath(property.xpathReportFilterReasonCodeIncludeAny)).click();
		else {
			if (componentList.get(0).equals("Include ALL")) {
				driver.findElement(By.xpath(property.xpathReportFilterReasonCodeIncludeAll)).click();
				util.wait(2);
			}
		}
		
		// then I have to tell how many rows do the user plans to enter
		List <String> itemList =  Arrays.asList(componentList.get(1).split(","));
		
		// variables set to start from first row
		String FilterReasonCode = driver.findElement(By.xpath("//*[contains(@id, '" + "mat-select-" + "')]")).getAttribute("id");
		String TextReasonCode = driver.findElement(By.xpath("//*[contains(@id, '" + "mat-input-" + "')]")).getAttribute("id");
		
		int rowFilterReasonCode = Integer.parseInt(FilterReasonCode.replaceAll("\\D+","")) + 1; //Integer.parseInt(property.dataRowFilterReasonCode);
		int rowTextReasonCode= Integer.parseInt(TextReasonCode.replaceAll("\\D+","")); //Integer.parseInt(property.dataRowTextReasonCode);
		int counter = 1;
		
		
		List <WebElement> addReasonCode = driver.findElements(By.xpath("//*[starts-with(@id, 'cdk-overlay-')]/mat-bottom-sheet-container/app-configure-report-parameter-container/app-configure-report-parameter/div/div[1]/app-criteria-configure-report-parameter/app-criteria/div/form/div/div/div[3]/button/span/mat-icon"));
		
		// add the first reason code row
		// addReasonCode.get(0).click();
		
		// for every item of the list a row is loaded
		for (String item : itemList) {
			
			List <String> row =  Arrays.asList(item.split("-"));
			
			//click on the filter combo
			driver.findElement(By.xpath("//*[@id=\"mat-select-" + rowFilterReasonCode + "\"]/div/div[2]/div")).click();
			//check selected component of the filter combo
			WebElement filterElement = driver.findElement(By.xpath("//span[contains(text(), '" + row.get(0) + "')]"));
			filterElement.click();
			
			//write the text of the filter
			driver.findElement(By.xpath("//*[@id=\"mat-input-" + rowTextReasonCode +"\"]")).sendKeys(row.get(1));
			
			util.logInfo("Customize Report - Reason Code '" + item + "' set", driver);
			
			if (itemList.size() != counter) {
			
				// add a new row and increment the counters
				//driver.findElement(By.xpath("/html/body/app-root/app-home-container/app-home/mat-sidenav-container/mat-sidenav-content/app-configure-report-container/app-configure-report/div/div/div[2]/div[1]/div/app-configure-report-parameter/div/app-criteria-configure-report-parameter/app-criteria/div/form/div/div[1]/div[3]/button/span/mat-icon")).click();
				addReasonCode.get(0).click();
				rowFilterReasonCode= rowFilterReasonCode + 1;
				rowTextReasonCode = rowTextReasonCode + 1;
				counter = counter + 1;
			}
			
			row = null;
			
		}
	
		util.logInfo("Customize Report - Reason Codes set OK", driver);
	}
	
	
	
	public void FillAccountCodes(String checkButton) throws IOException {
		
		property = new PropertyManager();
		property.generateProperty();
		
		util.wait(2);
		
		//check "Pull From Database" radio button
		WebElement checkPullFromDatabase = driver.findElement(By.xpath("//*[@id=" + '"' + property.idCheckPullFromDatabase + '"' + "]/label/div[2]"));
		checkPullFromDatabase.click();

		util.logInfo("Customize Report - Account Codes - Selected check 'Pull From Database'", driver);
		
	}
	
	
	
	public void FillAccountCodes(String checkButton, String accountCodes) throws IOException {
		
		property = new PropertyManager();
		property.generateProperty();
		
		util.wait(2);
		
		//check "Pre-Configured Account Codes" radio button
		WebElement checkPreconfiguredAccountsCodes = driver.findElement(By.xpath("//*[@id=" + '"' + property.idCheckPreconfiguredAccountsCodes + '"' + "]/label/div[2]"));
		checkPreconfiguredAccountsCodes.click();	
		
		util.logInfo("Customize Report - Account Codes - Selected check 'Pre-Configured Account Codes'", driver);
		
		//selected account codes
		List <String> CodeList =  Arrays.asList(accountCodes.split(","));
		
		for (String code : CodeList) {
		
			//check selected code
			WebElement checkboxTreeElement = driver.findElement(By.xpath("//span[contains(text(), '" + code + "')]"));
			checkboxTreeElement.click();
			util.logInfo("Customize Report - Account Codes - Selected account code '" + code + "'", driver);
		}
		
	}
	
	
	
	public void FillReportSkin(String skin) throws IOException {

		// click on skin combo
		List <WebElement> reportSkinCombo = driver.findElements(By.xpath("//*[starts-with(@id, 'mat-select-')]/div/div[2]/div"));
		reportSkinCombo.get(0).click();
		
		// webelement to pick skin
		List <WebElement> reportSkinOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'mat-option-')]/span"));
		
		if (skin.equals("Xima Classic"))
			// pick "Xima Classic" skin
			reportSkinOptions.get(0).click();
		else
			// pick "Xima Default" skin
			reportSkinOptions.get(1).click();
		
		util.logInfo("Customize Report - Report skin set OK", driver);
		
	}
	
	
	
	public void applyFilter() throws IOException {

		// apply the filter
		util.logInfo("Applying filter", driver);
		
		List <WebElement> applyFilter = driver.findElements(By.xpath("//*[starts-with(@id, 'cdk-overlay-')]/mat-bottom-sheet-container/app-configure-report-parameter-container/app-configure-report-parameter/div/div[2]/div[2]/button[2]/span"));
		applyFilter.get(0).click();
		
		util.logInfo("Customize Report - Filter to apply", driver);
		
	}	

	
	
	public ReportResultsPage RunReport () throws IOException {
		
		// running the report
		util.logInfo("Running the report", driver);
		
		// run report
		WebElement runReportButton = driver.findElement(By.xpath("/html/body/app-root/app-home-container/app-home/mat-sidenav-container/mat-sidenav-content/div/app-reports-container/app-reports/app-run-report-container/app-run-report/mat-sidenav-container/mat-sidenav/app-configure-report-container/app-configure-report/div/div[2]/div[2]/button[2]/span"));
		runReportButton.click();
				
		util.logInfo("Report executed OK", driver);
		
		// return a ReportResultsPage object
		return PageFactory.initElements(driver, ReportResultsPage.class);
		
	}
		
}
