package com.selenium.phaseend.project;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AmazonProject {
	
	static WebDriver driver;
	static WebDriverWait wait;
	
	public static void main(String args[]) throws InterruptedException {
		setUp();
		mobileclick();
		selectRange();
		buyItem();
		
	}
	
	public static void setUp() {
		// step1: formulate a test domain url & driver path
				String siteUrl = "https://amazon.in/";
				String driverPath = "drivers/windows/geckodriver.exe";

				// step2: set system properties for selenium dirver
				System.setProperty("webdriver.gecko.driver", driverPath);

				// step3: instantiate selenium webdriver
				  driver = new FirefoxDriver();

				// step4: add explicit wait (Conditional Delay)
				wait = new WebDriverWait(driver, Duration.ofSeconds(40));

				// step5: launch browser
				driver.get(siteUrl);
				takeScreenshot("amazon.png");

				
				driver.manage().window().maximize();
	}
	
	public static void mobileclick() {
		driver.findElement(By.linkText("Mobiles")).click();
		
		
	
		driver.findElement(By.cssSelector("div.a-section:nth-child(6) > ul:nth-child(2) > li:nth-child(4) > span:nth-child(1) > a:nth-child(1)")).click();
	    
		takeScreenshot("select-type.png");


	
	}
	
	public static void selectRange() throws InterruptedException {
		driver.findElement(By.cssSelector("#p_36\\/1318507031 > span:nth-child(1) > a:nth-child(1)")).click();
		
		takeScreenshot("select-range.png");

		Thread.sleep(2000);
		driver .findElement(By.cssSelector(".widgetId\\=search-results_1 > span:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > h2:nth-child(1) > a:nth-child(1)")).click();
		
		takeScreenshot("select-item.png");




	}
	
	public static void buyItem() throws InterruptedException {
		ArrayList<String> newtab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(newtab.get(1));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#buy-now-button")).click();
		
		takeScreenshot("sign-in.png");
		
		String expectedText = "Sign in";
		
		String actualText = driver.findElement(By.xpath("//h1[contains(@class,'a-spacing-small')]")).getText();
		
		if(actualText.equals(expectedText)) {
			System.out.println("Test case passed");
		}
		else {
			System.out.println("Test case failed");
		}

	}
	
	public static void takeScreenshot(String filename) {
		TakesScreenshot tsc = (TakesScreenshot) driver;
		
File src = tsc.getScreenshotAs(OutputType.FILE);
		
		// 3. create  file  with screen shot
		try {
			FileHandler.copy(src, new File("output-screenshot\\"+filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
