package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.TestBase;

public class LoginPage extends TestBase{
	
	@FindBy(id = "user-name")
	WebElement username;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "login-button")
	WebElement loginButton;
	
	@FindBy(xpath = "//h3[@data-test='error']")
	WebElement errorLabel;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyPageTitle(){
		return driver.getTitle();
	}
	
	public void login(String user, String pass) {
		username.clear();
		username.sendKeys(user);
		password.clear();
		password.sendKeys(pass);
		loginButton.click();
		
	}
	
	public String getCurrentPageUrl() {
		return driver.getCurrentUrl();
	}
	
	
	
	

}
