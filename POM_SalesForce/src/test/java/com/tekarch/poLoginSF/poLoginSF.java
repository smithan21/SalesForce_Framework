package com.tekarch.poLoginSF;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tekarch.utilities.TestBase;

public class poLoginSF extends TestBase {
	
	Logger log = Logger.getLogger(getClass().getSimpleName());

	public poLoginSF(WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "username")
	WebElement ph_username;
	@FindBy(xpath = "//input[@id = 'password']")
	WebElement ph_password;
	@FindBy(id="Login")
	WebElement button_loginToAccount;
	@FindBy(xpath = "//a[normalize-space()='Home']")
	WebElement button_Home;
	
	public boolean loginToSalesForce() throws Exception {
		boolean bRes_Flag=false;
		oBroUti.waitForElementVisible(driver, ph_username, 5);
		oBroUti.ufSendKeys(ph_username, System.getProperty("td_emailId"));
		oBroUti.ufSendKeys(ph_password, System.getProperty("td_password"));
		oBroUti.ufClick(button_loginToAccount);
		oBroUti.waitForElementVisible(driver, button_Home, 5);
		if(oBroUti.isDisplayed(button_Home))
			bRes_Flag=true;
		return bRes_Flag;
	}
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement button_logout;
	
	public boolean logoutOfSalesforce() throws Exception {
		boolean bRes_Flag=false;
		if(oBroUti.isDisplayed(button_logout))
			button_logout.click();
		bRes_Flag=true;
		return bRes_Flag;
	}
	public boolean checkLoggedIntoSalesForceOrNotElseLogin() throws Exception{
		
		boolean bRes_Flag=false;
		try {
		oBroUti.waitForElementVisible(driver, button_Home, 3);
		}catch(Exception ea) {log.error("By passing error when we run on suite case");}
		if(!oBroUti.isDisplayed(button_Home))
			bRes_Flag=loginToSalesForce();
			
		return bRes_Flag;
	}
	public boolean checkLoggedIntoSalesForceOrNotElseLogout() throws Exception{
		boolean bRes_Flag=false;
		if(oBroUti.isDisplayed(button_Home))
			bRes_Flag=logoutOfSalesforce();
			
		return bRes_Flag;
		
	}

	public boolean invalidloginToSalesForce() throws Exception{
		boolean bRes_Flag=false;
		oBroUti.waitForElementVisible(driver, ph_username, 5);
		oBroUti.ufSendKeys(ph_username, System.getProperty("td_invalid_emailId"));
		oBroUti.ufSendKeys(ph_password, System.getProperty("td_invalid_password"));
		oBroUti.ufClick(button_loginToAccount);
		log.info("****************************");
		Thread.sleep(2000);
		log.info(driver.switchTo().alert().getText());
		log.info("****************************");
		assertEquals(driver.switchTo().alert().getText(), "Please check your username and password. If you still can't log in, contact your Salesforce administrator.", 
				"Expected: Error : Please check your username and password. If you still can't log in, contact your Salesforce administrator. \nActual: "+driver.switchTo().alert().getText());
		
		driver.switchTo().alert().accept();
			bRes_Flag=true;
		return bRes_Flag;
	}

	public void quitBrowser() throws Exception{
		driver.quit();
	}

}
