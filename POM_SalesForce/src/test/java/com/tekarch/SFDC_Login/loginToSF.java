package com.tekarch.SFDC_Login;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tekarch.poLoginSF.poLoginSF;
import com.tekarch.utilities.TestBase;

public class loginToSF extends TestBase{
	
	poLoginSF login;
	
	Logger log = Logger.getLogger(getClass().getSimpleName());
	
	@BeforeTest
	public void settingUpEnvironment() throws Exception {
		sErrorMessage = "";
		sClassNameForScreenShot = getClass().getSimpleName();
		driver.get(oCons.getSalesForceURL());
		login = new poLoginSF(driver);
		login.checkLoggedIntoSalesForceOrNotElseLogout();
	}

	
	@AfterMethod
	public  void settingReqURL() throws Exception {
		driver.get(oCons.getSalesForceURL());
	}
	
	@Test(priority = 1)
	public void To_check_Error_For_Invalid_Login() throws Exception {
		login.invalidloginToSalesForce();
		SoftAssert sa = new SoftAssert();
		
		sa.assertAll();
	}
	
	@Test(priority = 2)
	public void to_Check_Whether_Login_Happening_Or_Not() throws Exception {
		login.loginToSalesForce();
	}
	
	@Test
	public void to_Close_Browser() throws Exception {
		login.quitBrowser();
	}
}
