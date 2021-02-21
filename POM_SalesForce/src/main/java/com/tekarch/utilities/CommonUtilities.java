package com.tekarch.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CommonUtilities {

	public static TestBase oTest = new TestBase();
	Logger log = Logger.getLogger(getClass().getSimpleName());
	static Properties props = new Properties();
	static FileInputStream fileIn = null;

	public void loadPropertyFiles(String PropertiesFilePath) throws Exception {

		log.info("Current dir using System:" + PropertiesFilePath);
		fileIn = new FileInputStream(PropertiesFilePath);
		props.load(fileIn);
		System.getProperties().putAll(props);
		// log.info(util.props);
	}

	public void loadLog4jProperty(String PropertiesFilePath) throws Exception {
		log.info("Log4j Property file Path :" + PropertiesFilePath);
		fileIn = new FileInputStream(PropertiesFilePath);
		props.load(fileIn);
		PropertyConfigurator.configure(props);
	}

	public void ufUserException(boolean bExpectedBoolean, boolean ActualBoolean, String strExceptionSummary)
			throws Exception {
		if (bExpectedBoolean != ActualBoolean)
			throw new Exception(strExceptionSummary);
	}

	public static String takeScreenShotWebReturnPath(WebDriver driver, String ClassName) throws IOException {
		String sDestDir = "/screenshots/";
		String sImageName = System.getProperty("user.dir") + sDestDir+ClassName+".jpg";
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "jpg", new File(sImageName));
		System.out.println(sImageName);
		return sImageName;
	}

	public void deleteScreenShotDirectory() {
		String destDir = "/screenshots";
		String SRC_FOLDER = System.getProperty("user.dir") + destDir;
		File directory = new File(SRC_FOLDER);
		if (directory.exists()) {
			try {
				delete(directory);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		new File(System.getProperty("user.dir") + destDir).mkdirs();

	}

	public void deleteCreateScreenShotDirectorySureFireReports() {
		String destDir = "screenshots";
		String SRC_FOLDER = System.getProperty("user.dir") + "/target/surefire-reports/" + destDir;
		File directory = new File(SRC_FOLDER);
		if (directory.exists()) {
			try {
				delete(directory);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		new File(System.getProperty("user.dir") + "/target/surefire-reports/" + destDir).mkdirs();

	}

	public void delete(File file) throws IOException {

		if (file.isDirectory()) {
			// directory is empty, then delete it
			if (file.list().length == 0) {
				file.delete();
				log.info("Directory is deleted : " + file.getAbsolutePath());

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);
					// recursive delete
					delete(fileDelete);
				}
				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					log.info("Directory is deleted : " + file.getAbsolutePath());
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			log.info("File is deleted : " + file.getAbsolutePath());
		}
	}

	//method to hover over the a particular webelement
	
		public void moveToElement(WebElement element) throws InterruptedException {
			
			Actions act = new Actions(TestBase.driver);
			act.moveToElement(element);
			
			act.build().perform();
					
			}
		
		public boolean isExist(WebElement ele) {
			boolean present;
			try {
			   ele.isEnabled();
			   present = true;
			} catch (Exception e) {
			   present = false;
			}
			
			return present;
		}
		
		public boolean isDisplayed(WebElement ele) {
			boolean displayed;
			try {
			   ele.isDisplayed();
			   displayed = true;
			} catch (Exception e) {
				
				
			   displayed = false;
			}
			
			return displayed;
		}
}
