package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

	public String FirefoxdriverPath = "";
	public String ChromedriverPath = "";
	public String ScreenshotsPath = "";
	public String SiteURL = "";
	
	public String idUsernameFieldLogin = "";
	public String idPasswordFieldLogin = "";
	public String idSearchField = "";
	public String idRowsPerPage = "";
	public String idFilterByTags = "";
	public String idSelectedTag = "";
	public String idCheckPullFromDatabase = "";
	public String idCheckPreconfiguredAccountsCodes = "";
	public String cssStartDate = "";
	public String cssEndDate = "";
	public String cssReportStartTimeHour = "";
	public String cssReportStartTimeMinute = "";
	public String cssReportStartTimeSeconds = "";
	public String cssReportEndTimeHour = "";
	public String cssReportEndTimeMinute = "";
	public String cssReportEndTimeSeconds = "";
	public String cssTimeOfDayHourFrom = "";
	public String cssTimeOfDayMinuteFrom = "";
	public String cssTimeOfDayHourTo = "";
	public String cssTimeOfDayMinuteTo = "";
	public String xpathLoginButton = "";
	public String xpathLogoChronicall = "";
	public String xpathUserOptions = "";
	public String xpathLogoutOption = "";
	public String xpathIconToExpandSideBarMenu = "";
	public String xpathIconToRetrieveSideBarMenu = "";
	public String xpathOptionTopLevelNavigation = "";
	public String xpathSearchIcon = "";
	public String xpathSearchReturnAllReports = "";
	public String xpathRowNameReport = "";
	public String xpathHeaderCellAllReports = "";
	public String xpathFirstRowPinningIconAllReports = "";
	public String xpathTimeframeSlideAdvanced = "";
	public String xpathComboShift = "";
	public String xpathComboRowsPerPage = "";
	public String xpathRangePagination = "";
	public String xpathNextPageIcon = "";
	public String xpathConfigureButton = "";
	public String xpathReportFilterNameHead = "";
	public String xpathReportFilterNameTail = "";
	public String xpathReportFilterIndicatorHead = "";
	public String xpathReportFilterIndicatorTail = "";
	public String xpathGroupAllIndicator = "";
	public String xpathRunReportButton = "";
	public String xpathLastTimeRunSection = "";
	public String xpathReportFilterAgentsSection = "";
	public String xpathReportFilterRolesSection = "";
	public String xpathReportFilterReasonCodeIncludeAny = "";
	public String xpathReportFilterReasonCodeIncludeAll = "";
	
	public String dataUserNameOK = "";
	public String dataPasswordOK = "";
	public String dataUserNameNOK = "";
	public String dataPasswordNOK = "";
	public String dataSideBarOptions = "";
	public String dataSearchExistentReport = "";
	public String dataSearchNonExistentReport = "";
	public String dataHeaderCellReport = "";
	public String dataNumberOfTags = "";
	public String dataRowFilterReasonCode = "";
	public String dataRowTextReasonCode = "";
	public String dataFirstColumnHeader = "";
	public String dataLastColumnHeader = "";
	public String dataTag = "";
	public String dataTags = "";
	public String dataTagsNonExistent = "";
	public String dataReportName = "";
	public String dataReportNames = "";
	public String dataReportNamesNonExistent = "";
	public String dataReportToPin = "";
	public String dataRowsPerPage = "";
	public String dataReportParametersAvailable = "";
	
	public void generateProperty() {
		
		Properties prop = new Properties();
		InputStream input = null;
	
		try {
			input = new FileInputStream("src/parameters.properties");
			prop.load(input);
			
			FirefoxdriverPath = prop.get("FirefoxdriverPath").toString();
			ChromedriverPath = prop.get("ChromedriverPath").toString();
			ScreenshotsPath = prop.get("ScreenshotsPath").toString();
			SiteURL = prop.get("SiteURL").toString();
			idUsernameFieldLogin = prop.get("idUsernameFieldLogin").toString();
			idPasswordFieldLogin = prop.get("idPasswordFieldLogin").toString();
			idSearchField = prop.get("idSearchField").toString();
			idRowsPerPage = prop.get("idRowsPerPage").toString();
			idFilterByTags = prop.get("idFilterByTags").toString();
			idSelectedTag = prop.get("idSelectedTag").toString();
			idCheckPullFromDatabase = prop.get("idCheckPullFromDatabase").toString();
			idCheckPreconfiguredAccountsCodes = prop.get("idCheckPreconfiguredAccountsCodes").toString();
			cssStartDate = prop.get("cssStartDate").toString();
			cssEndDate = prop.get("cssEndDate").toString();
			cssReportStartTimeHour = prop.get("cssReportStartTimeHour").toString();
			cssReportStartTimeMinute = prop.get("cssReportStartTimeMinute").toString();
			cssReportStartTimeSeconds = prop.get("cssReportStartTimeSeconds").toString();
			cssReportEndTimeHour = prop.get("cssReportEndTimeHour").toString();
			cssReportEndTimeMinute = prop.get("cssReportEndTimeMinute").toString();
			cssReportEndTimeSeconds = prop.get("cssReportEndTimeSeconds").toString();
			cssTimeOfDayHourFrom = prop.get("cssTimeOfDayHourFrom").toString();
			cssTimeOfDayMinuteFrom = prop.get("cssTimeOfDayMinuteFrom").toString();
			cssTimeOfDayHourTo = prop.get("cssTimeOfDayHourTo").toString();
			cssTimeOfDayMinuteTo = prop.get("cssTimeOfDayMinuteTo").toString();
			xpathLoginButton = prop.get("xpathLoginButton").toString();
			xpathLogoChronicall = prop.get("xpathLogoChronicall").toString();
			xpathUserOptions = prop.get("xpathUserOptions").toString();
			xpathLogoutOption = prop.get("xpathLogoutOption").toString();
			xpathIconToExpandSideBarMenu = prop.get("xpathIconToExpandSideBarMenu").toString();
			xpathIconToRetrieveSideBarMenu = prop.get("xpathIconToRetrieveSideBarMenu").toString();
			xpathOptionTopLevelNavigation = prop.get("xpathOptionTopLevelNavigation").toString();
			xpathSearchIcon = prop.get("xpathSearchIcon").toString();
			xpathSearchReturnAllReports = prop.get("xpathSearchReturnAllReports").toString();
			xpathRowNameReport = prop.get("xpathRowNameReport").toString();
			xpathHeaderCellAllReports = prop.get("xpathHeaderCellAllReports").toString();
			xpathFirstRowPinningIconAllReports = prop.get("xpathFirstRowPinningIconAllReports").toString();
			xpathTimeframeSlideAdvanced = prop.get("xpathTimeframeSlideAdvanced").toString();
			xpathComboShift = prop.get("xpathComboShift").toString();
			xpathComboRowsPerPage = prop.get("xpathComboRowsPerPage").toString();
			xpathRangePagination = prop.get("xpathRangePagination").toString();
			xpathNextPageIcon = prop.get("xpathNextPageIcon").toString();
			xpathConfigureButton = prop.get("xpathConfigureButton").toString();
			xpathReportFilterNameHead = prop.get("xpathReportFilterNameHead").toString();
			xpathReportFilterNameTail = prop.get("xpathReportFilterNameTail").toString();
			xpathReportFilterIndicatorHead = prop.get("xpathReportFilterIndicatorHead").toString();
			xpathReportFilterIndicatorTail = prop.get("xpathReportFilterIndicatorTail").toString();
			xpathGroupAllIndicator = prop.get("xpathGroupAllIndicator").toString();
			xpathRunReportButton = prop.get("xpathRunReportButton").toString();
			xpathLastTimeRunSection = prop.get("xpathLastTimeRunSection").toString();
			xpathReportFilterAgentsSection = prop.get("xpathReportFilterAgentsSection").toString();
			xpathReportFilterRolesSection = prop.get("xpathReportFilterRolesSection").toString();
			xpathReportFilterReasonCodeIncludeAny = prop.get("xpathReportFilterReasonCodeIncludeAny").toString();
			xpathReportFilterReasonCodeIncludeAll = prop.get("xpathReportFilterReasonCodeIncludeAll").toString();
						
			dataUserNameOK = prop.get("dataUserNameOK").toString();
			dataPasswordOK = prop.get("dataPasswordOK").toString();
			dataUserNameNOK = prop.get("dataUserNameNOK").toString();
			dataPasswordNOK = prop.get("dataPasswordNOK").toString();
			dataSideBarOptions = prop.get("dataSideBarOptions").toString();
			dataSearchExistentReport = prop.get("dataSearchExistentReport").toString();
			dataSearchNonExistentReport = prop.get("dataSearchNonExistentReport").toString();
			dataHeaderCellReport = prop.get("dataHeaderCellReport").toString();
			dataNumberOfTags = prop.get("dataNumberOfTags").toString();
			dataRowFilterReasonCode = prop.get("dataRowFilterReasonCode").toString();
			dataRowTextReasonCode = prop.get("dataRowTextReasonCode").toString();
			dataFirstColumnHeader = prop.get("dataFirstColumnHeader").toString();
			dataLastColumnHeader = prop.get("dataLastColumnHeader").toString();
			dataTag = prop.get("dataTag").toString();
			dataTags = prop.get("dataTags").toString();
			dataTagsNonExistent = prop.get("dataTagsNonExistent").toString();
			dataReportName = prop.get("dataReportName").toString();
			dataReportNames = prop.get("dataReportNames").toString();
			dataReportNamesNonExistent = prop.get("dataReportNamesNonExistent").toString();
			dataReportToPin = prop.get("dataReportToPin").toString();
			dataRowsPerPage = prop.get("dataRowsPerPage").toString();
			dataReportParametersAvailable = prop.get("dataReportParametersAvailable").toString();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
