package com.tekarch.utilities;

import org.apache.log4j.Logger;

public class Constants {

	Logger log = Logger.getLogger(getClass().getSimpleName());
	
	public static final String sConstEnvironment="PROD",sAutomationWeb="Web";
	
	public String getSalesForceURL() throws Exception{

		if(System.getProperty("Environment").toLowerCase().trim().startsWith("sf"))
			return "https://tekarch36-dev-ed.my.salesforce.com/";
		
		log.info("wrong URL for SalesForce please set environment correctly in property file");
		return "set environment correctly...";
	}
	
}
