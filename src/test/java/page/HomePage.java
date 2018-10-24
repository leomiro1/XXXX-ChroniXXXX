package page;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import util.PropertyManager;
import util.WebDriverUtils;

public class HomePage extends LoginPage {

	WebDriverUtils util = new WebDriverUtils(driver);
	PropertyManager property;
	
	
	
	public HomePage (WebDriver driver) {
		super(driver);
	}
	
	
	
	public LoginPage LogoutToLoginPage() throws IOException {
		
		property = new PropertyManager();
		property.generateProperty();
		
		// waits until the user options are available on screen
		while (!(util.isElementDisplayed(driver.findElement(By.xpath(property.xpathUserOptions))))) {
			util.wait(5);
		}
		
		// logout procedure			
		WebElement userOptions =    driver.findElement(By.xpath(property.xpathUserOptions));
		
		util.wait(1);
		userOptions.click();
		util.logInfo("Logout - user options", driver);
		
		WebElement logoutOption =   driver.findElement(By.xpath(property.xpathLogoutOption));
				
		util.wait(1);
		logoutOption.click();
		util.logInfo("Logout - logout requested", driver);
		
		// return a LoginPage object
		return PageFactory.initElements(driver, LoginPage.class);
		
	}
	
	
	
	public Integer SearchReport(String keyWord) throws IOException {
		
		property = new PropertyManager();
		property.generateProperty();
		
		int cont = 1;
		int contReturn  = 0;
		String row = "";
		WebElement rowSearch;
		
		// click on "Search" icon
		WebElement searchIcon =  driver.findElement(By.xpath(property.xpathSearchIcon));
		searchIcon.click();
		
		util.logInfo("Home - Entering search keyword '" + keyWord + "'", driver);
		
		// type keyword of the search
		WebElement searchField = driver.findElement(By.xpath("//*[contains(@id, '" + property.idSearchField + "')]"));
		searchField.sendKeys(keyWord);
		
		util.wait(2);

		// specify how many row per page are configured in the report list
		
		//WebElement idRowsPerPage = driver.findElement(By.id(property.idRowsPerPage));
		
		//search of the rows element by position, if a recursive search is done then the index change
		List <WebElement> idRowsPerPage = driver.findElements(By.xpath("//*[contains(@id, '" + "mat-select" + "')]"));
		
		util.logInfo("Home - Executing search", driver);
		
		// count how many rows return the search
		int rowsPerPage = Integer.parseInt(idRowsPerPage.get(0).getText());
		
		// check if the search return any rows
		for (cont=1; cont<=rowsPerPage;cont++) {
			row = property.xpathSearchReturnAllReports + "[" + cont + "]";
			
			try {
				rowSearch = driver.findElement(By.xpath(row));
				if (util.isElementDisplayed(rowSearch)) {
					contReturn = contReturn + 1;
				}
				} catch(NoSuchElementException e) {
		    }
		}
		
		// to return the icon to the primary state in case I need to do another search
		searchIcon.click();
		
		// return quantity of rows brought by the search
		return contReturn;		
	
	}
	
	
	
	public void CheckTopLevelNavigation() throws IOException {
		
        property = new PropertyManager();
		property.generateProperty();
		
		// expand sidebar menu
		WebElement iconToExpandSideBarMenu =  driver.findElement(By.xpath(property.xpathIconToExpandSideBarMenu));
		iconToExpandSideBarMenu.click();
		
		// specify the list of options of the sidebar
		List<String> sideBarOptionsPanel = Arrays.asList(property.dataSideBarOptions.split(","));
		Integer counterSideBarOptionsPanel = sideBarOptionsPanel.size();
		Integer counter = 0;
		
		// for every button in sidebar menu I contrast it with the string that appears on top level navigation
        for(String sideBarOption : sideBarOptionsPanel) {
    		
        	driver.findElement(By.xpath(".//*[text()='" + sideBarOption + "']")).click();
        	
        	util.wait(1);
        	
      	   	String headerOption = driver.findElement(By.xpath(property.xpathOptionTopLevelNavigation)).getText();
        	
      	   	util.wait(1);

      	   	// assert if the sidebar option matches with the title on top level
      	   	try {
    			  Assert.assertTrue(sideBarOption.toLowerCase().equals(headerOption.toLowerCase()));
  			  	  util.logInfo("TC001_XC-72 Check top level navigation - option '" + sideBarOption + "' match with top level navigation", driver);
  			  	  counter = counter + 1;
  			    } catch(NoSuchElementException nsee) {
  			      util.logError("TC001_XC-72 Check top level navigation - FAILED - The top level navigation does not match with the option '" + sideBarOption + "'");
  			      Assert.fail("TC001_XC-72 Check top level navigation - FAILED - The top level navigation does not match with the option '" + sideBarOption + "'");
  			    } catch(AssertionError ae) {
  			      util.logWarn("TC001_XC-72 Check top level navigation - FAILED - The top level navigation does not match with the option '" + sideBarOption + "'");
  			      Assert.fail("TC001_XC-72 Check top level navigation - FAILED - The top level navigation does not match with the option '" + sideBarOption + "'");
  			    }
    	}
        
        // if the variable counter matches with the total quantity of options of the panel then the test is passed
        if (counter == counterSideBarOptionsPanel) {
        	util.EndLoggin("TC001_XC-72 Check top level navigation - PASSED");
        }

	}
	
	
	
	public void CheckListinformation() throws IOException {
		
        property = new PropertyManager();
		property.generateProperty();
		
		// specify the headers of the report list
		List<String> dataHeaderCellReport = Arrays.asList(property.dataHeaderCellReport.split(","));
		int counter = Integer.parseInt(property.dataFirstColumnHeader); // 3 (the position of the first header of the report list) 
		String cellReport = "";
				
		// for every column in the header of report list contrast with the string that is passed by parameter
        for(String headerCellReport : dataHeaderCellReport) {
    		
        	cellReport = driver.findElement(By.xpath(property.xpathHeaderCellAllReports + "[" + counter + "]")).getText();
        	
      	   	util.wait(1);
      	   	
      	   	// assert if the header column matches with the ones passed by parameter
    		try {
    			  Assert.assertTrue(headerCellReport.toLowerCase().equals(cellReport.toLowerCase()));
  			  	  util.logInfo("TC003_XC-19_List information - header '" + headerCellReport + "' match with the List information", driver);
  			  	  counter = counter + 1;
  			    } catch(NoSuchElementException nsee) {
  			      util.logError("TC003_XC-19_List information - FAILED - The top level navigation does not match with the header '" + headerCellReport + "'");
  			      Assert.fail("TC003_XC-19_List information - FAILED - The top level navigation does not match with the header '" + headerCellReport + "'");
  			    } catch(AssertionError ae) {
  			      util.logWarn("TC003_XC-19_List information - FAILED - The top level navigation does not match with the header '" + headerCellReport + "'");
  			      Assert.fail("TC003_XC-19_List information - FAILED - The top level navigation does not match with the header '" + headerCellReport + "'");
  			    }
    	}
        
        // if the variable counter matches with the number of the last column header then the test is passed
        if (counter == Integer.parseInt(property.dataLastColumnHeader)) {   // 8 (the position of the last header of the report list) 
        	util.EndLoggin("TC003_XC-19_List information - PASSED");
        }

	}
	

	
	public Integer FilterExistentReportByTag(String tags, String reports) throws IOException {
		
		property = new PropertyManager();
		property.generateProperty();
		
		int contReturn  = 0;
		String row = "";
		WebElement rowTagged;
		String rowNameReport = "";
		String nameReport = "";
		
		// set number of filter tags that are in the filter tag section
		int numberOfTags = Integer.parseInt(property.dataNumberOfTags);
		
		// click on "Search tag"
		WebElement filterByTags =  driver.findElement(By.id(property.idFilterByTags));
		filterByTags.click();
		
		util.logInfo("Home - Start of filter by tags", driver);
		
		// click specific tags included in the parameter "tags"
		List<String> listOfTags = Arrays.asList(tags.split(","));
		
		// select specific tags
		for (String tag : listOfTags) {
			for (int i=0;i<=numberOfTags;i++) {
				WebElement selectedTag = driver.findElement(By.id(property.idSelectedTag + i));
				if (selectedTag.getText().toLowerCase().equals(tag.toLowerCase())) {
					selectedTag.click();
					util.logInfo("Home - Entering filter '" + selectedTag.getText() + "'", driver);
				}
			}
		}
		
		util.logInfo("Home - End of filter by tags", driver);
		
		// check in the report list if the reports included in the parameter "reports" are listed
		List<String> listOfReports = Arrays.asList(reports.split(","));
		
		// go through the report list checking if they match with the ones passes by parameter
		for (String report : listOfReports) {
			
			// check how many rows of the parameters "reports" are available in the report list
			WebElement idRowsPerPage = driver.findElement(By.id(property.idRowsPerPage));
			int rowsPerPage = Integer.parseInt(idRowsPerPage.getText());
			
			for (int cont=1; cont<rowsPerPage;cont++) {
				row = property.xpathSearchReturnAllReports + "[" + cont + "]";

				try {
					rowTagged = driver.findElement(By.xpath(row));
					if ((util.isElementDisplayed(rowTagged))) {
						rowNameReport = row + property.xpathRowNameReport;
						nameReport=driver.findElement(By.xpath(rowNameReport)).getText();
						if (nameReport.toLowerCase().contains(report.toLowerCase())) {
							contReturn = contReturn + 1;
						}
					}
					} catch(NoSuchElementException e) {
			    }
			}
		}
		
		// return quantity of rows that match with the parameter "report" found
		return contReturn;		
	
	}

	

	public void PinUnpinFirstReport() throws IOException {
		
        property = new PropertyManager();
		property.generateProperty();
		
		// pin/unpin first report of report list
		WebElement FirstRowPinningIcon =  driver.findElement(By.xpath(property.xpathFirstRowPinningIconAllReports));
		FirstRowPinningIcon.click();
		
		util.wait(1);
	}


	
	public void GoToSection(String section) throws IOException {
	
		property = new PropertyManager();
		property.generateProperty();
		
		// retrieve sidebar menu if the menu is not displayed
		try {
			  WebElement iconToRetrieveSideBarMenu =  driver.findElement(By.xpath(property.xpathIconToRetrieveSideBarMenu));
			  iconToRetrieveSideBarMenu.click();
			
			} catch(NoSuchElementException nsee) {}
			  
		// expand sidebar menu
		WebElement iconToExpandSideBarMenu =  driver.findElement(By.xpath(property.xpathIconToExpandSideBarMenu));
		iconToExpandSideBarMenu.click();
		
		// click on specific section
		driver.findElement(By.xpath(".//*[text()='" + section + "']")).click();

		// retrieve sidebar menu to clean the screen
		WebElement iconToRetrieveSideBarMenu =  driver.findElement(By.xpath(property.xpathIconToRetrieveSideBarMenu));
		iconToRetrieveSideBarMenu.click();
		
		util.logInfo("Go to Section " + section, driver);
		
	}
	

	
	public void GoToSection(String section, String reportSection) throws IOException {
		
		property = new PropertyManager();
		property.generateProperty();
		
		// retrieve sidebar menu if the menu is not displayed
		try {
			  WebElement iconToRetrieveSideBarMenu =  driver.findElement(By.xpath(property.xpathIconToRetrieveSideBarMenu));
			  iconToRetrieveSideBarMenu.click();
			
			} catch(NoSuchElementException nsee) {}
		
		// expand sidebar menu
		WebElement iconToExpandSideBarMenu =  driver.findElement(By.xpath(property.xpathIconToExpandSideBarMenu));
		iconToExpandSideBarMenu.click();
	
		// click on specific section
		driver.findElement(By.xpath(".//*[text()='" + section + "']")).click();
		
		util.logInfo("Go to Section " + section, driver);
		
		// click on specific report section
		WebElement reportSectionElement = driver.findElement(By.xpath("//h5[contains(text(), '" + reportSection + "')]"));
		reportSectionElement.click();
		
		// retrieve sidebar menu to clean the screen
		WebElement iconToRetrieveSideBarMenu =  driver.findElement(By.xpath(property.xpathIconToRetrieveSideBarMenu));
		iconToRetrieveSideBarMenu.click();
		
		util.logInfo("Go to Report Section " + reportSectionElement.getText(), driver);
	}

	
	
	public CustomizeReportPage PickReportToExecute(String reportToExecute) throws IOException {
		
		property = new PropertyManager();
		property.generateProperty();
		
		// search the report
		int searchReportToExecute = SearchReport(reportToExecute);
		
		// if report is found then click on reports name. If not I report the error
		if (searchReportToExecute >= 1) {
			util.logInfo("Picking report to execute - report '" + reportToExecute + "' found", driver);
			WebElement clickReport = driver.findElement(By.xpath(property.xpathSearchReturnAllReports + "[1]" + property.xpathRowNameReport));
			clickReport.click();		
		}
		else {
			  util.logError("Picking report to execute - FAILED - The report to pick was not found.");
			  Assert.fail("Picking report to execute - FAILED - The report to pick was not found.");
		}
		// return a CustomizeReport object
		return PageFactory.initElements(driver, CustomizeReportPage.class);
		
	}


	
	public void ChangeRowsPerPage (String rowsPerPage) throws IOException {
		
		// if the rows per page are not the one set by default (10) then I change it with the value of the parameter
		if (!(rowsPerPage.contains("10"))) {

			// specify how many row per page are configured in the report list
			driver.findElement(By.xpath(property.xpathComboRowsPerPage)).click();
			driver.findElement(By.xpath(".//*[text()='" + rowsPerPage + "']")).click();
					
			util.logInfo("Rows per page changed to " + rowsPerPage, driver);
		}

		
	}
	
	
	
	public boolean CheckRowsPerPage() throws IOException {
		
		int cont = 1;
		int contReturn  = 0;
		String row = "";
		WebElement rowList;
		
		// specify how many rows per page are configured in the report list and the range pagination to inform in the log
		WebElement idRowsPerPage = driver.findElement(By.id(property.idRowsPerPage));
		WebElement idRangePagination = driver.findElement(By.xpath(property.xpathRangePagination));
		
		int rowsPerPage = Integer.parseInt(idRowsPerPage.getText());
		String rangePagination = idRangePagination.getText();
		
		// check how many rows are in the report list
		for (cont=1; cont<=rowsPerPage;cont++) {
			row = property.xpathSearchReturnAllReports + "[" + cont + "]";
			
			try {
				rowList = driver.findElement(By.xpath(row));
				if (util.isElementDisplayed(rowList)) {
					contReturn = contReturn + 1;
				}
				} catch(NoSuchElementException e) {
		    }
		}
		
		util.logInfo("The amount of rows of page '" + rangePagination + "' in the report list matches the rows per page", driver);
		
		return(contReturn == rowsPerPage);
		
	}
	
	
	
	public Integer GoThroughPages () throws IOException {
		
		int pages=1;
		
		// specify the range pagination so I can keep going till the last row of the list
		WebElement idRangePagination = driver.findElement(By.xpath(property.xpathRangePagination));
		WebElement nextPageIcon = driver.findElement(By.xpath(property.xpathNextPageIcon));
		String rangePagination = idRangePagination.getText();
		List<String> range = Arrays.asList(rangePagination.split(" "));
		boolean rowsPerPage = CheckRowsPerPage();
		
		// go through the list till the last row and show every page of the list
		while (!(range.get(2).toString().equals(range.get(4).toString()))) {
			
			util.logInfo("Page '" + rangePagination + "' in the report list", driver);
			pages = pages + 1;
			
			nextPageIcon.click();
			
			idRangePagination = driver.findElement(By.xpath(property.xpathRangePagination));
			rangePagination = idRangePagination.getText();
			rangePagination = idRangePagination.getText();
			range = Arrays.asList(rangePagination.split(" "));
			rowsPerPage = CheckRowsPerPage();
			
			if ((rowsPerPage == false) && !(range.get(2).toString().equals(range.get(4).toString()))) {
				Assert.fail("The amount of rows of the report list in the report list does not match the rows per page");
			}
			
		}

		return pages;
		
	}
	
}
