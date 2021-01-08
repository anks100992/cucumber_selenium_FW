package stepdefs;

import java.util.concurrent.TimeUnit;

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
	
	@Before
	public void Setup()
	{
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@After
	public void Cleanup()
	{
	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS)	;
	 driver.quit();
    }
	

	@Given("user able to open desired site homepage")
	public void user_able_to_open_desired_site_homepage()
	{
		driver.get(base_url);
		String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actual =driver.getTitle();
		Assert.assertEquals("Page Title validation",expected,actual);
    }
	
	@When("User Search for product {string}")
	public void user_Search_for_product(String string) 
	{
		WebDriverWait webdriverwait = new WebDriverWait(driver, 20);
		WebElement SearchBox = webdriverwait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
		
		SearchBox.sendKeys("Laptop");
		driver.findElement(By.xpath("//input[@value='Go']")).click();
		
	}
	@Then("Search Result page is displayed")
	public void search_Result_page_is_displayed() 
	{
		WebDriverWait webdriverwait1 = new WebDriverWait(driver, 20);
		webdriverwait1.until(ExpectedConditions.titleIs("Amazon.in : Laptop"));
		
		Assert.assertEquals("Page Title validation",driver.getTitle(),"Amazon.in : Laptop");
	}
	
}
