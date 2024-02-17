package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	// we have to use this in through of our program
	public static WebDriver driver;
	public static Properties props;
	
	// Read the Properties file
	public TestBase() {
		
		props = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/main/java/com/config/config.properties");
			props.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		
		//Reading the browser property form properties file
		String browserName = props.getProperty("browser");
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("chrome")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOADOUT_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		
		driver.get(props.getProperty("url"));
	}

}
