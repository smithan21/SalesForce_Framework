package com.tekarch.utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.tekarch.listeners.ExtentManager;

@Listeners(com.tekarch.listeners.TestListener.class)

public class TestBase {

	public static CommonUtilities oCommUtil = new CommonUtilities();
	public static BrowserUtilities oBroUti = new BrowserUtilities();
	public static Constants oCons = new Constants();
	public static ExtentManager oExt = new ExtentManager();
	Logger log = Logger.getLogger(getClass().getSimpleName());
	public static WebDriver driver;
	public static String sHost;
	public static String sScreenShotName;
	public static String sClassNameForScreenShot;
	public static String sErrorMessage="";
	
	@BeforeSuite
	public void TriggerDependencies() throws Exception {
		oCommUtil.deleteScreenShotDirectory();
		oCommUtil.loadPropertyFiles(System.getProperty("user.dir") + "/src/main/java/properties/config.properties");
		oCommUtil.loadLog4jProperty(System.getProperty("user.dir") + "/src/main/java/properties/log4j.properties");
		oCommUtil.loadPropertyFiles(System.getProperty("user.dir") + "/src/main/java/properties/testdata.properties");
		oCommUtil.deleteCreateScreenShotDirectorySureFireReports();
		
		if(System.getProperty("Environment") == null || System.getProperty("Environment").isEmpty())
			System.setProperty("Environment", System.getProperty("EnvironmentConf"));
		
		log.info(System.getProperty("AutomationRunning"));
		log.info(System.getProperty("Environment"));
		
		
		if(System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.sAutomationWeb.toLowerCase()))
		{
			oBroUti.launchBrowser(System.getProperty("Browser"));

		}
	}
}
