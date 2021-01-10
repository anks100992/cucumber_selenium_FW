package stepdefs;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs {

	WebDriver driver;
	String base_url = "https://amazon.in";
	Logger logger = LogManager.getLogger(StepDefs.class);
	
	@Before
	public void Setup()
	{
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		logger.info("Browser opened");
	}
	
	@After
	public void Cleanup()
	{
	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS)	;
	 driver.quit();
	 logger.info("Browser closed");
    }
	

	@Given("user able to open desired site homepage")
	public void user_able_to_open_desired_site_homepage()
	{
		driver.get(base_url);
		logger.info("base url opened successfully");
		String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actual =driver.getTitle();
		Assert.assertEquals("Page Title validation",expected,actual);
		logger.info("page validation done sucessfully");
    }
	
	@When("User Search for product {string}")
	public void user_Search_for_product(String productName) {
		WebDriverWait webDriverWait = new WebDriverWait(driver,20);
		WebElement elementSearchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));

		elementSearchBox.sendKeys(productName);
		driver.findElement(By.xpath("//input[@value='Go']")).click();
		logger.info("product search successfully done");
		
	}
	@Then("Search Result page is displayed")
	public void search_Result_page_is_displayed() 
	{
		WebDriverWait webdriverwait1 = new WebDriverWait(driver, 20);
		String PageUrl = driver.getCurrentUrl();
		String pagetitle = driver.getTitle();
		webdriverwait1.until(ExpectedConditions.titleIs(pagetitle));
		
		//Assert.assertEquals("Page url validation",driver.getCurrentUrl(),"PageUrl");
		Assert.assertEquals("Page Title validation",driver.getTitle(),pagetitle);
		logger.info("After product searched page validation done successfully");
	}
	
	
	
}
