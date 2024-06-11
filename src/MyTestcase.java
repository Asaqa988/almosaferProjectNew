import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class MyTestcase {

	WebDriver driver = new ChromeDriver();
	String Url = "https://global.almosafer.com/en";
	String ExpectedLanaguge = "en";
	String ExpectedCurrency = "SAR";
	String ExpectedContactNumber = "+966554400000";
	
	boolean QitafLogoIsThere = true ; 

	@BeforeTest
	public void mySetup() {
		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();
		;

	}

	@Test
	public void CheckTheLanage() {

		WebElement HtmlTag = driver.findElement(By.tagName("html"));

		String ActualLangagueOnTheWebsite = HtmlTag.getAttribute("lang");

		org.testng.Assert.assertEquals(ActualLangagueOnTheWebsite, ExpectedLanaguge);

	}

	@Test
	public void TestTheCurrencyIsSAR() {
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid = 'Header__CurrencySelector']"))
				.getText();

		org.testng.Assert.assertEquals(ActualCurrency, ExpectedCurrency);

	}

	@Test
	public void TestContactNumber() {
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();

		org.testng.Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);

	}
	
	@Test
	public void CheckQitafLogoIfDisplayed() {
		WebElement FooterTag = driver.findElement(By.tagName("footer"));
 boolean ActualQitaflogo = FooterTag.findElement(By.xpath("//svg[@data-testid='Footer__QitafLogo']")).isDisplayed(); 
		org.testng.Assert.assertEquals(ActualQitaflogo, QitafLogoIsThere); 
	}

}
